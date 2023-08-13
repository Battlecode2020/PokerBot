package com.klein.poker;

public class RobotPlayer extends Player{
    @Override
    public void doTurn(Game game) {
        if(handState == HandState.ALL_IN){
            return;
        }
        if(game.getRound() == RoundOfPlay.PRE_FLOP){
            passivePlay(game);
            return;
        }
        if(game.getRound() == RoundOfPlay.FLOP){
            passivePlay(game);
            System.out.println(getOddsFlop(game,new PersonalHand(new PokerCard(Suit.SPADES, CardNumber.TWO), new PokerCard(Suit.HEARTS, CardNumber.TWO))));
        }
        if(game.getRound() == RoundOfPlay.TURN){
            passivePlay(game);
            System.out.println(getOddsTurn(game,new PersonalHand(new PokerCard(Suit.SPADES, CardNumber.TWO), new PokerCard(Suit.HEARTS, CardNumber.TWO))));
        }
        if(game.getRound() == RoundOfPlay.RIVER){
            passivePlay(game);
            System.out.println(getOddsRiver(game,new PersonalHand(new PokerCard(Suit.SPADES, CardNumber.TWO), new PokerCard(Suit.HEARTS, CardNumber.TWO))));
        }

    }
    @Override
    public boolean isHuman(){
        return false;
    }
    @Override
    public void describeHand() throws InterruptedException{
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
    public void passivePlay(Game game){
        if(potInvestment < game.getMaxAmount()){
            call(game.getMaxAmount()-potInvestment);
        } else{
            check();
        }
    }
}
