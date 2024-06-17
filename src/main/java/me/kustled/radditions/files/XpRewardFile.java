package me.kustled.radditions.files;

import me.kustled.radditions.Randomadditions;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;

public class XpRewardFile {
    private final static XpRewardFile instance = new XpRewardFile();
    private File file;
    private YamlConfiguration config;
    private int pickStoneXP;
    private int pickCoalXP;
    private int pickIronXP;
    private int pickLapisXP;
    private int pickRedstoneXP;
    private int pickGoldXP;
    private int pickDiamondXP;
    private int pickNetheriteXP;
    private int KillRewardXP;

    private XpRewardFile(){}

    public void load(){
        file = new File(Randomadditions.getPlugin(Randomadditions.class).getDataFolder(), "xpreward.yml");

        if(!file.exists()){
            Randomadditions.getPlugin(Randomadditions.class).saveResource("xpreward.yml", false);
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
            pickStoneXP = config.getInt("PICKAXE.stone-reward");
            pickCoalXP = config.getInt("PICKAXE.coal-reward");
            pickIronXP = config.getInt("PICKAXE.iron-reward");
            pickLapisXP = config.getInt("PICKAXE.lapis-reward");
            pickRedstoneXP = config.getInt("PICKAXE.redstone-reward");
            pickGoldXP = config.getInt("PICKAXE.gold-reward");
            pickDiamondXP = config.getInt("PICKAXE.diamond-reward");
            pickNetheriteXP = config.getInt("PICKAXE.ancientdebris-reward");
            KillRewardXP = config.getInt("SWORD.kill-reward");
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

    public int getPickStoneXP(){
        setPickStoneXP(pickStoneXP);
        return pickStoneXP;
    }

    public int getPickCoalXP(){
        setPickCoalXP(pickCoalXP);
        return pickCoalXP;
    }

    public int getPickIronXP(){
        setPickIronXP(pickIronXP);
        return pickIronXP;
    }

    public int getPickLapisXP(){
        setPickLapisXP(pickLapisXP);
        return pickLapisXP;
    }

    public int getPickRedstoneXP(){
        setPickRedstoneXP(pickRedstoneXP);
        return pickRedstoneXP;
    }

    public int getPickGoldXP(){
        setPickGoldXP(pickGoldXP);
        return pickGoldXP;
    }

    public int getPickDiamondXP(){
        setPickDiamondXP(pickDiamondXP);
        return pickDiamondXP;
    }

    public int getPickNetheriteXP(){
        setPickNetheriteXP(pickNetheriteXP);
        return pickNetheriteXP;
    }

    public int getKillRewardXP(){
        setKillRewardXP(KillRewardXP);
        return KillRewardXP;
    }

    public void setPickStoneXP(int pickStoneXP){
        this.pickStoneXP = pickStoneXP;
        set("PICK.stone-reward", pickStoneXP);
    }

    public void setPickCoalXP(int pickCoalXP){
        this.pickCoalXP = pickCoalXP;
        set("PICKAXE.coal-reward", pickCoalXP);
    }

    public void setPickIronXP(int pickIronXP){
        this.pickIronXP = pickIronXP;
        set("PICKAXE.iron-reward", pickIronXP);
    }

    public void setPickLapisXP(int pickLapisXP){
        this.pickLapisXP = pickLapisXP;
        set("PICKAXE.lapis-reward", pickLapisXP);
    }

    public void setPickRedstoneXP(int pickRedstoneXP){
        this.pickRedstoneXP = pickRedstoneXP;
        set("PICKAXE.redstone-reward", pickRedstoneXP);
    }

    public void setPickGoldXP(int pickGoldXP){
        this.pickGoldXP = pickGoldXP;
        set("PICKAXE.gold-reward", pickGoldXP);
    }

    public void setPickDiamondXP(int pickDiamondXP){
        this.pickDiamondXP = pickDiamondXP;
        set("PICKAXE.diamond-reward", pickDiamondXP);
    }

    public void setPickNetheriteXP(int pickNetheriteXP){
        this.pickNetheriteXP = pickNetheriteXP;
        set("PICKAXE.ancientdebris-reward", pickNetheriteXP);
    }

    public void setKillRewardXP(int KillRewardXP){
        this.KillRewardXP = KillRewardXP;
        set("SWORD.kill-reward", KillRewardXP);
    }

    public static XpRewardFile getInstance() {
        return instance;
    }
}
