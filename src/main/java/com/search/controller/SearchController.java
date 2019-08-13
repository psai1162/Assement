package com.search.controller;

import com.search.dto.SearchRequestDTO;
import com.search.dto.SearchResponseDTO;
import com.search.service.IndexedSearchService;
import com.search.service.RegularExpressionSearchService;
import com.search.service.StringMatchService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
public class SearchController {

    private RegularExpressionSearchService regularExpressionSearchService;
    private IndexedSearchService indexedSearchService;
    private StringMatchService stringMatchService;

    public SearchController(RegularExpressionSearchService regularExpressionSearchService,
                            IndexedSearchService indexedSearchService,
                            StringMatchService stringMatchService){
        this.regularExpressionSearchService = regularExpressionSearchService;
        this.indexedSearchService = indexedSearchService;
        this.stringMatchService = stringMatchService;
    }

    @PutMapping("/stringMatchSearch")
    @ResponseStatus(HttpStatus.OK)
    public SearchResponseDTO stringMatchSearch(@RequestBody SearchRequestDTO requestDTO) throws IOException  {
        SearchResponseDTO responseDTO = stringMatchService.searchString(requestDTO.getSearchString());
      return responseDTO;
    }

    @PutMapping("/regularExpressionSearch")
    @ResponseStatus(HttpStatus.OK)
    public SearchResponseDTO regularExpressionSearch(@RequestBody SearchRequestDTO requestDTO) throws IOException {
        SearchResponseDTO responseDTO =  regularExpressionSearchService.searchByRegularExpression(requestDTO.getSearchString());
        return responseDTO;
    }

    @PutMapping("/indexedSearch")
    @ResponseStatus(HttpStatus.OK)
    public SearchResponseDTO indexedSearch(@RequestBody SearchRequestDTO requestDTO) throws IOException, ClassNotFoundException {

        SearchResponseDTO responseDTO =  indexedSearchService.searchString(requestDTO.getSearchString());
        return responseDTO;
    }
}
