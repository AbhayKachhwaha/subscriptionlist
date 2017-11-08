
package com.subscriptionlist.taghandlers;

import com.prog32758.db.DBConnection;
import com.subscriptionlist.business.Subscription;
import com.subscriptionlist.dao.SubscriptionDAO;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.JspFragment;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class EmailListTag extends SimpleTagSupport {
    private Connection conn;

    /**
     * Called by the container to invoke this tag. The implementation of this
     * method is provided by the tag library developer, and handles all tag
     * processing, body iteration, etc.
     */
    @Override
    public void doTag() throws JspException {
        JspWriter out = getJspContext().getOut();
        
        try {
            
            //This custom tag will create a dynamic dropdown list of all email adds
            
            //Get the dbConnection from servlet context
            DBConnection dbConnection = (DBConnection) (getJspContext().getAttribute("dbConnection", PageContext.APPLICATION_SCOPE));
            conn = dbConnection.getConnection();
            SubscriptionDAO subscriptionDAO = new SubscriptionDAO();
            
            ArrayList<Subscription> subscriptions = subscriptionDAO.getAllSubscriptions(conn);
            
            //Use the jspWriter to display a dropdown list of all email adds
            
            out.print("<select name='email'>");
            //Loop through the list of subscription list and set each email address as an option
            for(int i=0; i<subscriptions.size();i++){
                Subscription sub = subscriptions.get(i);
                out.print("<option>");
                out.print(sub.getEmail());
                out.print("</option>");
            }
            out.print("</select>");
            
            JspFragment f = getJspBody();
            if (f != null) {
                f.invoke(out);
            }


        }
        catch (java.io.IOException ex) {
            throw new JspException("Error in EmailListTag tag", ex);
        }
    }
    
}
