package com.adobe.aem.guides.demo.core.workflows;

//package com.adobe.aem.guides.demo.core.workflows;

import com.adobe.granite.workflow.WorkflowException;
import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.WorkflowProcess;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import org.osgi.framework.Constants;
import org.osgi.service.component.annotations.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Node;
import javax.jcr.RepositoryException;
import javax.jcr.Session;
import java.util.Calendar;

@Component(service = WorkflowProcess.class, property = {
        Constants.SERVICE_DESCRIPTION + "=Workflow Step: Log cq:lastReplicated Date",
        "process.label=Log cq:lastReplicated Date"
})
public class LogLastReplicatedWorkflow implements WorkflowProcess {

    private static final Logger log = LoggerFactory.getLogger(LogLastReplicatedWorkflow.class);

    @Override
    public void execute(WorkItem workItem, WorkflowSession workflowSession, MetaDataMap args) throws WorkflowException {
        String pagePath = workItem.getWorkflowData().getPayload().toString();

        try {
            // Adapt workflowSession to JCR Session
            Session session = workflowSession.adaptTo(Session.class);

            Node node = (session != null && session.nodeExists(pagePath + "/jcr:content"))
                    ? session.getNode(pagePath + "/jcr:content")
                    : null;

            
            if (node != null && node.hasProperty("cq:lastReplicated")) {
                Calendar date = node.getProperty("cq:lastReplicated").getDate();
                log.info(" Page {} has cq:lastReplicated date: {}", pagePath, date.getTime());
            } else {
                log.warn(" Page {} does not have cq:lastReplicated property", pagePath);
            }

        } catch (RepositoryException e) {
            log.error("Error checking cq:lastReplicated for {}", pagePath, e);
        }
    }
}
