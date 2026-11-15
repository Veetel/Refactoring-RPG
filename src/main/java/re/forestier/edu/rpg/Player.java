package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Player {
    // xp needed to reach levels 2, 3, 4 and 5
    private static final int[] LEVEL_THRESHOLDS = {10, 27, 57, 111};

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;

    public Integer money;
    
    


    
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;


    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;
    public Player(String playerName, String avatar_name, String avatarClass, int money, ArrayList<String> inventory) {
        if (!avatarClass.equals("ARCHER") && !avatarClass.equals("ADVENTURER") && !avatarClass.equals("DWARF")) {
            throw new IllegalArgumentException("Unknown avatar class: " + avatarClass);
        }
        if (playerName == null || playerName.isEmpty()) {
            throw new IllegalArgumentException("Player name can't be empty!");
        }

        this.playerName = playerName;

        Avatar_name = avatar_name;
        AvatarClass = avatarClass;
        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    public String getAvatarClass () {
        return AvatarClass;
    }

    public void removeMoney(int amount) {
    if (money - amount < 0) {
        throw new IllegalArgumentException("Player can't have a negative money!");
    }
    money -= amount;
    }

    public void addMoney(int amount) {
    money += amount;
    }
    public int retrieveLevel() {
        int level = 1;
        for (int threshold : LEVEL_THRESHOLDS) {
            // one level gained for each threshold reached
            if (xp >= threshold) {
                level++;
            }
        }
        return level;
    }

    public int getXp() {
        return this.xp;
    }


    public boolean addXp(int xp) {
        if (xp < 0) {
            throw new IllegalArgumentException("XP can't be negative!");
        }

        int currentLevel = retrieveLevel();
        this.xp += xp;
        int newLevel = retrieveLevel();

        if (newLevel == currentLevel) {
            return false;
        }

        // Player leveled-up: give a random object
        Random random = new Random();
        inventory.add(UpdatePlayer.objectList[random.nextInt(UpdatePlayer.objectList.length)]);

        // Upgrade abilities
        HashMap<String, Integer> newAbilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(this.getAvatarClass()).get(newLevel);
        for (String ability : newAbilities.keySet()) {
            this.abilities.put(ability, newAbilities.get(ability));
        }
    
        return true;
    }
    /*
    Ингредиенты:
        Для теста:

            250 г муки
            125 г сливочного масла (холодное)
            70 г сахара
            1 яйцо
            1 щепотка соли
     */

}