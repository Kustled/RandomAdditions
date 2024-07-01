package me.kustled.radditions.files;

import me.kustled.radditions.Randomadditions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class XpConfigFile {
    private final static XpConfigFile instance = new XpConfigFile();
    private File file;
    private YamlConfiguration config;
    private int pickInitialXpAmount;
    private int pickAdditiveXpAmount;
    private int swordInitialXpAmount;
    private int swordAdditiveXpAmount;
    private int shovelInitialXpAmount;
    private int shovelAdditiveXpAmount;
    private int axeInitialXpAmount;
    private int axeAdditiveXpAmount;

    private XpConfigFile(){

    }

    public void load(){
        file = new File(Randomadditions.getPlugin(Randomadditions.class).getDataFolder(), "xpconfig.yml");

        if(!file.exists()){
            Randomadditions.getPlugin(Randomadditions.class).saveResource("xpconfig.yml", false);
            config = new YamlConfiguration();
            config.options().parseComments(true);
            try{
                config.load(file);
            }catch(Exception e){e.printStackTrace();}
        }
        config = new YamlConfiguration();
        config.options().parseComments(true);
        try{
            config.load(file);
            pickInitialXpAmount = config.getInt("PICK.initial-xp-amount");
            pickAdditiveXpAmount = config.getInt("PICK.additive-xp-amount");
            swordInitialXpAmount = config.getInt("SWORD.initial-xp-amount");
            swordAdditiveXpAmount = config.getInt("SWORD.additive-xp-amount");
            shovelInitialXpAmount = config.getInt("SHOVEL.initial-xp-amount");
            shovelAdditiveXpAmount = config.getInt("SHOVEL.additive-xp-amount");
            axeInitialXpAmount = config.getInt("AXE.initial-xp-amount");
            axeAdditiveXpAmount = config.getInt("AXE.additive-xp-amount");
        }catch(Exception e){e.printStackTrace();}
    }

    public void save(){
        try{
            config.save(file);
        }catch(Exception ignored){}
    }

    public void set(String path, Object value){
        config.set(path, value);
        save();
    }

    public int getpickInitialXpAmount(){
        setpickInitialXpAmount(pickInitialXpAmount);
        return pickInitialXpAmount;
    }
    public void setpickInitialXpAmount(int pickInitialXpAmount){
        this.pickInitialXpAmount = pickInitialXpAmount;
        set("PICK.initial-xp-amount", pickInitialXpAmount);
    }

    public int getpickAdditiveXpAmount(){
        setpickAdditiveXpAmount(pickAdditiveXpAmount);
        return pickAdditiveXpAmount;
    }
    public void setpickAdditiveXpAmount(int pickAdditiveXpAmount){
        this.pickAdditiveXpAmount = pickAdditiveXpAmount;
        set("PICK.additive-xp-amount", pickAdditiveXpAmount);
    }

    public int getswordInitialXpAmount(){
        setswordInitialXpAmount(swordInitialXpAmount);
        return swordInitialXpAmount;
    }
    public void setswordInitialXpAmount(int swordInitialXpAmount){
        this.swordInitialXpAmount = swordInitialXpAmount;
        set("SWORD.initial-xp-amount", swordInitialXpAmount);
    }

    public int getswordAdditiveXpAmount(){
        setswordAdditiveXpAmount(swordAdditiveXpAmount);
        return swordAdditiveXpAmount;
    }

    public void setswordAdditiveXpAmount(int swordAdditiveXpAmount){
        this.swordAdditiveXpAmount = swordAdditiveXpAmount;
        set("SWORD.additive-xp-amount", swordAdditiveXpAmount);
    }

    public int getshovelInitialXpAmount(){
        setshovelInitialXpAmount(shovelInitialXpAmount);
        return shovelInitialXpAmount;
    }
    public void setshovelInitialXpAmount(int shovelInitialXpAmount){
        this.shovelInitialXpAmount = shovelInitialXpAmount;
        set("SHOVEL.initial-xp-amount", shovelInitialXpAmount);
    }

    public int getshovelAdditiveXpAmount(){
        setshovelAdditiveXpAmount(shovelAdditiveXpAmount);
        return shovelAdditiveXpAmount;
    }
    public void setshovelAdditiveXpAmount(int shovelAdditiveXpAmount){
        this.shovelAdditiveXpAmount = shovelAdditiveXpAmount;
        set("SHOVEL.additive-xp-amount", shovelAdditiveXpAmount);
    }

    public int getaxeInitialXpAmount(){
        setaxeInitialXpAmount(axeInitialXpAmount);
        return axeInitialXpAmount;
    }
    public void setaxeInitialXpAmount(int axeInitialXpAmount){
        this.axeInitialXpAmount = axeInitialXpAmount;
        set("AXE.initial-xp-amount", axeInitialXpAmount);
    }

    public int getaxeAdditiveXpAmount(){
        setaxeAdditiveXpAmount(axeAdditiveXpAmount);
        return axeAdditiveXpAmount;
    }
    public void setaxeAdditiveXpAmount(int axeAdditiveXpAmount){
        this.axeAdditiveXpAmount = axeAdditiveXpAmount;
        set("AXE.additive-xp-amount", axeAdditiveXpAmount);
    }

    public static XpConfigFile getInstance() {
        return instance;
    }

}
