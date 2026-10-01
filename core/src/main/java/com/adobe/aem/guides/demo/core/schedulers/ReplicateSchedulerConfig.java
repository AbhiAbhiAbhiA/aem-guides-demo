package com.adobe.aem.guides.demo.core.schedulers;

//package com.adobe.aem.guides.demo.core.schedulers;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "Replicate Scheduler Config", description = "Config to replicate all /content/en pages automatically")
public @interface ReplicateSchedulerConfig {

    @AttributeDefinition(name = "Scheduler Name")
    String schedulerName() default "ReplicateScheduler";

    @AttributeDefinition(name = "Cron Expression")
    String cronExpression() default "0/5 * * * * ?"; // every 5 seconds

    @AttributeDefinition(name = "Replication Path")
    String replicationPath() default "/content/Demo/us/en";
}
