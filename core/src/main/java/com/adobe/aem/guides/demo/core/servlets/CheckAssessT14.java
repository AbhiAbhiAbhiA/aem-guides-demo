package com.adobe.aem.guides.demo.core.servlets;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;
import org.apache.sling.api.servlets.SlingAllMethodsServlet;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.jackrabbit.api.security.user.Authorizable;
import org.apache.jackrabbit.api.security.user.Group;
import org.apache.jackrabbit.api.security.user.UserManager;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.Servlet;
import javax.jcr.RepositoryException;
import javax.servlet.ServletException; //task14
import java.io.IOException;

@Component(service = Servlet.class, property = {
        "sling.servlet.paths=/bin/granite/security/content/v2/groupeditor.html/home/groups/3/3Aihh7AMaBCpgXHiaMm0",
        "sling.servlet.methods=GET"
})
public class CheckAssessT14 extends SlingAllMethodsServlet {

    private static final Logger LOG = LoggerFactory.getLogger(CheckAssessT14.class);

    @Reference
    private ResourceResolverFactory resourceResolverFactory;

    @Override
    protected void doGet(SlingHttpServletRequest request, SlingHttpServletResponse response)
            throws ServletException, IOException {

        LOG.info("Task14 Servlet invoked.");

        ResourceResolver resourceResolver = null;

        try {
            // Get the current ResourceResolver
            resourceResolver = request.getResourceResolver();
            LOG.info("ResourceResolver obtained successfully.");

            // Get the UserManager from the ResourceResolver
            UserManager userManager = resourceResolver.adaptTo(UserManager.class);

            if (userManager != null) {
                LOG.info("UserManager adapted successfully from ResourceResolver.");

                // Get the current user ID from the session
                String userId = resourceResolver.getUserID();
                LOG.info("Current user ID: {}", userId);

                // Get the user as an Authorizable object
                Authorizable user = userManager.getAuthorizable(userId);

                // Get the "access" group as an Authorizable object
                Authorizable group = userManager.getAuthorizable("asus");

                if (user != null && group != null && group.isGroup()) {
                    LOG.info("User and group 'surge' found successfully.");

                    // Check if the user belongs to the "access" group
                    boolean hasAccess = ((Group) group).isMember(user);
                    LOG.info("User {} membership status in 'surge' group: {}", userId, hasAccess);

                    // Respond with the appropriate message
                    if (hasAccess) {
                        response.getWriter().write("User " + userId + " has access.");
                        LOG.info("Access granted to user: {}", userId);
                    } else {
                        response.getWriter().write("User " + userId + " doesn't have access.");
                        LOG.warn("Access denied for user: {}", userId);
                    }
                } else {
                    response.getWriter().write("Error: Group 'asus' or User not found.");
                    LOG.error("Group 'surge' or user not found.");
                }
            } else {
                response.getWriter().write("Error: Unable to get UserManager.");
                LOG.error("Unable to adapt UserManager from ResourceResolver.");
            }
        } catch (RepositoryException e) {
            response.getWriter().write("Error: " + e.getMessage());
            LOG.error("RepositoryException encountered: ", e);
        } finally {
            if (resourceResolver != null && resourceResolver.isLive()) {
                resourceResolver.close();
                LOG.info("ResourceResolver closed.");
            }
        }
    }
}