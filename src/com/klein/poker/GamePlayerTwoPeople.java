package com.klein.poker;

import com.klein.poker.hands.HandStrength;

import java.util.ArrayList;

public class GamePlayerTwoPeople {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static void main(String[] args) throws InterruptedException{
        boolean play = true;
        System.out.println("Hello, welcome to the game of poker!");
        Player human = new HumanPlayer();
        Player robot = new RobotPlayer();
        ArrayList<Player> players = new ArrayList<>();
        if(Math.random() > 0.5){
            players.add(human);
            players.add(robot);
        } else {
            players.add(robot);
            players.add(human);
        }
        while(play && human.getChipStack() > 0 && robot.getChipStack() > 0){
            Game game = new Game(players);
            for(Player player : players){
                player.receiveHand(new PersonalHand(game.getDeck().dealCard(),game.getDeck().dealCard()));
                player.joinHand();
                System.out.println(player.getNameFinal() + " has " + player.getChipStack());
                if(player.isHuman()){
                    player.describeHand();
                } else{
                    //player.describeHand();
                }
            }
            players.get(0).isSmallBlind(game);
            players.get(1).isBigBlind(game);
            PokerCard card1 = game.getDeck().dealCard();
            PokerCard card2 = game.getDeck().dealCard();
            PokerCard card3 = game.getDeck().dealCard();
            PokerCard card4 = game.getDeck().dealCard();
            PokerCard card5 = game.getDeck().dealCard();
            game.addCards(card1,card2,card3,card4,card5);

            game.setRound(RoundOfPlay.PRE_FLOP);

            players.get(0).doTurn(game);
            players.get(1).doTurn(game);
            while((players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment()) || (players.get(1).getHandState() == HandState.ALL_IN && players.get(1).getPotInvestment() > players.get(0).getPotInvestment()) ){
                players.get(0).doTurn(game);
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND || players.get(0).getPotInvestment() == players.get(1).getPotInvestment()){
                    break;
                }
                players.get(1).doTurn(game);
            }
            if(players.get(0).getHandState() == HandState.NOT_IN_HAND || players.get(1).getHandState() == HandState.NOT_IN_HAND){
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND){
                    players.get(1).receivePot(game.getPot());
                } else{
                    players.get(0).receivePot(game.getPot());
                }
                reshuffle();
                players.add(2, players.get(1));
                players.add(3, players.get(0));
                players.remove(0);
                players.remove(0);
                continue;
            }

            game.setRound(RoundOfPlay.FLOP);

