package com.klein.poker;

public class PersonalHand {
    PokerCard cardOne;
    PokerCard cardTwo;
    public PersonalHand(PokerCard cardOne, PokerCard cardTwo){
        this.cardOne = cardOne;
        this.cardTwo = cardTwo;
    }
    public String toString(){
        return cardOne.toString() + "\n and the " + cardTwo.toString();
    }
    public PokerCard getCardOne(){
        return cardOne;
    }
    public PokerCard getCardTwo(){
        return cardTwo;
    }
}
