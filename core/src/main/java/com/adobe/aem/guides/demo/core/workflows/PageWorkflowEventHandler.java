package com.adobe.aem.guides.demo.core.workflows;

//package com.adobe.aem.guides.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.adobe.granite.workflow.exec.WorkflowData;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/ADDED",
        EventConstants.EVENT_TOPIC + "=org/apache/sling/api/resource/Resource/CHANGED"
})
public class PageWorkflowEventHandler implements EventHandler {

    private static final Logger log = LoggerFactory.getLogger(PageWorkflowEventHandler.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void handleEvent(Event event) {
        String path = (String) event.getProperty("path"); // path from event
        log.info("Event received for path: {}", path);

        // Filter: only pages under /content
        if (path == null || !path.startsWith("/content/")) {
            log.info("Ignoring non-content path: {}", path);
            return;
        }

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Map.of(ResourceResolverFactory.SUBSERVICE, "res") // subservice
        )) {

            WorkflowSession wfSession = resolver.adaptTo(WorkflowSession.class);
            WorkflowModel wfModel = wfSession.getModel("/var/workflow/models/demo-workflow");

            WorkflowData wfData = wfSession.newWorkflowData("JCR_PATH", path);
            wfSession.startWorkflow(wfModel, wfData);

            log.info("Workflow started for payload: {}", path);

        } catch (Exception e) {
            log.error("Error starting workflow for payload: {}", path, e);
        }
    }
}
