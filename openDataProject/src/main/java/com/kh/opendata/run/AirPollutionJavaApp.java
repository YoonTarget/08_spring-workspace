package com.kh.opendata.run;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.kh.opendata.model.vo.AirVO;

public class AirPollutionJavaApp {

	// 발급받은 인증키 정보 변수에 담아두기
	public static final String serviceKey = "0JDMJTyfQuGcidtXawr2aOoBFpceafVK4%2BLy6HzQ92GJYjetxkopfQi80%2Fr3oZkc%2FtrRoEugNtLnnMuj3U4n9g%3D%3D";
	
	public static void main(String[] args) throws IOException {
		

		// OpenAPI 서버로 요청하고자 하는 URL 만들기
		String url = "https://apis.data.go.kr/B552584/ArpltnInforInqireSvc/getCtprvnRltmMesureDnsty";
		// url += "?serviceKey=서비스키"; // 서비스키가 제대로 부여되지 않았을 경우 => SERVICE_KEY_IS_NOT_REGISTERED_ERROR
		url += "?serviceKey=" + serviceKey;
		url += "&sidoName=" + URLEncoder.encode("서울", "UTF-8"); // 요청시 전달값 중 한글이 있을 경우 encoding 처리를 반드시 해야한다
		url += "&returnType=json";
		
		// System.out.println(url);
		
		// ** HttpURLConnection 객체를 활용해서 OpenAPI 요청 절차 **
		
		// 1. 요청하고자 하는 url 전달하면서 java.net.URL 객체 생성
		URL requestUrl = new URL(url);
		
		// 2. 1번 과정으로 생성된 URL 객체를 가지고 HttpURLConnection 객체 생성
		HttpURLConnection urlConnection = (HttpURLConnection)requestUrl.openConnection();
		
		// 3. 요청에 필요한 Header 설정하기
		urlConnection.setRequestMethod("GET");
		
		// 4. 해당 OpenAPI 서버로 요청 보낸 후 입력 스트림을 통해 응답 데이터 읽어들이기
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
		// 보조 => 기반!!!
		
		String responseText = "";
		String line;
		while((line = br.readLine()) != null) {
			// System.out.println(line);
			responseText += line;
		}
		
		// System.out.println(responseText);
		
		/*
		{
			"response":
				{
					"body":
						{
							"totalCount":40,
							"items":
								[
									{
										"so2Grade":"1",
										"coFlag":null,
										"khaiValue":"75",
										"so2Value":"0.003",
										"coValue":"0.4",
										"pm10Flag":null,
										"o3Grade":"2",
										"pm10Value":"26",
										"khaiGrade":"2",
										"sidoName":"서울",
										"no2Flag":null,
										"no2Grade":"1",
										"o3Flag":null,
										"so2Flag":null,
										"dataTime":"2023-10-12 14:00",
										"coGrade":"1",
										"no2Value":"0.017",
										"stationName":"중구",
										"pm10Grade":"1",
										"o3Value":"0.060"	
									},
									{
										"so2Grade":"1",
										"coFlag":null,
										"khaiValue":"-",
										"so2Value":"0.003",
										"coValue":"0.6",
										"pm10Flag":null,
										"o3Grade":"2",
										"pm10Value":"33",
										"khaiGrade":null,
										"sidoName":"서울",
										"no2Flag":null,
										"no2Grade":null,
										"o3Flag":null,
										"so2Flag":null,
										"dataTime":"2023-10-12 14:00",
										"coGrade":"1",
										"no2Value":"0.031",
										"stationName":"한강대로",
										"pm10Grade":"2",
										"o3Value":"0.045"
									},
									{
										"so2Grade":"1",
										"coFlag":null,
										"khaiValue":"73",
										"so2Value":"0.004",
										"coValue":"0.5",
										"pm10Flag":null,
										"o3Grade":"2",
										"pm10Value":"24",
										"khaiGrade":"2",
										"sidoName":"서울",
										"no2Flag":null,
										"no2Grade":"1",
										"o3Flag":null,
										"so2Flag":null,
										"dataTime":"2023-10-12 14:00",
										"coGrade":"1",
										"no2Value":"0.018",
										"stationName":"종로구",
										"pm10Grade":"1",
										"o3Value":"0.058"
									},
									{
										"so2Grade":"1",
										"coFlag":null,
										"khaiValue":"62",
										"so2Value":"0.004",
										"coValue":"0.6",
										"pm10Flag":null,
										"o3Grade":"2",
										"pm10Value":"29",
										"khaiGrade":"2",
										"sidoName":"서울",
										"no2Flag":null,
										"no2Grade":"1",
										"o3Flag":null,
										"so2Flag":null,
										"dataTime":"2023-10-12 14:00",
										"coGrade":"1",
										"no2Value":"0.028",
										"stationName":"청계천로",
										"pm10Grade":"2",
										"o3Value":"0.044"
									},
									{
										"so2Grade":null,"coFlag":"통신장애","khaiValue":"-","so2Value":"-","coValue":"-","pm10Flag":"통신장애","o3Grade":null,"pm10Value":"-","khaiGrade":null,"sidoName":"서울","no2Flag":"통신장애","no2Grade":null,"o3Flag":"통신장애","so2Flag":"통신장애","dataTime":"2023-10-12 14:00","coGrade":null,"no2Value":"-","stationName":"종로","pm10Grade":null,"o3Value":"-"},{"so2Grade":"1","coFlag":null,"khaiValue":"68","so2Value":"0.004","coValue":"0.5","pm10Flag":null,"o3Grade":"2","pm10Value":"33","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-10-12 14:00","coGrade":"1","no2Value":"0.025","stationName":"용산구","pm10Grade":"2","o3Value":"0.052"},{"so2Grade":"1","coFlag":null,"khaiValue":"-","so2Value":"0.003","coValue":"0.4","pm10Flag":"통신장애","o3Grade":"2","pm10Value":"-","khaiGrade":null,"sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-10-12 14:00","coGrade":"1","no2Value":"0.012","stationName":"광진구","pm10Grade":null,"o3Value":"0.058"},{"so2Grade":"1","coFlag":null,"khaiValue":"74","so2Value":"0.003","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"26","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-10-12 14:00","coGrade":"1","no2Value":"0.016","stationName":"성동구","pm10Grade":"1","o3Value":"0.059"},{"so2Grade":"1","coFlag":null,"khaiValue":"70","so2Value":"0.003","coValue":"0.5","pm10Flag":null,"o3Grade":"2","pm10Value":"48","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"2","o3Flag":null,"so2Flag":null,"dataTime":"2023-10-12 14:00","coGrade":"1","no2Value":"0.043","stationName":"강변북로","pm10Grade":"2","o3Value":"0.036"},{"so2Grade":"1","coFlag":null,"khaiValue":"78","so2Value":"0.002","coValue":"0.3","pm10Flag":null,"o3Grade":"2","pm10Value":"22","khaiGrade":"2","sidoName":"서울","no2Flag":null,"no2Grade":"1","o3Flag":null,"so2Flag":null,"dataTime":"2023-10-12 14:00","coGrade":"1","no2Value":"0.014","stationName":"중랑구","pm10Grade":"1","o3Value":"0.063"}],"pageNo":1,"numOfRows":10},"header":{"resultMsg":"NORMAL_CODE","resultCode":"00"}}}
		*/
		
		// JSONObject, JSONArray, JSONElement 이용해서 파싱할 수 있음(gson 라이브러리) => 내가 원하는 데이터만을 추출할 수 있음
		// 각각의 item 정보를 => AriVO 객체에 담고 => ArrayList에 차곡차곡 쌓기
		JsonObject totalObj = JsonParser.parseString(responseText).getAsJsonObject();
		JsonObject responseObj = totalObj.getAsJsonObject("response"); // response 속성에 접근 => {} JsonObject
		JsonObject bodyObj = responseObj.getAsJsonObject("body"); // body 속성에 접근 => {} JsonObject
		// 순수 json은 JSON, gson에서 제공하는 json은 Json
		int totalCount = bodyObj.get("totalCount").getAsInt(); // totalCount 속성에 접근 => 40 int
		JsonArray itemArr = bodyObj.getAsJsonArray("items"); // items 속성에 접근 => [] JsonArray
		
		ArrayList<AirVO> list = new ArrayList<AirVO>(); // []
		
		for(int i=0; i<itemArr.size(); i++) {
			JsonObject item = itemArr.get(i).getAsJsonObject();
			
			AirVO air = new AirVO();
			
			air.setStationName(item.get("stationName").getAsString());
			air.setDataTime(item.get("dataTime").getAsString());
			air.setKhaiValue(item.get("khaiValue").getAsString());
			air.setPm10Value(item.get("pm10Value").getAsString());
			air.setSo2Value(item.get("so2Value").getAsString());
			air.setCoValue(item.get("coValue").getAsString());
			air.setNo2Value(item.get("no2Value").getAsString());
			air.setO3Value(item.get("o3Value").getAsString());
			
			list.add(air);
			
		}
		
		for(AirVO a : list) {
			System.out.println(a);
		}
		
		// 5. 다 사용한 스트림 반납
		br.close();
		urlConnection.disconnect();
		
	}

}
