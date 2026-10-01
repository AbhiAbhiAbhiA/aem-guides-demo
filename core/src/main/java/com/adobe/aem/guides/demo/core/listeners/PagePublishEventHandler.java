package com.adobe.aem.guides.demo.core.listeners;

import org.apache.sling.api.resource.*;
import org.osgi.service.component.annotations.*;
import org.osgi.service.event.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

@Component(service = EventHandler.class, immediate = true, property = {
        EventConstants.EVENT_TOPIC + "=com/day/cq/replication",
        EventConstants.EVENT_FILTER + "=(replication.action=Activate)"
})
public class PagePublishEventHandler implements EventHandler {

    private static final Logger LOG = LoggerFactory.getLogger(PagePublishEventHandler.class);

    @Reference
    private ResourceResolverFactory resolverFactory;

    @Override
    public void handleEvent(Event event) {
        String path = (String) event.getProperty("path");

        if (path != null && path.startsWith("/content")) {
            LOG.info("Page published: {}", path);

            try {
                Map<String, Object> authInfo = Map.of(
                        ResourceResolverFactory.SUBSERVICE, "writeService");

                try (ResourceResolver resolver = resolverFactory.getServiceResourceResolver(authInfo)) {
                    Resource resource = resolver.getResource(path + "/jcr:content");

                    if (resource != null) {
                        ModifiableValueMap properties = resource.adaptTo(ModifiableValueMap.class);
                        if (properties != null) {
                            properties.put("changed", true);
                            resolver.commit();
                            LOG.info("Property 'changed=true' added at: {}/jcr:content", path);
                        }
                    }
                }
            } catch (Exception e) {
                LOG.error("Error updating 'changed' property on published page", e);
            }
        }
    }
}
