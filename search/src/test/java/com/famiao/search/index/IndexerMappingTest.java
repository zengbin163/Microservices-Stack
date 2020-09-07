package com.famiao.search.index;

import java.io.IOException;

import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.client.indices.CreateIndexRequest;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.common.xcontent.XContentType;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.alibaba.fastjson.JSONObject;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 30, 2019 1:45:01 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexerMappingTest {

    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private Indexer indexer;
    private static final Logger logger = LoggerFactory.getLogger(IndexerMappingTest.class);

    @Test
    public void build() throws IOException {
        this.createIndex2();
    }

    public XContentBuilder createIndex() throws IOException {
        String index = "famiao_lawer";

        if (indexer.exists(index)) {
            boolean ack = indexer.delete(index);
            logger.info("Delete index acknowledge " + ack);
        }

        String docId = "1000" + System.currentTimeMillis();
        IndexRequest request = new IndexRequest(index);

        XContentBuilder mapping = null;
        mapping = XContentFactory.jsonBuilder();
        for (int i = 0; i < 100; i++) {
            mapping.startObject();
            mapping.field("type", "string").field("title", "张三");
            mapping.field("type", "string").field("question", "服装").field("analyzer", "ik_max_word");
            mapping.field("type", "text").field("answer", "西装").field("analyzer", "ik_max_word");
            mapping.field("type", "text").field("category", "服装西装").field("analyzer", "ik_max_word");
            mapping.field("type", "string").field("index", "not_analyzed");
            mapping.endObject();
            request.id(docId).opType("create").source(mapping, XContentType.JSON);
        }
        IndexResponse response = client.index(request, RequestOptions.DEFAULT);
        logger.info("response.status = {}", response.status());

        return mapping;
    }

    public void createIndex2() throws IOException {
        String index = "famiao_lawer";
        String index2 = "famiao_asset";

        if (indexer.exists(index)) {
            boolean ack = indexer.delete(index);
            logger.info("Delete index='" + index + "' acknowledge=" + ack);
        }
        if (indexer.exists(index2)) {
            boolean ack = indexer.delete(index2);
            logger.info("Delete index='" + index2 + "' acknowledge=" + ack);
        }

        BulkRequest request = new BulkRequest();
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "北京市法苗网络科技有限公司深圳分公司" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user01" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法苗" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user02" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法苗网" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user03" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法苗网络" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user04" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法苗网络科技有限公司" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user05" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法苗律所" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user06" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "法法律所" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user07" + i).opType("create").source(json, XContentType.JSON));
        }
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("realName", "法苗" + i);
            json.put("userName", "法苗网" + i);
            json.put("province", "法" + i);
            json.put("city", "苗" + i);
            json.put("lawOfficeName", "法苗律所" + i);
            json.put("lawOfficeAddr", "苗苗律所" + i);
            json.put("analyzer", "ik_max_word");
            json.put("search_analyzer", "ik_smart");
            request.add(new IndexRequest(index).id("user08" + i).opType("create").source(json, XContentType.JSON));
        }
        BulkResponse bulkResponse = client.bulk(request, RequestOptions.DEFAULT);
        logger.info(index + " response.status = {}", bulkResponse.status());

        BulkRequest request2 = new BulkRequest();
        for (int i = 0; i < 10; i++) {
            JSONObject json = new JSONObject();
            json.put("type", "text");
            json.put("assetRealName", "法" + i);
            json.put("assetName", "房抵贷01" + i);
            json.put("assetDesc", "<p>中国人寿保险期货债权</p>" + i);
            json.put("analyzer", "ik_max_word");
            request2.add(new IndexRequest(index2).opType("create").id("user" + i).source(json, XContentType.JSON));
        }
        BulkResponse bulkResponse2 = client.bulk(request2, RequestOptions.DEFAULT);
        logger.info(index2 + " response.status = {}", bulkResponse2.status());
    }
    
    @Test
    public void mapping() throws Exception {
        //使用官方提供的工具构建json。可以直接拼接一个json字符串，也可以使用map嵌套。
        XContentBuilder jsonMapping = XContentFactory.jsonBuilder();
        //所有数据类型 看官方文档:https://www.elastic.co/guide/en/elasticsearch/reference/7.4/mapping-types.html#_core_datatypes
        jsonMapping.startObject().startObject("properties")
                .startObject("testId").field("type", "long").endObject()
                .startObject("price").field("type", "double").endObject()
                //keyword类型不会分词存储
                .startObject("name").field("type", "keyword").endObject()
                //指定分词器
                .startObject("content").field("type", "text").field("analyzer", "ik_max_word").endObject()
                .startObject("createTime").field("type", "date").field("format", "yyyy-MM-dd HH:mm:ss").endObject()
                .endObject().endObject();
        CreateIndexRequest request = new CreateIndexRequest("test");
        request.mapping(jsonMapping);
        System.out.println(jsonMapping.toString());
    }

}
