package org.example.solver;

import java.util.*;

public class SudokuStructure {
    private List<SmallBox> valuesInStructure = new ArrayList<>();
    private static final Integer[] possibleValues = {1, 2, 3, 4, 5, 6, 7, 8, 9};
    private Set<Integer> valuesNeeded = new HashSet<>(Arrays.asList(possibleValues));
}
