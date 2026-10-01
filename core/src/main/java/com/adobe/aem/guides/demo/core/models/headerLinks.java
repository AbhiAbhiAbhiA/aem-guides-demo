package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//tab2 task3 multifield
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class headerLinks {
    @ValueMapValue
    private String name;
    @ValueMapValue
    private String image;

    public String getName() {
        return name;
    }

    public String getImage() {
        return image;
    }
}
