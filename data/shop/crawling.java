//import java.io.BufferedReader;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.net.CookieManager;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.net.URLEncoder;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//import java.util.Random;
//import java.util.Scanner;
//
//import javax.swing.text.html.HTML;
//
//import org.apache.http.HttpEntity;
//import org.apache.http.HttpResponse;
//import org.apache.http.NameValuePair;
//import org.apache.http.client.ClientProtocolException;
//import org.apache.http.client.HttpClient;
//import org.apache.http.client.entity.UrlEncodedFormEntity;
//import org.apache.http.client.methods.HttpGet;
//import org.apache.http.client.methods.HttpPost;
//import org.apache.http.client.params.CookiePolicy;
//import org.apache.http.cookie.Cookie;
//import org.apache.http.impl.client.DefaultHttpClient;
//import org.apache.http.message.BasicNameValuePair;
//
//public class tto {
//	public static String subString(String str, String start, String end) {
//		str = str.substring(str.indexOf(start) + 1, str.length());
//		str = str.substring(0, str.indexOf(end));
//		return str;
//	}
//	public static String removeTag(String html) throws Exception {
//	    return html.replaceAll("<(/)?([a-zA-Z]*)(\\s[a-zA-Z]*=[^>]*)?(\\s)*(/)?>", "");
//	}
//	public static void main(String[] args) throws Exception {
//		HttpGet httpGet = new HttpGet(
//				"http://cyber.inu.ac.kr/User.do?cmd=loginUser&userDTO.localeKey=&userDTO.userId=201401422&userDTO.password=1183819");
//		HttpPost httppost = new HttpPost(
//				"http://portal.inu.ac.kr:7780/enpass/login?_epLogin_=enview&service=http://portal.inu.ac.kr:7780/enview/user/enpassLoginProcess.face");
//		// 웹 서버에 페이지를 요청해 응답값을
//		// 받습니다.http://cyber.inu.ac.kr/MCourse.do?cmd=viewLearnerCourseList
//
//		HttpClient httpClient = new DefaultHttpClient();
//		List<NameValuePair> params = new ArrayList<NameValuePair>();
//		List<Cookie> cookie;
//		BufferedReader br = null;
//		String line = null;
//		String session = null;
//		HttpEntity entity;
//		params.add(new BasicNameValuePair("callCount", "1"));
//		params.add(new BasicNameValuePair("_enpass_login_", "submit"));
//		params.add(new BasicNameValuePair("username", "201401422"));
//		params.add(new BasicNameValuePair("userId", "201401422"));
//		params.add(new BasicNameValuePair("password", "1183819"));
//		params.add(new BasicNameValuePair("langKnd", "ko"));
//		params.add(new BasicNameValuePair("gateway", "true"));
//		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//		HttpResponse response = httpClient.execute(httppost);
//		entity = response.getEntity();
//		br = new BufferedReader(new InputStreamReader(entity.getContent(),
//				"utf-8"));
//		cookie = ((DefaultHttpClient) httpClient).getCookieStore().getCookies();
//		String ur = null;
//		while ((line = br.readLine()) != null) {
//			String arr[];
//			// System.out.println(line.trim());
//			if (line.contains("Ticket")) {
//				arr = line.split("\"");
//				ur = arr[1];
//				System.out.println(ur);
//			}
//		}
//		// ----------------
//		httpGet = new HttpGet(ur);
//		for (int i = 0; i < cookie.size(); i++) {
//			((DefaultHttpClient) httpClient).getCookieStore().addCookie(
//					cookie.get(i));
//		}
//		response = httpClient.execute(httpGet);
//		entity = response.getEntity();
//		br = new BufferedReader(new InputStreamReader(entity.getContent(),
//				"utf-8"));
//		while ((line = br.readLine()) != null) {
//			String arr[];
//			//System.out.println(line.trim());
//		}
//		// --------------------------------
//		//cookie = ((DefaultHttpClient) httpClient).getCookieStore().getCookies();
//
////		httpGet = new HttpGet("http://portal.inu.ac.kr:7780/enview/portal/icu_stu/main/total_info.page?");
////		httpClient = new DefaultHttpClient();
////		for (int i = 0; i < cookie.size(); i++) 
////			((DefaultHttpClient) httpClient).getCookieStore().addCookie(cookie.get(i));
////		response = httpClient.execute(httpGet);
////		cookie = ((DefaultHttpClient) httpClient).getCookieStore().getCookies();
//		
//		httpGet = new HttpGet("http://haksa.inu.ac.kr:7790/common/nssoHaksa.jsp");
//		httpClient = new DefaultHttpClient();
//		for (int i = 0; i < cookie.size(); i++) 
//			((DefaultHttpClient) httpClient).getCookieStore().addCookie(cookie.get(i));
//		response = httpClient.execute(httpGet);
//		cookie=null;
//		cookie = ((DefaultHttpClient) httpClient).getCookieStore().getCookies();
//		
//		httppost = new HttpPost("http://haksa.inu.ac.kr:7790/isssj/jsp/ssj0206q.jsp");
//		httpClient = new DefaultHttpClient();
//		for (int i = 0; i < cookie.size(); i++) 
//		{
//			((DefaultHttpClient) httpClient).getCookieStore().addCookie(cookie.get(i));
//			System.out.println(cookie.get(i));
//		}
//		httppost.addHeader("User-Agent", "rwviewer");
//
//		params.clear();
//		params.add(new BasicNameValuePair("searchGroupCd", "20"));
//		params.add(new BasicNameValuePair("searchYear", "2015"));
//		params.add(new BasicNameValuePair("searchTermCd", "20"));
//		params.add(new BasicNameValuePair("operationType", "MAINSEARCH"));
//		params.add(new BasicNameValuePair("multiUpdate", ""));
//		params.add(new BasicNameValuePair("progId", "ssj0206q"));
//		params.add(new BasicNameValuePair("studentNo", "201401422"));
//		params.add(new BasicNameValuePair("studentNm", "김민석"));
//		params.add(new BasicNameValuePair("workCd", "SSJ01"));
//		params.add(new BasicNameValuePair("comType", "SHJ028"));
//		params.add(new BasicNameValuePair("closeYn", "N"));
//		params.add(new BasicNameValuePair("groupGubun", "S"));
//		httppost.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
//
//		response = httpClient.execute(httppost);
//		entity = response.getEntity();
//
//		// 응답 페이지 읽기
//		br = new BufferedReader(new InputStreamReader(entity.getContent(),
//				"euc-kr"));
//		while ((line = br.readLine()) != null) {
//			System.out.println(removeTag(line.trim())+"\n");
//		}
//
//		httpClient.getConnectionManager().shutdown();
//
//	}
//}
