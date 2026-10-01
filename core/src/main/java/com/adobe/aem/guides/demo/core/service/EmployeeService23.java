package com.adobe.aem.guides.demo.core.service;

//package com.adobe.aem.guides.demo.core.service;

import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.metatype.annotations.Designate;

@Component(service = EmployeeService23.class, immediate = true)
@Designate(ocd = EmployeeConfig.class)
public class EmployeeService23 {

    private String name;
    private String city;
    private String path;
    // private String designation;

    @Activate
    protected void activate(EmployeeConfig config) {// ttt2
        this.name = config.employeeName();
        this.city = config.city();
        this.path = config.path();
        // this.designation = config.designation();
    }

    public String getName() {
        return this.name;
    }

    public String getCity() {
        return this.city;
    }

    public String getPath() {
        return this.path;
    }

    //public String getDesignation() {
    //return this.designation;
    //}
}
