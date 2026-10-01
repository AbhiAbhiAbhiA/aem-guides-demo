package com.adobe.aem.guides.demo.core.schedulers;
//tt12.0

//package com.adobe.aem.guides.demo.core.config;

//package com.adobe.aem.guides.demo.core.config;

import org.osgi.service.metatype.annotations.ObjectClassDefinition;
import org.osgi.service.metatype.annotations.AttributeDefinition;

@ObjectClassDefinition(name = "Employee raju Configuration")
public @interface EmployeeSchedulerConfig {

    @AttributeDefinition(name = "Employee Name")
    String name() default "raju";

    @AttributeDefinition(name = "Employee Path")
    String path() default "/content/demo/en";

    @AttributeDefinition(name = "Cron Expression")
    String expression() default "0/5 * * * * ?"; // every 5 seconds
}
