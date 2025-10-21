package riddle;

import java.util.*;

/*
 Utility class for generating permutations of given arrays. This class is used by 
 the Einstein Riddle Solver to generate all possible permutations of colors, 
 nationalities, drinks, cigarette brands, and pets. By combining these permutations,
 we can test all possible house configurations.
 
 Contains two main methods:
 - public static <T> void permute(T[], T[], boolean[], int, List<T[]>) — generates all permutations of an array
 - public static <T> void permuteFixFirst(T[], T, T[], boolean[], int, List<T[]>) — generates all permutations 
 but keeps the first element fixed (useful for 11th Constrait of the riddle "11.Der Norweger wohnt im ersten Haus").

*/


public class Permutation {
	/*
	 
     Generates all permutations of the given array "items".
     Uses recursive backtracking: each recursive call adds one unused element until all positions are filled.

     @param items   The array of elements to permute.
     @param out     A working array to build each permutation.
     @param used    A boolean array tracking which elements are already used.
     @param depth   Current recursion depth (position to fill).
     @param results A list where all generated permutations will be stored.
     @param <T>     Generic type (e.g., Color, Nation, etc.)
     
    */
	
    public static <T> void permute(T[] items, T[] out, boolean[] used, int depth, List<T[]> results) {
        // Base case: if all positions are filled, store a copy of the permutation
        if (depth == items.length) {
            results.add(out.clone());
            return;
        }
        
        // Try each unused element in the current position
        for (int i = 0; i < items.length; i++) {
            if (used[i]) continue; // skip already used items
            used[i] = true;
            out[depth] = items[i];
            permute(items, out, used, depth + 1, results);
            used[i] = false;
        }
    }
    
	/*
	 
    Generates all permutations of the given array "items",
	but ensures that the first element is fixed to a specific value.
	This method is used for applying constraint #11: "Der Norweger wohnt im ersten Haus."
	It reduces the total search space by fixing one known condition before generating permutations.
	
    @param items       The array of elements to permute.
    @param fixedFirst  The element that must appear at index 0.
    @param out         A working array to build each permutation.
    @param used        A boolean array tracking which elements are already used.
    @param depth       Current recursion depth (position to fill).
    @param results     A list where all generated permutations will be stored.
    @param <T>         Generic type (e.g., Nation)
    
   */
    public static <T> void permuteFixFirst(T[] items, T fixedFirst, T[] out, boolean[] used, int depth, List<T[]> results) {
        // At the beginning, place the fixed element at position 0
        if (depth == 0) {
            int idx = indexOf(items, fixedFirst);
            if (idx == -1) return;
            used[idx] = true;
            out[0] = fixedFirst;
            permuteFixFirst(items, fixedFirst, out, used, 1, results);
            used[idx] = false; // backtrack
            return;
        }
        
        // Base case: all positions filled -> store result
        if (depth == items.length) {
            results.add(out.clone());
            return;
        }
        
        // Try each unused item for the current position
        for (int i = 0; i < items.length; i++) {
            if (used[i]) continue;
            used[i] = true;
            out[depth] = items[i];
            permuteFixFirst(items, fixedFirst, out, used, depth + 1, results);
            used[i] = false; // backtrack
        }
    }

    /**
     Utility method to find the index of a value in an array.
    **/
    public static <T> int indexOf(T[] arr, T val) {
        for (int i = 0; i < arr.length; i++)
            if (arr[i] == val)
                return i;
        return -1;
    }
}

