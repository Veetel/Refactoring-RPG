package re.forestier.edu.rpg;
import java.util.HashMap;
abstract class RPGClass {

    //level sera changé par la classe héritière grâcec à la méthode levelUp()
    private int level;
    private int INT;
    private int ATK;
    private int DEF;
    private int CHA;
    private int ALC;
    private int VIS;

    protected RPGClass() {
        
        this.level = 1;
        this.INT = 0;
        this.ATK = 0;
        this.DEF = 0;
        this.CHA = 0;
        this.ALC = 0;
        this.VIS = 0;
    }


    public abstract String getClassName();
    public abstract String getClassDescription();
    
    public String getStatsToString(){
        return "INT : " + this.INT + "\n" +
                "ATK : " + this.ATK + "\n" +
                "DEF : " + this.DEF + "\n" +
                "CHA : " + this.CHA + "\n" +
                "ALC : " + this.ALC + "\n" +
                "VIS : " + this.VIS;
    };

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

    public abstract void levelUp();

    protected void incrementLevel(){
        this.level++;
    }

    
    protected int getINT(){
        return this.INT;
    }
    protected  int getATK(){
        return this.ATK;
    }
    protected  int getDEF(){
        return this.DEF;
    }
    protected  int getCHA(){
        return this.CHA;
    }
    protected  int getALC(){
        return this.ALC;
    }

    protected  int getVIS(){
        return this.VIS;
    }

    protected void setINT(int INT){
        this.INT = INT;
    }
    protected void setATK(int ATK){
        this.ATK = ATK;
    }
    protected void setDEF(int DEF){
        this.DEF = DEF;
    }

    protected void setCHA(int CHA){
        this.CHA = CHA;
    }

    protected void setALC(int ALC){
        this.ALC = ALC;
    }

    protected void setVIS(int VIS){
        this.VIS = VIS;
    }
    protected int getLevel(){
        return this.level;
    }





    
}