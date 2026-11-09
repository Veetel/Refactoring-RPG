package re.forestier.edu;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertSame;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class UnitTests {

    private static final String[] CLASSES = {"ADVENTURER", "ARCHER", "DWARF"};
    
    private player newPlayer(String avatarClass, int money) {
        return new player("Florian", "Grognak le barbare", avatarClass, money, new ArrayList<>());
    }

    @Test
    @DisplayName("Sample test")
    void testPlayerName() {
        player player = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertThat(player.playerName, is("Florian"));
    }

    @Test
    @DisplayName("Impossible to have negative money")
    void testNegativeMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());

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
            player p = new player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertTrue(p.healthpoints >= 0, c);
            assertTrue(p.currenthealthpoints >= 0, c);
        }
    }

    @Test 
    @DisplayName ("Impossible to have negative XP")
    void testNegativeXP() {
        for (String c : CLASSES) {
            player p = new player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertEquals(0, p.getXp());
            UpdatePlayer.addXp(p, 0);
            assertTrue(p.getXp() >= 0, c);
        }
    }  
      

    @Test 
    @DisplayName ("Impossible to have negative level")
    void testNegativeLevel() {
        for (String c : CLASSES) {
            player p = new player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            assertTrue(p.retrieveLevel() >= 1, c);
        }
    }

    @Test
    @DisplayName ("Impossible to have negative inventory size")
    void testNegativeInventorySize() {
        ArrayList<String> items = new ArrayList<>();
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, items);
        assertEquals(0, p.inventory.size());

        items.add("Holy Elixir");
        assertEquals(1, p.inventory.size());    
    } 

    @Test 
    @DisplayName ("Impossible to have negative stats")
    void testNegativeStats() {
        for (String c : CLASSES) {
            player p = new player("Florian", "Grognak le barbare", c, 100, new ArrayList<>());
            p.abilities.forEach((name, value) -> assertTrue(value > 0, c + " " + name));
        }
    }

    @Test
    @DisplayName ("Impossible to have duplicate items in inventory")
    void testDuplicateItemsInInventory() {
       player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        assertEquals(new HashSet<>(p.inventory).size(), p.inventory.size());

        UpdatePlayer.addXp(p, 10);
        assertEquals(1, p.inventory.size());
    }

    @Test
    @Disabled 
    @DisplayName ("Impossible to have invalid player type")
    void testInvalidPlayerType() {
        try {
            player p = new player("Florian", "Grognak le barbare", "INVALID_TYPE", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return; 
        }
        fail();
    }

    @Test
    @Disabled 
    @DisplayName ("Impossible to have invalid player class")
    void testInvalidPlayerClass() {
        try {
            player p = new player("Florian", "Grognak le barbare", "INVALID_CLASS", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return;

        }
        fail("Expected IllegalArgumentException to be thrown");
    }


    @Test 
    @Disabled 
    @DisplayName ("Impossible to have invalid player name")
    void testInvalidPlayerName() {
        try {
            player p = new player("", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        } catch (IllegalArgumentException e) {
            return;
        }
        fail("Expected IllegalArgumentException to be thrown");
    }

    @Test
    @DisplayName("Unknown class leaves the player uninitialised")
    void testUnknownClassLeavesFieldsEmpty() {
        player p = new player("Florian", "Grognak le barbare", "WIZARD", 100, new ArrayList<>());

        assertNull(p.playerName);
        assertNull(p.getAvatarClass());
        assertNull(p.abilities);
    }

    @Test
    @DisplayName("removeMoney subtracts the amount")
    void testRemoveMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(30);
        assertEquals(Integer.valueOf(70), p.money);
    }

    @Test
    @DisplayName("removeMoney can bring the money down to exactly 0")
    void testRemoveAllMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.removeMoney(100);
        assertEquals(Integer.valueOf(0), p.money);
    }

    @Test
    @DisplayName("addMoney adds the amount")
    void testAddMoney() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addMoney(50);
        assertEquals(Integer.valueOf(150), p.money);
    }

    @Test
    @DisplayName("retrieveLevel : 10, 27, 57 and 111 xp")
    void testRetrieveLevel() {
        int[][] xpToLevel = {
            {0, 1}, {9, 1}, {10, 2}, {26, 2}, {27, 3}, {56, 3}, {57, 4}, {110, 4}, {111, 5}, {5000, 5}
        };
        for (int[] pair : xpToLevel) {
            player p = new player("Florian", "Grognak le barbare", "DWARF", 0, new ArrayList<>());
            UpdatePlayer.addXp(p, pair[0]);
            assertEquals(pair[1], p.retrieveLevel(), "xp = " + pair[0]);
        }
    }

    @Test
    @DisplayName("getXp returns the xp gained")
    void testGetXp() {
        player p = new player("Florian", "Grognak le barbare", "DWARF", 0, new ArrayList<>());
        UpdatePlayer.addXp(p, 15);
        assertEquals(15, p.getXp());
    }
    
}
