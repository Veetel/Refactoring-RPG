package re.forestier.edu;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Archer;

public class RPGClassTest {
    
    @Test
    void testArcherClass() {
        Archer archer = new Archer(1);
        assert(archer.getClassName().equals("Archer"));
        assert(archer.getClassDescription().equals("The Archer is a class that uses bow and arrows"));
        assert(archer.getStatsToString().equals("INT : 0\nATK : 0\nDEF : 0\nCHA : 0\nALC : 0\nVIS : 0"));
    }

    @Test
    @DisplayName ("Test level up for Archer class")
    void testArcherLevelUp() {
        Archer archer = new Archer(1);
        
        assertEquals(archer.getStatsToString(), "INT : 1\nATK : 3\nDEF : 1\nCHA : 1\nALC : 1\nVIS : 3");
        
        archer.levelUp(); // level 2
        assertEquals(archer.getStatsToString(), "INT : 1\nATK : 3\nDEF : 2\nCHA : 2\nALC : 1\nVIS : 3");

        archer.levelUp();// level 3
        assertEquals(archer.getStatsToString(), "INT : 1\nATK : 4\nDEF : 2\nCHA : 2\nALC : 1\nVIS : 3");

        archer.levelUp();// level 4
        assertEquals(archer.getStatsToString(), "INT : 1\nATK : 4\nDEF : 3\nCHA : 2\nALC : 1\nVIS : 3");

        archer.levelUp();// level 5
        assertEquals(archer.getStatsToString(), "INT : 1\nATK : 5\nDEF : 3\nCHA : 2\nALC : 1\nVIS : 3");
    }


}
