package searching.service;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class NaverApiAgent {

    public static String translate(String source, String target, String text) {
        //source = en, target = ko
        //public static String CLIENT_ID = " ";     상수화
        //String postParams = "source=" + source + "&target=" + target + "&text=" + text;

        return null;
    }

    public static String ReturnKeywordAsJson(String keyword) {

        String clientId = "zYKOAUxRRPueChQC3b9b";   // App 클라이언트 아이디값
        String clientSecret = "RoFsIfRyvz";         // App 클라이언트 시크릿값

        try {

            String text = URLEncoder.encode("keyword", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;   // json 결과, ?query=에 text 붙여서 검색어 전달
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();    // header 설정
            // header
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();   // Gets the status code from an HTTP response message.

            // Body
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {    // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();


        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }//ReturnKeywordAsJson 끝

    public static String ReturnKeywordAsJson2(String keyword, int displayValue) {

        String clientId = "zYKOAUxRRPueChQC3b9b";   // App 클라이언트 아이디값
        String clientSecret = "RoFsIfRyvz";         // App 클라이언트 시크릿값

        try {

            String text = URLEncoder.encode("keyword", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text + "&display=" + displayValue;   // json 결과, ?query=에 text 붙여서 검색어 전달
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

            //System.out.println(apiURL);
            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();    // header 설정
            // header
            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();   // Gets the status code from an HTTP response message.

            // Body
            BufferedReader br;
            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {    // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }
            br.close();

            return response.toString();


        } catch (Exception e) {
            System.out.println(e);
            return null;
        }

    }//ReturnKeywordAsJson2 끝
}
