package seaBattleGame_01;

import java.util.*;



public class DotComBust {

    private GameHelper helper = new GameHelper();
    private ArrayList<DotCom> dotComList = new ArrayList<DotCom>();
    private int numOfGuesses = 0;

    private void setUpGame() {
        DotCom one = new DotCom();
        one.setName("Pets.com");

        DotCom two = new DotCom();
        two.setName("eToys.com");

        DotCom three = new DotCom();
        three.setName("Go2.com");

        dotComList.add(one);
        dotComList.add(two);
        dotComList.add(three);

        System.out.println("Your goal is to kill 3 sites!");
        System.out.println("Pets.com, eToys.com, Go2.com");
        System.out.println("Try to winn with the less number of guesses");

        for (DotCom dotComToSet : dotComList) {
            ArrayList<String> newLocation = helper.placeDotCom(3);
            dotComToSet.setLocationCells(newLocation);
        }
    }

    private void startPlaying() {
        while (!dotComList.isEmpty()) {
            String userGuess = helper.getUserInput("Do your step");
            checkUserGuess(userGuess);
        }

        finishGame();
    }

    private void checkUserGuess(String userGuess) {
        numOfGuesses++;
        String result = "Missed";

        for (DotCom dotComToTest : dotComList) {
            result = dotComToTest.checkYourself(userGuess);

            if (result.equals("Shooted")) {
                break;
            }

            if (result.equals("Killed")) {
                dotComList.remove(dotComToTest);
                break;
            }
        }
        System.out.println(result);
    }

    private void finishGame() {
        System.out.println("All sites are killed!");
        if (numOfGuesses <= 18) {
            System.out.println("It`s take for you only " + numOfGuesses + " guesses");
        } else {
            System.out.println("It`s take for you more time then wanted." + numOfGuesses + " guesses");
        }

    }
    
    public static void main(String[] args) {
        DotComBust game = new DotComBust();
        game.setUpGame();
        game.startPlaying();
    }
}