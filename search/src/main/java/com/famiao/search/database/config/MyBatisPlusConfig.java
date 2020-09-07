package com.famiao.search.database.config;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.jdbc.datasource.LazyConnectionDataSourceProxy;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import com.famiao.search.database.dyna.DynamicDataSource;
import com.famiao.search.database.dyna.DynamicDataSourceHolder;
import com.famiao.search.database.dyna.DynamicDataSourceInterceptor;

/**
 * @author zengbin
 * @Date 2019/6/8 16:12
 */
@Configuration // 该注解类似于spring配置文件
@MapperScan(basePackages = "com.famiao.search.dao*")
public class MyBatisPlusConfig {
    /**
     * 配置数据源
     * 
     * @return
     */
    @Bean(name = "master")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.master")
    public DataSource master() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = "slave")
    @ConfigurationProperties(prefix = "spring.datasource.hikari.slave")
    public DataSource slave() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = "dynamicDataSource")
    public DynamicDataSource dataSource(@Qualifier("master") DataSource master, @Qualifier("slave") DataSource slave) {
        Map<Object, Object> targetDataSource = new HashMap<>();
        targetDataSource.put(DynamicDataSourceHolder.DB_MASTER, master);
        targetDataSource.put(DynamicDataSourceHolder.DB_SLAVE, slave);
        DynamicDataSource dataSource = new DynamicDataSource();
        dataSource.setTargetDataSources(targetDataSource);
        return dataSource;
    }

    @Bean
    public PaginationInterceptor paginationInterceptor() {
        PaginationInterceptor pageInterceptor = new PaginationInterceptor();
        pageInterceptor.setDialect(new MySqlDialect());
        pageInterceptor.setCountSqlParser(new JsqlParserCountOptimize(true));
        return pageInterceptor;
    }

    @Bean
    public DynamicDataSourceInterceptor dynamicDataSourceInterceptor() {
        return new DynamicDataSourceInterceptor();
    }

    /**
     * 根据数据源创建SqlSessionFactory
     */
    @Bean(name = "SqlSessionFactory")
    public SqlSessionFactory getSqlSessionFactory() throws Exception {
        // 配置mybatis,对应mybatis-config.xml
        MybatisSqlSessionFactoryBean sqlSessionFactory = new MybatisSqlSessionFactoryBean();
        // 懒加载
        LazyConnectionDataSourceProxy p = new LazyConnectionDataSourceProxy();
        p.setTargetDataSource(dataSource(master(), slave()));
        sqlSessionFactory.setDataSource(p);
        sqlSessionFactory.setTypeAliasesPackage("com.famiao.search.vo.entity");
        // 需要mapper文件并且不在同一个包下时加入扫描，
        sqlSessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/**/*.xml"));
        MybatisConfiguration configuration = new MybatisConfiguration();
        configuration.setJdbcTypeForNull(JdbcType.NULL);
        configuration.setMapUnderscoreToCamelCase(true);
        configuration.setUseGeneratedKeys(true);
        configuration.setCacheEnabled(false);

        sqlSessionFactory.setConfiguration(configuration);
        // 加入上面的两个拦截器
        Interceptor interceptor[] = {paginationInterceptor(), dynamicDataSourceInterceptor()};
        sqlSessionFactory.setPlugins(interceptor);
        sqlSessionFactory.setGlobalConfig(globalConfig());
        return sqlSessionFactory.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dataSource) throws Exception {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.ASSIGN_ID);
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

}
