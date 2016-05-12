/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import javax.json.Json;
import javax.json.JsonArray;
import javax.json.JsonObject;
import javax.json.JsonReader;
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
        
        String url = "http://192.168.0.200:8081/records/newRecs/20160501";

	HttpClient client = HttpClientBuilder.create().build();
	HttpGet post = new HttpGet(url);

	// add header
	post.setHeader("User-Agent", USER_AGENT);
        
        /*
	List urlParameters = new ArrayList();
	urlParameters.add(new BasicNameValuePair("sn", "C02G8416DRJM"));
	urlParameters.add(new BasicNameValuePair("cn", ""));
	urlParameters.add(new BasicNameValuePair("locale", ""));
	urlParameters.add(new BasicNameValuePair("caller", ""));
	urlParameters.add(new BasicNameValuePair("num", "12345"));
        
	post.setEntity(new UrlEncodedFormEntity(urlParameters));

        */
        
	HttpResponse response = client.execute(post);
	System.out.println("Response Code : " 
                + response.getStatusLine().getStatusCode());

        BufferedReader rd = new BufferedReader(
	        new InputStreamReader(response.getEntity().getContent()));
        
        /*
        JsonParser jsonParser = Json.createParser(rd);
        
        String keyName;
        String value="";
        while(jsonParser.hasNext())
        {
            Event event = jsonParser.next();
            System.out.println("event: "+event);
            switch (event) {
            case KEY_NAME:
                keyName = jsonParser.getString();
                System.out.println("key: "+keyName);
                break;
            case VALUE_STRING:
                value = jsonParser.getString();
                System.out.println("value: "+value);
                
                break;
            }
        }
*/
        JsonReader jsonReader = Json.createReader(rd);
        JsonObject jsonObject = jsonReader.readObject();
        System.out.println("Field1: "+jsonObject.getString("Records"));
        //System.out.println("Field2: "+jsonObject.getInt("numEntries"));
        
        
        jsonReader.close();
        rd.close();
        //JsonObject obj = jsonParser.;
        
    }
    
}
