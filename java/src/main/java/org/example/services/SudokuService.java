package org.example.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.example.model.Grid;
import org.example.model.NewBoard;
import org.example.model.SudokuGetter;
import org.springframework.web.client.RestTemplate;

public class SudokuService {

    private RestTemplate restTemplate = new RestTemplate();
    private final String API_URL = "https://sudoku-api.vercel.app/api/dosuku";


    public Grid getNewPuzzle() {
        String response = restTemplate.getForObject(API_URL, String.class);
        Grid newPuzzle = new Grid();
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(response);
            JsonNode root = jsonNode.path("newboard");
            JsonNode gridRoot = root.path("grids");
            JsonNode valueNode = gridRoot.path(0).path("value");
            JsonNode solutionNode = gridRoot.path(0).path("solution");
            String difficulty = gridRoot.path(0).path("difficulty").toString();
            newPuzzle.setValue(nodeToPuzzle(valueNode));
            newPuzzle.setSolution(nodeToPuzzle(solutionNode));
            newPuzzle.setDifficulty(difficulty);
//            for (int i = 0; i < valueNode.size(); i++) {
//                JsonNode arr = valueNode.path(i);
//                for (int j = 0; j < arr.size(); j++) {
//                    int val = arr.path(j).asInt();
//                    System.out.println(val);
//                }
//            }

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return newPuzzle;
    }

    private int[][] nodeToPuzzle(JsonNode inputNode){
        int[][] puzzleOutput = new int[9][9];
        for(int i = 0; i < inputNode.size(); i++) {
            JsonNode row = inputNode.path(i);
            for (int j = 0; j < row.size(); j++) {
                int squareValue = row.path(j).asInt();
                puzzleOutput[i][j] = squareValue;
            }
        }

        return puzzleOutput;
    }
//    public NewBoard getNewPuzzle(){
//        SudokuGetter newSudoku = restTemplate.getForObject(API_URL, SudokuGetter.class);
//        System.out.println(newSudoku.getNewboard().getMessage());
//        return newSudoku.getNewboard();
//    }
//    public Grid getNewPuzzle(){
//        Grid newGrid = restTemplate.getForObject(API_URL, Grid.class);
//        System.out.println(newGrid.getMessage());
//        return newGrid;
//    }

}
