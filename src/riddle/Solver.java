package riddle;

import java.util.*;
import static riddle.RiddleEnums.*;
import static riddle.Permutation.*;
import static riddle.Printer.*;


/*
 Main class that solves Einstein's Riddle
 
 This solver uses a brute-force approach with pruning:
 it generates all possible permutations for each property
 (Color, Nation, Drink, Smoke, Pet), and filters them step by step
 according to the 19 logical constraints of the riddle.

 Each constraint is applied as early as possible to reduce the
 total search space and improve performance.
*/

public class Solver {
	
    static int N = 5; // number of houses
    
    public static void main(String[] args) {
    	
        // STEP 1: Generate Colors
        List<Color[]> colorPerms = new ArrayList<>();
        permute(Color.values(), new Color[N], new boolean[N], 0, colorPerms);
        
        // Apply immediate color constraint (Rule #16)
        // Rule #16: "Das grüne Haus steht unmittelbar links vom weißen Haus."
        colorPerms.removeIf(colors -> {
            int greenIdx = indexOf(colors, Color.GREEN);
            int whiteIdx = indexOf(colors, Color.WHITE);
            if (!(greenIdx != -1 && whiteIdx != -1 && greenIdx + 1 == whiteIdx)) return true;
            return false;
        });

        // STEP 2: Generate Nationalities
        List<Nation[]> nationPerms = new ArrayList<>();
        // Rule #11: "Der Norweger wohnt im ersten Haus."
        permuteFixFirst(Nation.values(), Nation.NORWEGIAN, new Nation[N], new boolean[N], 0, nationPerms);

        // STEP 3: Generate Drinks
        List<Drink[]> drinkPerms = new ArrayList<>();
        permute(Drink.values(), new Drink[N], new boolean[N], 0, drinkPerms);
        // Additional drink rules will be checked later in nested loops.

        // STEP 4: Generate Smokes
        List<Smoke[]> smokePerms = new ArrayList<>();
        permute(Smoke.values(), new Smoke[N], new boolean[N], 0, smokePerms);

        // STEP 5: Generate Pets
        List<Pet[]> petPerms = new ArrayList<>();
        permute(Pet.values(), new Pet[N], new boolean[N], 0, petPerms);

        int solutions = 0;

        // STEP 6: Combine permutations and apply rules
        for (Color[] colors : colorPerms) {
            for (Nation[] nations : nationPerms) {
            	
                // Rule #12: "Der Norweger wohnt neben dem blauen Haus."
                int norIdx = indexOf(nations, Nation.NORWEGIAN);
                int blueIdx = indexOf(colors, Color.BLUE);
                if (norIdx == -1 || blueIdx == -1 || Math.abs(norIdx - blueIdx) != 1) continue;
                
                // Rule #5: "Der Brite lebt im roten Haus."
                int britIdx = indexOf(nations, Nation.BRIT);
                if (britIdx == -1 || colors[britIdx] != Color.RED) continue;
                
                // Check Drink constraints
                for (Drink[] drinks : drinkPerms) {
                	
                    // Rule #7: "Der Däne trinkt gerne Tee."
                    int daneIdx = indexOf(nations, Nation.DANE);
                    if (daneIdx == -1 || drinks[daneIdx] != Drink.TEA) continue;
                    
                    // Rule #9: "Der Besitzer des grünen Hauses trinkt Kaffee."
                    int greenIdx = indexOf(colors, Color.GREEN);
                    if (greenIdx == -1 || drinks[greenIdx] != Drink.COFFEE) continue;
                    
                    // Rule #15: "Der Mann, der im mittleren Haus wohnt, trinkt Milch."
                    if (drinks[2] != Drink.MILK) continue;

                    // Check Smoke constraints
                    for (Smoke[] smokes : smokePerms) {
                    	
                        // Rule #8: "Der Deutsche raucht Rothmanns."
                        int germanIdx = indexOf(nations, Nation.GERMAN);
                        if (germanIdx == -1 || smokes[germanIdx] != Smoke.ROTHMANS) continue;
                        
                        // Rule #13: "Der Besitzer des gelben Hauses raucht Dunhill."
                        int yellowIdx = indexOf(colors, Color.YELLOW);
                        if (yellowIdx == -1 || smokes[yellowIdx] != Smoke.DUNHILL) continue;
                        
                        // Rule #10: "Der Winfield-Raucher trinkt gerne Bier."
                        int winfieldIdx = indexOf(smokes, Smoke.WINFIELD);
                        if (winfieldIdx == -1 || drinks[winfieldIdx] != Drink.BEER) continue;

                        // Check Pet constraints
                        for (Pet[] pets : petPerms) {
                        	
                            // Rule #6: "Der Schwede hält einen Hund."
                            int swedeIdx = indexOf(nations, Nation.SWEDE);
                            if (swedeIdx == -1 || pets[swedeIdx] != Pet.DOG) continue;
                            
                            // Rule #14: "Die Person, die Pall Mall raucht, hält einen Vogel."
                            int pallIdx = indexOf(smokes, Smoke.PALLMALL);
                            if (pallIdx == -1 || pets[pallIdx] != Pet.BIRD) continue;
                            
                            // Rule #17: "Der Marlboro-Raucher wohnt neben dem, der eine Katze hält."
                            int marlboroIdx = indexOf(smokes, Smoke.MARLBORO);
                            int catIdx = indexOf(pets, Pet.CAT);
                            if (marlboroIdx == -1 || catIdx == -1 || Math.abs(marlboroIdx - catIdx) != 1) continue;
                            
                            // Rule #18: "Der Marlboro-Raucher hat einen Nachbarn, der Wasser trinkt."
                            int waterIdx = indexOf(drinks, Drink.WATER);
                            if (waterIdx == -1 || Math.abs(marlboroIdx - waterIdx) != 1) continue;
                            
                            // Rule #19: "Der Mann, der ein Pferd hält, wohnt neben dem, der Dunhill raucht."
                            int horseIdx = indexOf(pets, Pet.HORSE);
                            int dunIdx = indexOf(smokes, Smoke.DUNHILL);
                            if (horseIdx == -1 || dunIdx == -1 || Math.abs(horseIdx - dunIdx) != 1) continue;

                            // All constraints satisfied -> valid solution
                            solutions++;
                            System.out.println("Solution #" + solutions);
                            printSolution(colors, nations, drinks, smokes, pets);

                            // Identify who owns the fish (the final question)
                            int fishOwner = indexOf(pets, Pet.FISH);
                            if (fishOwner != -1) {
                                System.out.println("\nThe owner of the FISH is: " + nations[fishOwner] + "\n");
                            } else {
                                System.out.println("No Fish in this Solution.\n");
                            }
                        }
                    }
                }
            }
        }

        System.out.println("Found Solutions: " + solutions);
    }
}
