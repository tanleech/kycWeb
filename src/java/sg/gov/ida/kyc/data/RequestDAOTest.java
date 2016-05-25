/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

import java.util.List;
import sg.gov.ida.kyc.data.dto.RequestDto;

/**
 *
 * @author ftbs
 */
public class RequestDAOTest {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        RequestDAO dao = new RequestDAO();
        List<RequestDto> list =  dao.getIncomingRequests(2);
        for(int i=0;i<list.size();i++)
        {
            System.out.println("Req: "+list.get(i).getReqId());
            System.out.println("Owner: "+list.get(i).getOwner().getName());
            System.out.println("Req by: "+list.get(i).getRequester().getName());
        }
        
        
        System.exit(0);
    }
    
}