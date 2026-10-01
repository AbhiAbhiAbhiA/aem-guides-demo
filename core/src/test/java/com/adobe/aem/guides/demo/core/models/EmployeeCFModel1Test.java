// package com.adobe.aem.guides.demo.core.models;

// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertNotNull;

// import org.apache.sling.api.resource.Resource;
// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import org.junit.jupiter.api.extension.ExtendWith;

// import io.wcm.testing.mock.aem.junit5.AemContext;
// import io.wcm.testing.mock.aem.junit5.AemContextExtension;

// @ExtendWith(AemContextExtension.class)
// class EmployeeCFModel1Test {

//     private final AemContext context = new AemContext();

//     @BeforeEach
//     void setUp() {

//         // -------- Master CF --------
//         context.create().resource("/content/dummy");
//         context.create().resource("/content/dummy/jcr:content");
//         context.create().resource("/content/dummy/jcr:content/elements");

//         context.create().resource("/content/dummy/jcr:content/elements/name",
//                 "value", "Abhilash");
//         context.create().resource("/content/dummy/jcr:content/elements/age",
//                 "value", "25");
//         context.create().resource("/content/dummy/jcr:content/elements/salary",
//                 "value", "50000");
//         context.create().resource("/content/dummy/jcr:content/elements/domain",
//                 "value", "AEM");
//         context.create().resource("/content/dummy/jcr:content/elements/company",
//                 "value", "Adobe");

//         // -------- Variation --------
//         context.create().resource("/content/dummy/jcr:content/variations/dev");
//         context.create().resource("/content/dummy/jcr:content/variations/dev/elements");
//         context.create().resource("/content/dummy/jcr:content/variations/dev/elements/name",
//                 "value", "VarName");

//         // -------- CF without elements --------
//         context.create().resource("/content/noelements");
//         context.create().resource("/content/noelements/jcr:content");

//         // -------- CF with element but NO value --------
//         context.create().resource("/content/nullvalue");
//         context.create().resource("/content/nullvalue/jcr:content");
//         context.create().resource("/content/nullvalue/jcr:content/elements");
//         context.create().resource("/content/nullvalue/jcr:content/elements/name");
//     }

//     private EmployeeCFModel1 getModel(String cfPath, String variation) {
//         Resource resource = context.create().resource("/content/test",
//                 "cfPath", cfPath,
//                 "variationName", variation);

//         return resource.adaptTo(EmployeeCFModel1.class);
//     }

//     // ---------- HAPPY PATH ----------

//     // @Test
//     // void testGetName() {
//     //     EmployeeCFModel1 model = getModel("/content/dummy", "master");
//     //     assertEquals("Abhilash", model.getName());
//     // }

//     @Test
//     void testGetAge() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "master");
//         assertEquals("25", model.getAge());
//     }

//     @Test
//     void testGetSalary() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "master");
//         assertEquals("50000", model.getSalary());
//     }

//     @Test
//     void testGetDomain() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "master");
//         assertEquals("AEM", model.getDomain());
//     }

//     @Test
//     void testGetCompany() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "master");
//         assertEquals("Adobe", model.getCompany());
//     }

//     // ---------- VARIATION ----------

//     @Test
//     void testInitWithVariation() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "dev");
//         assertEquals("VarName", model.getName());
//     }

//     // ---------- BRANCH COVERAGE ----------

//     // cfRes == null
//     @Test
//     void testInvalidCfPath() {
//         EmployeeCFModel1 model = getModel("/content/not-exist", "master");
//         assertEquals("", model.getName());
//     }

//     // variationName != master BUT variation not found
//     @Test
//     void testVariationNotFound() {
//         EmployeeCFModel1 model = getModel("/content/dummy", "qa");
//         assertEquals("Abhilash", model.getName());
//     }

//     // selected != null BUT elements == null
//     @Test
//     void testWithoutElementsNode() {
//         EmployeeCFModel1 model = getModel("/content/noelements", "master");
//         assertEquals("", model.getName());
//     }

//     // val == null inside getValue()
//     @Test
//     void testElementWithoutValue() {
//         EmployeeCFModel1 model = getModel("/content/nullvalue", "master");
//         assertEquals("", model.getName());
//     }

//     // cfPath not authored
//     @Test
//     void testInitWithNullPath() {
//         Resource resource = context.create().resource("/content/nulltest");
//         EmployeeCFModel1 model = resource.adaptTo(EmployeeCFModel1.class);
//         assertNotNull(model);
//     }

//     @Test
//     void testVariationNameIsNull() {
//         Resource resource = context.create().resource("/content/test-null-variation",
//                 "cfPath", "/content/dummy");

//         EmployeeCFModel1 model = resource.adaptTo(EmployeeCFModel1.class);

//         assertEquals("Abhilash", model.getName());
//     }

//     @Test
//     void testSelectedIsNull() {

//         context.create().resource("/content/no-jcr");

//         Resource resource = context.create().resource("/content/test-no-jcr",
//                 "cfPath", "/content/no-jcr",
//                 "variationName", "master");

//         EmployeeCFModel1 model = resource.adaptTo(EmployeeCFModel1.class);

//         assertEquals("", model.getName());
//     }

// }
