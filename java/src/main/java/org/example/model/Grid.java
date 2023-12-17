package org.example.model;

public class Grid {
    private int[][] value;
    private int[][] solution;
    private String difficulty;

    public int[][] getSolution() {
        return solution;
    }

    public void setSolution(int[][] solution) {
        this.solution = solution;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public int[][] getValue() {
        return value;
    }

    public void setValue(int[][] value) {
        this.value = value;
    }
    //private String message;


}
