import javax.net.ssl.HttpsURLConnection;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-14.
 */
public class OpenAPISearch {

    public static void main(String[] args) {

        String clientId = "zYKOAUxRRPueChQC3b9b";   // App 클라이언트 아이디값
        String clientSecret = "RoFsIfRyvz";         // App 클라이언트 시크릿값

        try {

            String text = URLEncoder.encode("데이터과학", "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/search/blog?query=" + text;   // json 결과
            //String apiURL = "https://openapi.naver.com/v1/search/blog.xml?query="+ text; // xml 결과

            URL url = new URL(apiURL);

            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("GET");
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            int responseCode = con.getResponseCode();   // Gets the status code from an HTTP response message.

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

            System.out.println(response.toString());

        } catch (Exception e) {
            System.out.println(e);
        }

    }// main 값 끝
}
