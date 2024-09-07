import java.io.*;

public class GameCharacter implements Serializable {
    int power;
    String type;
    String[] weapons;
    
    public GameCharacter(int chPower, String chType, String[] chWeapons) {
        power = chPower;
        type = chType;
        weapons = chWeapons;
    }

    public int getPower() {
        return power;
    }

    public String getType() {
        return type;
    }
    
    public String getWeapons() {
        String weaponList = "";

        for (String weapon : weapons) {
            weaponList += weapon + " ";
        }
        return weaponList;
    }
}
