package com.funtl.my.shop.commons.utils;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * HttpClient 工具类
 */
public class HttpClientUtils {

    public static final String GET = "get";
    public static final String POST = "post";

    public static final  String REQUEST_HEADER_CONNECTION = "Connection";
    public static final  String REQUEST_HEADER_CONNECTION_VALUE = "keep-alive";
    public static final String REQUEST_HEADER_USER_AGENT = "User-Agent";
    public static final String REQUEST_HEADER_USER_AGENT_VALUE = "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36";
    public static final String REQUEST_HEADER_COOKIE = "Cookie";
    public static final String REQUEST_HEADER_COOKIE_VALUE = "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4";

    /**
     * GET 请求
     * @param url 请求地址
     * @return
     */
    public static String doGet(String url){
        return createRequest(url, GET, null);
    }

    /**
     * GET 请求
     * @param url 请求地址
     * @param cookie cookie
     * @return
     */
    public static String doGet(String url, String cookie){
        return createRequest(url, GET, cookie);
    }

    /**
     * POST 请求
     * @param url 请求地址
     * @return
     */
    public static String doPost(String url, BasicNameValuePair... params){
        return createRequest(url, POST, null, params);
    }

    /**
     * POST 请求
     * @param url 请求地址
     * @param cookie cookie
     * @return
     */
    public static String doPost(String url,String cookie, BasicNameValuePair... params){
        return createRequest(url, POST, cookie, params);
    }


    /**
     * 创建请求
     * @param url 请求地址
     * @param requestMethod 请求方式 GET/POST
     * @param cookie cookie
     * @param params 请求参数,仅限于 POST 请求
     * @return
     */
    public static String createRequest(String url, String requestMethod,String cookie, BasicNameValuePair... params){
        CloseableHttpClient httpClient = HttpClients.createDefault();
        String result = null;

        try {
            //请求方式
            HttpGet httpGet = null;
            HttpPost httpPost = null;

            //响应
            CloseableHttpResponse httpResponse = null;

            //请求结果
            result = null;

            if (GET.equals(requestMethod)){
                httpGet = new HttpGet(url);
                // 设置长连接
                httpGet.setHeader("Connection", REQUEST_HEADER_CONNECTION_VALUE);
                // 设置代理（模拟浏览器版本）
                httpGet.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT_VALUE);
                // 设置 Cookie
                httpGet.setHeader("Cookie", cookie);

                httpResponse = httpClient.execute(httpGet);
            } else if (POST.equals(requestMethod)){
                httpPost = new HttpPost(url);
                // 设置长连接
                httpPost.setHeader("Connection", REQUEST_HEADER_CONNECTION_VALUE);
                // 设置代理（模拟浏览器版本）
                httpPost.setHeader("User-Agent", REQUEST_HEADER_USER_AGENT_VALUE);
                // 设置 Cookie
                httpPost.setHeader("Cookie", cookie);

                if (null != params && params.length > 0){
                    httpPost.setEntity(new UrlEncodedFormEntity(Arrays.asList(params), "UTF-8"));
                }
                httpResponse = httpClient.execute(httpPost);
            }
            HttpEntity httpEntity = httpResponse.getEntity();

            result = EntityUtils.toString(httpEntity);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return result;
    }




















    public static String dooGet(){
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpGet 请求
        HttpGet httpGet = new HttpGet("http://localhost:8080/content/page?draw=1&start=0&length=10");
        // 设置长连接
        httpGet.setHeader("Connection", "keep-alive");
        // 设置代理（模拟浏览器版本）
        httpGet.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        // 设置 Cookie
        httpGet.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");

        CloseableHttpResponse httpResponse = null;
        try {
            // 请求并获得响应结果
            httpResponse = httpClient.execute(httpGet);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            System.out.println(EntityUtils.toString(httpEntity));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 无论如何必须关闭连接
        finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (httpClient != null) {
                try {
                    httpClient.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return  "";
    }

    private static void dooPost() {
        // 创建 HttpClient 客户端
        CloseableHttpClient httpClient = HttpClients.createDefault();

        // 创建 HttpPost 请求
        HttpPost httpPost = new HttpPost("http://localhost:8080/content/page");
        // 设置长连接
        httpPost.setHeader("Connection", "keep-alive");
        // 设置代理（模拟浏览器版本）
        httpPost.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 10.0; WOW64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/63.0.3239.132 Safari/537.36");
        // 设置 Cookie
        httpPost.setHeader("Cookie", "UM_distinctid=16442706a09352-0376059833914f-3c604504-1fa400-16442706a0b345; CNZZDATA1262458286=1603637673-1530123020-%7C1530123020; JSESSIONID=805587506F1594AE02DC45845A7216A4");

        // 创建 HttpPost 参数
        List<BasicNameValuePair> params = new ArrayList<BasicNameValuePair>();
        params.add(new BasicNameValuePair("draw", "1"));
        params.add(new BasicNameValuePair("start", "0"));
        params.add(new BasicNameValuePair("length", "10"));

        CloseableHttpResponse httpResponse = null;
        try {
            // 设置 HttpPost 参数
            httpPost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            httpResponse = httpClient.execute(httpPost);
            HttpEntity httpEntity = httpResponse.getEntity();
            // 输出请求结果
            System.out.println(EntityUtils.toString(httpEntity));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 无论如何必须关闭连接
        finally {
            try {
                if (httpResponse != null) {
                    httpResponse.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            try {
                if (httpClient != null) {
                    httpClient.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}
