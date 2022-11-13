import java.util.Random;

/**
 *
 * @author Karina
 */
public final class Memory {
    
    private static Memory instance;
    private final String player1 = "Maria";

    private Memory() { } 
    
    public static Memory getInstance() {
        if (instance==null) {
            instance= new Memory();
        }
        return instance;
    }
    
    public String getPlayer1() {
        return player1;
    }
    
    public int[] getCardNumbers() {
        
        int[] numbers = new int[16];
        int count = 0;

        while (count < 16) {
            Random r = new Random();
            int na = r.nextInt(8) + 1;
            int nvr = 0;
            
            for(int i = 0; i < 16; i++ ) {
                if(numbers[i] == na) {
                    nvr++;
                }
            }
            if(nvr < 2) {
                numbers[count] = na;
                count++;
            }
        }
        return numbers;
    } 
}
