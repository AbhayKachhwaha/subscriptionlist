
package com.subscriptionlist.listeners;

import com.prog32758.db.DBConnection;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;


public class DBConnectionListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        //Getting db config from web.xml
        //Getting the context from servlet context event ^right above 
        ServletContext sc = sce.getServletContext();
        String driver = sc.getInitParameter("driver");
        String url = sc.getInitParameter("url");
        String database = sc.getInitParameter("database");
        String user = sc.getInitParameter("user");
        String password = sc.getInitParameter("password");
        
        //creating instance for dbconnection
        DBConnection dbConnection = new DBConnection(driver, url, database, user, password);
        
        //Store a dbConnection obj as a servletContext attribute 
        sc.setAttribute("dbConnection", dbConnection);
        
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        
    }
}
