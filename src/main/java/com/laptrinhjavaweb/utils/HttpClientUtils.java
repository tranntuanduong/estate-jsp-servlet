package com.laptrinhjavaweb.utils;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

public class HttpClientUtils {
	final static Logger logger = Logger.getLogger(HttpClientUtils.class);
	public static String httpGet(String url) {
		DefaultHttpClient httpClient = new DefaultHttpClient();
		HttpGet getRequest = new HttpGet(url);
		
		try {
			HttpResponse httpResponse = httpClient.execute(getRequest); 
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			String result = "";
				if(statusCode >= 200 && statusCode <300) {
					HttpEntity httpEntity = httpResponse.getEntity();
					result =  EntityUtils.toString(httpEntity);
					return result;
				}
				logger.info(result);
		} catch (Exception e) {
				logger.error("ERROR execute API: "+e.getMessage(), e);
		}
		return null;
	}
}
