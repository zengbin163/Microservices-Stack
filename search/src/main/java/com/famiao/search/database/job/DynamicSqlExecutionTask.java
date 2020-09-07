package com.famiao.search.database.job;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.Trigger;
import org.springframework.scheduling.TriggerContext;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;
import org.springframework.scheduling.support.CronTrigger;
import org.springframework.stereotype.Component;

import com.famiao.search.database.sql.DynamicSqlResolver;
import com.famiao.search.database.sql.IndexBuildExecution;
import com.famiao.search.database.sql.template.DynamicSqlTemplate;

/**
 * 动态定时任务执行引擎
 * 
 * @author zengbin
 *
 */
@Component
@EnableScheduling
public class DynamicSqlExecutionTask implements SchedulingConfigurer {

    @Autowired
    private DynamicSqlResolver resolver;
    @Autowired
    private IndexBuildExecution execution;

    @Value("${dynamic.cron.expression}")
    private String cronExpression;

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        taskRegistrar.addTriggerTask(new Runnable() {
            @Override
            public void run() {
                List<DynamicSqlTemplate> tempList = null;
                try {
                    tempList = resolver.resolve();
                    System.out.println("开始执行构建索引，时间表达式cron为：" + cronExpression + "，执行过程个数：" + tempList.size() + "个");
                    for (DynamicSqlTemplate temp : tempList) {
                        System.out.println("-----DynamicExecutionTask 开始执行构建索引-----" + temp.getIndex() + "-----" + System.currentTimeMillis());
                        String executeSql = temp.getSql();
                        execution.execute(executeSql, temp.getIndex(), temp.getMapping());
                        System.out.println("-----DynamicExecutionTask 结束执行构建索引-----" + temp.getIndex() + "-----" + System.currentTimeMillis());
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, new Trigger() {
            @Override
            public Date nextExecutionTime(TriggerContext triggerContext) {
                CronTrigger cronTrigger = new CronTrigger(cronExpression);
                Date nextExecDate = cronTrigger.nextExecutionTime(triggerContext);
                return nextExecDate;
            }
        });
    }
}
