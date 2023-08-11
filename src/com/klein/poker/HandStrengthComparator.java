package com.klein.poker;

import com.klein.poker.hands.HandStrength;

import java.util.Comparator;

public class HandStrengthComparator implements Comparator<HandStrength> {
    @Override
    public int compare(HandStrength o1, HandStrength o2){
        if(o1.handValue() > o2.handValue()){
            return 1;
        } else if(o1.handValue() == o2.handValue()){
            return o1.compareWithSelf(o2);
        } else{
            return -1;
        }
    }
}
