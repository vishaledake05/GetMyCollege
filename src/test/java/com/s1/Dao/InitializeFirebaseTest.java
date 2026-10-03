package com.s1.Dao;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class InitializeFirebaseTest {

    @Test
    void shouldFindFirebaseCredentialsResource() {
        InputStream stream = InitializeFirebase.findServiceAccountStream();
        assertNotNull(stream, "Firebase service account should be available on the classpath.");
    }
}
