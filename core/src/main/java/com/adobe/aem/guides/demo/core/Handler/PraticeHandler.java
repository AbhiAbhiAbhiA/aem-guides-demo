package com.adobe.aem.guides.demo.core.Handler;



import org.apache.sling.api.SlingConstants;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.event.Event;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component(service = EventHandler.class,immediate = true,
property={EventConstants.EVENT_TOPIC+"=org/apache/sling/api/resource/Resource/*"})
public class PraticeHandler implements EventHandler{

    private static final Logger LOG =LoggerFactory.getLogger(PraticeHandler.class);
    @Override
    public void handleEvent(Event event) {
        
        LOG.info("Event topic{},path{}",event.getTopic(),event.getProperty(SlingConstants.PROPERTY_PATH));
    }

}
