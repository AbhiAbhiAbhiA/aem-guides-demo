package com.adobe.aem.guides.demo.core.schedulers;

//package com.adobe.aem.guides.demo.core.schedulers;

import com.day.cq.replication.Replicator;
import com.day.cq.replication.ReplicationActionType;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.api.resource.ResourceResolverFactory;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.*;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jcr.Session;
import java.util.Map;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = ReplicateSchedulerConfig.class)
public class ReplicateScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(ReplicateScheduler.class);

    @Reference
    private Scheduler scheduler;

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Reference
    private Replicator replicator;

    private String schedulerName;
    private String cronExpression;
    private String replicationPath;

    @Activate
    @Modified
    protected void activate(ReplicateSchedulerConfig config) {
        this.schedulerName = config.schedulerName();
        this.cronExpression = config.cronExpression();
        this.replicationPath = config.replicationPath();

        // Schedule the task using cron expression
        scheduler.schedule(this, scheduler.EXPR(cronExpression));
        log.info("Scheduler {} activated with cron: {} for path: {}", schedulerName, cronExpression, replicationPath);
    }

    @Override
    public void run() {
        try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(
                Map.of(ResourceResolverFactory.SUBSERVICE, "res"))) {

            Resource root = resolver.getResource(replicationPath);
            if (root != null) {
                for (Resource child : root.getChildren()) {
                    replicator.replicate(resolver.adaptTo(Session.class),
                            ReplicationActionType.ACTIVATE, child.getPath());
                    log.info("Replicated page: {}", child.getPath());//replicate
                }
            } else {
                log.info("No pages found at path: {}", replicationPath);//no pagess
            }

        } catch (Exception e) {
            log.error("Error in scheduler {}", schedulerName, e);
        }
    }
}
