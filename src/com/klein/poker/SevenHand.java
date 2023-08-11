package com.klein.poker;

import com.klein.poker.hands.HandStrength;

import java.util.ArrayList;
import java.util.Collections;

public class SevenHand {
    PokerCard[] cards;
    public SevenHand(PersonalHand hand, PokerCard card3,PokerCard card4, PokerCard card5, PokerCard card6, PokerCard card7){
        cards = new PokerCard[7];
        cards[0] = hand.getCardOne();
        cards[1] = hand.getCardTwo();
        cards[2] = card3;
        cards[3] = card4;
        cards[4] = card5;
        cards[5] = card6;
        cards[6] = card7;
    }
    public HandStrength getBestHand(){
        ArrayList<PokerHand> hands = new ArrayList<>();
        for(int a = 0; a < 7; a++){
            for(int b = a+1; b < 7; b++){
                for(int c = b+1; c < 7; c++){
                    for(int d = c+1; d < 7; d++){
                        for(int e = d+1; e < 7; e++){
                            hands.add(new PokerHand(cards[a], cards[b], cards[c],cards[d],cards[e]));
                        }
                    }
                }
            }
        }
        ArrayList<HandStrength> strengths = new ArrayList<>();
        for(PokerHand hand : hands){
            strengths.add(hand.evaluateHand());
        }
        Collections.sort(strengths,new HandStrengthComparator());
        return strengths.get(20);
    }

}
