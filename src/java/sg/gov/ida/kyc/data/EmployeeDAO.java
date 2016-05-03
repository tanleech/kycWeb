/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

import java.util.List;
import org.hibernate.Criteria;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author root
 */
public class EmployeeDAO {
    
    public EmployeeDto search(String id)
    {
        Session session = null;
        EmployeeDto emp = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria criteria = session.createCriteria(EmployeeDto.class)
            .add(Restrictions.eq("id", id));            
            Criteria bank = criteria.createCriteria("bank");
            
            Object result = criteria.uniqueResult();
            if(result!=null)
            {
                emp = (EmployeeDto) result;
            }            
            
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }
        return emp;
    }    
}
