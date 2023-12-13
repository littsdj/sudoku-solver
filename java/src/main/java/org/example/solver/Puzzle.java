package org.example.solver;

import java.util.ArrayList;
import java.util.List;

public class Puzzle {
    private List<SmallBox> smallBoxes = new ArrayList<>();
    private List<SudokuStructure> rows = new ArrayList<>();
    private List<SudokuStructure> columns = new ArrayList<>();
    private List<SudokuStructure> bigBoxes = new ArrayList<>();


    public Puzzle(){
        for(int i = 0; i < 9; i++){
            rows.add(new SudokuStructure());
            columns.add(new SudokuStructure());
            bigBoxes.add(new SudokuStructure());
        }
    }
    public void fillPuzzle(int[][] puzzleToSolve){
        for(int i = 0; i < 81; i++){
            int rowNumber = i / 9;
            int columnNumber = i % 9;
            int bigBoxNumber = (columnNumber / 3) + (3 * (rowNumber / 3));
            Integer boxValue = puzzleToSolve[rowNumber][columnNumber];

            SmallBox thisBox = new SmallBox(rowNumber, columnNumber, bigBoxNumber, boxValue);
            smallBoxes.add(thisBox);

            thisBox.setRow(rows.get(rowNumber));
            thisBox.setColumn(columns.get(columnNumber));
            thisBox.setBigBox(bigBoxes.get(bigBoxNumber));

            if (!boxValue.equals(0)){
                thisBox.getRow().setValueAsUsed(boxValue);
                thisBox.getColumn().setValueAsUsed(boxValue);
                thisBox.getBigBox().setValueAsUsed(boxValue);
            }


        }
    }
    public void solve(){
        for(int i = 0; i < 81; i++){
            SmallBox currentSquare = smallBoxes.get(i);
            if(currentSquare.getSmallBoxValue().equals(0)){
                for(int j = 1; j < 11; j++){
                    if (j == 10){
                        System.out.println("unable to solve");
                        System.exit(0);
                    }
                    boolean successfulTest = currentSquare.tryValue(j);
                    if(successfulTest){
                        currentSquare.addToStructures(j);
                        try {
                            guessNext(i);
                        } catch (GuessingException e){
                            currentSquare.revertBox();
                        }
                    }
                }
            }
        }
    }
    private void guessNext(int nextTest) throws GuessingException{
        for(int i = nextTest; i < 81; i++){
            SmallBox currentSquare = smallBoxes.get(i);
            if (currentSquare.getSmallBoxValue().equals(0)){
                for (int j = 1; j < 11; j++){
                    if (j == 10){
                        throw new GuessingException("Try Next Value");
                    }
                    boolean successfulTest = currentSquare.tryValue(j);
                    if(successfulTest){
                        currentSquare.addToStructures(j);
                        try{
                            guessNext(i + 1);
                        } catch (GuessingException e) {
                            currentSquare.revertBox();
                        }
                    }
                }
            }
        }
        System.out.println("Success! you did it");

    }

//    public void printSolution(){
//        for (int i = 0; i < 81; ){
//            String rowString = "{ ";
//            for (int column = 0; column < 9; column ++){
//                SmallBox currentBox = smallBoxes.get(i);
//                rowString += currentBox.getSmallBoxValue();
//                i++;
//            }
//            rowString += "}";
//            System.out.println(rowString);
//        }
//    }

}
