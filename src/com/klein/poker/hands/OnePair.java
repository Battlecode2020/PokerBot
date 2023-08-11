package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class OnePair extends HandStrength {
    CardNumber pair;
    CardNumber firstKicker;
    CardNumber secondKicker;
    CardNumber thirdKicker;
    public CardNumber getPair(){
        return pair;
    }
    public CardNumber getFirstKicker(){
        return firstKicker;
    }
    public CardNumber getSecondKicker(){
        return secondKicker;
    }
    public CardNumber getThirdKicker(){
        return thirdKicker;
    }
    public OnePair(CardNumber pair, CardNumber firstKicker, CardNumber secondKicker, CardNumber thirdKicker){
        this.pair = pair;
        this.firstKicker = firstKicker;
        this.secondKicker = secondKicker;
        this.thirdKicker = thirdKicker;
    }
    @Override
    public int handValue(){
        return 1;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        OnePair one = (OnePair) other;
        if (pair.compareTo(one.getPair()) != 0){
            return pair.compareTo( one.getPair());
        } else if (firstKicker.compareTo(one.getFirstKicker()) != 0){
            return firstKicker.compareTo(one.getFirstKicker());
        } else if (secondKicker.compareTo(one.getSecondKicker()) != 0){
            return secondKicker.compareTo(one.getSecondKicker());
        }
        return thirdKicker.compareTo(one.getThirdKicker());
    }

    @Override
    public String toString() {
        return "one pair";
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
