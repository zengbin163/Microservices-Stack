package com.famiao.search.web;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.famiao.search.search.Searcher;

import cn.hutool.core.net.URLDecoder;

/**
 * 搜索组件
 * 
 * @author zengbin
 * @since 2019-06-30 18:39
 */
@RestController
@RequestMapping("/searcher")
public class SearcherController {

    @Autowired
    private Searcher searcher;

    @GetMapping("/search")
    public Map<String, Object> search(@RequestParam("sqlId") Integer sqlId, @RequestParam("indexId") String indexId)
        throws Exception {
        return this.searcher.search(sqlId, indexId);
    }

    /**
     * 一键搜索
     * @param globalParam
     * @param pageStart
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/searchPages")
    public List<Map<String, Object>> search(@RequestParam(value = "pageStart", required = true) Integer pageStart,
        @RequestParam(value = "pageSize", required = true) Integer pageSize,
        @RequestParam(value = "globalParam", required = true) String globalParam) throws Exception {
        if (StringUtils.isBlank(globalParam)) {
            throw new IllegalArgumentException("参数不能为空");
        }
        return this.searcher.searchList(pageStart, pageSize, URLDecoder.decode(globalParam, Charset.defaultCharset()));
    }

    /**
     * 多条件组合搜索
     * @param globalParam
     * @param pageStart
     * @param pageSize
     * @return
     * @throws Exception
     */
    @PostMapping("/searchParams")
    public List<Map<String, Object>> search(@RequestParam(value = "pageStart", required = true) Integer pageStart,
        @RequestParam(value = "pageSize", required = true) Integer pageSize,
        @RequestParam(value = "realName", required = false) String realName,
        @RequestParam(value = "province", required = false) String province,
        @RequestParam(value = "city", required = false) String city,
        @RequestParam(value = "lawOfficeName", required = false) String lawOfficeName,
        @RequestParam(value = "assetRealName", required = false) String assetRealName,
        @RequestParam(value = "assetName", required = false) String assetName) throws Exception {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        if (StringUtils.isNotBlank(realName)) {
            paramMap.put("realName", realName);
        }
        if (StringUtils.isNotBlank(province)) {
            paramMap.put("province", province);
        }
        if (StringUtils.isNotBlank(city)) {
            paramMap.put("city", city);
        }
        if (StringUtils.isNotBlank(lawOfficeName)) {
            paramMap.put("lawOfficeName", lawOfficeName);
        }
        if (StringUtils.isNotBlank(assetRealName)) {
            paramMap.put("assetRealName", assetRealName);
        }
        if (StringUtils.isNotBlank(assetName)) {
            paramMap.put("assetName", assetName);
        }
        return this.searcher.searchList(pageStart, pageSize, paramMap);
    }
}
