/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import com.google.gson.Gson;
import java.util.Date;
import static javax.ws.rs.core.HttpHeaders.USER_AGENT;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import sg.gov.ida.kyc.data.BankDAO;
import sg.gov.ida.kyc.data.CustomerDAO;
import sg.gov.ida.kyc.data.RequestDAO;
import sg.gov.ida.kyc.data.dto.BankDto;
import sg.gov.ida.kyc.data.dto.CustomerDto;
import sg.gov.ida.kyc.data.dto.RequestDto;
import sg.gov.ida.kyc.util.DateUtil;

/**
 *
 * @author root
 */

public class ConsentService {
    
    
    public void delete(String uid)
    {
        
    }
    public void createNew(String uid, String name, String owner, BankDto req)
    {
        RequestDto reqDto = new RequestDto();

        
        BankDAO bankDao = new BankDAO();
        BankDto recOwner = bankDao.findByKey(owner);
        reqDto.setOwner(recOwner);
        reqDto.setRequester(req);
        
        CustomerDAO custDao = new CustomerDAO();
        CustomerDto customer = custDao.findByKey(uid,recOwner.getBankId());
        reqDto.setCustomer(customer);
        Date date = new Date();
        reqDto.setCreateDate(date);
        reqDto.setReqId(uid+recOwner.getName());
        //write to chain first
        try
        {
            writeToChain(reqDto,uid);
            RequestDAO dao = new RequestDAO();
            dao.createRequest(reqDto);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void writeToChain(RequestDto req,String uid)throws Exception
    {
                
        String url = "http://localhost:8081/requests/";

	HttpClient client = HttpClientBuilder.create().build();
	HttpPost post = new HttpPost(url);
	// add header
	post.setHeader("User-Agent", USER_AGENT);
        Gson gson = new Gson();
        String timestamp = DateUtil.format("yyyyMMdd", req.getCreateDate());
        
        RequestJson reqJson = new RequestJson();
        reqJson.setDateCreated(timestamp);
        
        reqJson.setRecordId(req.getReqId());
        reqJson.setRequestBy(req.getRequester().getName());
        
        StringEntity postingString = new StringEntity(gson.toJson(reqJson));// converts your pojo to json
        System.out.println("jsonString: "+gson.toJson(reqJson));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        
         
        HttpResponse  response = client.execute(post);
        
        System.out.println("status: "+response.getStatusLine().getStatusCode());
        System.out.println("resp: "+response);
        

    }
    
}
class RequestJson
{
    private String RecordId;
    private String RequestBy;
    private String DateCreated;

    public String getRecordId() {
        return RecordId;
    }

    public void setRecordId(String RecordId) {
        this.RecordId = RecordId;
    }

    public String getRequestBy() {
        return RequestBy;
    }

    public void setRequestBy(String RequestBy) {
        this.RequestBy = RequestBy;
    }

    
    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String DateCreated) {
        this.DateCreated = DateCreated;
    }


    
    


}