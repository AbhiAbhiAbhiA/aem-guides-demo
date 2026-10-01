package com.adobe.aem.guides.demo.core.models;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.sling.api.resource.ModifiableValueMap;
import org.apache.sling.api.resource.Resource;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import io.wcm.testing.mock.aem.junit5.AemContext;
import io.wcm.testing.mock.aem.junit5.AemContextExtension;

@ExtendWith(AemContextExtension.class)
class SingleArticleTest {

    private final AemContext context = new AemContext();

    @BeforeEach
    void setUp() {
        // Component resource
        context.create().resource("/content/article");

        // jcr:content with cssClass
        context.create().resource("/content/article/jcr:content",
                "cssClass", "test-css");
    }

    // ---------- enablePII = true (IF block NOT executed) ----------
    @Test
    void testEnablePIITrue() {
        Resource resource = context.create().resource("/content/test1",
                "enablePII", true,
                "cssClass", "my-css");

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        assertTrue(model.isEnablePII());
        assertEquals("my-css", model.getCssClass());
    }

    // ---------- enablePII = false + cssClass removed ----------
    @Test
    void testInitCssClassRemoved() {

        Resource resource = context.create().resource("/content/test2",
                "enablePII", false);

        // Attach jcr:content
        context.create().resource("/content/test2/jcr:content",
                "cssClass", "remove-me");

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        Resource content = resource.getChild("jcr:content");
        ModifiableValueMap mvm = content.adaptTo(ModifiableValueMap.class);

        assertNotNull(model);
        assertFalse(mvm.containsKey("cssClass"));
    }

    // ---------- enablePII = false BUT no cssClass ----------
    @Test
    void testInitNoCssClassPresent() {

        Resource resource = context.create().resource("/content/test3",
                "enablePII", false);

        context.create().resource("/content/test3/jcr:content");

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        assertNotNull(model);
    }

    // ---------- enablePII = false BUT contentNode == null ----------
    @Test
    void testInitWithNoContentNode() {

        Resource resource = context.create().resource("/content/test4",
                "enablePII", false);

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        assertNotNull(model);
    }

    // ---------- Getter coverage ----------
    @Test
    void testGetCssClass() {

        Resource resource = context.create().resource("/content/test5",
                "enablePII", true,
                "cssClass", "getter-css");

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        assertEquals("getter-css", model.getCssClass());
    }

    @Test
    void testIsEnablePII() {

        Resource resource = context.create().resource("/content/test6",
                "enablePII", false);

        SingleArticle model = resource.adaptTo(SingleArticle.class);

        assertFalse(model.isEnablePII());
    }

    @Test
    void testCssClassRemovedWhenEnablePIIDisabled() {

        // Component resource with enablePII = false
        Resource resource = context.create().resource("/content/cover-if",
                "enablePII", false);

        // jcr:content WITH cssClass (this is critical)
        Resource content = context.create().resource(
                "/content/cover-if/jcr:content",
                "cssClass", "remove-this");

        // Adapt model (PostConstruct runs here)
        SingleArticle model = resource.adaptTo(SingleArticle.class);

        // Verify removal
        ModifiableValueMap mvm = content.adaptTo(ModifiableValueMap.class);

        assertNotNull(mvm); // mvm != null
        assertFalse(mvm.containsKey("cssClass")); // cssClass removed
    }

    @Test
    void testCssClassRemoved_usingProperChildResourceInjection() {

        // Step 1: Create component resource
        Resource component = context.create().resource("/content/component",
                "enablePII", false);

        // Step 2: Create child jcr:content WITH cssClass
        Resource content = context.create().resource(
                "/content/component/jcr:content",
                "cssClass", "to-be-removed");

        // Step 3: Adapt SAME resource (important)
        SingleArticle model = component.adaptTo(SingleArticle.class);

        // Step 4: Validate removal
        ModifiableValueMap mvm = content.adaptTo(ModifiableValueMap.class);

        assertNotNull(mvm); // mvm != null
        assertFalse(mvm.containsKey("cssClass")); // cssClass removed
    }

}
