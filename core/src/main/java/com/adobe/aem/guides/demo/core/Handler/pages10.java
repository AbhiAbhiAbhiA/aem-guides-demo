package com.adobe.aem.guides.demo.core.Handler;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.model.WorkflowModel;

import org.apache.sling.api.SlingConstants;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.RepositoryException;
import java.util.HashMap;
import java.util.Map;

@Component(service = pages10.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
        EventConstants.EVENT_FILTER + "=(path=/content/demo/.*)"
})
public class pages10 implements EventHandler {
    private static final Logger LOG = LoggerFactory.getLogger(pages10.class);
    @Reference
    private ResourceResolverFactory resolverFactory;

    // @Reference
    // private com.adobe.granite.workflow.WorkflowService workflowService;

    public void handleEvent(Event event) {
        String path = (String) event.getProperty("Path");
        LOG.info("Event topic path{}", path);

        try {
            Map<String, Object> params = new HashMap<>();
            params.put(ResourceResolverFactory.SUBSERVICE, "workflow-service");
            try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(params)) {

                WorkflowSession wfSession = resolver.adaptTo(WorkflowSession.class);

                // Workflow model path (should exist in /var/workflow/models/)
                String modelPath = "/var/workflow/models/add-expiry-date";

                WorkflowModel wfModel = wfSession.getModel(modelPath);

                // Payload = page path
                WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", path);

                // Start workflow
                wfSession.startWorkflow(wfModel, wfData);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
