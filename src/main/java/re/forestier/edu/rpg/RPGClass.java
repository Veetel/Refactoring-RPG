package re.forestier.edu.rpg;
import java.util.HashMap;


/**
 * Abstract class representing a character class in the RPG. All attributes are
 * private and can only be accessed through the API provided by this class.
 * Subclasses can access them through the protected getters and setters.
 * The {@link #getStats()} method is provided for legacy implementations.
 */
abstract class RPGClass {

    // The level is changed by subclasses through the levelUp() method.
    private int level;
    private int INT;
    private int ATK;
    private int DEF;
    private int CHA;
    private int ALC;
    private int VIS;

    //----------------- Constructors -------------------

    /**
     * Initializes the character at level 1 with all attributes set to zero.
     * Subclass constructors call this constructor.
     */
    protected RPGClass() {
        
        this.level = 1;
        this.INT = 0;
        this.ATK = 0;
        this.DEF = 0;
        this.CHA = 0;
        this.ALC = 0;
        this.VIS = 0;
    }


    //----------------- Abstract methods ----------------
    /** @return the name of this character class */
    public abstract String getClassName();
    
    protected abstract void updateStatsAfterLevelUp();
    
    /** @return a description of this character class */
    public abstract String getClassDescription();
    
    /**
     * Formats the six attributes as text in a stable order.
     *
     * @return the attributes separated by line breaks
     */
    public String getStatsToString(){
        return "INT : " + this.INT + "\n" +
                "ATK : " + this.ATK + "\n" +
                "DEF : " + this.DEF + "\n" +
                "CHA : " + this.CHA + "\n" +
                "ALC : " + this.ALC + "\n" +
                "VIS : " + this.VIS;
    };

    /**
     * Returns the character attributes indexed by their codes.
     * The level is not included in this collection.
     *
     * @return a new map containing INT, ATK, DEF, CHA, ALC, and VIS
     */
    public HashMap<String, Integer> getStats() {
        HashMap<String, Integer> stats = new HashMap<>();
        stats.put("INT", this.INT);
        stats.put("ATK", this.ATK);
        stats.put("DEF", this.DEF);
        stats.put("CHA", this.CHA);
        stats.put("ALC", this.ALC);
        stats.put("VIS", this.VIS);
        return stats;
    }

    /**
     * Applies the progression defined by the subclass when the character levels up.
     */
    public final void levelUp(){
        incrementLevel();
        updateStatsAfterLevelUp();
    };

    /** Increments the current level by one. */
    protected void incrementLevel(){
        this.level++;
    }

    /** @return the current INT attribute value */
    protected int getINT(){
        return this.INT;
    }

    /** @return the current ATK attribute value */
    protected  int getATK(){
        return this.ATK;
    }

    /** @return the current DEF attribute value */
    protected  int getDEF(){
        return this.DEF;
    }

    /** @return the current CHA attribute value */
    protected  int getCHA(){
        return this.CHA;
    }

    /** @return the current ALC attribute value */
    protected  int getALC(){
        return this.ALC;
    }

    /** @return the current VIS attribute value */
    protected  int getVIS(){
        return this.VIS;
    }

    /** Sets the INT attribute value. */
    protected void setINT(int INT){
        this.INT = INT;
    }

    /** Sets the ATK attribute value. */
    protected void setATK(int ATK){
        this.ATK = ATK;
    }

    /** Sets the DEF attribute value. */
    protected void setDEF(int DEF){
        this.DEF = DEF;
    }

    /** Sets the CHA attribute value. */
    protected void setCHA(int CHA){
        this.CHA = CHA;
    }

    /** Sets the ALC attribute value. */
    protected void setALC(int ALC){
        this.ALC = ALC;
    }

    /** Sets the VIS attribute value. */
    protected void setVIS(int VIS){
        this.VIS = VIS;
    }

    /** @return the character's current level */
    protected int getLevel(){
        return this.level;
    }
}