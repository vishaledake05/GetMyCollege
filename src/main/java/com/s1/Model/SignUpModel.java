package com.s1.Model;

import java.util.HashMap;
import java.util.Map;

//import impl.org.controlsfx.collections.MappingChange.Map;

public class SignUpModel {
   static String name;
   static String email;
   static String password;
    public SignUpModel(String name,String email,  String password){
        this.name=name;
        this.email=email;
        this.password=password;
    }
    static public Map<String,String> getMap(){
        Map<String,String> data = new HashMap<String,String>();
        data.put("name",name);
        data.put("email",email);
        data.put("password", password);
        
        return data;
    }
}
