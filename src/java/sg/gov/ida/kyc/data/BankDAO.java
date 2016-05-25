/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;


import sg.gov.ida.kyc.data.dto.CustomerDto;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;
import sg.gov.ida.kyc.data.dto.BankDto;

/**
 *
 * @author Mike
 */
public class BankDAO {
    
    public BankDto findByKey(String name)
    {
        Session session = null;
        BankDto org = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria criteria = session.createCriteria(BankDto.class)
            .add(Restrictions.eq("name", name));            
            Object result = criteria.uniqueResult();
            if(result!=null)
            {
                org = (BankDto) result;
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
        return org;

    }
    public List<CustomerDto> search(String name, String uid)
    {
        Session session = null;
        List custList = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria cr = session.createCriteria(CustomerDto.class);
            
            Criteria orig = cr.createCriteria("originator");
            Criteria consent = cr.createCriteria("consent");
            Criteria pep  = cr.createCriteria("pepStatus");
            
            // Add restriction.
            Criterion nameR=null,uidR=null;
            if(name!=null&& !name.isEmpty())
               nameR = Restrictions.like("name", "%"+name+"%");
            if(uid!=null&&!uid.isEmpty())
               uidR  = Restrictions.like("uid", "%"+uid+"%");
            // To get records matching with AND condistions
            if(nameR!=null&&uidR!=null)
            {
               LogicalExpression andExp = Restrictions.and(nameR, uidR);
               cr.add( andExp );
            }
            else if(nameR==null&&uidR!=null)
            {
               //System.out.println("adding uid R");
               cr.add(uidR);
            }
            else if(nameR!=null&&uidR==null)
            {
               //System.out.println("adding name R");
               cr.add(nameR);
            }
            
            custList = cr.list();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }
        return custList;
    }    
}
