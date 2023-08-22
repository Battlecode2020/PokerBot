package com.klein.poker;

import java.util.ArrayList;
import java.util.Collections;

public class Range {
    ArrayList<PersonalHand> hands;
    public Range(PersonalHand myHand){
        hands = new ArrayList<>();
        Deck deck = new Deck();
        deck.remove(myHand.getCardOne());
        deck.remove(myHand.getCardTwo());
        for(int a = 0; a < deck.getSize(); a++){
            for(int b = a+1; b < deck.getSize(); b++){
                hands.add(new PersonalHand(deck.getCard(a),deck.getCard(b) ));
            }
        }
    }
    public void removeCard(PokerCard card){
        for(int i = hands.size()-1; i >= 0; i--){
            if(hands.get(i).hasCard(card)){
                hands.remove(i);
            }
        }
    }
    public void removeHand(PersonalHand hand){
        for(int i = hands.size()-1; i >= 0; i--){
            if(hands.get(i).equals(hand)){
                hands.remove(i);
                return;
            }
        }
    }
    public ArrayList<PersonalHand> getRange(){
        return hands;
    }
    //should return the top percent of personal hands in the Range
    public void topPercentage(int percent){
    }
    //should return the bottom percent of personal hands in the range
    public void bottomPercentage(int percent){

    }
}
