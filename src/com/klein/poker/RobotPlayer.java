package com.klein.poker;

public class RobotPlayer extends Player{
    @Override
    public void doTurn(Game game) {
        if(handState == HandState.ALL_IN){
            return;
        }
        if(potInvestment < game.getMaxAmount()){
            call(game.getMaxAmount()-potInvestment);
        } else{
            check();
        }
    }
    @Override
    public boolean isHuman(){
        return false;
    }
    @Override
    public void describeHand(){
        System.out.println("Computer has the " + hand.toString());
    }
    @Override
    public String getName(){
        return "Computer is";
    }
    @Override
    public String getNameFinal(){
        return "Computer";
    }
}
