package com.klein.poker;

public abstract class Player {
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
        handState = HandState.NOT_IN_HAND;
    }

    public int getPotInvestment() {
        return potInvestment;
    }

    public void check() {

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
            potInvestment += chipStack;
            chipStack = 0;
            handState = HandState.ALL_IN;
            return;
        }
        if (amount == chipStack) {
            potInvestment = potInvestment + chipStack;
            chipStack = 0;
            handState = HandState.ALL_IN;
            return;
        }
        chipStack = chipStack - amount;
        potInvestment += amount;
    }

    public void call(int amountNeeded) {
        if (amountNeeded >= chipStack) {
            handState = HandState.ALL_IN;
            potInvestment += chipStack;
            chipStack = 0;
            return;
        }
        potInvestment += amountNeeded;
        chipStack = chipStack - amountNeeded;
    }

    public int getChipStack() {
        return chipStack;
    }

    public abstract boolean isHuman();

    public abstract void describeHand();
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
    public abstract String getNameFinal();
    public void receivePot(int pot){
        System.out.println(getName() + "receives pot of " + pot);
        chipStack += pot;
    }
}
