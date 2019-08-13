package com.search.service;

import com.search.dto.SearchResponseDTO;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class RegularExpressionSearchService {

    public SearchResponseDTO searchByRegularExpression(String searchString) throws IOException {
        SearchResponseDTO responseDTO = new SearchResponseDTO();
        List<String> responseList = new ArrayList<>();
        String[] words = null; //Intialize the word Array

        List<String> filePaths = Files.walk(Paths.get("/Users/praveenapardesi/Downloads/sample_text"))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .collect(Collectors.toList());
        Pattern pattern = Pattern.compile(searchString);

        for (int count = 0; count < filePaths.size(); count++) {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(filePaths.get(count)));
            String s;
            int no_of_occurrences = 0;   //Intialize the word count to zero
            while ((s = bufferedReader.readLine()) != null)   //Reading Content from the file
            {
                words = s.split(" ");  //Split the word using space
                for (String word : words) {
                    Matcher matcher = pattern.matcher(word);
                    if (matcher.matches()) {
                        no_of_occurrences++;
                    }
                }
            }
            responseList.add(filePaths.get(count) + " - " + no_of_occurrences + "matches");
        }
        responseDTO.setSearchResult(responseList);
        return responseDTO;
    }
}