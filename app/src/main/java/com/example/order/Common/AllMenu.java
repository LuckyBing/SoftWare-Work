package com.example.order.Common;


import java.util.List;

public class AllMenu {
    private String error;
    public List<Menu> results;

    public List<Menu> getResults() {
        return results;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public void setResults(List<Menu> results) {
        this.results = results;
    }
}
