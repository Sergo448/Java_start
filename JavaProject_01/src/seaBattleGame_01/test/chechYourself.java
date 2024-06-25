// package seaBattleGame_01.test;

// /* Получаем ход пользователя в виде строкового параметра */
// public String checkYourself(String stringGuess) {

//     int guess = Integer.parseInt(stringGuess); /* Преобразуем String -> Integer */
//     String result = "Miss"; /* Переменная для сохранения результата  */

//     /* Повторяем с каждой ячейкой из массива locationCells */
//     for (int cell : locationCells) {
//         if (guess == cell) { /* сравниваем ход пользователя и ячейкой */
//             result = "Shooted"; /* попадание */
//             numOfHits++;
//             break;
//         }
//     }

//     if (numOfHits == locationCells.length) {
//         result = "Killed";
//     }

//     System.out.println(result);
//     return result;
// }