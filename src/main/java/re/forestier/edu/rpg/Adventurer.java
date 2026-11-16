package re.forestier.edu.rpg;

public class Adventurer extends RPGClass {
    

    @Override 
    public String getClassName(){
        return "Adventurer";
    }
    @Override 
    public String getClassDescription(){
        return "The default class for new player";
    }


    @Override
    protected void initializeBaseStats(){
        setINT(1);
        setDEF(1);
        setATK(3);
        setCHA(2);
    }

    @Override
    protected void updateStatsAfterLevelUp(){
        switch(this.getLevel()){
            default: 
                initializeBaseStats();
            case 2:
                setINT(2);
                setCHA(3);

            case 3:
                setATK(5);
                setALC(1);
            case 4:
                setDEF(3);
            case 5:  
                setVIS(1);
                setDEF(4);
        }

    }
    

}
