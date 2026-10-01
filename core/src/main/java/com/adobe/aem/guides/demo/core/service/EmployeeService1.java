package com.adobe.aem.guides.demo.core.service;


//package com.adobe.aem.guides.demo.core.service;

//import com.adobe.aem.guides.demo.core.config.EmployeeConfig;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Modified;

@Component(service = EmployeeService1.class, immediate = true)
public class EmployeeService1 {

    private String name;
    private String city;
    private String path;

    @Activate
    @Modified
    protected void activate(EmployeeConfig config) {
        name = config.employeeName();
        city = config.city();
        path = config.path();
    }

    public String getName() {
        return name;
    }

    public String getCity() {
        return city;
    }

    public String getPath() {
        return path;
    }
}
