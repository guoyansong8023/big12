import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

public class TestJson2 {
    @Test
    public void testJson(){
        String text = "{\"name\":\"tomas\"}";
        JSONObject jo = JSON.parseObject(text);
        Object name = jo.get("name");
        System.out.println(name);
    }

    @Test
    public void testJsonArray(){
        String line = "{\"person\":[{\"name\":\"tomas\",\"wife\":[\"marry\",\"jenny\"],\"age\":20},{\"name\":\"tom\",\"wife\":[\"marry\",\"jenny\"],\"age\":20}]}";
        JSONObject jo = JSON.parseObject(line);
        JSONArray jsonArray = jo.getJSONArray("person");
        for (Object object : jsonArray) {
            JSONObject jo2 = JSON.parseObject(object.toString());
            if (jo2.get("name").toString().equals("tom")){
                JSONArray ja2 = jo2.getJSONArray("wife");
                for (Object obj2 : ja2) {
                    System.out.println(obj2);
                }
            }
        }
    }

    @Test
    public void test() throws Exception {
        BufferedReader br = new BufferedReader(new FileReader("D:/json/temptags.txt"));
        String line = null;
        while((line = br.readLine()) != null){
            JSONObject jo = JSON.parseObject(line.split("\t")[1]);
        }
    }
}
