package com.klein.poker;

public class RobotPlayer extends Player{
    @Override
    public void doTurn(Game game) {
        if(handState == HandState.ALL_IN){
            return;
        }
        Range oppRange = new Range(hand);
        if(game.getRound() == RoundOfPlay.PRE_FLOP){
            passivePlay(game);
            return;
        }
        if(game.getRound() == RoundOfPlay.FLOP){
            passivePlay(game);
            oppRange.removeCard(game.getFirstFlop());
            oppRange.removeCard(game.getSecondFlop());
            oppRange.removeCard(game.getThirdFlop());
            System.out.println(oddsVsRangeFlop(game, oppRange));
        }
        if(game.getRound() == RoundOfPlay.TURN){
            passivePlay(game);
            oppRange.removeCard(game.getFirstFlop());
            oppRange.removeCard(game.getSecondFlop());
            oppRange.removeCard(game.getThirdFlop());
            oppRange.removeCard(game.getTurn());
            System.out.println(oddsVsRangeTurn(game, oppRange));
        }
        if(game.getRound() == RoundOfPlay.RIVER){
            passivePlay(game);
            oppRange.removeCard(game.getFirstFlop());
            oppRange.removeCard(game.getSecondFlop());
            oppRange.removeCard(game.getThirdFlop());
            oppRange.removeCard(game.getTurn());
            oppRange.removeCard(game.getRiver());
            System.out.println(oddsVsRangeRiver(game, oppRange));
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
    public double oddsVsRangeFlop(Game game, Range range){
        double sum = 0.0;
        int hands = 0;
        for(int i = 0; i < range.getRange().size(); i++){
            sum = sum + getOddsFlop(game, range.getRange().get(i));
            hands++;
        }
        return sum / ((double) hands);
    }
    public double oddsVsRangeTurn(Game game, Range range){
        double sum = 0.0;
        int hands = 0;
        for(int i = 0; i < range.getRange().size(); i++){
            sum = sum + getOddsTurn(game, range.getRange().get(i));
            hands++;
        }
        return sum / ((double) hands);
    }
    public double oddsVsRangeRiver(Game game, Range range){
        double sum = 0.0;
        int hands = 0;
        for(int i = 0; i < range.getRange().size(); i++){
            sum = sum + getOddsRiver(game, range.getRange().get(i));
            hands++;
        }
        return sum / ((double) hands);
    }
}
