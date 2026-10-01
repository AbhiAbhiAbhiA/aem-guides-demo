package com.adobe.aem.guides.demo.core.service;

//package com.adobe.aem.guides.demo.core.config;

import org.osgi.service.metatype.annotations.AttributeDefinition;//dxftgyhttttttttt
import org.osgi.service.metatype.annotations.ObjectClassDefinition;

@ObjectClassDefinition(name = "Employee Service Configuration")
public @interface EmployeeConfig {

    @AttributeDefinition(name = "Employee Name", description = "Name of the employee")
    String employeeName() default "abhiPakam";

    @AttributeDefinition(name = "City", description = "City of the employee")
    String city() default "Hyderabad";

    @AttributeDefinition(name = "Path", description = "Employee Path")
    String path() default "/content/demo/en";
}
