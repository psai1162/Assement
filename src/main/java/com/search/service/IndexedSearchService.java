package com.search.service;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.search.dto.SearchResponseDTO;
import org.springframework.stereotype.Service;

@Service
public class IndexedSearchService {

    public SearchResponseDTO searchString(String searchString) throws IOException,ClassNotFoundException {
 SearchResponseDTO responseDTO = new SearchResponseDTO();
        List<String> responseList = new ArrayList<>();
        List<String> filePaths = Files.walk(Paths.get("/Users/praveenapardesi/Downloads/sample_text"))
                .filter(Files::isRegularFile)
                .map(Path::toString)
                .collect(Collectors.toList());

        InvertedSearchOperation operations = new InvertedSearchOperation();

        operations.processIndexing(filePaths);

        HashMap<String, int[]> indexedMap = operations.readFromFile();

        for (int count = 0; count < filePaths.size(); count++) {
            int no_of_occurrences = 0;
            if (indexedMap.containsKey(searchString)) {
                no_of_occurrences = indexedMap.get(searchString)[count];
            }

            responseList.add( filePaths.get(count) + " - " + no_of_occurrences + "matches");
        }
        responseDTO.setSearchResult(responseList);
        return  responseDTO;
    }

}
