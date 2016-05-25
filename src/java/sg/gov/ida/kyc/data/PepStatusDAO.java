/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

/**
 *
 * @author root
 */

import sg.gov.ida.kyc.data.dto.PepStatusDto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;

public class PepStatusDAO {
    
    public List<PepStatusDto> all()
    {
        Session session = null;
        List pepList = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria cr = session.createCriteria(PepStatusDto.class);
            pepList = cr.list();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }
        return pepList;

    }
    
}
