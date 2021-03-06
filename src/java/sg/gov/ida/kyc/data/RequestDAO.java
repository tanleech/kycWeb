/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package sg.gov.ida.kyc.data;

import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import sg.gov.ida.kyc.data.dto.RequestDto;

/**
 *
 * @author root
 */
public class RequestDAO {
    
    public void createRequest(RequestDto reqDto){
        
        Session session = null;
        Transaction txn = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            txn = session.beginTransaction();
            
            session.save(reqDto);
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
    
    public void delete (String reqId)
    {
        Session session=null;
        Transaction txn = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            txn = session.beginTransaction();
            Query delQry = session.createQuery("DELETE FROM sg.gov.ida.kyc.data.dto.RequestDto req where req.reqId = :reqId");
            delQry.setParameter("reqId", reqId);
            delQry.executeUpdate();   
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
    
    public List<RequestDto> getIncomingRequests(int ownerId)
    {
        Session session = null;
        List reqList = null;
        try
        {
            session =  DaoDelegate.getInstance().create();
            Criteria cr = session.createCriteria(RequestDto.class,"request");
            cr.createAlias("request.owner", "owner");
            cr.add(Restrictions.eq("owner.bankId", ownerId));
           
            //Criteria owner = cr.createCriteria("owner");
            Criteria requester = cr.createCriteria("requester");
            Criteria customer  = cr.createCriteria("customer");
            reqList = cr.list();
        }
        catch (Exception ex)
        {
            ex.printStackTrace();
        }
        finally
        {
            DaoDelegate.getInstance().close(session);
        }
        return reqList;

    }
    
}
