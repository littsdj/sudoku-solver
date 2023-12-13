package org.example.solver;

import java.util.HashSet;
import java.util.Set;

public class SmallBox {
    private Integer smallBoxValue;
    private int rowNumber;
    private int columnNumber;
    private int bigBoxNumber;
    private SudokuStructure row;
    private SudokuStructure column;
    private SudokuStructure bigBox;

    public Integer getSmallBoxValue() {
        return smallBoxValue;
    }

    public void setSmallBoxValue(Integer smallBoxValue) {
        this.smallBoxValue = smallBoxValue;
    }

    public int getRowNumber() {
        return rowNumber;
    }

    public void setRowNumber(int rowNumber) {
        this.rowNumber = rowNumber;
    }

    public int getColumnNumber() {
        return columnNumber;
    }

    public void setColumnNumber(int columnNumber) {
        this.columnNumber = columnNumber;
    }

    public int getBigBoxNumber() {
        return bigBoxNumber;
    }

    public void setBigBoxNumber(int bigBoxNumber) {
        this.bigBoxNumber = bigBoxNumber;
    }

    public SudokuStructure getRow() {
        return row;
    }

    public void setRow(SudokuStructure row) {
        this.row = row;
    }

    public SudokuStructure getColumn() {
        return column;
    }

    public void setColumn(SudokuStructure column) {
        this.column = column;
    }

    public SudokuStructure getBigBox() {
        return bigBox;
    }

    public void setBigBox(SudokuStructure bigBox) {
        this.bigBox = bigBox;
    }


    public boolean tryValue(Integer testValue){
        //returns true if all work
        return !row.valueIsUsed(testValue) && !column.valueIsUsed(testValue) && !bigBox.valueIsUsed(testValue);
    }

    public void addToStructures(Integer testValue){
        row.setValueAsUsed(testValue);
        column.setValueAsUsed(testValue);
        bigBox.setValueAsUsed(testValue);
        setSmallBoxValue(testValue);
    }
    public void revertBox(){
        row.setValueAsUnused(smallBoxValue);
        column.setValueAsUnused(smallBoxValue);
        bigBox.setValueAsUnused(smallBoxValue);
        setSmallBoxValue(0);
    }




    public SmallBox(int rowNumber, int columnNumber, int bigBoxNumber, int smallBoxValue){
        this.rowNumber = rowNumber;
        this.columnNumber = columnNumber;
        this.bigBoxNumber = bigBoxNumber;
        this.smallBoxValue = smallBoxValue;
    }
}
