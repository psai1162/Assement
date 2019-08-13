package com.search.service;

import java.io.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class InvertedSearchOperation {
    void processIndexing(List<String> filePaths) throws IOException {
        HashMap<String, int[]> indexedData = new HashMap<>();

        for (int count = 0; count < filePaths.size(); count++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePaths.get(count)));

            int finalCount = count;
            bufferedReader.lines().collect(Collectors.toList())
                    .stream().flatMap(sample -> Arrays.stream(sample.split(" "))).forEach(string -> {
                if (indexedData.containsKey(string)) {
                    int[] values = indexedData.get(string);
                    values[finalCount] += 1;
                    indexedData.put(string, values);
                } else {
                    int[] values = new int[filePaths.size()];
                    values[0] += 1;
                    indexedData.put(string, values);
                }
            });
        }

        writeToFile(indexedData);
    }

    void writeToFile(HashMap<String, int[]> indexedData) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("/Users/praveenapardesi/Downloads/sample_output/index.ind");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(indexedData);
        objectOutputStream.close();
        fileOutputStream.close();
    }

    HashMap<String, int[]> readFromFile() throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("/Users/praveenapardesi/Downloads/sample_output/index.ind");
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
        HashMap<String, int[]> deserializedData = (HashMap<String, int[]>) objectInputStream.readObject();
        objectInputStream.close();
        fileInputStream.close();

        return deserializedData;
    }
}
