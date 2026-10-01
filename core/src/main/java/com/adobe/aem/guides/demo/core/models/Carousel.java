package com.adobe.aem.guides.demo.core.models;



import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Carousel {
    @ValueMapValue
    private String additionaltext;
    @ValueMapValue
    private String pathfield;
    
    @ChildResource
    private List<Carousel1> nestedmultifield;

    public String getAdditionaltext() {
        return additionaltext;
    }

    public String getPathfield() {
        return pathfield;
    }

    public List<Carousel1> getNestedmultifield() {
        return nestedmultifield;
    }

}

