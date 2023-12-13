package org.example.solver;

import java.util.ArrayList;
import java.util.List;

public class SudokuSolver {
    private static final int GRID_SIZE = 9;
    public static void main(String[] args) {
        int[][] testBoard = {
                {6, 0, 0, 0, 3, 1, 4, 0, 0},
                {0, 3, 0, 8, 0, 5, 0, 0, 2},
                {0, 0, 9, 0, 0, 0, 0, 0, 8},

                {5, 6, 0, 0, 2, 4, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 8, 7},
                {1, 0, 7, 3, 8, 6, 2, 0, 0},

                {4, 0, 0, 6, 5, 0, 8, 9, 0},
                {9, 2, 0, 1, 7, 0, 3, 0, 5},
                {8, 5, 1, 0, 4, 0, 0, 0, 0}
        };
        Puzzle puzzle = new Puzzle();
        puzzle.fillPuzzle(testBoard);
        puzzle.solve();
        //puzzle.printSolution();

    }
}
