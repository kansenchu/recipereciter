package com.example.recipereciter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestHelper {

    public static String getOutputFile(String filename) throws IOException {
        return new String(Files.readAllBytes(Paths.get("src/test/resources/outputs/").resolve(filename + ".json")));
    }
}
