package com.klein.poker;

public enum Suit {
    SPADES,HEARTS,DIAMONDS,CLUBS;
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public String toString(){
        if(this == SPADES){
            return ANSI_YELLOW + "SPADES" + ANSI_RESET;
        }
        if(this == DIAMONDS){
            return ANSI_RED + "DIAMONDS" + ANSI_RESET;
        }
        if(this == HEARTS){
            return ANSI_GREEN + "HEARTS" + ANSI_RESET;
        }

            return ANSI_CYAN + "CLUBS" + ANSI_RESET;

    }
}
