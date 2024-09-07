package GuessGame;

/* public - Модификатор доступа. Делает класс доступным для всех
 * классов / методовю Любой другой класс / метод может получить
 * его методы / переменные  */

public class Guessgame {
    Player p1;
    Player p2;
    Player p3;

    public void startgame() {
        /* players */
        p1 = new Player();
        p2 = new Player();
        p3 = new Player();
        /* guesses */
        int guess1 = 0;
        int guess2 = 0;
        int guess3 = 0;

        /* results */
        boolean p1isRight = false;
        boolean p2isRight = false;
        boolean p3isRight = false;

        int targetNumber = (int) (Math.random() * 10);
        System.out.println("I am creating a number from 0 to 9...");

        while (true) {
            System.out.println("Number that players shoud match is: " + targetNumber);

            p1.guess();
            p2.guess();
            p3.guess();

            guess1 = p1.number;
            System.out.println("Player 1 thinks that number is: " + guess1);

            guess2 = p2.number;
            System.out.println("Player 2 thinks thar number is: " + guess2);

            guess3 = p3.number;
            System.out.println("Player 3 thinks that number is: " + guess3);

            if (guess1 == targetNumber) {
                p1isRight = true;
            }

            if (guess2 == targetNumber) {
                p2isRight = true;
            }

            if (guess3 == targetNumber) {
                p3isRight = true;
            }

            if (p1isRight || p2isRight || p3isRight) {
                System.out.println("We have winner!");
                System.out.println("First player won? " + p1isRight);
                System.out.println("Second player won? " + p2isRight);
                System.out.println("Third player won? " + p3isRight);

                System.out.println("Game ends!");
                break;
            }
            else {
                System.out.println("Player must have a new try!");
            }
        }
    }
}