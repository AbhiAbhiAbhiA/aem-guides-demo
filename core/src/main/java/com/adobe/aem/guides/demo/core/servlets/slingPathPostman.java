package com.adobe.aem.guides.demo.core.servlets;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletPaths;
import org.osgi.service.component.annotations.Component;
@Component(service = Servlet.class,enabled = true,immediate = true)
@SlingServletPaths( value = "/apps/abhi/banglore")
public class slingPathPostman extends SlingAllMethodsServlet {
@Override
     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException { 
             response.getWriter().write("Path based Response coming from the path based-->GET");
     }
     @Override
     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException {    
                response.getWriter().write("Path based Response coming from the path based--->Post");
     }

     @Override
     protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException { 
                response.getWriter().write("Path based Response coming from the path based--->Put");
     }

     @Override
     protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException {
       
                response.getWriter().write("Path based Response coming from the path based-->Deleter");
             }
}
