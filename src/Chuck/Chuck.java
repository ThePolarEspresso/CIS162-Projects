package Chuck;

/***********************************************
 * Chuck a Luck dice Game
 * 
 * @author Anna Carvalho
 * @version 10/26/2020
 ***********************************************/
public class Chuck {

    public GVdie [] dice;   /** An array of three Objects of the GVdie class  */
    public boolean [] bets; /** An array of ten booleans values */

    /** 
     * An array of ten booleans values, one for each of the possible nine bets 
     * that are set to true, if the bet has been made. 
     * The order of the bets: 1's , 2's,..., 6's,  field, small, and large   
     */

    public int credits;      /** credit balance  */   
    public String message;   /** stores the current messages from the game  */

    public static final int INITIAL_CREDITS = 10;   /** initial credits */

    /********************************************
     * Constructor for objects of class Chuck
     *******************************************/
    public Chuck(){
        // instantiate the dice array and populate with 3 GVdie objects
        this.dice = new GVdie[3];
        for (int n = 0; n < this.dice.length; n++) {
            this.dice[n] = new GVdie();
        }

        // initialize the Array of bets
        this.bets = new boolean[10];

        // Reset credits to 10; Set all dice to blank; Set all bets elements to false
        this.reset();

    }

    /********************************************************
     * reset for a fresh game.  
     * Set all dice to blank. Set the message, 
     * credits and all bets to appropriate values. 
     ********************************************************************/     
    public void reset () {
        this.setDiceToBlank();
        this.clearAllBets() ;
        this.credits = INITIAL_CREDITS;
        this.message = "Welcome to Chuck a luck!";
    }

    /************************************************
     * getMessage - 
     * @return String - returns the message
     ***********************************************/
    public String getMessage () {
        return this.message;
    }

    /************************************************
     * sets the dice to blank
     ***********************************************/
    private void setDiceToBlank() {
        //for each loop
        for (GVdie d : this.dice ){
            d.setBlank(); 
        }
    }

    /************************************************
     * getCredits - 
     * @return int - current credits of the game
     ***********************************************/    
    public int getCredits () {
        return this.credits;
    }

    /************************************************
     * getCredits - 
     * @return GVdie [] - the array of dice
     * used in the GUI
     ***********************************************/ 
    public GVdie [] getDice () {
        return this.dice;
    }

    /***********************************************************
     * sets all bets to false to start the next round.  
     **********************************************************/        
    public void clearAllBets() {
        for (boolean b : this.bets) {
            b = false;
        }  
    }

    /**
     * Phase 2
     * 
     * Private Helper Methods  
     */

    /***********************************************************  
     *@return int - the sum of the values of the dice 
     **********************************************************/ 
    private int  getSumDiceValues () {
        int sum = 0;

        for (GVdie d : dice) {
            sum += d.getValue();
        }

        return sum;
    }

    /***********************************************************
     * @return  boolean - true if two of the dice match num, 
     * return false otherwise.
     * @param num - number to be matched
     **********************************************************/       
    private boolean isDoubles (int num) {
        int count  = 0;

        for (GVdie d : dice) {
            if (d.getValue() == num) {
                count++;
            }
        }

        return count == 2; 
    }

    /***********************************************************
     *@return  boolean - return true if all three dice are identical, 
     *return false otherwise.
     **********************************************************/
    private boolean isTriplets ( ) {
        return (this.dice[0].getValue() == this.dice[1].getValue()) && (this.dice[0].getValue() == this.dice[2].getValue());
    }

    /***********************************************************  
     *Check if the dice total is greater than 10 and there is 
     *no three-of-a-kind.  If so, increase the credits by 2 and 
     *update the message to tell the player she won. 
     **********************************************************/    
    private void checkLarge( ) {
        int sum = this.getSumDiceValues();

        if (sum > 10 && !this.isTriplets()) {
            this.credits += 2;
            this.message = "You won!";
        }
    }

    /*********************************************************** 
     *Check if the dice total is less than 11 and there is 
     *no three-of-a-kind.  If so, increase the credits by 2 and 
     *update the message to tell the player she won. 
     **********************************************************/    
    private void checkSmall( )  {
        int sum = this.getSumDiceValues();

        if (sum < 11 && !this.isTriplets()) {
            this.credits += 2;
            this.message = "You won!";
        }
    }

