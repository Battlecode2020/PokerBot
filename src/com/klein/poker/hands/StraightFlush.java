package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class StraightFlush extends HandStrength {
    CardNumber topCard;
    public CardNumber getTopCard(){
        return topCard;
    }
    public StraightFlush(CardNumber topCard){
        this.topCard = topCard;
    }
    @Override
    public int handValue(){return 8;}

    @Override
    public int compareWithSelf(HandStrength other) {
        StraightFlush straightFlush = (StraightFlush) other;
        return topCard.compareTo(straightFlush.getTopCard());
    }

    @Override
    public String toString() {
        if(topCard == CardNumber.ACE){
            return "WTF ROYAL FLUSH!!!";
        }
        return "straight flush";
    }

    /*
    high card = 0
    one pair = 1
    two pair = 2
    3 of a kind = 3
    straight = 4
    flush = 5
    full house = 6
    quads = 7
    straight flush = 8
    royal flush = 9
     */
}
