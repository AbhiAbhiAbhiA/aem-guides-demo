package com.adobe.aem.guides.demo.core.servlets;
import java.io.IOException;
import javax.servlet.Servlet;
import javax.servlet.ServletException;
import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.servlets.annotations.SlingServletResourceTypes;
import org.osgi.service.component.annotations.Component;
@Component(service=Servlet.class,immediate=true,enabled = true)
@SlingServletResourceTypes(resourceTypes="abhi/banglore")
public class PraticePostman extends SlingAllMethodsServlet {
  @Override
     protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException { 
             response.getWriter().write("Resource based Response coming from the path based-->GET");
     }
     @Override
     protected void doPost(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException {    
                response.getWriter().write("Resource based Response coming from the path based--->Post");
     }

     @Override
     protected void doPut(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException { 
                response.getWriter().write("Resource based Response coming from the path based---?Put");
     }

     @Override
     protected void doDelete(SlingHttpServletRequest request, SlingHttpServletResponse response)
             throws ServletException, IOException {
       
                response.getWriter().write("Resource based Response coming from the path based-->Deleter");
             }
}