    /***********************************************************
     *Check if the dice total is less than 8 or dice total is
     *over twelve.  If so, increase the credits by 2 and 
     *update the message to tell the player she won. 
     **********************************************************/    
    private void checkField( )  {
        if (this.getSumDiceValues() < 8 || this.getSumDiceValues() > 12) {
            this.credits += 2;
            this.message = "You won!";
        }
    }

    /***********************************************************
     *@param num num - number to be checked
     *If all three dice match num, increase credits by 11.  
     *If two dice match num, increase credits by 3.  
     *If one die matches num, increase credits by 2.  
     **********************************************************/     
    private void checkNumber(int num) {
        if (this.isTriplets() && this.dice[0].getValue() == num) {
            this.credits += 11;
        } else if (this.isDoubles(num)) {
            this.credits += 3;
        } else if (this.dice[0].getValue() == num || this.dice[1].getValue() == num || this.dice[2].getValue() == num) {
            this.credits += 2;
        }
    }

    /***********************************************************
     *for each bet that was placed, this method invokes the 
     *appropriate service method described above to check if the bet was won.    
     **********************************************************/    
    private void checkAllBets ( ) {
        message = "Sorry, you lose. Try again";

        for (int i = 1; i < this.bets.length; i++) {
            if (this.bets[i]) {
                this.credits--;
                switch (i) {
                    case 9:
                    checkLarge(); break;
                    case 8:
                    checkSmall(); break;
                    case 7:
                    checkField(); break;
                    default:
                    checkNumber (i);
                }
            }
        }

    }
    
    /**
     * Phase 3
     * 
     * 
     */

    /************************************************
     * addCredits - 
     * @param  amount - amount to be added to credits.
     ***********************************************/    
    public void addCredits (int amount) {
        if (amount > 0) {
            credits += amount;
        }
    }

    /***********************************************************
     * placeBet - Set the element of the array bets 
     * to true based on the number passed to the method.
     * @param index bet number range [1 - 9]
     **********************************************************/        
     public void placeBet (int index){
         if (index >= 1 && index <= 9) {
             bets[index] = true;
         }
    }

    /***********************************************************
     * CancelBet - Set the element of the array bets 
     * to false based on the number passed to the method.
     * @param index bet number range [1 - 9]
     **********************************************************/        
    public void cancelBet (int index) {
        if (index >= 1 && index <= 9) {
             bets[index] = false;
         }
    }

    /***********************************************************
     * roll - Roll the three dice and check each of the bets to 
     * determine which bets, if any, the player won.
     * Afterwards, clear all bets to prepare for the next round.
     **********************************************************/        
    public void roll ( ) {
        for (int i = 0; i < this.dice.length; i++) {
            this.dice[i].roll();
        }
        this.checkAllBets();
        this.clearAllBets();
    }

    /**
     * Preventing player errors
     */

    /***********************************************************
     *@return boolean - true if the player has enough credits 
     *to cover all bets, return false otherwise.    
     **********************************************************/     
    public boolean enoughCredits ( ) {
        int numBets = 0;
        for (boolean b : bets) {
            if (b) {
                numBets++;
            }
        }
        return this.credits >= numBets;
    }

    /***********************************************************
     * diceToString - Used to test
     * @return - String - the dice as a String
     ************************************************************/
    public String diceToString () {

        return ("[" + this.dice[0].getValue() + ", " + this.dice[1].getValue() + ", " + this.dice[2].getValue() + "]");
    }
    
    private void makeCorrections(int[] targetDiceValue) {
        for (int i = 0; i < targetDiceValue.length; i++) {
            if (targetDiceValue[i] < 1 || targetDiceValue[i] > 6) {
                targetDiceValue[i] = 1;
            }
        }
    }

    /***********************************************************
     *@param values [] desired values
     * 
     *rolls each die until the value passed as input parameter
     **********************************************************/         
    public void testRoll(int [] values)  {
        if (this.enoughCredits()) {
            this.makeCorrections(values);
            
            for (int i = 0; i < values.length; i++) {
                this.dice[i].roll();   
                while (this.dice[i].getValue() != values[i]) {
                    dice[i].roll();
                }
            }
            this.checkAllBets();
            this.clearAllBets();
        } else {
            message = "You don't have enough credits";
            this.setDiceToBlank();
        }
    }
}

