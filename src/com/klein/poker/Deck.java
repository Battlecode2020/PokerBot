package com.klein.poker;

import java.util.ArrayList;
import java.util.Objects;

public class Deck {
    ArrayList<PokerCard> deck;
    public Deck(){
        deck = new ArrayList<>();
        CardNumber[] number = new CardNumber[]{CardNumber.TWO, CardNumber.THREE, CardNumber.FOUR, CardNumber.FIVE, CardNumber.SIX, CardNumber.SEVEN, CardNumber.EIGHT, CardNumber.NINE, CardNumber.TEN, CardNumber.JACK, CardNumber.QUEEN, CardNumber.KING, CardNumber.ACE};
        Suit[] suits = new Suit[]{Suit.SPADES,Suit.DIAMONDS, Suit.CLUBS, Suit.HEARTS};
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 13; j++){
                deck.add(new PokerCard(suits[i], number[j]));
            }
        }
    }
    public PokerCard dealCard(){
        int x = (int) ( Math.random() * deck.size() );
        PokerCard card = deck.get(x);
        deck.remove(x);
        return card;
    }
    public void remove(PokerCard card){
        for(int i = 0; i < deck.size(); i++){
            if(deck.get(i).equals(card)){
                deck.remove(i);
            }
        }
    }
    public int getSize(){
        return deck.size();
    }
    public PokerCard getCard(int x){
        return deck.get(x);
    }
}
