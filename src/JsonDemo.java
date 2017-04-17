import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class JsonDemo {

    public static void main(String[] args) {

        String source = "That we beat to death in Tucson";

        //오픈API ID, PW
        String clientId = "zYKOAUxRRPueChQC3b9b";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "RoFsIfRyvz";//애플리케이션 클라이언트 시크릿값";

        try {

            String text = URLEncoder.encode(source, "UTF-8");
            String apiURL = "https://openapi.naver.com/v1/language/translate";  // 뒤에 query X, body로 데이터 보낸다

            URL url = new URL(apiURL);

            // header 설정
            HttpURLConnection con = (HttpURLConnection)url.openConnection();

            con.setRequestMethod("POST");                                       // POST형식
            con.setRequestProperty("X-Naver-Client-Id", clientId);
            con.setRequestProperty("X-Naver-Client-Secret", clientSecret);

            // post request
            String postParams = "source=en&target=ko&text=" + text;             // source&target&text 로 3가지 parameter 전달, &로 구분

            con.setDoOutput(true);
            DataOutputStream wr = new DataOutputStream(con.getOutputStream());  // con.getOutputStream()이 응답을 받는 함수

            wr.writeBytes(postParams);
            wr.flush();
            wr.close();

            int responseCode = con.getResponseCode();
            BufferedReader br;

            if(responseCode==200) { // 정상 호출
                br = new BufferedReader(new InputStreamReader(con.getInputStream()));
            } else {  // 에러 발생
                br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
            }

            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = br.readLine()) != null) {
                response.append(inputLine);
            }

            br.close();
            System.out.println(response.toString());


            /*
             * Jason Parsing
             */
            String jsonData = response.toString();

            JSONObject obj = new JSONObject(jsonData);

            JSONObject msgObj = obj.getJSONObject("message");

            String type = msgObj.getString("@type");

            System.out.println(type);

            JSONObject resObj = msgObj.getJSONObject("result");
            String result = resObj.getString("translatedText");

            System.out.println(result);

            // 한방에
            String result2 = obj.getJSONObject("message")
                    .getJSONObject("result")
                    .getString("translated");

            System.out.println(result2);



        } catch (Exception e) {
            System.out.println(e);
        }

    }// main 끝
}


