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
import org.hibernate.Transaction;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Mike
 */
public class CustomerDAO {
    
    public void updateCustomer(CustomerDto cust)
    {
        java.util.Date current = new java.util.Date();
        Session session = null;
        Transaction txn = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            txn = session.beginTransaction();
            session.saveOrUpdate(cust);
            /*
            org.hibernate.Query qry = session.createQuery("UPDATE sg.gov.ida.kyc.data.dto.CustomerDto cust SET cust.consent=:consent, cust.lastupdate=:lastupdate WHERE cust.uid=:uid");
            qry.setParameter("uid", uid);
            qry.setParameter("consent", consent);
            qry.setParameter("lastupdate", current);
            qry.executeUpdate();
            */
            txn.commit();
            
        }catch (Exception ex)
        {
            if(txn!=null)
            {
                txn.rollback();
            }
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }

    }
    public void createCustomer(CustomerDto custDto){
        Session session = null;
        Transaction txn = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            txn = session.beginTransaction();
            
            session.save(custDto);
            txn.commit();
            
        }catch (Exception ex)
        {
            if(txn!=null)
            {
                txn.rollback();
            }
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }
    }
    
    public List<CustomerDto> all()
    {
        Session session = null;
        List custList = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria cr = session.createCriteria(CustomerDto.class);
            
            Criteria bank = cr.createCriteria("originator");
            Criteria consent = cr.createCriteria("consent");
            //Criteria pep  = cr.createCriteria("pepStatus");
            
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
    
    public CustomerDto findByKey(String uid, int originator)
    {
        Session session = null;
        CustomerDto cust = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria criteria = session.createCriteria(CustomerDto.class)
            .add(Restrictions.eq("uid", uid));            
            
             Criteria orig = criteria.createCriteria("originator");
             Criteria consent = criteria.createCriteria("consent");
             
             //Criteria pep  = criteria.createCriteria("pepStatus");
           
            Object result = criteria.uniqueResult();
            if(result!=null)
            {
                cust = (CustomerDto) result;
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
        return cust;

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
           //Criteria pep  = cr.createCriteria("pepStatus");
            
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
