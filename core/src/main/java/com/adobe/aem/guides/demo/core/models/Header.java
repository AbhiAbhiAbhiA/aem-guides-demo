package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//tab-1 task3
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Header {
    @ValueMapValue
    private String logoPath;
    @ValueMapValue
    private String logoMobileImage;
    @ValueMapValue
    private String logoLink;
    @ValueMapValue
    private String enableSwitch;

    @ChildResource
    private List<headerLinks> headerLinks;

    @ChildResource
    private List<sidebarNavigation> sidebarNavigation;

    public String getLogoPath() {
        return logoPath;
    }

    public String getLogoMobileImage() {
        return logoMobileImage;
    }

    public String getLogoLink() {
        return logoLink;
    }

    public String getEnableSwitch() {
        return enableSwitch;
    }

    public List<headerLinks> getHeaderLinks() {
        return headerLinks;
    }

    public List<sidebarNavigation> getSidebarNavigation() {
        return sidebarNavigation;
    }
}
