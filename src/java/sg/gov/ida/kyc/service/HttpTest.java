/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;

/**
 *
 * @author root
 */
public class HttpTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        // TODO code application logic here
        
        String url = "http://localhost:8081/records/newRecs/20160501";

	HttpClient client = HttpClientBuilder.create().build();
	HttpGet post = new HttpGet(url);

	// add header
	post.setHeader("User-Agent", USER_AGENT);
        
	HttpResponse response = client.execute(post);
	System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
	        new InputStreamReader(response.getEntity().getContent()));
        
        //System.out.println("resp: "+rd.readLine());
        JsonParser parser = new JsonParser();
        Gson gson = new Gson();
        String json=rd.readLine();
                //"{\"Records\":\"{\"numEntries\":3,\"records\":[{\"recId\":\"S0000001ADBSUOB\",\"custId\":\"S0000001A\",\"custName\":\"Aladdin\",\"recOwner\":\"DBS\",\"encFor\":\"UOB\",\"encData\":\"LALALALALA\",\"dateCreated\":\"20160505\",\"dateModified\":\"20160505\"},{\"recId\":\"S0000001ADBSDBS\",\"custId\":\"S0000001A\",\"custName\":\"Aladdin\",\"recOwner\":\"DBS\",\"encFor\":\"DBS\",\"encData\":\"Xas8QPA7bB8ff7LwHfBMs82Cv8rJS5vPUwxHyNq77tbO9bc\",\"dateCreated\":\"20160501\",\"dateModified\":\"20160501\"},{\"recId\":\"S9516193FBAMLBAML\",\"custId\":\"S9516193F\",\"custName\":\"Marvin Chin\",\"recOwner\":\"BAML\",\"encFor\":\"BAML\",\"encData\":\"Marvin's info encryted for BAML\",\"dateCreated\":\"20160512\",\"dateModified\":\"20160512\"}]}\"}";
        
        JsonElement element = parser.parse(json);
        //JsonArray array = parser.parse(json).getAsJsonArray();
        //String message = gson.fromJson(array.get(0), String.class);
        //int number = gson.fromJson(array.get(1), int.class);
        String rec =  element.getAsJsonObject().get("Records").getAsString();
        JsonElement inner = parser.parse(rec);
        JsonArray array = inner.getAsJsonObject().get("records").getAsJsonArray();
        int num = inner.getAsJsonObject().get("numEntries").getAsInt();
        //String message = gson.fromJson(array.get(2), String.class);
        for(int i=0;i<array.size();i++){
            System.out.println("recId:"  +array.get(i).getAsJsonObject().get("recId"));
            System.out.println("custId:"  +array.get(i).getAsJsonObject().get("custId"));
            System.out.println("custName:"  +array.get(i).getAsJsonObject().get("custName"));
            System.out.println("recOwner:"  +array.get(i).getAsJsonObject().get("recOwner"));
            System.out.println("encFor:"  +array.get(i).getAsJsonObject().get("encFor"));
            System.out.println("encData:"  +array.get(i).getAsJsonObject().get("encData"));
            
            
        }
        //System.out.println("Using Gson.fromJson() to get:"  +array.get(2).getAsJsonObject().get("custName"));

        
        rd.close();
        //JsonObject obj = jsonParser.;
        
    }
    
}
