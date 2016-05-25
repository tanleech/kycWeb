/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import com.google.gson.Gson;
import java.util.Date;
import java.util.List;
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
import sg.gov.ida.kyc.util.DateUtil;

/**
 *
 * @author root
 */

public class CustomerService {
    
    
    public List<CustomerDto> all()
    {
       CustomerDAO dao = new CustomerDAO();
       List<CustomerDto>custList =  dao.all();
         
       return custList;
        
    }
    
    public void updateConsent(String uid, String consent, String owner, String reqId)
    {
        BankDAO bankDao = new BankDAO();
        BankDto requester = bankDao.findByKey(consent);
        BankDto own = bankDao.findByKey(owner);
        
        CustomerDAO custDao = new CustomerDAO();
        CustomerDto cust = custDao.findByKey(uid, own.getBankId());
        cust.setConsent(requester);
        Date current = new Date();
        cust.setLastupdate(current);
        custDao.updateCustomer(cust);
        
        RequestDAO request = new RequestDAO();
        System.out.println("delete req: "+reqId);
        request.delete(reqId);
    }
    
    
    public void createNew(String uid, String name, String address, String phone, String org)
    {
        CustomerDto custDO = new CustomerDto();
        custDO.setUid(uid);
        custDO.setName(name);
        custDO.setAddress(address);
        custDO.setPhone(phone);
        BankDAO bankDao = new BankDAO();
        BankDto orig = bankDao.findByKey(org);
        custDO.setConsent(orig);
        custDO.setOriginator(orig);
        Date date = new Date();
        custDO.setCreateDate(date);
        custDO.setLastupdate(date);
        //write to chain first
        try
        {
            writeToChain(custDO);
            CustomerDAO dao = new CustomerDAO();
            dao.createCustomer(custDO);
        }
        catch(Exception ex){
            ex.printStackTrace();
        }
    }
    
    private void writeToChain(CustomerDto cust)throws Exception
    {
                
        String url = "http://localhost:8081/records/";

	HttpClient client = HttpClientBuilder.create().build();
	HttpPost post = new HttpPost(url);
	// add header
	post.setHeader("User-Agent", USER_AGENT);
        Gson gson = new Gson();
        CustomerJson custJson = new CustomerJson();
        custJson.setCustomerId(cust.getUid());
        custJson.setCustomerName(cust.getName());
        custJson.setEncData("#############");
        custJson.setEncFor(cust.getConsent().getName());
        
        String timestamp = DateUtil.format("yyyyMMdd", cust.getCreateDate());
        custJson.setDateCreated(timestamp);
        custJson.setRecordOwner(cust.getOriginator().getName());
        
        StringEntity postingString = new StringEntity(gson.toJson(custJson));// converts your pojo to json
        System.out.println("jsonString: "+gson.toJson(custJson));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        
          
        HttpResponse  response = client.execute(post);
        
        System.out.println("status: "+response.getStatusLine().getStatusCode());
        System.out.println("resp: "+response);
        

    }
    
}
class CustomerJson
{
    private String CustomerId;
    private String CustomerName;
    private String RecordOwner;
    private String EncFor;
    private String EncData;
    private String DateCreated;

    public String getCustomerId() {
        return CustomerId;
    }

    public void setCustomerId(String CustomerId) {
        this.CustomerId = CustomerId;
    }

    public String getCustomerName() {
        return CustomerName;
    }

    public void setCustomerName(String CustomerName) {
        this.CustomerName = CustomerName;
    }

    public String getRecordOwner() {
        return RecordOwner;
    }

    public void setRecordOwner(String RecordOwner) {
        this.RecordOwner = RecordOwner;
    }

    public String getEncFor() {
        return EncFor;
    }

    public void setEncFor(String EncFor) {
        this.EncFor = EncFor;
    }

    public String getEncData() {
        return EncData;
    }

    public void setEncData(String EncData) {
        this.EncData = EncData;
    }

    public String getDateCreated() {
        return DateCreated;
    }

    public void setDateCreated(String DateCreated) {
        this.DateCreated = DateCreated;
    }


    
    


}