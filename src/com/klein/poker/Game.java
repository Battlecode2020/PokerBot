package com.klein.poker;

import java.util.ArrayList;

public class Game {
    int smallBlind = 2;
    int bigBlind = 5;
    int ante = 0;
    int maxAmount;
    int pot;
    int turnToAct;
    Deck deck;
    PokerCard card1;
    PokerCard card2;
    PokerCard card3;
    PokerCard card4;
    PokerCard card5;
    ArrayList<Player> playersInGame;
    RoundOfPlay round;
    //In the arraylist of players, the 0th entry is the button
    public Game(ArrayList<Player> players){
        deck = new Deck();
        pot += smallBlind;
        pot += bigBlind;
        playersInGame = new ArrayList<>();
        for(Player player : players){
            playersInGame.add(player);
        }

    }

    public int getMaxAmount() {
        maxAmount = -1;
        for(Player player : playersInGame){
            if(player.getPotInvestment() > maxAmount){
                maxAmount = player.getPotInvestment();
            }
        }
        return maxAmount;
    }

    public Deck getDeck(){
        return deck;
    }
    public int getSmallBlind(){
        return smallBlind;
    }
    public int getBigBlind(){
        return bigBlind;
    }
    public int getPot(){
        int x = 0;
        for(Player player : playersInGame){
            x += player.getPotInvestment();
        }
        return x;
    }
    public void addCards(PokerCard card1, PokerCard card2, PokerCard card3, PokerCard card4, PokerCard card5){
        this.card1 = card1;
        this.card2 = card2;
        this.card3 = card3;
        this.card4 = card4;
        this.card5 = card5;
    }
    public PokerCard getFirstFlop(){
        return card1;
    }
    public PokerCard getSecondFlop(){
        return card2;
    }
    public PokerCard getThirdFlop(){
        return card3;
    }
    public PokerCard getTurn(){
        return card4;
    }
    public PokerCard getRiver(){
        return card5;
    }
    public RoundOfPlay getRound(){
        return round;
    }
    public void setRound(RoundOfPlay current){
        round = current;
    }
    public boolean isSomeoneAllIn(){
        for(Player player : playersInGame ){
            if(player.getHandState() == HandState.ALL_IN){
                return true;
            }
        }
        return false;
    }

}
