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
import sg.gov.ida.kyc.data.CustomerDAO;
import sg.gov.ida.kyc.data.CustomerDto;
import sg.gov.ida.kyc.data.EmployeeDto;

/**
 *
 * @author ftbs
 */
@WebServlet(urlPatterns = {"/customerView"})
public class CustomerView extends HttpServlet {

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
        String uid = request.getParameter("uid");
        String consent = request.getParameter("consent");
        
        //set the attribute
        /*
        request.setAttribute("uid", uid);
        request.setAttribute("orig", orig);
        */
        HttpSession session = request.getSession();
        EmployeeDto emp = (EmployeeDto)session.getAttribute("login");
        int permission = Integer.parseInt(consent);
        String page = "consent.jsp";
        if(permission == emp.getBank().getBankId())
        {
            CustomerDAO custDAO = new CustomerDAO();
            CustomerDto cust = custDAO.findByKey(uid, permission);
            request.setAttribute("cust", cust);
            page = "customer.jsp";
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
