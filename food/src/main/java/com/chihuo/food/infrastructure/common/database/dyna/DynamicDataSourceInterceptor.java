package com.chihuo.food.infrastructure.common.database.dyna;

import java.util.Locale;
import java.util.Properties;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.keygen.SelectKeyGenerator;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.chihuo.food.infrastructure.common.database.config.DataBaseSourceType;

/**
 * @author zengbin
 * @Date 2019/6/8 12:18
 */
// 指定拦截哪些方法,update包括增删改
@Intercepts({
	@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class}),
	@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class})
})
public class DynamicDataSourceInterceptor implements Interceptor {
	
    private static final String REGEX = ".*insert\\u0020.*|.*delete\\u0020.*|.*update\\u0020.*";
	private final static Logger LOGGER = LoggerFactory.getLogger(DynamicDataSourceInterceptor.class);

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        boolean synchronizationActive = TransactionSynchronizationManager.isActualTransactionActive();
        DataBaseSourceType dataBaseSourceType = DataBaseSourceType.MASTER_DATASOURCE;
        if (!synchronizationActive) {
            Object[] objects = invocation.getArgs();
            MappedStatement ms = (MappedStatement)objects[0];
            BoundSql boundSql = ms.getSqlSource().getBoundSql(objects[1]);
            String sql = boundSql.getSql().toLowerCase(Locale.CHINA).replaceAll("[\\t\\n\\r]", " ");
            if (ms.getSqlCommandType().equals(SqlCommandType.SELECT)) {
                // 如果selectKey为自增id查询主键,使用主库
                if (ms.getId().contains(SelectKeyGenerator.SELECT_KEY_SUFFIX)) {
                	dataBaseSourceType = DataBaseSourceType.MASTER_DATASOURCE;
                } else {
                    // 这里如果有多个从数据库，则添加挑选过程
                	dataBaseSourceType = DataBaseSourceType.SLAVE_DATASOURCE;
                }
            } else {
            	if (sql.matches(REGEX)) {
                	dataBaseSourceType = DataBaseSourceType.MASTER_DATASOURCE;
                }
            }
        } else {
        	dataBaseSourceType = DataBaseSourceType.MASTER_DATASOURCE;
        }
        
        DynamicDataSourceHolder.setDataSourceType(dataBaseSourceType);
        if(LOGGER.isWarnEnabled()) {
        	LOGGER.warn("======DynamicDataSourceInterceptor.intercept, synchronizationActive={}, dataBaseSourceType={}", synchronizationActive, dataBaseSourceType);
        }
        return invocation.proceed();
    }

    @Override
    public Object plugin(Object target) {
        // 增删改查的拦截，然后交由intercept处理
        if (target instanceof Executor) {
            return Plugin.wrap(target, this);
        } else {
            return target;
        }
    }

    @Override
    public void setProperties(Properties properties) {
		LOGGER.info("DynamicDataSourceInterceptor类  ---> setProperties方法 ---> properties参数：{}", properties);
    }
}
