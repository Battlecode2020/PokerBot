package com.klein.poker.hands;

import com.klein.poker.CardNumber;
import com.sun.istack.internal.NotNull;

public class Flush extends HandStrength {
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
    public Flush(CardNumber first, CardNumber second, CardNumber third, CardNumber fourth, CardNumber fifth){
        this.first = first;
        this.second = second;
        this.third = third;
        this.fourth = fourth;
        this.fifth = fifth;
    }

    @Override
    public int handValue(){
        return 5;
    }

    @Override
    public int compareWithSelf(HandStrength other) {
        Flush flush = (Flush) other;
        if (first.compareTo(flush.getFirst()) != 0){
            return first.compareTo( flush.getFirst());
        } else if (second.compareTo(flush.getSecond()) != 0){
            return second.compareTo(flush.getSecond());
        } else if (third.compareTo(flush.getThird()) != 0){
            return third.compareTo(flush.getThird());
        } else if(fourth.compareTo(flush.getFourth()) != 0){
            return fourth.compareTo(flush.getFourth());
        }
        return fifth.compareTo(flush.getFifth());
    }

    @Override
    public String toString() {
        return "flush";
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