            System.out.println("\nThe flop is:\n" + card1 + "\n" +card2 + "\n" +card3);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment() || (players.get(0).getHandState() == HandState.ALL_IN && players.get(0).getPotInvestment() > players.get(1).getPotInvestment())){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND || players.get(0).getPotInvestment() == players.get(1).getPotInvestment()){
                    break;
                }
                players.get(0).doTurn(game);
            }
            if(players.get(0).getHandState() == HandState.NOT_IN_HAND || players.get(1).getHandState() == HandState.NOT_IN_HAND){
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND){
                    players.get(1).receivePot(game.getPot());
                } else{
                    players.get(0).receivePot(game.getPot());
                }
                reshuffle();
                players.add(2, players.get(1));
                players.add(3, players.get(0));
                players.remove(0);
                players.remove(0);
                continue;
            }

            game.setRound(RoundOfPlay.TURN);

            System.out.println("\nThe turn is:\n" + card4);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment() || (players.get(0).getHandState() == HandState.ALL_IN && players.get(0).getPotInvestment() > players.get(1).getPotInvestment())){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND || players.get(0).getPotInvestment() == players.get(1).getPotInvestment()){
                    break;
                }
                players.get(0).doTurn(game);
            }
            if(players.get(0).getHandState() == HandState.NOT_IN_HAND || players.get(1).getHandState() == HandState.NOT_IN_HAND){
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND){
                    players.get(1).receivePot(game.getPot());
                } else{
                    players.get(0).receivePot(game.getPot());
                }
                reshuffle();
                players.add(2, players.get(1));
                players.add(3, players.get(0));
                players.remove(0);
                players.remove(0);
                continue;
            }

            game.setRound(RoundOfPlay.RIVER);

            System.out.println("\nThe river is:\n" + card5);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment() || (players.get(0).getHandState() == HandState.ALL_IN && players.get(0).getPotInvestment() > players.get(1).getPotInvestment())){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND || players.get(0).getPotInvestment() == players.get(1).getPotInvestment()){
                    break;
                }
                players.get(0).doTurn(game);
            }
            if(players.get(0).getHandState() == HandState.NOT_IN_HAND || players.get(1).getHandState() == HandState.NOT_IN_HAND){
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND){
                    players.get(1).receivePot(game.getPot());
                } else{
                    players.get(0).receivePot(game.getPot());
                }
                reshuffle();
                players.add(2, players.get(1));
                players.add(3, players.get(0));
                players.remove(0);
                players.remove(0);
                continue;
            }
            SevenHand zero = new SevenHand(players.get(0).getHand(),card1,card2,card3,card4,card5);
            SevenHand one = new SevenHand(players.get(1).getHand(), card1,card2,card3,card4,card5);
            HandStrength zeroStrength = zero.getBestHand();
            HandStrength oneStrength = one.getBestHand();
            System.out.println(players.get(0).getNameFinal() + " has " + zeroStrength);
            System.out.println(players.get(1).getNameFinal() + " has " + oneStrength);
            for(Player player : players){
                if(!player.isHuman()){
                    player.describeHand();
                }
            }
            if(players.get(0).getPotInvestment() > players.get(1).getPotInvestment()){
                System.out.println(players.get(0).getNameFinal() + " has extra money and receives that as pot.");
                players.get(0).receivePot(players.get(0).getPotInvestment() - players.get(1).getPotInvestment());
                players.get(0).removePotInvestment(players.get(0).getPotInvestment() - players.get(1).getPotInvestment());
            } else if(players.get(1).getPotInvestment() > players.get(0).getPotInvestment()){
                System.out.println(players.get(1).getNameFinal() + " has extra money and receives that as pot.");
                players.get(1).receivePot(players.get(1).getPotInvestment() - players.get(0).getPotInvestment());
                players.get(1).removePotInvestment(players.get(1).getPotInvestment() - players.get(0).getPotInvestment());
            }
            if(zeroStrength.handValue() > oneStrength.handValue()){
                //player zero wins
                System.out.println(players.get(0).getNameFinal() + " wins!");
                players.get(0).receivePot(game.getPot());
            } else if(zeroStrength.handValue() == oneStrength.handValue()){
                if(zeroStrength.compareWithSelf(oneStrength) > 0){
                    System.out.println(players.get(0).getNameFinal() + " wins!");
                    players.get(0).receivePot(game.getPot());
                } else if(zeroStrength.compareWithSelf(oneStrength) == 0){
                    System.out.println("Split pot!");
                    players.get(0).receivePot((int) (game.getPot() / 2));
                    players.get(1).receivePot((int) (game.getPot() / 2));
                } else{
                    System.out.println(players.get(1).getNameFinal() + " wins!");
                    players.get(1).receivePot(game.getPot());
                }
            } else{
                System.out.println(players.get(1).getNameFinal() + " wins!");
                players.get(1).receivePot(game.getPot());
            }
            reshuffle();
            players.add(2, players.get(1));
            players.add(3, players.get(0));
            players.remove(0);
            players.remove(0);
            //hand evaluation


        }
        if(human.getChipStack() > robot.getChipStack()) {
            System.out.println(ANSI_GREEN + "GAME OVER! YOU WIN!!" );
        } else{
            System.out.println(ANSI_RED + "GAME OVER! YOU LOSE! ROBOTS RULE!" );
        }
    }
    public static void reshuffle() throws InterruptedException{
        Thread.sleep(3000);
        for(int i = 0; i <= 20; i++){
            int j = 0;
            if(i > 9){
                j = 20 - i;
            } else{
                j = i;
            }
            String x = "";

            while(j > -1){
                x = x + " ";
                j--;
            }
            System.out.println(ANSI_WHITE + x + "RESHUFFLING!!!!" + ANSI_RESET);
            Thread.sleep(150);
        }


    }
}
