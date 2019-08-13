package com.search.dto;

import java.util.Objects;

public class SearchRequestDTO {
    private String searchString;

    public String getSearchString() {
        return searchString;
    }

    public void setSearchString(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SearchRequestDTO that = (SearchRequestDTO) o;
        return Objects.equals(searchString, that.searchString);
    }

    @Override
    public int hashCode() {
        return Objects.hash(searchString);
    }

    @Override
    public String toString() {
        return "SearchRequestDTO{" +
                "searchString='" + searchString + '\'' +
                '}';
    }
}
