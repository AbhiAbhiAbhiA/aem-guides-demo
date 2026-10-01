package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.*;
import org.apache.sling.models.annotations.*;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

import javax.annotation.PostConstruct;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class SingleArticle {

    @ValueMapValue(name = "enablePII")
    private boolean enablePII;

    @ValueMapValue(name = "cssClass")
    private String cssClass;

    @ChildResource(name = "jcr:content")
    private Resource contentNode;

    @PostConstruct
    protected void init() {
        if (!enablePII && contentNode != null) {
            ModifiableValueMap mvm = contentNode.adaptTo(ModifiableValueMap.class);
            if (mvm != null && mvm.containsKey("cssClass")) {
                mvm.remove("cssClass");
            }
        }
    }

    public boolean isEnablePII() {
        return enablePII;
    }

    public String getCssClass() {
        return cssClass;
    }
}
