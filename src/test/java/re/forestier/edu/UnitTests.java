package re.forestier.edu;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.fail;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.player;

public class UnitTests {

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
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
    }
    @Test 
    @DisplayName ("Impossible to have negative XP")
    void testNegativeXP() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
    }  

    @Test 
    @DisplayName ("Impossible to have negative level")
    void testNegativeLevel() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
    }

    @Test
    @DisplayName ("Impossible to have negative inventory size")
    void testNegativeInventorySize() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
    } 

    @Test 
    @DisplayName ("Impossible to have negative stats")
    void testNegativeStats() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
    }
    @Test
    @DisplayName ("Impossible to have duplicate items in inventory")
    void testDuplicateItemsInInventory() {
        player p = new player("Florian", "Grognak le barbare", "ADVENTURER", 100, new ArrayList<>());  
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
        assertThat(p.getXp(), is(20));
    }
    
}
