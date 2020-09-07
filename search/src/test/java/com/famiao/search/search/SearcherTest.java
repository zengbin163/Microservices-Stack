package com.famiao.search.search;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @desc
 * @author 曾斌
 * @version 创建时间：Jun 30, 2019 12:31:59 AM
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class SearcherTest {

	@Autowired
	private Searcher searcher;

	@Test
	public void search() throws Exception {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("firstCategoryName", "西装123");
		List<Map<String, Object>> list = this.searcher.searchList(0, 100, paramMap);
		for (Map<String, Object> map : list) {
			StringBuffer sb = new StringBuffer();
			for (Map.Entry<String, Object> entry : map.entrySet()) {
				sb.append("|" + entry.getKey() + " : " + entry.getValue() + "|");
			}
			System.out.println(sb.toString());
		}
	}

}
