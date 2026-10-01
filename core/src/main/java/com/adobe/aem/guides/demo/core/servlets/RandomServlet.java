package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.osgi.service.component.annotations.Component;

import javax.servlet.Servlet;
import javax.servlet.ServletException;
import java.io.IOException;
import java.util.Random;

import static org.apache.sling.api.servlets.ServletResolverConstants.SLING_SERVLET_PATHS;

//task5
@Component(service = Servlet.class, property = {
    
        SLING_SERVLET_PATHS + "=/bin/generate/ab/random"
})
public class RandomServlet extends SlingAllMethodsServlet {

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        String type = request.getParameter("type"); // expected: Number / Letters / Random
        String result = "";

        if ("Number".equalsIgnoreCase(type)) {
            result = generateRandomNumbers(6);// Number
        } else if ("Letters".equalsIgnoreCase(type)) {
            result = generateRandomLetters(6);// Letters http://localhost:4502/bin/generate/abhi/random?type=Letters
        } else if ("Random".equalsIgnoreCase(type)) {
            result = generateRandomNumbers(3) + generateRandomLetters(3);// Random
        } else {
            result = "Invalid parameter. Use: Number, Letters, or Random";
        }

        response.setContentType("text/plain");
        response.getWriter().write(result);
    }

    private String generateRandomNumbers(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10)); // 0 to 9
        }
        return sb.toString();
    }

    private String generateRandomLetters(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        String alphabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        for (int i = 0; i < length; i++) {
            sb.append(alphabet.charAt(random.nextInt(alphabet.length())));
        }
        return sb.toString();
    }
}
