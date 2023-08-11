package com.klein.poker;

public class PokerCard {
    Suit suit;
    CardNumber cardNumber;
    public PokerCard(Suit suit, CardNumber cardNumber) {
        this.suit = suit;
        this.cardNumber = cardNumber;
    }
    public String toString(){
        return this.cardNumber.toString() + " of " + this.suit.toString();
    }
    public CardNumber getCardNumber(){
        return cardNumber;
    }
    public Suit getSuit(){
        return suit;
    }
}
