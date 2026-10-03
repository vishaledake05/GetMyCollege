package com.s1.view;

import com.s1.Dao.InitializeFirebase;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {

        // 🔥 Firebase FIRST
        InitializeFirebase.initIfNeeded();

        System.out.println("App starting...");

        Application.launch(Loginpage.class, args);
    }
}
