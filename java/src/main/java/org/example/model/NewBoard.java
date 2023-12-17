package org.example.model;

public class NewBoard {
    private Grid[] grids;
    private String message;
    private int results;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public Grid[] getGrids() {
        return grids;
    }

    public void setGrids(Grid[] grids) {
        this.grids = grids;
    }
}
