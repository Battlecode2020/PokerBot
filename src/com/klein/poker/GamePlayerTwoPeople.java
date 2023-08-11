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
    public static void main(String[] args){
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
                System.out.println(player.getName() + "has" + player.getChipStack());
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
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment()){
                players.get(0).doTurn(game);
                if(players.get(0).getHandState() == HandState.NOT_IN_HAND){
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
                System.out.println(ANSI_WHITE + "RESHUFFLING!!!!" + ANSI_RESET);
                continue;
            }
            System.out.println("\nThe flop is:\n" + card1 + "\n" +card2 + "\n" +card3);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment()){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND){
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
                System.out.println(ANSI_WHITE + "RESHUFFLING!!!!" + ANSI_RESET);
                continue;
            }
            System.out.println("\nThe turn is:\n" + card4);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment()){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND){
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
                System.out.println(ANSI_WHITE + "RESHUFFLING!!!!" + ANSI_RESET);
                continue;
            }
            System.out.println("\nThe river is:\n" + card5);
            players.get(1).doTurn(game);
            players.get(0).doTurn(game);
            while(players.get(0).getHandState() == HandState.IN_HAND && players.get(1).getHandState() == HandState.IN_HAND && players.get(0).getPotInvestment() != players.get(1).getPotInvestment()){
                players.get(1).doTurn(game);
                if(players.get(1).getHandState() == HandState.NOT_IN_HAND){
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
                System.out.println(ANSI_WHITE + "RESHUFFLING!!!!" + ANSI_RESET);
                continue;
            }
            SevenHand zero = new SevenHand(players.get(0).getHand(),card1,card2,card3,card4,card5);
            SevenHand one = new SevenHand(players.get(1).getHand(), card1,card2,card3,card4,card5);
            HandStrength zeroStrength = zero.getBestHand();
            HandStrength oneStrength = one.getBestHand();
            System.out.println(players.get(0).getName() + " has " + zeroStrength);
            System.out.println(players.get(1).getName() + " has " + oneStrength);
            for(Player player : players){
                if(!player.isHuman()){
                    player.describeHand();
                }
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
            //hand evaluation


        }
    }
}
