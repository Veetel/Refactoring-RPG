package re.forestier.edu.rpg;

import java.util.HashMap;

abstract class RPGClasse {

    private int level;
    private int INT;
    private int ATK;
    private int DEF;
    private int CHA;
    private int ALC;
    private int VIS;



    public abstract String getClassName();
    public abstract String getClassDescription();
    public abstract String getAbilities();
    
    public String getStats(){
        return "INT : " + this.INT + "\n" +
                "ATK : " + this.ATK + "\n" +
                "DEF : " + this.DEF + "\n" +
                "CHA : " + this.CHA + "\n" +
                "ALC : " + this.ALC + "\n" +
                "VIS : " + this.VIS;
    };

    
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



    
}