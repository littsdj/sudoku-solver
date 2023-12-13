package org.example.solver;

import java.util.*;

public class SudokuStructure {
    private List<SmallBox> smallBoxesInStructure = new ArrayList<>();
    private static final Integer[] possibleValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Set<Integer> valuesNeeded = new HashSet<>(Arrays.asList(possibleValues));
    private Set<Integer> valuesUsed = new HashSet<>();

    public Set<Integer> getValuesNeeded() {
        return valuesNeeded;
    }

    public Set<Integer> getValuesUsed() {
        return valuesUsed;
    }

    public void addSmallBox(SmallBox boxToAdd){
        smallBoxesInStructure.add(boxToAdd);
    }

    public void updateNeedsAndUsed(){

    }
    public boolean valueIsUsed(Integer testValue){
        return valuesUsed.contains(testValue);
    }
    public void setValueAsUsed(Integer value){
        valuesUsed.add(value);
    }
    public void setValueAsUnused(Integer value){
        valuesUsed.remove(value);
    }
}
