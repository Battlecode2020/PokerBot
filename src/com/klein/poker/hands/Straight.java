package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class Straight extends HandStrength {
    CardNumber topCard;
    public CardNumber getTopCard(){
        return topCard;
    }
    public Straight(CardNumber topCard){
        this.topCard = topCard;
    }
    @Override
    public int handValue(){
        return 4;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        Straight straight = (Straight) other;
        return topCard.compareTo(straight.getTopCard());
    }

    @Override
    public String toString() {
        return "straight";
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
