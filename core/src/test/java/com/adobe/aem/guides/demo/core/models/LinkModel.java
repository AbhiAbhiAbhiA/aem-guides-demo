package com.adobe.aem.guides.demo.core.models;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class RegionTest {

    private final AemContext context = new AemContext();

    @Test
    void testGetCountry() {

        // Create resource with country property
        Resource resource = context.create().resource("/content/region",
                "country", "India");

        // Adapt to Sling Model
        Region region = resource.adaptTo(Region.class);

        // Assert getter
        assertEquals("India", region.getCountry());
    }



// package com.uefalcon.core.models;

// import com.tr.emcm.utils.EMCMHelper;
// import lombok.Getter;
// import org.apache.commons.lang3.StringUtils;
// import org.apache.sling.api.SlingHttpServletRequest;
// import org.apache.sling.models.annotations.DefaultInjectionStrategy;
// import org.apache.sling.models.annotations.Model;
// import org.apache.sling.models.annotations.injectorspecific.Self;
// import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

// @Model(adaptables = SlingHttpServletRequest.class, resourceType = LinkModel.RESOURSE_TYPE,
//     defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
// public class LinkModel {

//     public static final String RESOURSE_TYPE = "uefalcon/components/link";
//     private static final String REGEXP_HTML = "<(?:\"[^\"]*\"['\"]*|'[^']*'['\"]*|[^'\">])+>";
//     private static final String HTTP_PATH_PREFIX = "http://";
//     private static final String HTTPS_PATH_PREFIX = "https://";
//     private static final String PROTOCOL_AGNOSTIC_PATH_PREFIX = "//";

//     @Self
//     private SlingHttpServletRequest request;

//     @ValueMapValue
//     private String title;

//     @Getter
//     @ValueMapValue
//     private String content;

//     @ValueMapValue
//     private String href;

//     @Getter
//     @ValueMapValue
//     private String target;

//     @Getter
//     @ValueMapValue
//     private String downloadFileName;

//     @Getter
//     @ValueMapValue
//     private String variant;

//     @Getter
//     @ValueMapValue
//     private String tag;

//     @Getter
//     @ValueMapValue
//     private String tagColor;

//     public String getHref() {
//         return EMCMHelper.getHrefLink(href, request.getResource());
//     }

//     public String getTitle() {
//         if (StringUtils.isBlank(title)) {
//             return content
//                 .replaceAll(REGEXP_HTML, "")
//                 .replaceAll("\r", " ")
//                 .replaceAll("\n", " ")
//                 .trim();
//         }
//         return title;
    }

