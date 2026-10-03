package com.s1.Model;

import java.util.*;

import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.s1.Dao.InitializeFirebase;
import com.s1.view.nextpage;

public class Controllerugpg {

    public interface DataCallback {
        void onSuccess(List<nextpage> colleges);
        void onFailure(String error);
    }

    public void fetchCollegeData(
            String caste,
            String branch,
            double score,
            DataCallback callback
    ) {

        List<nextpage> result = new ArrayList<>();

        try {
            InitializeFirebase.initIfNeeded();
            Firestore db = InitializeFirebase.db;

            if (db == null) {
                callback.onFailure("Firestore not initialized. Check Firebase credentials.");
                return;
            }

            DocumentSnapshot doc = db
                    .collection("EngineeringUG")
                    .document("JEE")
                    .get()
                    .get();

            if (!doc.exists()) {
                callback.onFailure("JEE document not found in Firestore.");
                return;
            }

            Map<String, Object> colleges = doc.getData();
            if (colleges == null || colleges.isEmpty()) {
                callback.onSuccess(result);
                return;
            }

            for (String collegeName : colleges.keySet()) {
                Object rawCollege = colleges.get(collegeName);
                if (!(rawCollege instanceof Map<?, ?> collegeData)) {
                    continue;
                }

                Object rawCasteMap = collegeData.get(caste);
                if (!(rawCasteMap instanceof Map<?, ?> casteData)) {
                    continue;
                }

                Object rawBranchCutoff = casteData.get(branch);
                if (rawBranchCutoff == null) {
                    continue;
                }

                try {
                    double cutoff = Double.parseDouble(rawBranchCutoff.toString());
                    if (cutoff <= score) {
                        result.add(new nextpage(collegeName, branch, cutoff, caste));
                    }
                } catch (NumberFormatException ignored) {
                    // Skip malformed cutoff values instead of crashing the entire query
                }
            }

            callback.onSuccess(result);

        } catch (Exception e) {
            e.printStackTrace();
            callback.onFailure(e.getMessage());
        }
    }
}
