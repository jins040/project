package searching;


import org.json.JSONArray;
import org.json.JSONObject;
import searching.service.NaverApiAgent;

/**
 * Created by danawacomputer on 2017-04-17.
 */
public class SearchingViaNaverAPI {

    public static void main(String[] args) {

        String keyword = "자바라";

        /*
         * 1. 3번째 검색 결과의 bloggerlink 값 출력
         */
        String json = NaverApiAgent.ReturnKeywordAsJson(keyword);
        //System.out.println(json);

        JSONObject jsonObj = new JSONObject(json);

        JSONArray jsonArr = jsonObj.getJSONArray("items");

        JSONObject thridValue = jsonArr.getJSONObject(2);
        //System.out.println(thridValue);

        String result = thridValue.getString("bloggerlink");
        System.out.printf("3번째 검색결과의 bloggerlink값은 %s입니다.\n\n",result);


        /*
         * 2. 검색 결과를 100개로 가져와서 검색 결과의 bloggername값을 전체 출력
         */
        String json2 = NaverApiAgent.ReturnKeywordAsJson2(keyword, 100);
        //System.out.println(json2);

        JSONObject jsonobj2 = new JSONObject(json2);
        JSONArray jsonArr2 = jsonobj2.getJSONArray("items");

        for (int i=0 ; i<jsonArr2.length() ; i++) {
            JSONObject eachValue = jsonArr2.getJSONObject(i);
            String bloggerName = eachValue.getString("bloggername");
            System.out.printf("%d번 블로그 이름: %s\n", i+1, bloggerName);
        }
        //System.out.println(jsonArr2.length());

    }
}
