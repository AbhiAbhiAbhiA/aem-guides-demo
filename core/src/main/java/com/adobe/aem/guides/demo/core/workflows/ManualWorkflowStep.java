package com.adobe.aem.guides.demo.core.workflows;

//package com.adobe.aem.guides.demo.core.workflows;

import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(service = WorkflowProcess.class, immediate = true, property = { "process.label=ManualWorkflowStep" })
public class ManualWorkflowStep implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(ManualWorkflowStep.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) {
        String payload = workItem.getWorkflowData().getPayload().toString();
        log.info("Workflow Step executed for payload: {}", payload);

        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Map.of(ResourceResolverFactory.SUBSERVICE, "res"))) {
            Resource root = resolver.getResource(payload);

            if (root != null) {
                log.info("Root resource exists at payload: {}", payload);
                for (Resource child : root.getChildren()) {
                    log.info("Child resource found: {}", child.getPath());
                }
            } else {
                log.info("No resource found at path: {}", payload);
            }

        } catch (Exception e) {
            log.error("Error processing workflow step for payload: {}", payload, e);
        }
    }
}
