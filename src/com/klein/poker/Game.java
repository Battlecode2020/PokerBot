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
    ArrayList<Player> playersInGame;
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

}
