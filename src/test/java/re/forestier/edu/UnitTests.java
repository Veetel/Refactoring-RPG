package re.forestier.edu;

import java.util.ArrayList;
import java.util.HashSet;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
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
            p.addXp( 0);
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

        p.addXp(10);
        assertEquals(1, p.inventory.size());
    }

    @Test
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
    @DisplayName ("Addxp problem")
    void testAddXp() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
        p.addXp(20);
        assertEquals(20, p.getXp());
    }
    @Test
    @DisplayName ("Addxp negative problem")
    void testAddXpNegative() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());
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
        player p = new player("Florian", "Grognak le barbare", "ARCHER", 100, new ArrayList<>());
        p.addXp(20);
        assertEquals(20, p.getXp());
        assertEquals("ARCHER", p.getAvatarClass());
        assertEquals(1, p.retrieveLevel());
        UpdatePlayer.majFinDeTour(p);
        assertEquals(100, p.currenthealthpoints);
    }
}

