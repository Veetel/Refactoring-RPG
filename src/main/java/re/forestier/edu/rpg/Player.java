package re.forestier.edu.rpg;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

/**
 * A player of the RPG, identified by a name and an avatar.
 * Holds the player's money, health points, xp, abilities and inventory.
 */

public class Player {
    // xp needed to reach levels 2, 3, 4 and 5
    private static final int[] LEVEL_THRESHOLDS = {10, 27, 57, 111};

    public String playerName;
    public String Avatar_name;
    private String AvatarClass;
    private RPGClass rpgClass;

    public Integer money;
    
    public int healthpoints;
    public int currenthealthpoints;
    protected int xp;

    public HashMap<String, Integer> abilities;
    public ArrayList<String> inventory;

    /**
    * Creates a player at level 1 with no xp.
    * @param playerName the name of the player: can't be empty
    * @param avatar_name the name of the avatar
    * @param avatarClass the class of the avatar (ADVENTURER, ARCHER, DWARF)
    * @param money the amount of money the player has
    * @param inventory the list of objects the player has
    * @throws IllegalArgumentException if the avatarClass is not one of the three known classes, or if the playerName is empty
    */

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

        if (avatarClass.equals("ARCHER")) {
            this.rpgClass = new Archer(1);
        }  

        this.money = Integer.valueOf(money);
        this.inventory = inventory;
        this.abilities = UpdatePlayer.abilitiesPerTypeAndLevel().get(AvatarClass).get(1);
    }

    /**  @return the class of the avatar */
    public String getAvatarClass () {
        return AvatarClass;
    }

    /**
    * Removes money from player.
    * @param amount the amount of money to remove
    * @throws IllegalArgumentException if the amount is greater than the player's current money
    */

    public void removeMoney(int amount) {
        if (money - amount < 0) {
            throw new IllegalArgumentException("Player can't have a negative money!");
        }
        money -= amount;
        }
    
    /** @param amount the amount of money to add */
    public void addMoney(int amount) {
        money += amount;
        }
    
    /**
     * Computes the level from the current xp (level 1 at 0 xp, level 2 at 10 xp, level 3 at 27 xp, level 4 at 57 xp, level 5 at 111 xp).
     * @return the level of the player, between 1 and 5
     */
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

    /** @return the current xp of the player */
    public int getXp() {
        return this.xp;
    }

    /**
     * Adds xp to the player. On a level-up, a random item is added to the player's inventory
     * and the abilities of the new level are applied.
     * @param xp the gained xp to add; must be non-negative
     * @return true if the player leveled-up, false otherwise
     * @throws IllegalArgumentException if the xp is negative
     */
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

}