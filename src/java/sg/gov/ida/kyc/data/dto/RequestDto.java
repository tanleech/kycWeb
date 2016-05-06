/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data.dto;

import java.io.Serializable;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author root
 */
@Entity  
@Table(name= "request")  

public class RequestDto implements Serializable {
    
    @Id
    @Column(name = "reqId")
    private String reqId;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="owner")
    private BankDto owner;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="requester")
    private BankDto requester;
    
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="uid")
    private CustomerDto customer;

    public String getReqId() {
        return reqId;
    }

    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    public BankDto getOwner() {
        return owner;
    }

    public void setOwner(BankDto owner) {
        this.owner = owner;
    }

    public BankDto getRequester() {
        return requester;
    }

    public void setRequester(BankDto requester) {
        this.requester = requester;
    }


    public CustomerDto getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerDto customer) {
        this.customer = customer;
    }
    
    
    
    
}
