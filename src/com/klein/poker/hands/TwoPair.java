package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class TwoPair extends HandStrength {
    CardNumber topPair;
    CardNumber secondPair;
    CardNumber kicker;
    public TwoPair(CardNumber topPair, CardNumber secondPair, CardNumber kicker){
        this.topPair = topPair;
        this.secondPair = secondPair;
        this.kicker = kicker;
    }
    public CardNumber getTopPair(){
        return topPair;
    }
    public CardNumber getSecondPair(){
        return secondPair;
    }
    public CardNumber getKicker(){
        return kicker;
    }


    @Override
    public int handValue(){
        return 2;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        TwoPair two = (TwoPair) other;
        if (topPair.compareTo(two.getTopPair()) != 0){
            return topPair.compareTo( two.getTopPair());
        } else if (secondPair.compareTo(two.getSecondPair()) != 0){
            return secondPair.compareTo(two.getSecondPair());
        }
        return kicker.compareTo(two.getKicker());
    }

    @Override
    public String toString() {
        return "two pair";
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
