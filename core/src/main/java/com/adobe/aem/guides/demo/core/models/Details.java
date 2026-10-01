package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//task1 header parent class
@Model(adaptables = { Resource.class,
        SlingHttpServletRequest.class }, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Details {
    @ValueMapValue
    private String textfield;
    @ValueMapValue
    private String pathfield;
    @ValueMapValue
    private String checkbox;
    // inject
    @ChildResource
    List<Detailschild> detailChild;

    // getter
    public String getTextfield() {
        return textfield;
    }

    public String getPathfield() {
        return pathfield;
    }

    public String getCheckbox() {
        return checkbox;
    }

    public List<Detailschild> getDetailChild() {
        return detailChild;
    }

}