package com.chihuo.food.infrastructure.config;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.cache.RedisCacheWriter;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.SerializationException;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.parser.Feature;
import com.alibaba.fastjson.serializer.SerializerFeature;

@EnableCaching              // 启用缓存
@Configuration
public class RedisConfiguration extends CachingConfigurerSupport {
	
    @Value("${spring.redis.host}")
    private String host;
    @Value("${spring.redis.password}")
    private String password;
    @Value("${spring.redis.port}")
    private Integer port;
    @Value("${spring.redis.timeout}")
    private Integer timeout;

	/**
	 * 设置缓存管理器，这里可以配置默认过期时间等
	 *
	 * @param connectionFactory 连接池
	 * @return
	 */
	@Bean
	public CacheManager cacheManager(RedisConnectionFactory connectionFactory) {
		RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofSeconds(60));
		// 注意：请勿使用先new 配置对象，然后在调用entryTtl方法的方式来操作
		// 会导致配置不生效，原因是调用.entryTtl方法会返回一个新的配置对象，而不是在原来的配置对象上修改
		RedisCacheWriter redisCacheWriter = RedisCacheWriter.nonLockingRedisCacheWriter(connectionFactory);
		RedisCacheManager manager = new RedisCacheManager(redisCacheWriter, redisCacheConfiguration);
		return manager;
	}

	@SuppressWarnings("all")
	@Bean
	public RedisTemplate<String, String> redisTemplate(JedisConnectionFactory factory) {
		StringRedisTemplate template = new StringRedisTemplate(factory);
		RedisSerializer stringSerializer = new StringRedisSerializer();
		//key采用String序列化方式
		template.setKeySerializer(stringSerializer);
		template.setHashKeySerializer(stringSerializer);
		//value采用fast-json序列化方式。
		template.setValueSerializer(fastJson2JsonRedisSerializer());
		template.setHashValueSerializer(fastJson2JsonRedisSerializer());
		template.afterPropertiesSet();
		return template;
	}

	// 使用jedis连接池建立jedis连接工厂
	@Bean
	public JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(host);
        redisStandaloneConfiguration.setPort(port);
        //由于我们使用了动态配置库,所以此处省略
        //redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
        jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration, jedisClientConfiguration.build());
        return factory;
	}
        
	@SuppressWarnings("rawtypes")
	@Bean
	public RedisSerializer fastJson2JsonRedisSerializer() {
		return new FastJson2JsonRedisSerializer<>(Object.class);
	}
	
	public static class FastJson2JsonRedisSerializer<T> implements RedisSerializer<T> {
		public static final Charset DEFAULT_CHARSET = StandardCharsets.UTF_8;
		private Class<T> clazz;

		public FastJson2JsonRedisSerializer(Class<T> clazz) {
			super();
			this.clazz = clazz;
		}

		public byte[] serialize(T t) throws SerializationException {
			if (t == null) {
				return new byte[0];
			}
			return JSON.toJSONString(t, SerializerFeature.WriteClassName).getBytes(DEFAULT_CHARSET);
		}

		public T deserialize(byte[] bytes) throws SerializationException {
			if (bytes == null || bytes.length <= 0) {
				return null;
			}
			String str = new String(bytes, DEFAULT_CHARSET);
			return JSON.parseObject(str, clazz, Feature.SupportAutoType);
		}
	}

}
