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
    public boolean equals(PersonalHand other){
        if(getCardOne().equals(other.getCardOne()) && getCardTwo().equals(other.getCardTwo())){
            return true;
        }
        if(getCardOne().equals(other.getCardTwo()) && getCardTwo().equals(other.getCardOne())){
            return true;
        }
        return false;
    }
    public boolean hasCard(PokerCard card){
        if(getCardOne().equals(card) || getCardTwo().equals(card)){
            return true;
        }
        return false;
    }
}
