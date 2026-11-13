package re.forestier.edu.rpg;

/**
 * RPG character class specializing in ranged combat.
 * Its attributes progress according to the Archer-specific level-up rules.
 */
public final class Archer extends RPGClass {
    
    

    /** Creates an Archer using the base class defaults. */
    private Archer() {
        super();
        
    }

    /**
     * Creates an Archer at the specified level.
     * The initial attack and vision values are set before applying each level-up.
     *
     * @param level the starting level; must be at least 1
     * @throws IllegalArgumentException if {@code level} is less than 1
     */
    public Archer(int level) throws IllegalArgumentException {
        super();
        if (level < 1) {
            throw new IllegalArgumentException("Level must be greater than 0");
        }            
        setINT(1);
        setCHA(1);
        setATK(3);
        setVIS(3);

        for (int i = 1; i < level; i++) {
            levelUp();            
        }
    }

    /**
     * Advances the Archer by one level and updates its attributes.
     * Starting from an odd level increases ATK; starting from an even level
     * increases DEF. Advancing from level 2 also increases CHA.
     */
    @Override 
    public void levelUp() {
        incrementLevel();
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

    /** @return the class name, {@code "Archer"} */
    @Override
    public String getClassName() {
        return "Archer";
    }

    /** @return a short description of the Archer class */
    @Override
    public String getClassDescription() {
        return "The Archer is a class that uses bow and arrows";
    }
}
