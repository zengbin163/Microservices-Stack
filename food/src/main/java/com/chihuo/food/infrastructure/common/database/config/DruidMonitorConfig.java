package com.chihuo.food.infrastructure.common.database.config;

import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;

/**
 * Druid监控配置类
 */
public final class DruidMonitorConfig {
	
    private DruidMonitorConfig() {
    	
    }

    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static ServletRegistrationBean druidStatViewServlet() {
        //org.springframework.boot.context.embedded.ServletRegistrationBean提供类的进行注册
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
        //添加初始化参数：initParams
        // IP白名单 (没有配置或者为空，则允许所有访问)
        // /24 表示这个IP掩码是255.255.255.0，表示这个地址的前24（也就是IP前三位十进制）位为网络位。
        //servletRegistrationBean.addInitParameter("allow", "127.0.0.1,192.168.3.0/24,192.168.100.0/24");
        //IP黑名单 (存在共同时，deny优先于allow)
        //servletRegistrationBean.addInitParameter("deny", "192.168.1.73");
        //登录查看信息的账号密码.
        servletRegistrationBean.addInitParameter("loginUsername", "bate");
        servletRegistrationBean.addInitParameter("loginPassword", "bate");
        // 禁用HTML页面上的“Reset All”功能
        servletRegistrationBean.addInitParameter("resetEnable", "false");
        return servletRegistrationBean;
    }

    /**
     * 注册Druid的Stat过滤器
     * <p>已开启session监控，需要将用户名写入session，key=USER_HTID_SESSION_NAME</p>
     *
     * @return Druid的Stat过滤器
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static FilterRegistrationBean druidStatFilter() {
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean(new WebStatFilter());
        // 添加过滤规则
        filterRegistrationBean.addUrlPatterns("/*");
        // 忽略资源
		filterRegistrationBean.addInitParameter("exclusions", "*.js,*.gif,*.jpg,*.jpeg,*.png,*.css,*.ico,/druid/*");
        // 配置profileEnable能够监控单个url调用的sql列表
        filterRegistrationBean.addInitParameter("profileEnable", "true");
        // 开启session统计功能
        filterRegistrationBean.addInitParameter("sessionStatEnable", "true");
        //  使得druid能够知道当前的session的用户是谁
        //  user信息保存在session中的sessionName
        // 如果session中保存的是非string类型的对象，需要重载toString方法
        filterRegistrationBean.addInitParameter("principalSessionName", "USER_HTID_SESSION_NAME");
        return filterRegistrationBean;
    }
}
