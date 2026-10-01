package com.adobe.aem.guides.demo.core.schedulers;
//tt20.1

//package com.adobe.aem.guides.demo.core.schedulers;

//package com.adobe.aem.guides.demo.core.schedulers;

import org.apache.sling.commons.scheduler.ScheduleOptions;
import org.apache.sling.commons.scheduler.Scheduler;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Reference;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.metatype.annotations.Designate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = Runnable.class, immediate = true)
@Designate(ocd = EmployeeSchedulerConfig.class)
public class EmployeeScheduler implements Runnable {

    private static final Logger log = LoggerFactory.getLogger(EmployeeScheduler.class);

    @Reference
    private Scheduler scheduler;

    private String name;
    private String path;
    private String expression;

    @Activate
    protected void activate(EmployeeSchedulerConfig config) {
        this.name = config.name();
        this.path = config.path();
        this.expression = config.expression();

        ScheduleOptions options = scheduler.EXPR(this.expression);
        scheduler.schedule(this, options);
    }

    @Override
    public void run() {
        // Just log the values using 'this'
        log.info("Scheduler triggered | Name: {}, Path: {}, Cron: {}", this.name, this.path, this.expression);
    }
}
