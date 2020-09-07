package com.chihuo.gateway.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

/**
 * 支持post和get请求
 * @author zengbin
 *
 */
public class RequestHelper {

    public static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    /**
     * get请求获取String类型数据
     * 
     * @param url
     *            请求链接
     * @return
     */
    public static String get(String url) {
        StringBuffer sb = new StringBuffer();
        HttpGet httpGet = new HttpGet(url);
        try {
            HttpResponse response = httpClient.execute(httpGet);
            
            if(response.getStatusLine().getStatusCode() == 429) {
            	throw new RuntimeException("Too many results, request refused!!!");
            }
            
            HttpEntity entity = response.getEntity();
            InputStreamReader reader = new InputStreamReader(entity.getContent(), "utf-8");
            char[] charbufer;
            while (0 < reader.read(charbufer = new char[10])) {
                sb.append(charbufer);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpGet.releaseConnection();
        }
        return sb.toString();
    }

    /**
     * post方式请求数据
     * 
     * @param url
     *            请求链接
     * @param data
     *            post数据体
     * @return
     */
    public static String post(String url, Map<String, String> data) {
        StringBuffer sb = new StringBuffer();
        HttpPost httpPost = new HttpPost(url);
        List<NameValuePair> valuePairs = new ArrayList<>();
        if (null != data) {
            for (String key : data.keySet()) {
                valuePairs.add(new BasicNameValuePair(key, data.get(key)));
            }
        }
        try {
            httpPost.setEntity(new UrlEncodedFormEntity(valuePairs));
            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity httpEntity = response.getEntity();
            BufferedInputStream bis = new BufferedInputStream(httpEntity.getContent());
            byte[] buffer;
            while (0 < bis.read(buffer = new byte[128])) {
                sb.append(new String(buffer, "utf-8"));
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            httpPost.releaseConnection();
        }
        return sb.toString();
    }
    
}
