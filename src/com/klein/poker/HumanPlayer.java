package com.klein.poker;
import java.util.*;

public class HumanPlayer extends Player{
    @Override
    public void doTurn(Game game){
        if(handState == HandState.ALL_IN){
            return;
        }
        if(potInvestment < game.getMaxAmount()){
            System.out.println("Do you want to call, fold, or raise?");
            System.out.println("c for call, f for fold, r for raise");
            boolean done = true;
            while (done) {
                Scanner sc = new Scanner(System.in);
                String next = sc.nextLine();
                if (next.equals("c")) {
                    call(game.getMaxAmount() - potInvestment);
                    done = false;
                } else if (next.equals("f")) {
                    fold();
                    done = false;
                } else if (next.equals("r")) {
                    System.out.println("How much?");
                    Scanner amount = new Scanner(System.in);
                    raise(Integer.parseInt(amount.nextLine()));
                    done = false;
                } else {
                    System.out.println("uh oh, what was that, please try again!");
                }
            }
        }else {
            System.out.println("Do you want to check or raise?");
            System.out.println("c for check, r for raise");
            boolean done = true;
            while (done) {
                Scanner sc = new Scanner(System.in);
                String next = sc.nextLine();
                if (next.equals("c")) {
                    check();
                    done = false;
                }  else if (next.equals("r")) {
                    System.out.println("How much?");
                    Scanner amount = new Scanner(System.in);
                    raise(Integer.parseInt(amount.nextLine()));
                    done = false;
                } else {
                    System.out.println("uh oh, what was that, please try again!");
                }
            }
        }

    }
    @Override
    public boolean isHuman(){
        return true;
    }
    @Override
    public void describeHand(){
        System.out.println("You have the " + hand.toString());
    }
    @Override
    public String getName(){
        return "You are";
    }
    @Override
    public String getNameFinal(){
        return "Human";
    }
}
