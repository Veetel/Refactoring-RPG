package re.forestier.edu;

import java.util.ArrayList;

import static org.approvaltests.Approvals.verify;
import org.junit.jupiter.api.Test;

import re.forestier.edu.rpg.Affichage;
import re.forestier.edu.rpg.UpdatePlayer;
import re.forestier.edu.rpg.player;

public class GlobalTest {

    @Test
    void testAffichageBase() {
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        UpdatePlayer.addXp(player, 20);
        player.inventory = new ArrayList<>();

        verify(Affichage.afficherJoueur(player));
    }
    @Test 
    void testAffichageWithInventory() {
        player player = new player("Florian", "Gnognak le Barbare", "ADVENTURER", 200, new ArrayList<>());
        UpdatePlayer.addXp(player, 20);
        player.inventory.add("Potion de soin");
        player.inventory.add("Epée en bois");

        verify(Affichage.afficherJoueur(player));
    }



}