package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class ThreeOfAKind extends HandStrength {
    CardNumber threeType;
    CardNumber firstKicker;
    CardNumber secondKicker;
    public CardNumber getThreeType(){
        return threeType;
    }
    public CardNumber getFirstKicker(){
        return firstKicker;
    }
    public CardNumber getSecondKicker(){
        return secondKicker;
    }
    public ThreeOfAKind(CardNumber threeType, CardNumber firstKicker, CardNumber secondKicker) {
        this.threeType = threeType;
        this.firstKicker = firstKicker;
        this.secondKicker = secondKicker;
    }


    @Override
    public int handValue(){
        return 3;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        ThreeOfAKind three = (ThreeOfAKind) other;
        if (threeType.compareTo(three.getThreeType()) != 0){
            return threeType.compareTo( three.getThreeType());
        } else if (firstKicker.compareTo(three.getFirstKicker()) != 0){
            return firstKicker.compareTo(three.getFirstKicker());
        }
        return secondKicker.compareTo(three.getSecondKicker());
    }

    @Override
    public String toString() {
        return "Three of a kind";
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
