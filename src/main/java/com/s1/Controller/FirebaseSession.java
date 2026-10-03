package com.s1.Controller;

public final class FirebaseSession {
    private static volatile String idToken;
    private static volatile String userId;

    private FirebaseSession() {
    }

    public static void setCredentials(String token, String uid) {
        idToken = token;
        userId = uid;
    }

    public static String getIdToken() {
        return idToken;
    }

    public static String getUserId() {
        return userId;
    }
}
