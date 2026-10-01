package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//task1 header child class
@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Detailschild {
    @ValueMapValue
    private String text;
    @ValueMapValue
    private String datepicker;
//getter
    public String getText() {
        return text;
    }

    public String getDatepicker() {
        return datepicker;
    }
}