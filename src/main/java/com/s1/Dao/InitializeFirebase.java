package com.s1.Dao;

import java.io.InputStream;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.Firestore;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;

public class InitializeFirebase {

    public static Firestore db;

    public static void initIfNeeded() {
        if (db != null) return;

        try (InputStream serviceAccount = findServiceAccountStream()) {
            if (serviceAccount == null) {
                String envPath = System.getenv("GOOGLE_APPLICATION_CREDENTIALS");
                if (envPath != null && !envPath.isBlank()) {
                    FirebaseOptions options = FirebaseOptions.builder()
                            .setCredentials(GoogleCredentials.fromStream(java.nio.file.Files.newInputStream(java.nio.file.Path.of(envPath))))
                            .build();
                    if (FirebaseApp.getApps().isEmpty()) {
                        FirebaseApp.initializeApp(options);
                    }
                    db = FirestoreClient.getFirestore();
                    System.out.println("✅ Firebase initialized from environment credentials");
                    return;
                }

                System.err.println("Firebase config not found. The app will continue without Firestore.");
                return;
            }

            FirebaseOptions options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount))
                    .build();

            if (FirebaseApp.getApps().isEmpty()) {
                FirebaseApp.initializeApp(options);
            }

            db = FirestoreClient.getFirestore();
            System.out.println("✅ Firebase initialized");

        } catch (Exception e) {
            System.err.println("❌ Firebase init failed");
            e.printStackTrace();
        }
    }

    static InputStream findServiceAccountStream() {
        String[] candidates = {
                "firebase-service-account.json",
                "firebase-service-account..json",
                "getmycollge-firebase-adminsdk-fbsvc-ec1598a8a9[1].json",
                "Assets/firebase-service-account..json",
                "Assets/firebase-service-account.json",
                "/Assets/firebase-service-account..json",
                "/Assets/firebase-service-account.json"
        };

        for (String candidate : candidates) {
            InputStream stream = InitializeFirebase.class
                    .getClassLoader()
                    .getResourceAsStream(candidate);
            if (stream != null) {
                return stream;
            }
        }

        return null;
    }
}
