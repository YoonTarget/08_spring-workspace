package com.kh.open;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private static final String serviceKey = "0JDMJTyfQuGcidtXawr2aOoBFpceafVK4%2BLy6HzQ92GJYjetxkopfQi80%2Fr3oZkc%2FtrRoEugNtLnnMuj3U4n9g%3D%3D";
	
	@ResponseBody
	@RequestMapping(value = "fraud.do", produces = "application/json; charset=UTF-8")
	public String fraud(String nation) throws IOException {
		
		String url = "https://apis.data.go.kr/B410001/cmmrcFraudCase/cmmrcFraudCase";
		
		url += "?serviceKey=" + serviceKey;
		url += "&type=json";
		url += "&numOfRows=10";
		url += "&pageNo=1";
		url += "&search1=" + URLEncoder.encode(nation, "UTF-8");
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			
			responseText += line;
			
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
		
	}

	@ResponseBody
	@RequestMapping(value = "fraud.do2", produces = "text/xml; charset=UTF-8")
	public String fraud2(String nation) throws IOException {
		
		String url = "https://apis.data.go.kr/B410001/cmmrcFraudCase/cmmrcFraudCase";
		
		url += "?serviceKey=" + serviceKey;
		url += "&type=xml";
		url += "&numOfRows=10";
		url += "&pageNo=1";
		url += "&search1=" + URLEncoder.encode(nation, "UTF-8");
		
		System.out.println(url);
		
		URL requestUrl = new URL(url);
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		urlConnection.setRequestMethod("GET");
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		
		String responseText = "";
		String line;
		
		while((line = br.readLine()) != null) {
			
			responseText += line;
			
		}
		
		br.close();
		urlConnection.disconnect();
		
		return responseText;
		
	}
	
}
