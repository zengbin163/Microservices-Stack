package com.famiao.search.database.sql;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.famiao.search.database.sql.template.DynamicSqlTemplate;

@RunWith(SpringRunner.class)
@SpringBootTest
public class IndexBuildExecutionTest {

	@Autowired
	private DynamicSqlResolver dynamicSqlResolver;
	@Autowired
	private IndexBuildExecution execution;

	@Test
	public void test1() {
		try {
			List<DynamicSqlTemplate> tempList = dynamicSqlResolver.resolve();
			for (int i = 0; i < tempList.size(); i++) {
				DynamicSqlTemplate temp = tempList.get(i);
				System.out.println("======第" + (i+1) + "条sql执行======");
				System.out.println("sql:" + temp.getSql());
				this.execution.execute(temp.getSql(), temp.getIndex(), temp.getMapping());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
