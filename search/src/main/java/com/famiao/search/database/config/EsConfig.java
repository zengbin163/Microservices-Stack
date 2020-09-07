package com.famiao.search.database.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.impl.nio.client.HttpAsyncClientBuilder;
import org.apache.http.impl.nio.reactor.IOReactorConfig;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.google.gson.Gson;

/**
 * @author zengbin
 * @since 2019-06-18 18:27
 */
@Configuration
public class EsConfig {
	@Value("${es.port}")
	private Integer port;
	@Value("${es.scheme}")
	private String scheme;
	@Value("${es.hosts}")
	private String hosts;

	@Bean
	public Gson gson() {
		return new Gson();
	}

	@Bean
	public RestHighLevelClient client() {
        String[] hostArray = hosts.split(",");
        HttpHost[] hostHosts = new HttpHost[hostArray.length];
        for (int i = 0; i < hostArray.length; i++) {
            hostHosts[i] = new HttpHost(hostArray[i], port, scheme);
        }
		RestClientBuilder restClientBuilder = RestClient.builder(hostHosts).setRequestConfigCallback(new RestClientBuilder.RequestConfigCallback() {
					@Override
					public RequestConfig.Builder customizeRequestConfig(RequestConfig.Builder requestConfigBuilder) {
						return requestConfigBuilder.setConnectTimeout(5000) // 连接超时（默认为1秒）
								                   .setSocketTimeout(60000);// 套接字超时（默认为30秒）
					}
				}).setHttpClientConfigCallback(new RestClientBuilder.HttpClientConfigCallback() {
					@Override
					public HttpAsyncClientBuilder customizeHttpClient(HttpAsyncClientBuilder httpClientBuilder) {
						return httpClientBuilder.setDefaultIOReactorConfig(IOReactorConfig.custom().setIoThreadCount(1).build());// 线程数
					}
				});
		RestHighLevelClient client = new RestHighLevelClient(restClientBuilder); // 这里如果要用client去访问其他节点，就添加进去
		return client;
	}
}
