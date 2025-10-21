package riddle;

import static riddle.RiddleEnums.*;

public class Printer {
    public static final int N = 5;
    
    public static void printSolution(Color[] colors, Nation[] nations, Drink[] drinks, Smoke[] smokes, Pet[] pets) {
        for (int i = 0; i < N; i++) {
            System.out.printf("%2d - %7s - %9s - %9s - %9s - %7s\n", i + 1, colors[i], nations[i], drinks[i], smokes[i], pets[i]);
        }
    }
}
