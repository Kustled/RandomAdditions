package me.kustled.radditions.files;



import me.kustled.radditions.Randomadditions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class XpConfigFile {
    private final static XpConfigFile instance = new XpConfigFile();
    private File file;
    private YamlConfiguration config;
    private int initialXpAmount;
    private int additiveXpAmount;

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
            initialXpAmount = config.getInt("XP.initial-xp-amount");
            additiveXpAmount = config.getInt("XP.additive-xp-amount");
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

    public int getInitialXpAmount(){
        setInitialXpAmount(initialXpAmount);
        return initialXpAmount;
    }

    public void setInitialXpAmount(int initialXpAmount){
        this.initialXpAmount = initialXpAmount;
        set("XP.initial-xp-amount", initialXpAmount);
    }

    public int getAdditiveXpAmount(){
        setAdditiveXpAmount(additiveXpAmount);
        return additiveXpAmount;
    }

    public void setAdditiveXpAmount(int additiveXpAmount){
        this.additiveXpAmount = additiveXpAmount;
        set("XP.additive-xp-amount", additiveXpAmount);
    }

    public static XpConfigFile getInstance() {
        return instance;
    }

}
