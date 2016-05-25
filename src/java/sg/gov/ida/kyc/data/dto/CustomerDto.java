/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data.dto;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;


/**
 *
 * @author Michael Tan
 */
@Entity  
@Table(name= "customer")  
public class CustomerDto implements Serializable{
    
    @Id
    @Column(name = "uid")
    private String uid;
    @Column(name = "name")
    private String name;
    @Column(name="address")
    private String address;
    @Column(name="phone")
    private String phone;
    
    /*
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="pep")
    private PepStatusDto pepStatus;
    
    @Column(name="pep_reason")
    private String pepReason;
    */
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="originator")
    private BankDto originator;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="consent")
    private BankDto consent;
    
    @Column(name="createdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date createDate;
    
    @Column(name="lastupdate")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date lastupdate;

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }


    public Date getLastupdate() {
        return lastupdate;
    }

    public void setLastupdate(Date lastupdate) {
        this.lastupdate = lastupdate;
    }
    
    

    public BankDto getConsent() {
        return consent;
    }

    public void setConsent(BankDto consent) {
        this.consent = consent;
    }
    

    
    public BankDto getOriginator() {
        return originator;
    }

    public void setOriginator(BankDto originator) {
        this.originator = originator;
    }

    
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }


    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    
}
