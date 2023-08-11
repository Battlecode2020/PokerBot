package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class HighCard extends HandStrength {
    CardNumber first;
    CardNumber second;
    CardNumber third;
    CardNumber fourth;
    CardNumber fifth;
    public CardNumber getFirst(){
        return first;
    }
    public CardNumber getSecond(){
        return second;
    }
    public CardNumber getThird(){
        return third;
    }
    public CardNumber getFourth(){
        return fourth;
    }
    public CardNumber getFifth(){
        return fifth;
    }
    public HighCard(CardNumber first, CardNumber second, CardNumber third, CardNumber fourth, CardNumber fifth){
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }


    @Override
    public int handValue(){
        return 0;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        HighCard high = (HighCard) other;
        if (first.compareTo(high.getFirst()) != 0){
            return first.compareTo( high.getFirst());
        } else if (second.compareTo(high.getSecond()) != 0){
            return second.compareTo(high.getSecond());
        } else if (third.compareTo(high.getThird()) != 0){
            return third.compareTo(high.getThird());
        } else if(fourth.compareTo(high.getFourth()) != 0){
            return fourth.compareTo(high.getFourth());
        }
        return fifth.compareTo(high.getFifth());
    }

    @Override
    public String toString() {
        return "High card";
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
