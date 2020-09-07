package com.famiao.search.web;

import java.io.IOException;
import java.lang.reflect.Field;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchType;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentBuilder;
import org.elasticsearch.common.xcontent.XContentFactory;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.builder.SearchSourceBuilder;
import org.elasticsearch.search.sort.SortBuilders;
import org.elasticsearch.search.sort.SortOrder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.famiao.search.vo.form.Lawer;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

/**
 * @author zengbin
 * @since 2019-06-18 18:39
 */
@RestController
public class LawerController {
    
    @Autowired
    private RestHighLevelClient client;
    @Autowired
    private Gson gson;

    private static final String INDEX = "lawer";
    
    private static final Logger logger = LoggerFactory.getLogger(LawerController.class);

    @GetMapping("/lawer/getById/{id}")
    public Map<String, Object> get(@PathVariable("id") String id) {
        GetRequest getRequest = new GetRequest(INDEX, id);
        Map<String, Object> map = new HashMap<String, Object>();
        GetResponse response = null;
        try {
            response = client.get(getRequest, RequestOptions.DEFAULT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (response.isExists()) {
            map.put("success", true);
            map.put("data", response.getSource());
        } else {
            map.put("success", false);
        }
        return map;
    }

    @DeleteMapping("/lawer/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        DeleteRequest request = new DeleteRequest(INDEX, id);
        try {
            DeleteResponse response = client.delete(request, RequestOptions.DEFAULT);
            return response.status().name();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @PostMapping("/lawer/update/{id}")
    public String update(@PathVariable("id") String id,
        @RequestParam(value = "nickName", required = false) String nickName,
        @RequestParam(value = "lawOfficeName", required = false) String lawOfficeName) throws IOException {
        UpdateRequest request = new UpdateRequest(INDEX, id);
        Map<String, Object> temp = new HashMap<>();
        if (!ObjectUtils.isEmpty(nickName)) {
            temp.put("nickName", nickName);
        }
        if (!ObjectUtils.isEmpty(lawOfficeName)) {
            temp.put("lawOfficeName", lawOfficeName);
        }
        request.doc(temp);
        return client.update(request, RequestOptions.DEFAULT).status().name();

    }

    @PostMapping("/lawer/create")
    public Map<String, Object> create(@RequestParam("lawers") String lawers) throws Exception {
        Map<String, Object> ret = new HashMap<String, Object>();
        if(StringUtils.isBlank(lawers)) {
            ret.put("error", "lawers 不能为空");
            return ret;
        }
        lawers = URLDecoder.decode(lawers, "UTF-8");
        IndexRequest request = new IndexRequest(INDEX);
        List<Lawer> lawerList = gson.fromJson(lawers, new TypeToken<List<Lawer>>() {}.getType());
        List<Map<String, Object>> list = lawerList.stream().map(e -> {
            Map<String, Object> temp = new HashMap<>();
            Field[] fields = e.getClass().getDeclaredFields();
            for (Field f : fields) {
                f.setAccessible(true);
                try {
                    temp.put(f.getName(), f.get(e));
                } catch (IllegalAccessException e1) {
                    e1.printStackTrace();
                }
            }
            return temp;
        }).collect(Collectors.toList());
        try {
            XContentBuilder builder = null;
            for (Map<String, Object> map : list) {
                builder = XContentFactory.jsonBuilder().startObject();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    builder = builder.field(entry.getKey(), entry.getValue());
                }
                builder = builder.endObject();
                request.id(UUID.randomUUID().toString()).opType("create").source(builder);
                IndexResponse response = client.index(request, RequestOptions.DEFAULT);
                logger.info("index create status=" + response.status());
            }
            ret.put("status", "success");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @PostMapping("/lawer/query")
    public List<Map<String, Object>> query(@RequestParam(value = "nickName", required = false) String nickName,
        @RequestParam(value = "province", required = false) String province,
        @RequestParam(value = "mobile", required = false) String mobile,
        @RequestParam(value = "lawOfficeName", required = false) String lawOfficeName,
        @RequestParam(value = "start", required = true) Integer start,
        @RequestParam(value = "size", required = true) Integer size) throws IOException {
        SearchRequest request = new SearchRequest(INDEX);
        // 构造bool查询
        BoolQueryBuilder boolQueryBuilder = QueryBuilders.boolQuery();
        if (!ObjectUtils.isEmpty(nickName)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("nickName", nickName));
        }
        if (!ObjectUtils.isEmpty(province)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("province", province));
        }
        if (!ObjectUtils.isEmpty(lawOfficeName)) {
            boolQueryBuilder.must(QueryBuilders.matchQuery("lawOfficeName", lawOfficeName));
        }
        SearchSourceBuilder searchSourceBuilder = new SearchSourceBuilder();
        // 排序
        searchSourceBuilder.sort(SortBuilders.fieldSort("mobile").order(SortOrder.DESC));
        // 分页
        searchSourceBuilder.from(start).size(size).query(boolQueryBuilder);
        request.searchType(SearchType.DEFAULT).source(searchSourceBuilder);
        List<Map<String, Object>> list = new ArrayList<>();
        for (SearchHit s : client.search(request, RequestOptions.DEFAULT).getHits().getHits()) {
            list.add(s.getSourceAsMap());
        }
        return list;
    }
}
