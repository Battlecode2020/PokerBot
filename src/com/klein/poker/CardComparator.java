package com.klein.poker;

import java.util.Comparator;

public class CardComparator implements Comparator<PokerCard> {
    @Override
    public int compare(PokerCard o1, PokerCard o2) {
        if (o1.getCardNumber().ordinal() > o2.getCardNumber().ordinal()) {
            return 1;
        } else if (o1.getCardNumber().ordinal() == o2.getCardNumber().ordinal()) {
            return 0;
        } else {
            return -1;
        }
    }
}