package com.frame.network.utils;


import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.SocketTimeoutException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.HttpVersion;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.params.ClientPNames;
import org.apache.http.client.params.CookiePolicy;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.conn.params.ConnManagerParams;
import org.apache.http.conn.params.ConnPerRouteBean;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.CoreProtocolPNames;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.json.JSONException;

import com.frame.network.NetConfig;
import com.frame.network.bean.NameValueParams;
import com.frame.network.exception.TypeMisMatchException;
import com.google.gson.Gson;
/**
 * http链接方法
 * Created by LDM on 2014/6/26. Email : nightkid-s@163.com
 */
public class HttpTool {

    private static final int connManagerTimeout = 10000; //线程池取出超时
    private static final int connectTimeout = 30*1000; //连接超时
    private static final int socketTimeout = 30*10000; //socket超时
    private static final String CHARSET = HTTP.UTF_8; //字符编码集
    private static HttpClient customerHttpClient; //连接参数设置
    public static final int MAX_ROUTE_NUM = 5; //设置每个路由最大连接数
    
    /**21cn免流量代理设置以及参数设置**/
    
    /**代理域名**/
    public static String DOMAIN_NAME = null; 
    /**代理端口号**/
    public static String PORT = null; 
    
    /**访问令牌**/
    public static String TOKEN = null; 
    /**时间戳**/
    public static String TIMESTAMP = null;
    /**网关代理 uaKey**/
    public static String KEY = null; 
    /**
     * 请求之前，初始化一些HttpClient基本参数
     * @return DefaultHttpClient
     */
    public static synchronized HttpClient getHttpClient() {
        if (null == customerHttpClient) {
            HttpParams params = new BasicHttpParams();
            params.setParameter(ClientPNames.COOKIE_POLICY, CookiePolicy.BROWSER_COMPATIBILITY);
            HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
            HttpProtocolParams.setContentCharset(params, CHARSET);
            HttpProtocolParams.setUserAgent(params, "Mozilla/5.0(Linux;U;Android 2.2.1;en-us;Nexus One Build.FRG83) "
                            + "AppleWebKit/553.1(KHTML,like Gecko) Version/4.0 Mobile Safari/533.1");
            params.setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            //从连接池中取连接的超时时间
            ConnManagerParams.setTimeout(params, connManagerTimeout);
            //连接超时
            HttpConnectionParams.setConnectionTimeout(params, connectTimeout);
            //请求等待超时
            HttpConnectionParams.setSoTimeout(params, socketTimeout);
            //设置每个路由最大连接数
            ConnManagerParams.setMaxConnectionsPerRoute(params, new ConnPerRouteBean(MAX_ROUTE_NUM));
            // 设置我们的HttpClient支持HTTP和HTTPS两种模式
            SchemeRegistry schReg = new SchemeRegistry();
            schReg.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
            schReg.register(new Scheme("https", SSLSocketFactory.getSocketFactory(), 443));
            // 使用线程安全的连接管理来创建HttpClient
            ClientConnectionManager conMgr = new ThreadSafeClientConnManager(params, schReg);
            customerHttpClient = new DefaultHttpClient(conMgr, params);
        }
        return customerHttpClient;
    }

