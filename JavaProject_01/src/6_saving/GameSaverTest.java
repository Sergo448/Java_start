import java.io.*;

public class GameSaverTest {
    public static void main(String[] args) {
        GameCharacter oneCharacter = new GameCharacter(50, "Elf", new String[] {"Лук", "Меч", "Стрелы"});
        GameCharacter twoCharacter = new GameCharacter(500, "Trol", new String[] {"Руки", "Топор"});
        GameCharacter threeCharacter = new GameCharacter(120, "Magishian", new String[] {"Посох"});

        try {
            ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream("Game.ser"));

            os.writeObject(oneCharacter);
            os.writeObject(twoCharacter);
            os.writeObject(threeCharacter);

            os.close();
        } catch (IOException ex) {ex.printStackTrace();}

        oneCharacter = null;
        twoCharacter = null;
        threeCharacter = null;

        try {
            ObjectInputStream is = new ObjectInputStream(new FileInputStream("Game.ser"));

            GameCharacter oneRestore = (GameCharacter) is.readObject();
            GameCharacter twoRestore = (GameCharacter) is.readObject();
            GameCharacter threeRestore = (GameCharacter) is.readObject();

            System.out.println("Type of first: " + oneRestore.getType());
            System.err.println("Type of second: " + twoRestore.getType());
            System.err.println("Type of third: " + threeRestore.getType());

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }    
}
