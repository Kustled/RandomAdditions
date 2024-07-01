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
    private int axeKillRewardXP;
    private int axeOakXP;
    private int axeBirchXP;
    private int axeSpruceXP;
    private int axeAcaciaXP;
    private int axeDarkOakXP;
    private int axeCrimsonXP;
    private int axeWarpedXP;
    private int shovDirtXP;
    private int shovGrassXP;
    private int shovPodzolXP;
    private int shovMyceliumXP;

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
            axeKillRewardXP = config.getInt("AXE.kill-reward");
            axeOakXP = config.getInt("AXE.oak-reward");
            axeBirchXP = config.getInt("AXE.birch-reward");
            axeSpruceXP = config.getInt("AXE.spruce-reward");
            axeAcaciaXP = config.getInt("AXE.acacia-reward");
            axeDarkOakXP = config.getInt("AXE.dark-oak-reward");
            axeCrimsonXP = config.getInt("AXE.crimson-stem-reward");
            axeWarpedXP = config.getInt("AXE.warped-stem-reward");
            shovDirtXP = config.getInt("SHOVEL.dirt-reward");
            shovGrassXP = config.getInt("SHOVEL.grass-reward");
            shovPodzolXP = config.getInt("SHOVEL.podzol-reward");
            shovMyceliumXP = config.getInt("SHOVEL.mycelium-reward");
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

    public int getAxeKillRewardXP(){
        setAxeKillRewardXP(axeKillRewardXP);
        return axeKillRewardXP;
    }

    public int getAxeOakXP(){
        setAxeOakXP(axeOakXP);
        return axeOakXP;
    }

    public int getAxeBirchXP(){
        setAxeBirchXP(axeBirchXP);
        return axeBirchXP;
    }

    public int getAxeSpruceXP(){
        setAxeSpruceXP(axeSpruceXP);
        return axeSpruceXP;
    }

    public int getAxeAcaciaXP(){
        setAxeAcaciaXP(axeAcaciaXP);
        return axeAcaciaXP;
    }

    public int getAxeDarkOakXP(){
        setAxeDarkOakXP(axeDarkOakXP);
        return axeDarkOakXP;
    }

    public int getAxeCrimsonXP(){
        setAxeCrimsonXP(axeCrimsonXP);
        return axeCrimsonXP;
    }

    public int getAxeWarpedXP(){
        setAxeWarpedXP(axeWarpedXP);
        return axeWarpedXP;
    }

    public int getShovDirtXP(){
        setShovDirtXP(shovDirtXP);
        return shovDirtXP;
    }

    public int getShovGrassXP(){
        setShovGrassXP(shovGrassXP);
        return shovGrassXP;
    }

    public int getShovPodzolXP(){
        setShovPodzolXP(shovPodzolXP);
        return shovPodzolXP;
    }

    public int getShovMyceliumXP(){
        setShovMyceliumXP(shovMyceliumXP);
        return shovMyceliumXP;
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

    public void setAxeKillRewardXP(int axeKillRewardXP){
        this.axeKillRewardXP = axeKillRewardXP;
        set("AXE.kill-reward", axeKillRewardXP);
    }

    public void setAxeOakXP(int axeOakXP){
        this.axeOakXP = axeOakXP;
        set("AXE.oak-reward", axeOakXP);
    }

    public void setAxeBirchXP(int axeBirchXP){
        this.axeBirchXP = axeBirchXP;
        set("AXE.birch-reward", axeBirchXP);
    }

    public void setAxeSpruceXP(int axeSpruceXP){
        this.axeSpruceXP = axeSpruceXP;
        set("AXE.spruce-reward", axeSpruceXP);
    }

    public void setAxeAcaciaXP(int axeAcaciaXP){
        this.axeAcaciaXP = axeAcaciaXP;
        set("AXE.acacia-reward", axeAcaciaXP);
    }

    public void setAxeDarkOakXP(int axeDarkOakXP){
        this.axeDarkOakXP = axeDarkOakXP;
        set("AXE.dark-oak-reward", axeDarkOakXP);
    }

    public void setAxeCrimsonXP(int axeCrimsonXP){
        this.axeCrimsonXP = axeCrimsonXP;
        set("AXE.crimson-stem-reward", axeCrimsonXP);
    }

    public void setAxeWarpedXP(int axeWarpedXP){
        this.axeWarpedXP = axeWarpedXP;
        set("AXE.warped-stem-reward", axeWarpedXP);
    }

    public void setShovDirtXP(int shovDirtXP){
        this.shovDirtXP = shovDirtXP;
        set("SHOVEL.dirt-reward", shovDirtXP);
    }

    public void setShovGrassXP(int shovGrassXP){
        this.shovGrassXP = shovGrassXP;
        set("SHOVEL.grass-reward", shovGrassXP);
    }

    public void setShovPodzolXP(int shovPodzolXP){
        this.shovPodzolXP = shovPodzolXP;
        set("SHOVEL.podzol-reward", shovPodzolXP);
    }

    public void setShovMyceliumXP(int shovMyceliumXP){
        this.shovMyceliumXP = shovMyceliumXP;
        set("SHOVEL.mycelium-reward", shovMyceliumXP);
    }

    public static XpRewardFile getInstance() {
        return instance;
    }
}
