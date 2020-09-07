package com.famiao.search.database.sql;

import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.famiao.search.base.IndexerParam;
import com.famiao.search.index.Indexer;
import com.famiao.search.service.SearchSqlService;

/**
 * 搜索引擎构建索引的sql执行引擎
 * 
 * @author zengbin
 *
 */
@Configuration
public class IndexBuildExecution {

	@Autowired
	private SearchSqlService searchSqlService;
	@Autowired
	private Indexer indexer;
	
	private static final Logger logger = LoggerFactory.getLogger(IndexBuildExecution.class);

	public void execute(String sql, String indexName, String mapping) throws Exception {
		if (StringUtils.isBlank(sql)) {
			throw new IllegalArgumentException("执行sql为空");
		}
		//构建索引文件
        this.indexer.build(indexName, mapping);
		//写入索引数据
		Long total = this.searchSqlService.countResultByPage(sql);
		Long totalPages = total % IndexerParam.SELECT_PAGE_SIZE == 0 ? total / IndexerParam.SELECT_PAGE_SIZE : total / IndexerParam.SELECT_PAGE_SIZE + 1;
		for (Long currentPage = 1L; currentPage <= totalPages; currentPage++) {
			IPage<Map<String, Object>> pages = this.searchSqlService.getResultByPage(sql, currentPage, IndexerParam.SELECT_PAGE_SIZE);
			List<Map<String, Object>> listMap = pages.getRecords();
			this.indexer.bulkIndex(indexName, listMap);
		}
		logger.info("=========构建索引执行总数 = {} ==========", total);
	}

}
