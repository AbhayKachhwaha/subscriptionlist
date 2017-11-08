
package com.subscriptionlist.sevlets;

import com.prog32758.db.DBConnection;
import com.subscriptionlist.business.Subscription;
import com.subscriptionlist.dao.SubscriptionDAO;
import java.io.IOException;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GetAllSubscriptionsServlet extends HttpServlet {
       
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Get the DBConnection stored in ServletContext
        ServletContext sc = this.getServletContext();
        DBConnection dbConnection =  (DBConnection) sc.getAttribute("dbConnection");
        Connection conn = dbConnection.getConnection();
        
        //Use subscriptionDAO to get all subscriptions
        SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
        ArrayList<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions(conn);
        
        //Store the arraylist as a requst attr so that the jsp we forward to cam access it
        request.setAttribute("subscriptions", subscriptions);
        
        //forward the request to subscriptionlist.jsp
        RequestDispatcher rd = request.getRequestDispatcher("subscriptionlist.jsp");
        rd.forward(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        // Have the Servlet do the same thing as HTTP GET requests 
        doGet(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
