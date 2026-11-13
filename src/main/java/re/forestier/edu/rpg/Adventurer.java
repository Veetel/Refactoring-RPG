package re.forestier.edu.rpg;

import java.util.HashMap;

public class Adventurer extends RPGClass {
    
    private HashMap<String,String> Abilities;

    @Override 
    public String getClassName(){
        return "Adventurer";
    }
    @Override 
    public String getClassDescription(){
        return "The default class for new player";
    }

    public String getAbilities(){
        String out;
        out = "Abilities : \n";
        for (String ability : Abilities.keySet()) {
            out += ability + " : " + Abilities.get(ability) + "\n";
        }

        return out;
    }

    @Override
    protected void updateStatsAfterLevelUp(){
    }
    

}
