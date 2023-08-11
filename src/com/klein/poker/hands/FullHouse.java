package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class FullHouse extends HandStrength {
    CardNumber threeType;
    CardNumber twoType;
    public CardNumber getThreeType(){
        return threeType;
    }
    public CardNumber getTwoType(){
        return twoType;
    }
    public FullHouse(CardNumber threeType, CardNumber twoType){
        this.threeType = threeType;
        this.twoType = twoType;
    }
    @Override
    public int handValue(){
        return 6;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        FullHouse three = (FullHouse) other;
        if (threeType.compareTo(three.getThreeType()) != 0){
            return threeType.compareTo( three.getThreeType());
        }
        return twoType.compareTo(three.getTwoType());
    }

    @Override
    public String toString() {
        return "full house";
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
