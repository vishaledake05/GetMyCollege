package com.s1.Controller;

import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.nio.file.AtomicMoveNotSupportedException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class MystorageSDK {
    private static final Path PROFILE_IMAGE_DIRECTORY = Path.of(
            System.getProperty("user.home"), ".getmycollege", "profile-images"
    );

    public String uploadImage(Stage stage, String accountId) throws IOException {
        if (accountId == null || accountId.isBlank()) {
            throw new IllegalArgumentException("Sign in before choosing a profile picture.");
        }

        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose Profile Picture");
        fileChooser.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("Image Files", "*.png", "*.jpg", "*.jpeg")
        );
        File selectedFile = fileChooser.showOpenDialog(stage);
        if (selectedFile == null) {
            return null;
        }

        String extension = getExtension(selectedFile);
        Files.createDirectories(PROFILE_IMAGE_DIRECTORY);
        Path destination = PROFILE_IMAGE_DIRECTORY.resolve(hashAccountId(accountId) + extension);
        Path temporaryFile = Files.createTempFile(PROFILE_IMAGE_DIRECTORY, "profile-", ".tmp");

        try {
            Files.copy(selectedFile.toPath(), temporaryFile, StandardCopyOption.REPLACE_EXISTING);
            try {
                Files.move(temporaryFile, destination,
                        StandardCopyOption.REPLACE_EXISTING, StandardCopyOption.ATOMIC_MOVE);
            } catch (AtomicMoveNotSupportedException e) {
                Files.move(temporaryFile, destination, StandardCopyOption.REPLACE_EXISTING);
            }
        } finally {
            Files.deleteIfExists(temporaryFile);
        }

        return destination.toUri().toString();
    }

    public String loadImageUrl(String accountId) throws IOException {
        if (accountId == null || accountId.isBlank()) {
            return null;
        }

        String fileName = hashAccountId(accountId);
        for (String extension : new String[]{".png", ".jpg", ".jpeg"}) {
            Path image = PROFILE_IMAGE_DIRECTORY.resolve(fileName + extension);
            if (Files.isRegularFile(image)) {
                return image.toUri().toString();
            }
        }
        return null;
    }

    private static String getExtension(File file) {
        String name = file.getName().toLowerCase();
        if (name.endsWith(".png")) {
            return ".png";
        }
        if (name.endsWith(".jpeg")) {
            return ".jpeg";
        }
        if (name.endsWith(".jpg")) {
            return ".jpg";
        }
        throw new IllegalArgumentException("Choose a PNG or JPEG image.");
    }

    private static String hashAccountId(String accountId) {
        try {
            byte[] digest = MessageDigest.getInstance("SHA-256")
                    .digest(accountId.trim().toLowerCase().getBytes(java.nio.charset.StandardCharsets.UTF_8));
            return HexFormat.of().formatHex(digest);
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 is not available.", e);
        }
    }
}
