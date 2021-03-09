package run.kabuctl.demo;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.io.FileReader;

public class GetMysqlCreds {
    public String[] getCreds() throws Exception {
        JSONParser parser = new JSONParser();
        Object credsObject = parser.parse(new FileReader("/Users/kabu/hashicorp/vault/vault-agent-template-demo/agent-config/render-mysql.txt"));
        JSONObject credsJson = (JSONObject) credsObject;
        JSONObject dataJson = (JSONObject) credsJson.get("Data");
        String password = dataJson.get("password").toString();
        String username = dataJson.get("username").toString();
        String creds[] = { username, password };

        System.out.println("Username = " + creds[0] + " & Password = " + creds[1]);

        return creds;
    }
}
