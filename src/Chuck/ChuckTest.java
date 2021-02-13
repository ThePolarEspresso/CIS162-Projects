package Chuck;

/**
 * Write a description of class ChuckTest here.
 * 
 * @author Ana Posada
 * @version September 14, 2013
 */
public class ChuckTest {

    public static void main(String [] args){
        int before = 0;

        final int ONES = 1;
        final int TWOS = 2;
        final int THREES= 3;
        final int FOURS = 4;
        final int FIVES = 5;
        final int SIXES = 6;
        final int FIELD = 7;
        final int SMALL = 8;
        final int LARGE = 9;
/**
        Chuck game = new Chuck();

        System.out.println("Testing begins...");
        assert game.getCredits() == 10 : "credits should start at 10";   

        // wins bet on Large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.testRoll(new int [] {6,3,3});  
        assert game.getCredits() == before + 1 : "should have won betting on Large";
             
        // loses bet on Large
        before = game.getCredits();
        game.placeBet(LARGE);
        game.testRoll(new int [] {2,3,3});       
        assert game.getCredits() == before - 1 : "should have lost betting on Large";
        
*/
        // =================== Phase 1 Testing ===================
        
        Chuck chuck = new Chuck();
        
        System.out.println("Testing getMessage()");
        System.out.println("===================");
        System.out.println("Correct message: Welcome to Chuck a luck! || Actual message: " + chuck.getMessage() + "\n");
        
        System.out.println("Testing getCredits()");
        System.out.println("====================");
        System.out.println("Correct credits: 10 || Actual credits: " + chuck.getCredits() + "\n");
        
        System.out.println("Testing diceToString()");
        System.out.println("=================");
        System.out.println("Should be in the form [#, #, #] with numbers 1-6: " + chuck.diceToString() + "\n");
          
        System.out.println("Testing completed.");
        
        // =================== Phase 2 & 3 Testing ===================
        
        chuck.addCredits(10);
        System.out.println("Testing addCredits(int amount)");
        System.out.println("==============================");
        System.out.println("Correct credits: 20 || Actual credits: " + chuck.getCredits() + "\n");
        
        chuck.roll();
    }
}
