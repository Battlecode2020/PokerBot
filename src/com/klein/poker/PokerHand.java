package com.klein.poker;

import com.klein.poker.hands.*;

import java.util.Arrays;
import java.util.Collections;

public class PokerHand {
    int[] amounts = new int[13];
    PokerCard[] hand;
    public PokerHand(PokerCard one, PokerCard two, PokerCard three, PokerCard four, PokerCard five){
        hand = new PokerCard[5];
        hand[0] = one;
        hand[1] = two;
        hand[2] = three;
        hand[3] = four;
        hand[4] = five;
    }
    public HandStrength evaluateHand(){
        boolean isStraight = true;
        boolean isFlush = true;
        int count = 0;
        for(int i = 0; i < 5; i++){
            for(int j = i+1; j < 5; j++){
                if(hand[0].getCardNumber().distance(hand[i].getCardNumber(),hand[j].getCardNumber())){

                } else{
                    isStraight = false;
                }
                if(hand[i].getSuit() == hand[j].getSuit()){

                } else{
                    isFlush = false;
                }
                if(hand[i].getCardNumber() == hand[j].getCardNumber()){
                    count++;
                }
            }
        }
        //count = 0: high card
        //count = 1: pair
        //count = 2: two pair
        //count = 3: three of a kind
        //count = 4: full house
        //count = 6: quads
        for(int i = 0; i < 13; i++){
            amounts[i] = 0;
        }
        for(int i = 0; i < 5; i++){
            amounts[hand[i].getCardNumber().ordinal()]++;
        }
        Arrays.sort(hand, new CardComparator());
        if(isStraight && isFlush){
            if(hand[4].getCardNumber() == CardNumber.ACE && hand[3].getCardNumber() == CardNumber.FIVE){
                return new StraightFlush(hand[3].getCardNumber());
            }
            return new StraightFlush(hand[4].getCardNumber());
        }
        if(count == 6){
            CardNumber four = null;
            CardNumber kicker = null;
            for(int i = 0; i < 13; i++){
                if(amounts[i] == 4){
                    four = CardNumber.values()[i];
                }else if(amounts[i] == 1){
                    kicker = CardNumber.values()[i];
                }
            }
            return new FourOfAKind(four, kicker);
        }
        if(count == 4){
            CardNumber three = null;
            CardNumber two = null;
            for(int i = 0; i < 13; i++){
                if(amounts[i] == 3){
                    three = CardNumber.values()[i];
                }else if(amounts[i] == 2){
                    two = CardNumber.values()[i];
                }
            }
            return new FullHouse(three, two);
        }
        if(isFlush){
            return new Flush(hand[4].getCardNumber(), hand[3].getCardNumber(), hand[2].getCardNumber(), hand[1].getCardNumber(), hand[0].getCardNumber());
        }
        if(isStraight){
            if(hand[4].getCardNumber() == CardNumber.ACE && hand[3].getCardNumber() == CardNumber.FIVE){
                return new Straight(hand[3].getCardNumber());
            }
            return new Straight(hand[4].getCardNumber());
        }
        if(count == 3){
            CardNumber three = null;
            CardNumber firstKicker = null;
            CardNumber secondKicker = null;
            for(int i = 12; i >= 0; i--){
                if(amounts[i] == 3){
                    three = CardNumber.values()[i];
                }else if(amounts[i] == 1 && firstKicker == null){
                    firstKicker = CardNumber.values()[i];
                } else if(amounts[i] == 1){
                    secondKicker = CardNumber.values()[i];
                }
            }
            return new ThreeOfAKind(three,firstKicker,secondKicker);
        }
        if(count == 2){
            CardNumber higherPair = null;
            CardNumber lowerPair = null;
            CardNumber kicker = null;
            for(int i = 12; i >= 0; i--){
                if(amounts[i] == 2 && higherPair == null){
                    higherPair = CardNumber.values()[i];
                }else if(amounts[i] == 2){
                    lowerPair = CardNumber.values()[i];
                } else if(amounts[i] == 1){
                    kicker = CardNumber.values()[i];
                }
            }
            return new TwoPair(higherPair, lowerPair, kicker);
        }
        if(count == 1){
            CardNumber pair = null;
            CardNumber firstKick = null;
            CardNumber secondKick = null;
            CardNumber thirdKick = null;
            for(int i = 12; i >= 0; i--){
                if(amounts[i] == 2){
                    pair = CardNumber.values()[i];
                }else if(amounts[i] == 1 && firstKick == null){
                    firstKick = CardNumber.values()[i];
                } else if(amounts[i] == 1 && secondKick == null){
                    secondKick = CardNumber.values()[i];
                } else if(amounts[i] == 1){
                    thirdKick = CardNumber.values()[i];
                }
            }
            return new OnePair(pair, firstKick, secondKick, thirdKick);
        }
        return new HighCard(hand[4].getCardNumber(), hand[3].getCardNumber(), hand[2].getCardNumber(), hand[1].getCardNumber(), hand[0].getCardNumber());

    }

}
