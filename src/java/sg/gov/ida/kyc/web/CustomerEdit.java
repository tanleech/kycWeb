package sg.gov.ida.kyc.web;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import sg.gov.ida.kyc.data.dto.EmployeeDto;
import sg.gov.ida.kyc.service.CustomerService;

/**
 *
 * @author ftbs
 */
@WebServlet(urlPatterns = {"/customerEdit"})
public class CustomerEdit extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String page = "customer.jsp";
        String uid = request.getParameter("uid");
        String action = request.getParameter("action");
        if(uid!=null&&!uid.isEmpty())
        {
            if(action!=null&&action.equals("A"))
            {
                String reqby = request.getParameter("reqby");
                String owner = request.getParameter("owner");
                String reqId = request.getParameter("reqId");
                CustomerService custService = new CustomerService();
                custService.updateConsent(uid, reqby, owner,reqId);
                //custService.createNew(uid, name, address, phone,emp.getBank().getName());
                page="/messageList";
                
            }
            else
            {
                HttpSession session = request.getSession();
                EmployeeDto emp = (EmployeeDto)session.getAttribute("login");
                String name = request.getParameter("name");
                String address = request.getParameter("address");
                String phone = request.getParameter("phone");
                CustomerService custService = new CustomerService();
                custService.createNew(uid, name, address, phone,emp.getBank().getName());
                page="/customerList";
            }    
        }
        
        RequestDispatcher rd = request.getRequestDispatcher(page);
        rd.forward(request, response);

    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
