package com.chihuo.food.infrastructure.common.database.config;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.fastjson.JSON;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSource;
import com.chihuo.food.infrastructure.common.database.properties.DruidMasterProperties;
import com.chihuo.food.infrastructure.common.database.properties.DruidSlaveProperties;

/**
 * @Author: zengbin
 * @Description:数据源配置类
 * @Date: 2020-07-15 12:01
 */
@Configuration
public class DruidConfiguration {
    private static final Logger LOGGER = LoggerFactory.getLogger(DruidConfiguration.class);

    private DruidMasterProperties druidMasterProperties;
    private DruidSlaveProperties druidSlaveProperties;

    @Autowired
    public DruidConfiguration(DruidMasterProperties druidMasterProperties, DruidSlaveProperties druidSlaveProperties) {
        this.druidMasterProperties = druidMasterProperties;
        this.druidSlaveProperties = druidSlaveProperties;
    }

    /**
     * 注册一个StatViewServlet
     */
    @SuppressWarnings("rawtypes")
	@Bean(name = "druidStatViewServlet")
    public ServletRegistrationBean druidStatViewServlet() {
        return DruidMonitorConfig.druidStatViewServlet();
    }

    /**
     * 注册Druid的Stat过滤器
     */
    @SuppressWarnings("rawtypes")
	@Bean(name = "druidStatFilter")
    public FilterRegistrationBean druidStatFilter() {
        return DruidMonitorConfig.druidStatFilter();
    }

    @Bean(name = "master")
    @Qualifier("master")
    @Primary
    public DataSource master() throws Exception {
        return getDataSource(druidMasterProperties.getJdbcUrl(), druidMasterProperties.getUsername(), druidMasterProperties.getPassword(), JSON.toJSONString(druidMasterProperties), druidMasterProperties.getDriverClassName(), druidMasterProperties.getInitialSize(), druidMasterProperties.getMinIdle(), druidMasterProperties.getMaxActive(), druidMasterProperties.getMaxWait(), druidMasterProperties.getTimeBetweenEvictionRunsMillis(), druidMasterProperties.getMinEvictableIdleTimeMillis(), druidMasterProperties.getValidationQuery(), druidMasterProperties.isTestWhileIdle(), druidMasterProperties.isTestOnBorrow(), druidMasterProperties.isTestOnReturn(), druidMasterProperties.isPoolPreparedStatements(), druidMasterProperties.getMaxPoolPreparedStatementPerConnectionSize(), druidMasterProperties.getFilters(), druidMasterProperties.getConnectionProperties());
    }

    @Bean(name = "slave")
    @Qualifier("slave")
    public DataSource slave() throws Exception {
        return getDataSource(druidSlaveProperties.getJdbcUrl(), druidSlaveProperties.getUsername(), druidSlaveProperties.getPassword(), JSON.toJSONString(druidSlaveProperties), druidSlaveProperties.getDriverClassName(), druidSlaveProperties.getInitialSize(), druidSlaveProperties.getMinIdle(), druidSlaveProperties.getMaxActive(), druidSlaveProperties.getMaxWait(), druidSlaveProperties.getTimeBetweenEvictionRunsMillis(), druidSlaveProperties.getMinEvictableIdleTimeMillis(), druidSlaveProperties.getValidationQuery(), druidSlaveProperties.isTestWhileIdle(), druidSlaveProperties.isTestOnBorrow(), druidSlaveProperties.isTestOnReturn(), druidSlaveProperties.isPoolPreparedStatements(), druidSlaveProperties.getMaxPoolPreparedStatementPerConnectionSize(), druidSlaveProperties.getFilters(), druidSlaveProperties.getConnectionProperties());
    }

    private DataSource getDataSource(String url, String username, String password, String x, String driverClassName, int initialSize, int minIdle, int maxActive, int maxWait, int timeBetweenEvictionRunsMillis, int minEvictableIdleTimeMillis, String validationQuery, boolean testWhileIdle, boolean testOnBorrow, boolean testOnReturn, boolean poolPreparedStatements, int maxPoolPreparedStatementPerConnectionSize, String filters, String connectionProperties) {
        LOGGER.info("[DATASOURCE]-[USERNAME:{}],[URL:{}]", username, url);
        DruidDataSource datasource = new DruidDataSource();
        datasource.setUrl(url);
        datasource.setUsername(username);
        datasource.setPassword(password);
        datasource.setDriverClassName(driverClassName);
        datasource.setInitialSize(initialSize);
        datasource.setMinIdle(minIdle);
        datasource.setMaxActive(maxActive);
        datasource.setMaxWait(maxWait);
        datasource.setTimeBetweenEvictionRunsMillis(timeBetweenEvictionRunsMillis);
        datasource.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        datasource.setValidationQuery(validationQuery);
        datasource.setTestWhileIdle(testWhileIdle);
        datasource.setTestOnBorrow(testOnBorrow);
        datasource.setTestOnReturn(testOnReturn);
        datasource.setPoolPreparedStatements(poolPreparedStatements);
        datasource.setMaxPoolPreparedStatementPerConnectionSize(maxPoolPreparedStatementPerConnectionSize);
        try {
            datasource.setFilters(filters);
        } catch (SQLException e) {
            LOGGER.error("druid configuration initialization filter", e);
        }
        datasource.setConnectionProperties(connectionProperties);
        return datasource;
    }

    @Bean
    public DynamicDataSource dataSource(
            @Qualifier("master") DataSource master,
            @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSources = new HashMap<>(3);
        targetDataSources.put(DataBaseSourceType.MASTER_DATASOURCE, master);
        targetDataSources.put(DataBaseSourceType.SLAVE_DATASOURCE, slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSources);
        //默认目标数据源为主库
        dataSource.setDefaultTargetDataSource(master);
        return dataSource;
    }

}
