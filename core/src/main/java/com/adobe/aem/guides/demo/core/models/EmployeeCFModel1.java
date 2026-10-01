package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class EmployeeCFModel1 {

    @ValueMapValue(name = "cfPath")
    private String cfPath;

    @ValueMapValue(name = "variationName")
    private String variationName;

    @ValueMapValue
    private ResourceResolver resourceResolver;

    private String name = "";
    private String age = "";
    private String salary = "";
    private String domain = "";
    private String company = "";

    @PostConstruct
    protected void init() {
        if (cfPath == null)
            return;

        Resource cfRes = resourceResolver.getResource(cfPath);
        if (cfRes == null)
            return;

        Resource selected = cfRes.getChild("jcr:content");

        if (variationName != null && !variationName.equals("master")) {
            Resource varRes = cfRes.getChild("jcr:content/variations/" + variationName);
            if (varRes != null)
                selected = varRes;
        }

        if (selected != null && selected.getChild("elements") != null) {
            Resource elements = selected.getChild("elements");
            name = getValue(elements, "name");
            age = getValue(elements, "age");
            salary = getValue(elements, "salary");
            domain = getValue(elements, "domain");
            company = getValue(elements, "company");
        }
    }

    private String getValue(Resource elements, String field) {
        Resource el = elements.getChild(field);
        if (el != null) {
            Object val = el.getValueMap().get("value");
            if (val != null)
                return val.toString();
        }
        return "";
    }

    public String getName() {
        return name;
    }

    public String getAge() {
        return age;
    }

    public String getSalary() {
        return salary;
    }

    public String getDomain() {
        return domain;
    }

    public String getCompany() {
        return company;
    }
}
