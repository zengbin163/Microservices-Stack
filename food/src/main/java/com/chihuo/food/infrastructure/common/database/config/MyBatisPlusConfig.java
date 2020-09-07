package com.chihuo.food.infrastructure.common.database.config;

import java.util.HashSet;
import java.util.Set;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.AutoMappingBehavior;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.core.config.GlobalConfig;
import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import com.baomidou.mybatisplus.extension.plugins.pagination.dialects.MySqlDialect;
import com.baomidou.mybatisplus.extension.plugins.pagination.optimize.JsqlParserCountOptimize;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSource;
import com.chihuo.food.infrastructure.common.database.dyna.DynamicDataSourceInterceptor;

/**
 * @author zengbin
 * @Date 2019/6/8 16:12
 */
@Configuration // 该注解类似于spring配置文件
@MapperScan(basePackages = "com.chihuo.food.domain.*.repository.mapper*")
public class MyBatisPlusConfig {
	
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
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean(DynamicDataSource dynamicDataSource) throws Exception {
    	
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dynamicDataSource);
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath*:mapper/**/*.xml"));
        sqlSessionFactoryBean.setTypeAliasesPackage("com.chihuo.food.domain.*.repository.po");

        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setCacheEnabled(true);
        //全局的映射器启用或禁用缓存
        configuration.setLazyLoadingEnabled(true);
        //全局启用或禁用延迟加载。当禁用时，所有关联对象都会即时加载
        configuration.setMultipleResultSetsEnabled(true);
        //允许或不允许多种结果集从一个单独的语句中返回（需要适合的驱动）
        configuration.setUseColumnLabel(true);
        //使用列标签代替列名。不同的驱动在这方便表现不同。参考驱动文档或充分测试两种方法来决定所使用的驱动
        configuration.setUseGeneratedKeys(false);
        //允许JDBC支持生成的键。需要适合的驱动。如果设置为true则这个设置强制生成的键被使用，尽管一些驱动拒绝兼容但仍然有效（比如Derby）
        configuration.setAutoMappingBehavior(AutoMappingBehavior.PARTIAL);
        //指定MyBatis如何自动映射列到字段/属性。PARTIAL只会自动映射简单，没有嵌套的结果。FULL会自动映射任意复杂的结果（嵌套的或其他情况）
        configuration.setSafeRowBoundsEnabled(false);
        //允许在嵌套语句中使用分页
        configuration.setMapUnderscoreToCamelCase(true);
        //是否开启自动驼峰命名规则（camel case）映射，即从经典数据库列名 A_COLUMN 到经典 Java 属性名 aColumn 的类似映射
        /**
         * MyBatis 利用本地缓存机制（Local Cache）防止循环引用（circular references）和加速重复嵌套查询。
                              默认值为 SESSION，这种情况下会缓存一个会话中执行的所有查询。
                             若设置值为 STATEMENT，本地会话仅用在语句执行上，对相同 SqlSession 的不同调用将不会共享数据*/
        configuration.setLocalCacheScope(LocalCacheScope.STATEMENT);
        /**
         * 当没有为参数提供特定的 JDBC 类型时，为空值指定 JDBC 类型。
                              某些驱动需要指定列的 JDBC 类型，多数情况直接用一般类型即可，比如 NULL、VARCHAR、OTHER*/
        configuration.setJdbcTypeForNull(JdbcType.OTHER);
        Set<String> methods = new HashSet<>();
        methods.add("equals");
        methods.add("clone");
        methods.add("hashCode");
        methods.add("toString");
        /**
         * 指定对象的方法触发一次延迟加载。--*/
        configuration.setLazyLoadTriggerMethods(methods);
        /**
         * 设置关联对象加载的形态，此处为按需加载字段(加载字段由SQL指 定)，不会加载关联表的所有字段，以提高性能
         */
        configuration.setAggressiveLazyLoading(false);
        sqlSessionFactoryBean.setConfiguration(configuration);
        Interceptor interceptor[] = {paginationInterceptor(), dynamicDataSourceInterceptor()};
        sqlSessionFactoryBean.setPlugins(interceptor);
        return sqlSessionFactoryBean.getObject();
    }

    /**
     * 配置事务管理器
     */
    @Bean
    public DataSourceTransactionManager transactionManager(DynamicDataSource dynamicDataSource) {
        return new DataSourceTransactionManager(dynamicDataSource);
    }

    @Bean
    public GlobalConfig globalConfig() {
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setBanner(false);
        GlobalConfig.DbConfig dbConfig = new GlobalConfig.DbConfig();
        dbConfig.setIdType(IdType.AUTO);
        globalConfig.setDbConfig(dbConfig);
        return globalConfig;
    }

}
