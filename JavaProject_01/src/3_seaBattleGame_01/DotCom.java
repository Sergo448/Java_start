package seaBattleGame_01;

import java.util.ArrayList;

public class DotCom {
    private ArrayList<String> locationCells;
    private String name;

    public void setLocationCells(ArrayList<String> loc) {
        locationCells = loc;
    }

    public void setName(String n) {
        name = n;
    }

    public String checkYourself(String userInput) {
        String result = "Missed";
        int index = locationCells.indexOf(userInput);

        if (index >= 0) {
            locationCells.remove(index);
            
            if (locationCells.isEmpty()) {
                result = "Killed";
                System.out.println("Oh! You killed " + name + "  :<");
            } else {
                result = "Shooted";
            }
        }
        return result;
    }
}
