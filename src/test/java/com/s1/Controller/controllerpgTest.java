package com.s1.Controller;

import com.s1.view.nextpage;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class controllerpgTest {

    @Test
    void parsesNestedFirestoreMapWithStringCutoff() {
        String firestoreDocument = """
                {
                  "fields": {
                    "NIT Delhi": {
                      "mapValue": {
                        "fields": {
                          "OBC": {
                            "mapValue": {
                              "fields": {
                                "CSE": { "stringValue": "52.3" }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                """;

        List<nextpage> colleges = new controllerpg()
                .parseData(firestoreDocument, "OBC", "CSE", 52.3);

        assertEquals(1, colleges.size());
        assertEquals("NIT Delhi", colleges.get(0).collegeName);
        assertEquals(52.3, colleges.get(0).cutoff);
    }

    @Test
    void parsesFirestoreNumericCutoff() {
        String firestoreDocument = """
                {
                  "fields": {
                    "NIT Delhi": {
                      "mapValue": {
                        "fields": {
                          "OBC": {
                            "mapValue": {
                              "fields": {
                                "CSE": { "doubleValue": 52.3 }
                              }
                            }
                          }
                        }
                      }
                    }
                  }
                }
                """;

        List<nextpage> colleges = new controllerpg()
                .parseData(firestoreDocument, "OBC", "CSE", 52.3);

        assertEquals(1, colleges.size());
    }
}
