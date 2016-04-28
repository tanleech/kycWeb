/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

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
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="status_cd")
    private PepStatusDto pepStatus;
    
    @ManyToOne(cascade=CascadeType.MERGE)
    @JoinColumn(name="bankId")
    private BankDto bankDto;
    
    @Column(name="pep_reason")
    private String pepReason;


    public BankDto getBankDto() {
        return bankDto;
    }

    public void setBankDto(BankDto bankDto) {
        this.bankDto = bankDto;
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

    public PepStatusDto getPepStatus() {
        return pepStatus;
    }

    public void setPepStatus(PepStatusDto pepStatus) {
        this.pepStatus = pepStatus;
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

    public String getPepReason() {
        return pepReason;
    }

    public void setPepReason(String pepReason) {
        this.pepReason = pepReason;
    }

    
}
