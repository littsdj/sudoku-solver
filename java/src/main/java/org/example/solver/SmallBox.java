package org.example.solver;

import java.util.HashSet;
import java.util.Set;

public class SmallBox {
    private int smallBoxValue;
    private int rowNumber;
    private int columnNumber;
    private int bigBoxNumber;
    private SudokuStructure row;
    private SudokuStructure column;
    private SudokuStructure bigBox;
    Set<Integer> possibleValues = new HashSet<>();

    public SmallBox(int rowNumber, int columnNumber, int bigBoxNumber, int smallBoxValue){
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.bigBoxNumber = bigBoxNumber;
        this.smallBoxValue = smallBoxValue;
    }
}
