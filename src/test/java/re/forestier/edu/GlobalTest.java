package re.forestier.edu;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.Player;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        Player player = new Player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        player.addXp( 20);
        player.inventory = new ArrayList<>();

        verify(Affichage.afficherJoueur(player));
    }
    @Test 
    void testAffichageWithInventory() {
        Player player = new Player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        player.addXp( 20);
        player.inventory.add("Potion de soin");
        player.inventory.add("Epée en bois");

        verify(Affichage.afficherJoueur(player));
    }



}