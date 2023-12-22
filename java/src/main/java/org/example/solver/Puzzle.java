package org.example.solver;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private List<SmallBox> smallBoxes = new ArrayList<>();
    private List<SudokuStructure> rows = new ArrayList<>();
    private List<SudokuStructure> columns = new ArrayList<>();
    private List<SudokuStructure> bigBoxes = new ArrayList<>();
    private List<int[][]> solutions = new ArrayList<>();


    public Puzzle() {
        for (int i = 0; i < 9; i++) {
            rows.add(new SudokuStructure());
            columns.add(new SudokuStructure());
            bigBoxes.add(new SudokuStructure());
        }
    }

    public void fillPuzzle(int[][] puzzleToSolve) {
        for (int i = 0; i < 81; i++) {
            int rowNumber = i / 9;
            int columnNumber = i % 9;
            int bigBoxNumber = (columnNumber / 3) + (3 * (rowNumber / 3));
            Integer boxValue = puzzleToSolve[rowNumber][columnNumber];

            SmallBox thisBox = new SmallBox(rowNumber, columnNumber, bigBoxNumber, boxValue);
            smallBoxes.add(thisBox);

            thisBox.setRow(rows.get(rowNumber));
            thisBox.setColumn(columns.get(columnNumber));
            thisBox.setBigBox(bigBoxes.get(bigBoxNumber));

            if (!boxValue.equals(0)) {
                thisBox.getRow().setValueAsUsed(boxValue);
                thisBox.getColumn().setValueAsUsed(boxValue);
                thisBox.getBigBox().setValueAsUsed(boxValue);
            }


        }
    }

    public void solve() {
        guessNext(0);
        if (solutions.size() > 0) {
            System.out.println("~~~~~~~~~~~~~~~~\n \n \nFound a total of " + solutions.size() + "solutions.");

        }
//        for(int i = 0; i < 81; i++){
//            SmallBox currentSquare = smallBoxes.get(i);
//            if(currentSquare.getSmallBoxValue().equals(0)){
//                for(int j = 1; j < 11; j++){
//                    if (j == 10){
//                        System.out.println("unable to solve");
//                        System.exit(0);
//                    }
//                    boolean successfulTest = currentSquare.tryValue(j);
//                    if(successfulTest){
//                        currentSquare.addToStructures(j);
//                        try {
//                            guessNext(i);
//                        } catch (GuessingException e){
//                            currentSquare.revertBox();
//                        } catch (SuccessException e){
//                            System.out.println(e.getMessage());
//                            return;
//                        }
//                    }
//                }
//            }
//        }
    }

    private void guessNext(int nextTest) {
        //searches for the next empty box
        for (int i = nextTest; i < 81; i++) {
            SmallBox currentSquare = smallBoxes.get(i);

            //skips block if box is already filled
            if (currentSquare.getSmallBoxValue().equals(0)) {
                for (int j = 1; j < 10; j++) {
//                    if (j == 10) {
////                        throw new GuessingException("Try Next Value");
//                        //if no values work, go to previous recursion
//                        return;
//                    }
                    boolean successfulTest = currentSquare.tryValue(j);
                    if (successfulTest) {
                        currentSquare.addToStructures(j);
                        if (i == 80) {
                            addSolution();
                            System.out.println("successfully found solution # " + solutions.size());
                        }
//                        try {
                        guessNext(i + 1);
//                        } catch (GuessingException e) {
                        currentSquare.revertBox();
//                        }
                    }
                }
                return;
            } else if (i == 80){
                addSolution();
                System.out.println("successfully found solution # " + solutions.size());
            }
        }


    }

    public void printSolutions() {
       for (int i = 0; i < solutions.size(); i++) {
           int[][] currentSolution = solutions.get(i);
           System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~\nSolution # " + (i + 1));
           for (int row = 0; row < 9; row++) {
               String rowString = " ";
               if (row % 3 == 0) {
                   System.out.println("  ------   ------   ------");
               }
               for (int column = 0; column < 9; column++) {
                   if(column % 3 == 0){
                       rowString += "| ";
                   }
                   rowString += currentSolution[row][column] + " ";
               }
               rowString += "|";
               System.out.println(rowString);
           }
           System.out.println("  ------ ------ ------");
       }
    }

    public void checkSolution(int[][] solution) {
        for (int i = 0; i < 81; i++) {
            int row = i / 9;
            int column = i % 9;
            int smallBoxValue = smallBoxes.get(i).getSmallBoxValue();
            int solutionValue = solution[row][column];
            if (smallBoxValue != solutionValue) {
                System.out.println("solutions do not match");
                return;
            }
        }
        System.out.println("Calculated solution matched solution from API");
    }

    private void addSolution() {
        int[][] solution = new int[9][9];
        for (int i = 0; i < 81; i++) {
            SmallBox currentSmallBox = smallBoxes.get(i);
            int row = currentSmallBox.getRowNumber();
            int column = currentSmallBox.getColumnNumber();
            solution[row][column] = currentSmallBox.getSmallBoxValue();
        }
        solutions.add(solution);
    }
}

