package re.forestier.edu.rpg;

import java.util.HashMap;

public class Adventurer extends RPGClasse {
    
    private HashMap<String,String> Abilities;

    public String getClassName(){
        return "Aventurier";
    }

    public String getClassDescription(){
        return "La classe par défaut pour tous les nouveaux joueurs";
    }

    public String getAbilities(){
        String out;
        out = "Abilities : \n";
        for (String ability : Abilities.keySet()) {
            out += ability + " : " + Abilities.get(ability) + "\n";
        }

        return out;
    }
    

}
