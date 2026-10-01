package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;

//tab4 task4
@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)
public class Region {

    @ValueMapValue
    private String country;

    public String getCountry() {
        return country;
    }



// package com.uefalcon.core.models;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.mockito.Mockito.mockStatic;

// import java.time.LocalDateTime;
// import java.util.HashMap;
// import java.util.Map;

// import com.day.cq.commons.Externalizer;
// import com.tr.emcm.utils.EMCMHelper;
// import io.wcm.testing.mock.aem.junit5.AemContext;
// import io.wcm.testing.mock.aem.junit5.AemContextExtension;
// import org.apache.sling.api.resource.Resource;
// import org.apache.sling.api.resource.ResourceResolver;
// import org.apache.sling.testing.mock.sling.ResourceResolverType;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;
// import org.mockito.Mock;
// import org.mockito.MockedStatic;
// import org.mockito.junit.jupiter.MockitoExtension;

// @ExtendWith({AemContextExtension.class, MockitoExtension.class})
// class LinkModelTest {

//     private final AemContext context = new AemContext(ResourceResolverType.JCR_MOCK);
//     LinkModel link = null;
//     String title = "title test";
//     String content = "content test";
//     String html = ".html";
//     String href = "/content/href-test";
//     String absolutePath = "http://localhost:4502" + href + html;
//     String externalPath = "www.external.com";
//     String target = "target test";
//     String downloadFileName = "download file name test";
//     String variant = "variant test";
//     String tag = "tag test";
//     String tagColor = "tag color test";
//     @Mock
//     Externalizer mockExternalizer;

//     @BeforeEach
//     void setUp() {
//         context.registerAdapter(ResourceResolver.class, Externalizer.class, mockExternalizer);
//         context.addModelsForClasses(LinkModel.class);
//         Map<String, Object> properties = new HashMap<>();
//         properties.put("sling:resourceType", LinkModel.RESOURSE_TYPE);
//         properties.put("jcr:primaryType", "nt:unstructured");
//         properties.put("jcr:created", LocalDateTime.now());
//         properties.put("jcr:createdBy", "devUmvel");
//         properties.put("title", title);
//         properties.put("content", content);
//         properties.put("href", href);
//         properties.put("target", target);
//         properties.put("downloadFileName", downloadFileName);
//         properties.put("variant", variant);
//         properties.put("tag", tag);
//         properties.put("tagColor", tagColor);

//         Resource resource = context.create().resource("/content/tr", properties);
//         context.currentResource(resource);
//     }

//     @Test
//     void gettersTest() {
//         try (MockedStatic<EMCMHelper> mockedHelper = mockStatic(EMCMHelper.class)) {
//         mockedHelper.when(() ->
//             EMCMHelper.getHrefLink(href, context.currentResource())
//         ).thenReturn(absolutePath);
//         link = context.request().adaptTo(LinkModel.class);
//         assertEquals(title, link.getTitle());
//         assertEquals(content, link.getContent());
//         assertEquals(absolutePath, link.getHref());
//         assertEquals(target, link.getTarget());
//         assertEquals(downloadFileName, link.getDownloadFileName());
//         assertEquals(variant, link.getVariant());
//         assertEquals(tag, link.getTag());
//         assertEquals(tagColor, link.getTagColor());
//         }
//     }

//     @Test
//     void testExternalLink() {
//         try (MockedStatic<EMCMHelper> mockedHelper = mockStatic(EMCMHelper.class)) {
//         mockedHelper.when(() ->
//             EMCMHelper.getHrefLink(href, context.currentResource())
//         ).thenReturn("//" + externalPath);
//         link = context.request().adaptTo(LinkModel.class);
//         assertEquals("//" + externalPath, link.getHref());
//         }
//     }

//     @Test
//     void getDefaultTitleTest() {
//         Map<String, Object> properties = new HashMap<>();
//         properties.put("content", content);
//         Resource resource = context.create().resource("/content/tr/aux", properties);
//         context.currentResource(resource);
//         link = context.request().adaptTo(LinkModel.class);
//         assertEquals(link.getTitle(), content);
     }




