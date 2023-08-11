package com.klein.poker.hands;

public abstract class HandStrength {
    public abstract int handValue();
    public abstract int compareWithSelf(HandStrength other);
    public abstract String toString();

}
