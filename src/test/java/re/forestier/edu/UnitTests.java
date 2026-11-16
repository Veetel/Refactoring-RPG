package re.forestier.edu;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;
import java.util.Map;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

public class UnitTests {

    private static final String[] CLASSES = {"ADVENTURER", "ARCHER", "DWARF"};
    
    private Player newPlayer(String avatarClass, int money) {
        return new Player("Florian", "Grognak le barbare", avatarClass, money, new ArrayList<>());
    }

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        Player player = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

        try {
            p.removeMoney(200);
        } catch (IllegalArgumentException e) {
            return;
        }
        fail();
    }

    @Test 
    @DisplayName ("Impossible to have negative health")
    void testNegativeHealth() {
        for (String c : CLASSES) {
            Player p = new Player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertTrue(p.healthpoints >= 0, c);
            assertTrue(p.currenthealthpoints >= 0, c);
        }
    }

    @Test 
    @DisplayName ("Impossible to have negative XP")
    void testNegativeXP() {
        for (String c : CLASSES) {
            Player p = new Player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertEquals(0, p.getXp());
            p.addXp( 0);
            assertTrue(p.getXp() >= 0, c);
        }
    }  
      

    @Test 
    @DisplayName ("Impossible to have negative level")
    void testNegativeLevel() {
        for (String c : CLASSES) {
            Player p = new Player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertTrue(p.retrieveLevel() >= 1, c);
        }
    }

    @Test
    @DisplayName ("Impossible to have negative inventory size")
    void testNegativeInventorySize() {
        ArrayList<String> items = new ArrayList<>();
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, items);
        assertEquals(0, p.inventory.size());

        items.add("Holy Elixir");
        assertEquals(1, p.inventory.size());    
    } 

    @Test 
    @DisplayName ("Impossible to have negative stats")
    void testNegativeStats() {
        for (String c : CLASSES) {
            Player p = new Player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            p.abilities.forEach((name, value) -> assertTrue(value > 0, c + " " + name));
        }
    }

    @Test
    @DisplayName ("Impossible to have duplicate items in inventory")
    void testDuplicateItemsInInventory() {
       Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertEquals(new HashSet<>(p.inventory).size(), p.inventory.size());

        p.addXp(10);
        assertEquals(1, p.inventory.size());
    }

    @Test
    @DisplayName ("Impossible to have invalid player type")
    void testInvalidPlayerType() {
        try {
            Player p = new Player("Florian", "Grognak le barbare", "INVALID_TYPE", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return; 
        }
        fail();
    }

    @Test
    @DisplayName ("Impossible to have invalid player class")
    void testInvalidPlayerClass() {
        try {
            Player p = new Player("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return;

        }
        fail("Expected IllegalArgumentException to be thrown");
    }


    @Test 
    @DisplayName ("Impossible to have invalid player name")
    void testInvalidPlayerName() {
        try {
            Player p = new Player("", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Expected IllegalArgumentException to be thrown");
    }

    @Test 
    @DisplayName ("Addxp problem")
    void testAddXp() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addXp(20);
        assertEquals(20, p.getXp());
    }
    @Test
    @DisplayName ("Addxp negative problem")
    void testAddXpNegative() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        try { 
            p.addXp(-20);

        } 
        catch (IllegalArgumentException e) {
            return;
        }

        fail("Expected IllegalArgumentException to be thrown");

    }

    @Test 
    @DisplayName ("UpdatePlayer ArcherLevel")
    void testUpdatePlayerArcherLevel() {
        Player p = new Player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.addXp(20);
        assertEquals(20, p.getXp());
        assertEquals("ARCHER", p.getAvatarClass());
        assertEquals(1, p.retrieveLevel());
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints);
    }

    @Test
    @DisplayName("removeMoney subtracts the amount")
    void testRemoveMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(30);
        assertEquals(Integer.valueOf(70), p.money);
    }

    @Test
    @DisplayName("removeMoney can bring the money down to exactly 0")
    void testRemoveAllMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertEquals(Integer.valueOf(0), p.money);
    }

    @Test
    @DisplayName("addMoney adds the amount")
    void testAddMoney() {
        Player p = new Player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(50);
        assertEquals(Integer.valueOf(150), p.money);
    }

    @Test
    @DisplayName("addXp returns true only when the player gains a level")
    void testAddXpReturnsLevelUp() {
        Player p = new Player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        assertFalse(p.addXp(9));   // 9 xp : still level 1
        assertTrue(p.addXp(1));    // 10 xp : level 2
        assertFalse(p.addXp(1));   // 11 xp : still level 2
    }

    @Test
    @DisplayName("Level up applies the new abilities and gives one item")
    void testLevelUpAppliesAbilities() {
        Player p = new Player("Florian", "Grognak le barbare", "DWARF", 100, new ArrayList<>());
        p.addXp(10);
        assertEquals(Map.of("ALC", 5, "INT", 1, "ATK", 3, "DEF", 1), p.abilities);
        assertEquals(1, p.inventory.size());
    }

}

