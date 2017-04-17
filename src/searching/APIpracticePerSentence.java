package searching;

import org.json.JSONObject;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class APIpracticePerSentence {

    public static void main(String[] args) {

        // 1. 입력(txt파일 읽어오기)
        List<String> lyricList = new ArrayList<>();

        try {
            BufferedReader br = new BufferedReader(new FileReader("closer2.txt"));

            String tempLine = "";
            String tempLineForCheck = "";
            while ((tempLine = br.readLine()) != null) {

                if (tempLine.contains(".")) {
                    lyricList.add(tempLineForCheck + tempLine);
                    tempLineForCheck = "";
                } else {
                    tempLineForCheck = tempLine + " ";
                }

            }// while 끝

        } catch (FileNotFoundException fne) {
            fne.printStackTrace();
            System.out.println("파일이 존재하지 않습니다.");
        } catch (IOException ie) {
            ie.printStackTrace();
            System.out.println("IO 처리 과정에서 문제가 발생했습니다.");
        }

        for (String e : lyricList) {
            System.out.println(e);
        }

        // 2. papago를 통해 전체 가사 번역

        //오픈API ID, PW
        String clientId = "zYKOAUxRRPueChQC3b9b";//애플리케이션 클라이언트 아이디값";
        String clientSecret = "RoFsIfRyvz";//애플리케이션 클라이언트 시크릿값";

        try {
            for (String e : lyricList) {    // 가사 한 줄 씩 입력받기

                String source = e;

                String text = URLEncoder.encode(e, "UTF-8");
                String apiURL = "https://openapi.naver.com/v1/language/translate";

                URL url = new URL(apiURL);

                // head 설정
                HttpURLConnection con = (HttpURLConnection)url.openConnection();

                con.setRequestMethod("POST");
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

                if (responseCode == 200) {
                    br = new BufferedReader(new InputStreamReader(con.getInputStream()));
                } else {
                    br = new BufferedReader(new InputStreamReader(con.getErrorStream()));
                }

                String inputLine;
                StringBuffer response = new StringBuffer();

                while ((inputLine = br.readLine()) != null) {
                    response.append(inputLine);
                }

                br.close();
                //System.out.println(response.toString());

                // JSON Parsing
                String jsonData = response.toString();
                JSONObject obj = new JSONObject(jsonData);

                String result = obj.getJSONObject("message").getJSONObject("result").getString("translatedText");

                System.out.println(result);

            }
        } catch (Exception e) {
            System.out.println(e);
        }


    }// main 끝
}
