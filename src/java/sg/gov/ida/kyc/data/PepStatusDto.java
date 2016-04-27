/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Mike
 */
@Entity  
@Table(name= "pep_status")
public class PepStatusDto {
    
    @Id
    @Column(name="status_cd")
    private String status_cd;

    @Column(name="description")
    private String description;

    public String getStatus_cd() {
        return status_cd;
    }

    public void setStatus_cd(String status_cd) {
        this.status_cd = status_cd;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
