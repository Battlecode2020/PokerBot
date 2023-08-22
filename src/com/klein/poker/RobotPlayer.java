package com.klein.poker;

public class RobotPlayer extends Player{
    Range oppRange;
    Range myRange;
    boolean bluff = false;
    @Override
    public void doTurn(Game game) {
        if(handState == HandState.ALL_IN){
            return;
        }
        if(game.isSomeoneAllIn()){
            int amountToCall = game.getMaxAmount() - potInvestment;
            if(game.getRound()==RoundOfPlay.PRE_FLOP){
                continuePlay(game);
                return;
            }
            if(game.getRound() == RoundOfPlay.FLOP){
                oppRange.removeCard(game.getFirstFlop());
                oppRange.removeCard(game.getSecondFlop());
                oppRange.removeCard(game.getThirdFlop());
                double odds = oddsVsRangeFlop(game, oppRange);
                if(odds > ((double) game.getPot() / (double) (2 * game.getPot() + amountToCall))){
                    call(amountToCall);
                } else{
                    fold();
                }
                return;
            }
            if(game.getRound() == RoundOfPlay.TURN){
                oppRange.removeCard(game.getTurn());
                double odds = oddsVsRangeTurn(game, oppRange);
                if(odds > ((double) game.getPot() / (double) (2 * game.getPot() + amountToCall))){
                    call(amountToCall);
                } else{
                    fold();
                }
                return;
            }
            if(game.getRound() == RoundOfPlay.RIVER){
                oppRange.removeCard(game.getRiver());
                double odds = oddsVsRangeRiver(game, oppRange);
                if(odds > ((double) game.getPot() / (double) (2 * game.getPot() + amountToCall))){
                    call(amountToCall);
                } else{
                    fold();
                }
                return;

            }
        }
        System.out.println("Pot is " + game.getPot());
        if(game.getRound() == RoundOfPlay.PRE_FLOP){
            continuePlay(game);
            return;
        }
        if(game.getRound() == RoundOfPlay.FLOP){
            System.out.print(ANSI_RED + "computer is thinking..." + ANSI_RESET);
            oppRange.removeCard(game.getFirstFlop());
            oppRange.removeCard(game.getSecondFlop());
            oppRange.removeCard(game.getThirdFlop());
            double odds = oddsVsRangeFlop(game, oppRange);
            for(int i = 0; i<23; i++){
                System.out.print("\b");
            }
            if(Math.random() > .8){
                if(odds > .6){
                    continuePlay(game);
                    return;
                } else if(odds < .35){
                    weakPlay(game);
                    return;
                } else {
                    continuePlay(game);
                    return;
                }
            }
            if(odds > .6){
                raise(game.getPot());
            } else if(odds < .4){
                if(bluff && Math.random() > 0.3){
                    aggressivePlay(game);
                } else{
                    weakPlay(game);
                }
            } else {
                continuePlay(game);
            }

        }
        if(game.getRound() == RoundOfPlay.TURN){
            oppRange.removeCard(game.getTurn());
            double odds = oddsVsRangeTurn(game, oppRange);
            if(Math.random() > .8){
                if(odds > .6){
                    continuePlay(game);
                    return;
                } else if(odds < .35){
                    weakPlay(game);
                    return;
                } else {
                    continuePlay(game);
                    return;
                }
            }
            if(odds > .7){
                aggressivePlay(game);
            } else if(odds < .3){
                if(bluff && Math.random() > 0.3){
                    aggressivePlay(game);
                } else{
                        weakPlay(game);
                }
            } else {
                continuePlay(game);
            }

        }
        if(game.getRound() == RoundOfPlay.RIVER){
            oppRange.removeCard(game.getRiver());
            double odds = oddsVsRangeRiver(game, oppRange);

            if(odds > .75){
                aggressivePlay(game);
            } else if(odds < .4 && odds > .2){
                weakPlay(game);
            } else if(odds < .2){
                if(bluff){
                    aggressivePlay(game);
                } else{
                    weakPlay(game);
                }
            } else {
                continuePlay(game);
            }
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
    public void continuePlay(Game game){
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
    public void weakPlay(Game game){
        if(potInvestment < game.getMaxAmount()){
            fold();
        } else{
            check();
        }
    }
    @Override
    public void joinHand(){
        handState = HandState.IN_HAND;
        potInvestment = 0;
        this.oppRange = new Range(hand);
        if(Math.random() > .85){
            bluff = true;
        }
    }
    public void aggressivePlay(Game game){
        if((chipStack + potInvestment) < game.getMaxAmount()){
            call(chipStack);
        } else {
            raise(game.getPot());
        }
    }
}
