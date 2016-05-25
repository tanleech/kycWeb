/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

import sg.gov.ida.kyc.data.dto.CustomerDto;
import java.util.List;

/**
 *
 * @author ftbs
 */
public class CustomerDAOTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        /*
        CustomerDto dto = new CustomerDto();
        dto.setUid("S9503806J");
        dto.setName("Marvin");
        dto.setAddress("Bukit Timah");
        dto.setPhone("62111210");
        CustomerDAO dao = new CustomerDAO();
        dao.createCustomer(dto);
        System.exit(0);
                */
        CustomerDAO dao = new CustomerDAO();
        List<CustomerDto> list =  dao.search("MI", "");
        for(int i=0;i<list.size();i++)
        {
            System.out.println("Name: "+list.get(i).getName());
          //  System.out.println("PEP: "+list.get(i).getPepStatus().getDescription());
            System.out.println("Owned by: "+list.get(i).getOriginator().getName());
        }
        
        
        System.exit(0);
    }
    
}