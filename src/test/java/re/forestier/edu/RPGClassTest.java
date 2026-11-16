package re.forestier.edu;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Archer;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class RPGClassTest {
    
    @Test
    void testArcherClass() {
        Archer archer = new Archer(1);
        assertEquals("Archer",archer.getClassName());
        assertEquals("The Archer is a class that uses bow and arrows",archer.getClassDescription());
        assertEquals("INT : 1\nATK : 3\nDEF : 0\nCHA : 1\nALC : 0\nVIS : 3",archer.getStatsToString());

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> new Archer(0));
        assertEquals("Level must be greater than 0", exception);
    }

    @Test
    @DisplayName ("Test level up for Archer class")
    void testArcherLevelUp() {
        Archer archer = new Archer(1);
        
        assertEquals( "INT : 1\nATK : 3\nDEF : 0\nCHA : 1\nALC : 0\nVIS : 3",archer.getStatsToString());
        
        archer.levelUp(); // level 2
        assertEquals( "INT : 1\nATK : 3\nDEF : 1\nCHA : 2\nALC : 0\nVIS : 3",archer.getStatsToString());

        archer.levelUp();// level 3
        assertEquals( "INT : 1\nATK : 3\nDEF : 1\nCHA : 2\nALC : 0\nVIS : 3",archer.getStatsToString());

        archer.levelUp();// level 4
        assertEquals( "INT : 1\nATK : 3\nDEF : 2\nCHA : 2\nALC : 0\nVIS : 3",archer.getStatsToString());

        archer.levelUp();// level 5
        assertEquals( "INT : 1\nATK : 4\nDEF : 2\nCHA : 2\nALC : 0\nVIS : 3",archer.getStatsToString());
    }

    @Test
    @DisplayName("Reset and level history preserve the Archer's current level")
    void testArcherResetAndLevelHistory() {
        Archer archer = new Archer(3);

        var abilities = archer.abilitiesPerTypeAndLevel();
        assertEquals(4, abilities.get(5).get("ATK"));
        assertEquals("INT : 1\nATK : 3\nDEF : 1\nCHA : 2\nALC : 0\nVIS : 3", archer.getStatsToString());

        archer.levelUp();
        assertEquals("INT : 1\nATK : 3\nDEF : 2\nCHA : 2\nALC : 0\nVIS : 3", archer.getStatsToString());

        archer.reset();
        assertEquals("INT : 1\nATK : 3\nDEF : 0\nCHA : 1\nALC : 0\nVIS : 3", archer.getStatsToString());
    }

    @Test 
    @DisplayName ("Test getStats and legacy compatibility for Archer ")
    void testArcherCompatibility(){
        player p = new player("Florian", "Ragnar le breton","Archer", 0, new ArrayList<>());
        Archer archer = new Archer(1);
        assertEquals(UpdatePlayer.abilitiesPerTypeAndLevel().get("Archer"), archer.abilitiesPerTypeAndLevel());
        
    }
    


}
