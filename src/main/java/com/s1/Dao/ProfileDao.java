package com.s1.Dao;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.SetOptions;

import java.util.HashMap;
import java.util.Map;

public class ProfileDao {
    public static void updateCollection(String imageUrl, String email) throws Exception {
        if (email == null || email.isBlank() || imageUrl == null || imageUrl.isBlank()) {
            throw new IllegalArgumentException("A signed-in user and uploaded image URL are required.");
        }

        InitializeFirebase.initIfNeeded();
        if (InitializeFirebase.db == null) {
            throw new IllegalStateException("Firestore is not initialized.");
        }

        Map<String, Object> updates = new HashMap<>();
        updates.put("imageUrl", imageUrl);
        InitializeFirebase.db.collection("user")
                .document(email)
                .set(updates, SetOptions.merge())
                .get();
    }

    public static String getImageUrl(String email) throws Exception {
        if (email == null || email.isBlank()) {
            return null;
        }

        InitializeFirebase.initIfNeeded();
        if (InitializeFirebase.db == null) {
            throw new IllegalStateException("Firestore is not initialized.");
        }

        DocumentSnapshot profile = InitializeFirebase.db.collection("user")
                .document(email)
                .get()
                .get();
        return profile.exists() ? profile.getString("imageUrl") : null;
    }
}
