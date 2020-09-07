package com.famiao.search.index;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.elasticsearch.action.admin.indices.alias.Alias;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest;
import org.elasticsearch.action.admin.indices.alias.IndicesAliasesRequest.AliasActions;
import org.elasticsearch.action.admin.indices.delete.DeleteIndexRequest;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.support.IndicesOptions;
import org.elasticsearch.action.support.master.AcknowledgedResponse;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.client.indices.CreateIndexResponse;
import org.elasticsearch.client.indices.GetIndexRequest;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.common.xcontent.XContentType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSONObject;
import com.famiao.search.base.IndexerParam;

import cn.hutool.core.io.FileUtil;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 30, 2019 12:31:59 AM
 */
@Component
public class Indexer {

	@Autowired
	private RestHighLevelClient client;
	
    @Value("${es.cluster.name}")
    private String clusterName;
    @Value("${es.index.shards}")
    private Integer indexShardsNum;
    @Value("${es.index.replicas}")
    private Integer indexReplicasNum;
    @Value("${dynamic.settings.path}")
    private String settingsPath;
	
	private static final Logger logger = LoggerFactory.getLogger(Indexer.class);

	/**
	 * 创建索引目录
	 * 
	 * @throws IOException
	 */
	public boolean build(String index, String mapping) throws IOException {
		if (StringUtils.isBlank(index)) {
			throw new IllegalArgumentException("Index is null");
		}
		if (this.exists(index)) {
			boolean isAcknowledged = this.delete(index);
			logger.warn("delete index isAcknowledged = {}", isAcknowledged);
		}
		CreateIndexRequest request = new CreateIndexRequest(index);
		request.settings(this.settings(), XContentType.JSON);
		request.mapping(mapping, XContentType.JSON);
		request.alias(new Alias(IndexerParam.SEARCH_INDEX_ALIAS));
        //创建索引
        CreateIndexResponse indexResponse = this.client.indices().create(request, RequestOptions.DEFAULT);
        boolean isAcknowledged = indexResponse.isAcknowledged();
        logger.info(index + " response.isAcknowledged = {}", isAcknowledged);
        if(isAcknowledged) {
            logger.info("索引构建成功!");
            //创建别名
            this.createAliases(index, IndexerParam.SEARCH_INDEX_ALIAS);
        }
        return isAcknowledged;
	}
	
	/**
	 * 批量插入数据
	 * 
	 * @throws IOException
	 */
	public void bulkIndex(String index, List<Map<String, Object>> list) throws IOException {
	    if (StringUtils.isBlank(index)) {
	        throw new IllegalArgumentException("Index is null");
	    }
	    if (CollectionUtils.isEmpty(list)) {
	        throw new IllegalArgumentException("list is null");
	    }
	    BulkRequest request = new BulkRequest();
	    for (Map<String, Object> map : list) {
	        JSONObject json = new JSONObject();
	        String indexId = null;
	        for (Map.Entry<String, Object> entry : map.entrySet()) {
	            String key = entry.getKey();
	            Object value = entry.getValue();
	            json.put(key, value);
	            if("rowno".equals(key)) {
	                indexId = value.toString();
	            }
	        }
	        request.add(new IndexRequest(index).id(indexId).source(json.toJSONString(), XContentType.JSON));
	    }
	    //创建索引
	    BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
	    logger.info(index + " response.status = {}", bulkResponse.status());
	}

	/**
	 * 更新索引
	 * 
	 * @throws IOException
	 */
	public void update(String index, String id, Map<String, Object> map) throws IOException {
		if (StringUtils.isBlank(index)) {
			throw new IllegalArgumentException("Index is null");
		}
		if (null == id) {
			throw new IllegalArgumentException("Id is null");
		}
		if (null == map || map.size() == 0) {
			throw new IllegalArgumentException("map is null");
		}
		UpdateRequest request = new UpdateRequest(index, id);
		request.doc(map);
		UpdateResponse response = this.client.update(request, RequestOptions.DEFAULT);
		logger.info("response.status = {}", response.status());
	}

	/**
	 * 删除索引
	 * 
	 * @param index
	 * @param id
	 * @throws IOException
	 */
	public void delete(String index, String id) throws IOException {
		if (StringUtils.isBlank(index)) {
			throw new IllegalArgumentException("Index is null");
		}
		if (null == id) {
			throw new IllegalArgumentException("Id is null");
		}
		DeleteRequest request = new DeleteRequest(index, id);
		DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
		logger.info("response.status = {}", response.status());
	}

	/**
	 * 删除所有索引
	 * 
	 * @param index
	 * @throws IOException
	 */
	public boolean delete(String index) throws IOException {
		if (StringUtils.isBlank(index)) {
			throw new IllegalArgumentException("Index is null");
		}
		DeleteIndexRequest request = new DeleteIndexRequest(index);
		request.timeout(TimeValue.timeValueHours(1));
		AcknowledgedResponse deleteIndexResponse = this.client.indices().delete(request, RequestOptions.DEFAULT);
		return deleteIndexResponse.isAcknowledged();
	}

	/**
	 * 判断索引是否存在
	 * 
	 * @param index
	 * @return
	 * @throws IOException
	 */
	public boolean exists(String index) throws IOException {
		GetIndexRequest request = new GetIndexRequest(index);
		request.local(false);
		request.humanReadable(true);
		request.includeDefaults(false);
		request.indicesOptions(IndicesOptions.STRICT_EXPAND_OPEN);
		return this.client.indices().exists(request, RequestOptions.DEFAULT);
	}
	
	/**
	 * 创建多个索引联合别名
	 * @param indexName
	 * @return
	 * @throws IOException
	 */
	public boolean createAliases(String indexName, String aliasName) throws IOException {
        AliasActions actions = new AliasActions(AliasActions.Type.ADD);
        actions = actions.index(indexName).alias(aliasName);
        IndicesAliasesRequest indicesAliasesRequest = new IndicesAliasesRequest();
        indicesAliasesRequest.addAliasAction(actions);
        AcknowledgedResponse response = this.client.indices().updateAliases(indicesAliasesRequest, RequestOptions.DEFAULT);
        return response.isAcknowledged();
	}

	/**
	 * 读取外部配置的settings.json配置文件
	 * @return
	 */
	public String settings() {
        return FileUtil.readString(settingsPath, "UTF-8");
	}
}
