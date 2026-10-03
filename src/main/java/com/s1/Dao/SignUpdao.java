package com.s1.Dao;

import java.util.Map;

public class SignUpdao {
    public static void addSignUpData(Map<String,String> data){
        try{
            InitializeFirebase.initIfNeeded();
            if (InitializeFirebase.db == null) {
                System.err.println("Firestore is not initialized. Cannot save signup data.");
                return;
            }

            System.out.println("----------------------" + data.get("email"));
            InitializeFirebase.db.collection("user").document(data.get("email")).set(data);
            System.out.println("data Added succesfully");
            
        }catch(Exception e){
            
            System.out.println("Failed to Add data");
            e.printStackTrace();
        }
    }
}