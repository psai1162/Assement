package com.search.dto;

import java.util.List;
import java.util.Objects;

public class SearchResponseDTO {
    public List<String> getSearchResult() {
        return searchResult;
    }

    public void setSearchResult(List<String> searchResult) {
        this.searchResult = searchResult;
    }

    private List<String> searchResult;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchResponseDTO that = (SearchResponseDTO) o;
        return Objects.equals(searchResult, that.searchResult);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchResult);
    }

    @Override
    public String toString() {
        return "SearchResponseDTO{" +
                "searchResult=" + searchResult +
                '}';
    }
}
