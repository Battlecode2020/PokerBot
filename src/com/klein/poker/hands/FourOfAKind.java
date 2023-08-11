package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class FourOfAKind extends HandStrength {
    CardNumber fourType;
    CardNumber kicker;
    public FourOfAKind(CardNumber fourType, CardNumber kicker){
        this.fourType = fourType;
        this.kicker = kicker;
    }
    public CardNumber getFourType(){
        return fourType;
    }
    public CardNumber getKicker(){
        return kicker;
    }
    @Override
    public int handValue(){
        return 7;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        FourOfAKind four = (FourOfAKind) other;
        if(fourType.compareTo(four.getFourType()) != 0 ){
            return fourType.compareTo(four.getFourType());
        }
        return kicker.compareTo(four.getKicker());
    }

    @Override
    public String toString() {
        return "four of a kind";
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
