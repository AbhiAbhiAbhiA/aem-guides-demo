package com.adobe.aem.guides.demo.core.models;

import org.apache.sling.models.annotations.injectorspecific.ValueMapValue;
import org.apache.sling.models.annotations.Model;
import org.apache.sling.api.resource.Resource;
import org.apache.sling.models.annotations.DefaultInjectionStrategy;

@Model(adaptables = Resource.class, defaultInjectionStrategy = DefaultInjectionStrategy.OPTIONAL)

public class Textex {

    @ValueMapValue
    private String text;



    /*public String getReversedText(){


    String rev = "";

    for(int i = text.length() - 1; i >= 0; i--){
        rev = rev + text.charAt(i);
    }

    return rev;
}*/


public void getFun(){
     
}

}
