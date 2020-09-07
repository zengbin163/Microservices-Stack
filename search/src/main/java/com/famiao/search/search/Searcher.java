package com.famiao.search.search;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.TimeValue;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.MultiMatchQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.famiao.search.base.IndexerParam;
import com.famiao.search.database.sql.DynamicSqlResolver;
import com.famiao.search.database.sql.template.DynamicSqlTemplate;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 30, 2019 12:33:10 AM
 */
@Component
public class Searcher {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private DynamicSqlResolver resolver;

    public Map<String, Object> search(Integer sqlId, String id) throws Exception {
        DynamicSqlTemplate template = this.resolver.resolveNodeById(sqlId);
        if (null == id) {
            throw new IllegalArgumentException("Id is null");
        }
        GetRequest getRequest = new GetRequest(template.getIndex(), id);
        GetResponse response = client.get(getRequest, RequestOptions.DEFAULT);
        Map<String, Object> map = new HashMap<>();
        if (response.isExists()) {
            map.put("success", true);
            map.put("data", response.getSource());
        } else {
            map.put("success", false);
        }
        return map;
    }

    public List<Map<String, Object>> searchList(Integer pageStart, Integer pageSize, String globalParam) throws Exception {
        String []paramNames = {"realName","province","city","lawOfficeName","assetRealName","assetName"};
        List<Map<String, Object>> list = new ArrayList<>();
        SearchRequest request = new SearchRequest(IndexerParam.SEARCH_INDEX_ALIAS);
        MultiMatchQueryBuilder multiMatchQueryBuilder = QueryBuilders.multiMatchQuery(globalParam, paramNames);
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 排序
        // searchSourceBuilder.sort(SortBuilders.fieldSort(IndexerParam.SEARCH_SORTED_PARAM).order(SortOrder.DESC));
        // 分页
        searchSourceBuilder.from(pageStart).size(pageSize).query(multiMatchQueryBuilder);
        // 设置一个可选的超时，控制允许搜索的时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        SearchHit [] hits = this.client.search(request, RequestOptions.DEFAULT).getHits().getHits();
        for (SearchHit s : hits) {
            list.add(s.getSourceAsMap());
        }
        return list;
    }
    
    public List<Map<String, Object>> searchList(Integer pageStart, Integer pageSize, Map<String, Object> paramMap) throws Exception {
        if (null == paramMap) {
            throw new IllegalArgumentException("paramMap is null");
        }
        List<Map<String, Object>> list = new ArrayList<>();
        SearchRequest request = new SearchRequest(IndexerParam.SEARCH_INDEX_ALIAS);
        // 构造bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            boolQueryBuilder.must(QueryBuilders.matchPhraseQuery(entry.getKey(), entry.getValue()));
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        searchSourceBuilder.query(QueryBuilders.matchAllQuery());
        // 排序
        // searchSourceBuilder.sort(SortBuilders.fieldSort(IndexerParam.SEARCH_SORTED_PARAM).order(SortOrder.DESC));
        // 分页
        searchSourceBuilder.from(pageStart).size(pageSize).query(boolQueryBuilder);
        // 设置一个可选的超时，控制允许搜索的时间
        searchSourceBuilder.timeout(new TimeValue(60, TimeUnit.SECONDS));
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        for (SearchHit s : client.search(request, RequestOptions.DEFAULT).getHits().getHits()) {
            list.add(s.getSourceAsMap());
        }
        return list;
    }

}
