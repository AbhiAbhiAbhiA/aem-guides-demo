package com.adobe.aem.guides.demo.core.models;

import java.util.List;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ChildResource;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
//tab3 taask3 multifield part1
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class sidebarNavigation {
    @ValueMapValue
    private String desktopIcon;
    @ValueMapValue
    private String mobileIcon;
//nested
    @ChildResource
    private List<neste> nested;

    public String getDesktopIcon() {
        return desktopIcon;
    }

    public String getMobileIcon() {
        return mobileIcon;
    }

    public List<neste> getNested() {
        return nested;
    }
}
