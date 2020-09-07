package com.famiao.search.index;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.famiao.search.base.IndexerParam;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 30, 2019 1:45:01 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexerTest {

	@Autowired
	private Indexer indexer;

	@Test
	public void build() throws IOException {
		List<Map<String, Object>> list = new ArrayList<Map<String, Object>>();
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", 11);
		map.put("productName", "Gucci1");
		map.put("firstCategoryId", 11);
		map.put("firstCategoryName", "服装");
		map.put("secondCategoryId", 12);
		map.put("secondCategoryName", "服装");
		map.put("thirdCategoryId", 13);
		map.put("thirdCategoryName", "服装");
		map.put("brandName", "Gucci1");
		map.put("brandName", "红色");
		map.put("sex", "男");
		map.put("season", "夏季");
		list.add(map);

		map = new HashMap<String,Object>();
		map.put("id", 21);
		map.put("productName", "Gucci2");
		map.put("firstCategoryId", 21);
		map.put("firstCategoryName", "西装");
		map.put("secondCategoryId", 22);
		map.put("secondCategoryName", "西装");
		map.put("thirdCategoryId", 23);
		map.put("thirdCategoryName", "西装");
		map.put("brandName", "Gucci2");
		map.put("brandName", "绿色");
		map.put("sex", "女");
		map.put("season", "冬季");
		list.add(map);

		map = new HashMap<String,Object>();
		map.put("id", 31);
		map.put("productName", "Gucci3");
		map.put("firstCategoryId", 31);
		map.put("firstCategoryName", "服装西装");
		map.put("secondCategoryId", 32);
		map.put("secondCategoryName", "服装西装");
		map.put("thirdCategoryId", 33);
		map.put("thirdCategoryName", "服装西装");
		map.put("brandName", "Gucci3");
		map.put("brandName", "黑色");
		map.put("sex", "男");
		map.put("season", "夏季");
		list.add(map);
		
		this.indexer.build(null, null);
	}
	
	@Test
	public void exists() throws IOException {
		System.out.println(this.indexer.exists(null));
	}
	
	@Test
	public void createAliases() throws IOException {
	    indexer.createAliases("famiao_lawer", IndexerParam.SEARCH_INDEX_ALIAS);
	    indexer.createAliases("famiao_asset", IndexerParam.SEARCH_INDEX_ALIAS);
	}

}
