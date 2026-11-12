package re.forestier.edu.rpg;

public class Archer extends RPGClass {
    
    

    private Archer() {
        super();
        
    }
    
    public Archer(int level) throws IllegalArgumentException {
        super();
        if (level < 1) {
            throw new IllegalArgumentException("Level must be greater than 0");
        }
        switch (level) {
            case 1:
                setATK(3);
                setVIS(3);
                break;
            default:
                for (int i = 1; i < level; i++) {
                    levelUp();
                }
                break;
        }
    }

    public void levelUp() {
        int classLevel = super.getLevel();
       
            if (classLevel%2 == 0){
                setDEF(getDEF() + 1);
            }
            if( classLevel%2 == 1){
                setATK(getATK() + 1);
            }            


            if(classLevel == 2){
                setCHA(getCHA() + 1);
            }
        
    }

    @Override
    public String getClassName() {
        return "Archer";
    }

    @Override
    public String getClassDescription() {
        return "The Archer is a class that uses bow and arrows";
    }
}