    /**
     * get请求方法
     * @param url 连接地址
     * @param params 附加参数
     * @return 请求结果
     * @throws RuntimeException 处理（请求失败，字符码不支持，连接协议不支持，连接失败）失败应该统一抛出的异常
     */
    public static String get(String url, List<NameValueParams> params) throws RuntimeException {
        url = constructUrl(url, params);
        LogUtil.d("http_get_url", url);
        try {
            HttpGet request = new HttpGet(url);
            HttpResponse response = getHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
                throw new RuntimeException("请求失败");
            HttpEntity resEntity = response.getEntity();
            if(resEntity == null)
                throw new RuntimeException("返回结果为空");
            String result = EntityUtils.toString(resEntity, CHARSET);
            LogUtil.d("http_get_result： " , result == null ? "result == null" :  result);
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("The connection request to return the character encoding type not supported", e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("The connection with the client protocol does not support", e);
        } catch (SocketTimeoutException e){
            throw new RuntimeException("Connection timed out", e);
        } catch (ConnectTimeoutException e){
            throw new RuntimeException("Connection timed out", e);
        } catch (IOException e) {
            throw new RuntimeException("Connection failed", e);
        }

    }

    /**
     * 简单的post请求， 请求参数和返回值都只含有字符类型
     * @param url 请求地址
     * @param params 参数集合
     * @return 返回结果
     * @throws RuntimeException 处理（请求失败，字符码不支持，连接协议不支持，连接失败）失败应该统一抛出的异常
     * @throws JSONException
     */
    public static String simplePost(String url, List<NameValueParams> params) throws RuntimeException, JSONException{
        LogUtil.d("http_post_url", constructUrl(url, params));
        try {
            // 编码参数，进行了一次url encode转换
            UrlEncodedFormEntity entity = new UrlEncodedFormEntity(params, CHARSET);
            // 创建POST请求
            HttpPost request = new HttpPost(url);
            request.setEntity(entity);
            // 发送请求
            HttpResponse response = getHttpClient().execute(request);
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK)
                throw new RuntimeException("请求失败");
            HttpEntity resEntity = response.getEntity();
            if(resEntity == null)
                throw new RuntimeException("返回结果为空");
            String result = EntityUtils.toString(resEntity, CHARSET);
            LogUtil.d("http_post_result： " , result == null ? "result == null" :  result);
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("请求连接返回不支持的字符编码类型", e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("不支持该连接采用的client协议", e);
        }catch (ConnectTimeoutException e){
            throw new RuntimeException("连接超时", e);
        } catch (SocketTimeoutException e){
            throw new RuntimeException("连接超时", e);
        } catch (IOException e) {
            throw new RuntimeException("连接失败", e);
        }

    }

    /**
     * 支持附带文件的post请求方式
     * @param url 请求地址
     * @param params 字符参数 包含了带文件路径的参数
     * @return 返回结果
     * @throws RuntimeException
     */


    public static String post(String url, List<NameValueParams> params,String cookie) throws RuntimeException {
        LogUtil.d("http_post_url", constructUrl(url, params));
        boolean isGet = false;
        try {
           for (NameValueParams pair1 : params){
                if(pair1.getName().equals("pageId")){
                    isGet = true;
                    break;
                }  else isGet = false;
           }
            if(isGet) url = constructUrl(url, params);
            HttpPost httppost = new HttpPost(url);

            if (Utils.isEmple(cookie)) {
                httppost.setHeader("Cookie",cookie);
			}else{
                customerHttpClient = null;
            }
            MultipartEntity mpEntity = new MultipartEntity(); //文件传输
           if(!isGet){
               for (NameValueParams pair : params){
                   switch (pair.getType()){
                       case Text_:
                           mpEntity.addPart(pair.getName(), new StringBody(pair.getValue(), Charset.forName(CHARSET)));
                           break;
                       case File_:
                           mpEntity.addPart(pair.getName(), new FileBody(pair.getFile())); // <input type="file" name="~" />
                           break;
                       case ByteArray_:
                           mpEntity.addPart(pair.getName(), pair.getByteArrayBody());
                           break;
                       case InputStream_:
                           mpEntity.addPart(pair.getName(), pair.getInputStreamBody());
                           break;
                   }
               }
           }

            httppost.setEntity(mpEntity);      
            HttpClient httpClient = getHttpClient();
            HttpParams httpParams = httpClient.getParams();
            httpParams.setParameter(CoreProtocolPNames.USE_EXPECT_CONTINUE, false);
            HttpResponse response = httpClient.execute(httppost);
            LogUtil.i("HttpTool","post-->StatusCode:"+response.getStatusLine().getStatusCode());
            if (response.getStatusLine().getStatusCode() != HttpStatus.SC_OK) throw new RuntimeException("Connection failed-1");
            HttpEntity resEntity = response.getEntity();
            if (resEntity == null)
                throw new RuntimeException("返回结果为空");
            String result = EntityUtils.toString(resEntity, CHARSET);
            LogUtil.d("http_post_result： ", result == null ? "result == null" : result);
            return result;
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("The connection request to return the character encoding type not supported", e);
        } catch (ClientProtocolException e) {
            throw new RuntimeException("The connection with the client protocol does not support", e);
        } catch (SocketTimeoutException e) {
            throw new RuntimeException("Connection timed out", e);
        } catch (ConnectTimeoutException e) {
            throw new RuntimeException("Connection timed out", e);
        } catch (TypeMisMatchException e){
            throw new RuntimeException("Gets the type does not match the parameters");
        } catch (IOException e) {
            throw new RuntimeException("Connection failed", e);
        }
    }
    
    
    public static String post21cn(String url) {
		// TODO Auto-generated method stub
         try {
             HttpPost httppost = new HttpPost(url);          
             HttpResponse response = getHttpClient().execute(httppost);    
             HttpEntity resEntity = response.getEntity();
             if (resEntity == null)
                 throw new RuntimeException("返回结果为空");
             String result = EntityUtils.toString(resEntity, CHARSET);
             LogUtil.d("http_post_result： ", result == null ? "result == null" : result);
             return result;
         } catch (UnsupportedEncodingException e) {
             throw new RuntimeException("请求连接返回不支持的字符编码类型", e);
         } catch (ClientProtocolException e) {
             throw new RuntimeException("不支持该连接采用的client协议", e);
         } catch (SocketTimeoutException e) {
             throw new RuntimeException("连接超时", e);
         } catch (ConnectTimeoutException e) {
             throw new RuntimeException("连接超时", e);
         } catch (IOException e) {
             throw new RuntimeException("连接失败", e);
         }
	}

    /**
     * 拼接post显式请求连接，多用于复制入浏览器查看请求结果。
     * @param url 请求连接
     * @param params 请求参数
     * @return 返回结果
     * @throws UnsupportedEncodingException
     */
    public static String constructUrl(String url, List<NameValueParams> params){
        StringBuilder sb = new StringBuilder(url == null ? "" : url).append("?");
        for (int i = 0 ; i < ((params == null) ? 0 : params.size()); i ++){
            NameValuePair pair = params.get(i);
            try {
				sb.append(pair.getName()).append("=").append(URLEncoder.encode(pair.getValue(), "UTF-8")).append("&");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
            if(i == params.size() - 1) sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }
    
    
    /**
	 * 将bean解析成url参数
	 * @param bean
	 * @return
	 * @throws UnsupportedEncodingException
	 */
    public static String parseBean2UrlParams(Object bean) throws UnsupportedEncodingException {
		if(null == bean) {
			return "";
		}
		Map<String, Object> params = BeanRefUtil.getFieldValueMap(bean);
		if(null == params || params.isEmpty()) {
			return "";
		}
		StringBuilder sb = new StringBuilder();
		Iterator<Entry<String, Object>> iterator = params.entrySet().iterator();
		while(iterator.hasNext()) {
			Entry<String, Object> entry = iterator.next();
			String key = entry.getKey();
			Object obj = entry.getValue();
			if(sb.length() > 0) {
				sb.append("&");
			}
			sb.append(key);
			sb.append("=");
			if(obj instanceof List) {
				sb.append(URLEncoder.encode(obj == null ? "" : new Gson().toJson(obj), CHARSET));
			} else if(obj instanceof Integer) {
				if(null != obj) {
					Integer value = (Integer) obj;
					sb.append(URLEncoder.encode(value == NetConfig.INVALID_VALUE ? "" : value.toString(),CHARSET));
				}
			}else if(obj instanceof Long){
				if(null != obj) {
					Long value = (Long) obj;
					sb.append(URLEncoder.encode(value == NetConfig.INVALID_VALUE ? "" : value.toString(),CHARSET));
				}
			}			
			else {
				sb.append(URLEncoder.encode(obj == null ? "" : obj.toString(),CHARSET));
			}
		}
		return sb.toString();
	}
}
