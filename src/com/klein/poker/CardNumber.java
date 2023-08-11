package com.klein.poker;

public enum CardNumber {
    TWO,THREE,FOUR,FIVE,SIX,SEVEN,EIGHT,NINE,TEN,JACK,QUEEN,KING,ACE;

    //returns true if card could be in straight, so 2 of the same returns false
    public boolean distance(CardNumber num1, CardNumber num2){
        if(num1 == TWO){
            if(num2 == ACE || num2 == THREE || num2 == FOUR || num2 == FIVE || num2 == SIX){
                return true;
            }
            return false;
        }
        if(num1 == THREE){
            if(num2 == ACE || num2 == TWO || num2 == FOUR || num2 == FIVE || num2 == SIX || num2 == SEVEN){
                return true;
            }
            return false;
        }
        if(num1 == FOUR){
            if(num2 == ACE || num2 == TWO || num2 == THREE || num2 == FIVE || num2 == SIX || num2 == SEVEN || num2 == EIGHT){
                return true;
            }
            return false;
        }
        if(num1 == FIVE){
            if(num2 == ACE || num2 == TWO || num2 == THREE || num2 == FOUR || num2 == SIX || num2 == SEVEN || num2 == EIGHT || num2 == NINE){
                return true;
            }
            return false;
        }
        if(num1 == SIX){
            if( num2 == TWO || num2 == THREE || num2 == FOUR || num2 == FIVE || num2 == SEVEN || num2 == EIGHT || num2 == NINE || num2 == TEN){
                return true;
            }
            return false;
        }
        if(num1 == SEVEN){
            if(num2 == THREE || num2 == FOUR || num2 == FIVE || num2 == SIX || num2 == EIGHT || num2 == NINE || num2 == TEN || num2 == JACK){
                return true;
            }
            return false;
        }
        if(num1 == EIGHT){
            if(num2 == FOUR || num2 == FIVE || num2 == SIX || num2 == SEVEN || num2 == NINE || num2 == TEN || num2 == JACK || num2 == QUEEN){
                return true;
            }
            return false;
        }
        if(num1 == NINE){
            if(num2 == FIVE || num2 == SIX || num2 == SEVEN || num2 == EIGHT || num2 == TEN || num2 == JACK || num2 == QUEEN || num2 == KING){
                return true;
            }
            return false;
        }
        if(num1 == TEN){
            if(num2 == SIX || num2 == SEVEN || num2 == EIGHT || num2 == NINE || num2 == JACK || num2 == QUEEN || num2 == KING || num2 == ACE){
                return true;
            }
            return false;
        }
        if(num1 == JACK){
            if(num2 == SEVEN || num2 == EIGHT || num2 == NINE || num2 == TEN || num2 == QUEEN || num2 == KING || num2 == ACE){
                return true;
            }
            return false;
        }
        if(num1 == QUEEN){
            if(num2 == EIGHT || num2 == NINE || num2 == TEN || num2 == JACK || num2 == KING || num2 == ACE){
                return true;
            }
            return false;
        }
        if(num1 == KING){
            if(num2 == NINE || num2 == TEN || num2 == JACK || num2 == QUEEN || num2 == ACE){
                return true;
            }
            return false;
        }
        if(num1 == ACE){
            if(num2 == TEN || num2 == JACK || num2 == QUEEN || num2 == KING){
                return true;
            }

        }
        return false;

    }
}


