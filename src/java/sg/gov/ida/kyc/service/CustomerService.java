/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.service;

import java.util.List;
import sg.gov.ida.kyc.data.CustomerDAO;
import sg.gov.ida.kyc.data.RequestDAO;
import sg.gov.ida.kyc.data.dto.CustomerDto;
import sg.gov.ida.kyc.data.dto.RequestDto;

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

    
}
