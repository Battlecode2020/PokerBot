package com.klein.poker;

import com.klein.poker.hands.HandStrength;

public abstract class Player {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    int chipStack = 100000;
    PersonalHand hand;
    HandState handState;
    boolean isTurn;
    int potInvestment = 0; //amount you have put in the pot so far.

    public abstract void doTurn(Game game);

    public HandState getHandState() {
        return handState;
    }

    public void fold() {

        System.out.println(ANSI_BLUE + getNameFinal() + " folded" + ANSI_RESET);
        handState = HandState.NOT_IN_HAND;
    }

    public int getPotInvestment() {
        return potInvestment;
    }

    public void check() {
        System.out.println(ANSI_BLUE + getNameFinal() + " checked" + ANSI_RESET);
    }
    public void joinHand(){
        handState = HandState.IN_HAND;
        potInvestment = 0;
    }
    public PersonalHand getHand(){
        return hand;
    }

    public void receiveHand(PersonalHand hand) {
        this.hand = hand;
    }

    //make sure reraises are possible
    public void raise(int amount) {
        if (amount > chipStack) {
            System.out.println("That is too big of a raise. You are now all in");
            System.out.println(ANSI_BLUE + getNameFinal() + " raised all in" + ANSI_RESET);
            potInvestment += chipStack;
            chipStack = 0;
            handState = HandState.ALL_IN;
            return;
        }
        if (amount == chipStack) {
            potInvestment = potInvestment + chipStack;
            chipStack = 0;
            handState = HandState.ALL_IN;
            System.out.println(ANSI_BLUE + getNameFinal() + " raised all in" + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_BLUE + getNameFinal() + " raised " + amount + ANSI_RESET);
        chipStack = chipStack - amount;
        potInvestment += amount;
    }

    public void call(int amountNeeded) {
        if (amountNeeded >= chipStack) {
            int x = chipStack;
            handState = HandState.ALL_IN;
            potInvestment += chipStack;
            chipStack = 0;
            System.out.println(ANSI_BLUE + getNameFinal() + " called for " + x + " and is now all in" + ANSI_RESET);
            return;
        }
        System.out.println(ANSI_BLUE + getNameFinal() + " called for " + amountNeeded + ANSI_RESET);
        potInvestment += amountNeeded;
        chipStack = chipStack - amountNeeded;
    }

    public int getChipStack() {
        return chipStack;
    }

    public abstract boolean isHuman();

    public abstract void describeHand() throws InterruptedException;
    public abstract String getName();
    public void isSmallBlind(Game game){
        if(chipStack <= game.getSmallBlind()){
            handState = HandState.ALL_IN;
            potInvestment += chipStack;
            chipStack = 0;
            System.out.println(getName() + " all in due to small blind");
            return;
        }
        potInvestment += game.getSmallBlind();
        chipStack = chipStack - game.getSmallBlind();

    }
    public void isBigBlind(Game game){
        if(chipStack <= game.getBigBlind()){
            handState = HandState.ALL_IN;
            potInvestment += chipStack;
            chipStack = 0;
            System.out.println(getName() + " all in due to big blind");
            return;
        }
        potInvestment += game.getBigBlind();
        chipStack = chipStack - game.getBigBlind();
    }
    public void removePotInvestment(int amount){
        potInvestment = potInvestment - amount;
    }
    public abstract String getNameFinal();
    public void receivePot(int pot){
        System.out.println(getName() + "receives pot of " + pot);
        chipStack += pot;
    }
    public double getOddsFlop(Game game, PersonalHand opponent){
        double sum = 0.0;
        int rounds = 0;
        PokerCard turn;
        PokerCard river;
        Deck deck = new Deck();
        deck.remove(game.getFirstFlop());
        deck.remove(game.getSecondFlop());
        deck.remove(game.getThirdFlop());
        deck.remove(hand.getCardOne());
        deck.remove(hand.getCardTwo());
        deck.remove(opponent.getCardOne());
        deck.remove(opponent.getCardTwo());
        for(int a = 0; a < deck.getSize(); a++){
            for(int b = a + 1; b < deck.getSize(); b++){
                turn = deck.getCard(a);
                river = deck.getCard(b);
                SevenHand mine = new SevenHand(hand, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),turn,river);
                SevenHand opp = new SevenHand(opponent, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),turn,river);
                HandStrength myStrength = mine.getBestHand();
                HandStrength oppStrength = opp.getBestHand();
                if(myStrength.handValue() > oppStrength.handValue()){
                    sum = sum +1.0;
                    rounds++;
                } else if(oppStrength.handValue() > myStrength.handValue()){
                    rounds++;
                } else{
                    if(myStrength.compareWithSelf(oppStrength) > 0){
                        sum = sum + 1.0;
                        rounds++;
                    } else if(myStrength.compareWithSelf(oppStrength) == 0){
                        sum = sum + 0.5;
                        rounds++;
                    } else{
                        rounds++;
                    }
                }
            }
        }
        return (sum / ((double) rounds)) ;
    }
    public double getOddsTurn(Game game, PersonalHand opponent) {
        double sum = 0.0;
        int rounds = 0;
        PokerCard river;
        Deck deck = new Deck();
        deck.remove(game.getFirstFlop());
        deck.remove(game.getSecondFlop());
        deck.remove(game.getThirdFlop());
        deck.remove(game.getTurn());
        deck.remove(hand.getCardOne());
        deck.remove(hand.getCardTwo());
        deck.remove(opponent.getCardOne());
        deck.remove(opponent.getCardTwo());
        for(int a = 0; a < deck.getSize(); a++){
                river = deck.getCard(a);
                SevenHand mine = new SevenHand(hand, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),game.getTurn(),river);
                SevenHand opp = new SevenHand(opponent, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),game.getTurn(),river);
                HandStrength myStrength = mine.getBestHand();
                HandStrength oppStrength = opp.getBestHand();
                if(myStrength.handValue() > oppStrength.handValue()){
                    sum = sum +1.0;
                    rounds++;
                } else if(oppStrength.handValue() > myStrength.handValue()){
                    rounds++;
                } else{
                    if(myStrength.compareWithSelf(oppStrength) > 0){
                        sum = sum + 1.0;
                        rounds++;
                    } else if(myStrength.compareWithSelf(oppStrength) == 0){
                        sum = sum + 0.5;
                        rounds++;
                    } else{
                        rounds++;
                    }
                }
        }
        return (sum / ((double) rounds)) ;

    }
    //make simpler
    public double getOddsRiver(Game game, PersonalHand opponent){
        double sum = 0.0;
        int rounds = 0;
        Deck deck = new Deck();
        deck.remove(game.getFirstFlop());
        deck.remove(game.getSecondFlop());
        deck.remove(game.getThirdFlop());
        deck.remove(game.getTurn());
        deck.remove(game.getRiver());
        deck.remove(hand.getCardOne());
        deck.remove(hand.getCardTwo());
        deck.remove(opponent.getCardOne());
        deck.remove(opponent.getCardTwo());
                SevenHand mine = new SevenHand(hand, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),game.getTurn(),game.getRiver());
                SevenHand opp = new SevenHand(opponent, game.getFirstFlop(),game.getSecondFlop(),game.getThirdFlop(),game.getTurn(),game.getRiver());
                HandStrength myStrength = mine.getBestHand();
                HandStrength oppStrength = opp.getBestHand();
                if(myStrength.handValue() > oppStrength.handValue()){
                    sum = sum +1.0;
                    rounds++;
                } else if(oppStrength.handValue() > myStrength.handValue()){
                    rounds++;
                } else{
                    if(myStrength.compareWithSelf(oppStrength) > 0){
                        sum = sum + 1.0;
                        rounds++;
                    } else if(myStrength.compareWithSelf(oppStrength) == 0){
                        sum = sum + 0.5;
                        rounds++;
                    } else{
                        rounds++;
                    }
        }
        return (sum / ((double) rounds)) ;
    }
}
