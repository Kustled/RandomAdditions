package me.kustled.radditions;

import me.kustled.radditions.events.LevelCheckEvent;
import me.kustled.radditions.files.XpConfigFile;
import me.kustled.radditions.util.DelayedTask;
import net.md_5.bungee.api.ChatMessageType;
import net.md_5.bungee.api.chat.TextComponent;
import org.bukkit.*;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.entity.*;
import org.bukkit.event.inventory.PrepareAnvilEvent;
import org.bukkit.event.inventory.PrepareGrindstoneEvent;
import org.bukkit.inventory.*;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.persistence.PersistentDataType;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.UUID;

public final class Randomadditions extends JavaPlugin implements Listener {


    @Override
    public void onEnable() {

        //configurar config
        FileConfiguration config = this.getConfig();
        List<String> headerS = new ArrayList<String>();
        headerS.add("This is the default configuration file.");
        headerS.add("You can edit if charged creepers spawn naturally, and");
        headerS.add("edit the chance of it happening (Default is 1 in 1024).");
        config.options().setHeader(headerS);
        config.addDefault("spawnChargedCreeperNaturally", true);
        config.addDefault("spawnChargedCreeperChance", 1024);
        config.options().copyDefaults(true);
        saveConfig();
        XpConfigFile.getInstance().load();

        //aço itemstack
        ItemStack steel = new ItemStack(Material.IRON_INGOT);
        ItemMeta steelMeta = steel.getItemMeta();
        steelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Ingot");
        ArrayList<String> steelLore = new ArrayList<String>();
        steelLore.add(ChatColor.GOLD + "Stronger than iron.");
        steelMeta.setLore(steelLore);
        steel.setItemMeta(steelMeta);
        //aço9 itemstack
        ItemStack steel9 = new ItemStack(Material.IRON_INGOT);
        ItemMeta steel9Meta = steel9.getItemMeta();
        steel9Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Ingot");
        ArrayList<String> steel9Lore = new ArrayList<String>();
        steel9Lore.add(ChatColor.GOLD + "Stronger than iron.");
        steel9Meta.setLore(steel9Lore);
        steel9.setAmount(9);
        steel9.setItemMeta(steel9Meta);
        //bloco de aço itemstack
        ItemStack steelblock = new ItemStack(Material.IRON_BLOCK);
        ItemMeta steelblockMeta = steelblock.getItemMeta();
        steelblockMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Block");
        steelblock.setItemMeta(steelblockMeta);
        //pepita de aço itemstack
        ItemStack steelnugget = new ItemStack(Material.IRON_NUGGET);
        ItemMeta steelnuggetMeta = steelnugget.getItemMeta();
        steelnuggetMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Nugget");
        steelnugget.setItemMeta(steelnuggetMeta);
        //pepita de aço9 itemstack
        ItemStack steelnugget9 = new ItemStack(Material.IRON_NUGGET);
        ItemMeta steelnugget9Meta = steelnugget9.getItemMeta();
        steelnugget9Meta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Nugget");
        steelnugget9.setAmount(9);
        steelnugget9.setItemMeta(steelnugget9Meta);

        //multibow itemstack
        ItemStack arcotop = new ItemStack(Material.BOW);
        ItemMeta arcotopMeta = arcotop.getItemMeta();
        arcotopMeta.setDisplayName(ChatColor.AQUA + "MultiBow");
        ArrayList<String> arcolore = new ArrayList<String>();
        arcolore.add(ChatColor.GOLD + "Shoots multiple arrows at once.");
        arcotopMeta.setLore(arcolore);
        arcotopMeta.getPersistentDataContainer().set(new NamespacedKey(this, "arcotopcheck"), PersistentDataType.BOOLEAN, true);
        arcotop.setItemMeta(arcotopMeta);

        //flecha de gelo itemstack
        ItemStack flechadegelo = new ItemStack(Material.ARROW);
        ItemMeta flechageloMeta = flechadegelo.getItemMeta();
        flechageloMeta.setDisplayName(ChatColor.BLUE + "Ice Arrow");
        flechageloMeta.getPersistentDataContainer().set(new NamespacedKey(this, "flechagelocheck"), PersistentDataType.BOOLEAN, true);
        ArrayList<String> flechageloLore = new ArrayList<String>();
        flechageloLore.add(ChatColor.GOLD + "It will freeze your enemies.");
        flechageloMeta.setLore(flechageloLore);
        flechadegelo.setItemMeta(flechageloMeta);
        flechadegelo.setAmount(8);

        //armadura de cobre itemstack
        //capacete
        ItemStack capacetecobre = new ItemStack(Material.LEATHER_HELMET);
        LeatherArmorMeta capacetecobreMeta = (LeatherArmorMeta) capacetecobre.getItemMeta();
        capacetecobreMeta.setDisplayName(ChatColor.WHITE + "Copper Helmet");
        capacetecobreMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        capacetecobreMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Armor", 2, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HEAD));
        capacetecobreMeta.setColor(Color.ORANGE);
        capacetecobre.setItemMeta(capacetecobreMeta);
        //peitoral
        ItemStack peitoralcobre = new ItemStack(Material.LEATHER_CHESTPLATE);
        LeatherArmorMeta peitoralcobreMeta = (LeatherArmorMeta) peitoralcobre.getItemMeta();
        peitoralcobreMeta.setDisplayName(ChatColor.WHITE + "Copper Chestplate");
        peitoralcobreMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        peitoralcobreMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"Armor", 5, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        peitoralcobreMeta.setColor(Color.ORANGE);
        peitoralcobre.setItemMeta(peitoralcobreMeta);
        //calça
        ItemStack calcacobre = new ItemStack(Material.LEATHER_LEGGINGS);
        LeatherArmorMeta calcacobreMeta = (LeatherArmorMeta) calcacobre.getItemMeta();
        calcacobreMeta.setDisplayName(ChatColor.WHITE + "Copper Leggings");
        calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "calcacobrecheck"), PersistentDataType.BOOLEAN, true);
        calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "calcacobretimercheck"), PersistentDataType.BOOLEAN, true);
        calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER, 0);
        ArrayList<String> calcacobrelore = new ArrayList<String>();
        calcacobrelore.add(ChatColor.YELLOW + "Ability: Charging");
        calcacobrelore.add(ChatColor.YELLOW + "Every time you get hit by lightning,");
        calcacobrelore.add(ChatColor.YELLOW + "This item gets +10% speed. (Max 30%)");
        calcacobrelore.add(ChatColor.GOLD + "Current bonus: " + calcacobreMeta.getPersistentDataContainer().get(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER).toString());
        calcacobreMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        calcacobreMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"Armor", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
        calcacobreMeta.setColor(Color.ORANGE);
        calcacobreMeta.setLore(calcacobrelore);
        calcacobre.setItemMeta(calcacobreMeta);
        //botas
        ItemStack botascobre = new ItemStack(Material.LEATHER_BOOTS);
        LeatherArmorMeta botascobreMeta = (LeatherArmorMeta) botascobre.getItemMeta();
        botascobreMeta.setDisplayName(ChatColor.WHITE + "Copper Boots");
        botascobreMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        botascobreMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(),"Armor", 1, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.FEET));
        botascobreMeta.setColor(Color.ORANGE);
        botascobre.setItemMeta(botascobreMeta);

        //ferramentas de cobre itemstack
        //espada
        ItemStack espadacobre = new ItemStack(Material.STONE_SWORD);
        ItemMeta espadacobreMeta = espadacobre.getItemMeta();
        espadacobreMeta.setDisplayName(ChatColor.AQUA + "Copper Sword");
        ArrayList<String> espadacobreLore = new ArrayList<String>();
        espadacobreLore.add(ChatColor.GOLD + "Has a 5% chance to strike enemies with lightning.");
        espadacobreLore.add(ChatColor.WHITE + "Lightning I");
        espadacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        espadacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "espadacobrecheck"), PersistentDataType.BOOLEAN, true);
        espadacobreMeta.setLore(espadacobreLore);
        espadacobre.setItemMeta(espadacobreMeta);
        //picareta
        ItemStack picaretacobre = new ItemStack(Material.STONE_PICKAXE);
        ItemMeta picaretacobreMeta = picaretacobre.getItemMeta();
        picaretacobreMeta.setDisplayName(ChatColor.AQUA + "Copper Pickaxe");
        picaretacobreMeta.addEnchant(Enchantment.DIG_SPEED, 1, false);
        picaretacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        picaretacobre.setItemMeta(picaretacobreMeta);
        //machado
        ItemStack machadocobre = new ItemStack(Material.STONE_AXE);
        ItemMeta machadocobreMeta = machadocobre.getItemMeta();
        machadocobreMeta.setDisplayName(ChatColor.AQUA + "Copper Axe");
        machadocobreMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        machadocobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        machadocobre.setItemMeta(machadocobreMeta);
        //pá
        ItemStack pacobre = new ItemStack(Material.STONE_SHOVEL);
        ItemMeta pacobreMeta = pacobre.getItemMeta();
        pacobreMeta.setDisplayName(ChatColor.AQUA + "Copper Shovel");
        pacobreMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        pacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        pacobre.setItemMeta(pacobreMeta);
        //enxada
        ItemStack enxadacobre = new ItemStack(Material.STONE_HOE);
        ItemMeta enxadacobreMeta = enxadacobre.getItemMeta();
        enxadacobreMeta.setDisplayName(ChatColor.AQUA + "Copper Hoe");
        enxadacobreMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
        enxadacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        enxadacobre.setItemMeta(enxadacobreMeta);

        //armadura de aço itemstack
        //capacete
        ItemStack capacetesteel = new ItemStack(Material.IRON_HELMET);
        ItemMeta capacetesteelMeta = capacetesteel.getItemMeta();
        capacetesteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Helmet");
        capacetesteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        capacetesteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        capacetesteel.setItemMeta(capacetesteelMeta);
        //peitoral
        ItemStack peitoralsteel = new ItemStack(Material.IRON_CHESTPLATE);
        ItemMeta peitoralsteelMeta = peitoralsteel.getItemMeta();
        peitoralsteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Chestplate");
        peitoralsteelMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
        peitoralsteelMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Armor", 7, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.CHEST));
        peitoralsteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        peitoralsteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        peitoralsteel.setItemMeta(peitoralsteelMeta);
        //calça
        ItemStack calcasteel = new ItemStack(Material.IRON_LEGGINGS);
        ItemMeta calcasteelMeta = calcasteel.getItemMeta();
        calcasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD +  "Steel Leggings");
        calcasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        calcasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        calcasteel.setItemMeta(calcasteelMeta);
        //botas
        ItemStack botasteel = new ItemStack(Material.IRON_BOOTS);
        ItemMeta botasteelMeta = botasteel.getItemMeta();
        botasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Boots");
        botasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        botasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        botasteel.setItemMeta(botasteelMeta);

        //ferramentas de aço itemstack
        //espada
        ItemStack espadasteel = new ItemStack(Material.IRON_SWORD);
        ItemMeta espadasteelMeta = espadasteel.getItemMeta();
        espadasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Sword");
        espadasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        espadasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        espadasteel.setItemMeta(espadasteelMeta);
        //picareta
        ItemStack picaretasteel = new ItemStack(Material.IRON_PICKAXE);
        ItemMeta picaretasteelMeta = picaretasteel.getItemMeta();
        picaretasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Pickaxe");
        picaretasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        picaretasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        picaretasteel.setItemMeta(picaretasteelMeta);
        //machado
        ItemStack machadosteel = new ItemStack(Material.IRON_AXE);
        ItemMeta machadosteelMeta = machadosteel.getItemMeta();
        machadosteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Axe");
        machadosteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        machadosteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        machadosteel.setItemMeta(machadosteelMeta);
        //pá
        ItemStack pasteel = new ItemStack(Material.IRON_SHOVEL);
        ItemMeta pasteelMeta = pasteel.getItemMeta();
        pasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Shovel");
        pasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        pasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        pasteel.setItemMeta(pasteelMeta);
        //enxada
        ItemStack enxadasteel = new ItemStack(Material.IRON_HOE);
        ItemMeta enxadasteelMeta = enxadasteel.getItemMeta();
        enxadasteelMeta.setDisplayName(ChatColor.WHITE + "" + ChatColor.BOLD + "Steel Hoe");
        enxadasteelMeta.addEnchant(Enchantment.DURABILITY, 5, true);
        enxadasteelMeta.getPersistentDataContainer().set(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN, true);
        enxadasteel.setItemMeta(enxadasteelMeta);

        //aço recipe
        ShapelessRecipe steelRecipe = new ShapelessRecipe(new NamespacedKey(this, "steelkey"), steel);
        steelRecipe.addIngredient(Material.IRON_INGOT);
        steelRecipe.addIngredient(Material.COAL);
        steelRecipe.addIngredient(Material.COAL);
        steelRecipe.addIngredient(Material.COAL);
        steelRecipe.addIngredient(Material.COAL);
        //aço9 recipe
        ShapelessRecipe steel9Recipe = new ShapelessRecipe(new NamespacedKey(this, "steel9key"), steel9);
        steel9Recipe.addIngredient(new RecipeChoice.ExactChoice(steelblock));
        //bloco de aço recipe
        ShapedRecipe steelblockRecipe = new ShapedRecipe(new NamespacedKey(this, "steelblockkey"), steelblock);
        steelblockRecipe.shape("sss", "sss", "sss");
        steelblockRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        //pepita de aço recipe
        ShapelessRecipe steelnuggetRecipe = new ShapelessRecipe(new NamespacedKey(this, "steelnuggetkey"), steelnugget9);
        steelnuggetRecipe.addIngredient(new RecipeChoice.ExactChoice(steel));

        //multibow recipe
        ShapedRecipe arcotopRecipe = new ShapedRecipe(new NamespacedKey(this, "arcotopkey"), arcotop);
        arcotopRecipe.shape("db ", "d b", "db ");
        arcotopRecipe.setIngredient('b', Material.BOW);
        arcotopRecipe.setIngredient('d', Material.DIAMOND);

        //flecha de gelo recipe
        ShapedRecipe flechageloRecipe = new ShapedRecipe(new NamespacedKey(this, "flechagelokey"), flechadegelo);
        flechageloRecipe.shape("aaa", "aia", "aaa");
        flechageloRecipe.setIngredient('a', Material.ARROW);
        flechageloRecipe.setIngredient('i', Material.BLUE_ICE);

        //armor de cobre recipe
        //capacete cobre
        ShapedRecipe capacetecobreRecipe = new ShapedRecipe(new NamespacedKey(this, "capacetecobrekey"), capacetecobre);
        capacetecobreRecipe.shape("ccc", "c c", "   ");
        capacetecobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        //peitoral cobre
        ShapedRecipe peitoralcobreRecipe = new ShapedRecipe(new NamespacedKey(this, "peitoralcobrekey"), peitoralcobre);
        peitoralcobreRecipe.shape("c c", "ccc", "ccc");
        peitoralcobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        //calça cobre
        ShapedRecipe calcacobreRecipe = new ShapedRecipe(new NamespacedKey(this, "calcacobrekey"), calcacobre);
        calcacobreRecipe.shape("ccc", "c c", "c c");
        calcacobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        //botas cobre
        ShapedRecipe botascobreRecipe = new ShapedRecipe(new NamespacedKey(this, "botascobrekey"), botascobre);
        botascobreRecipe.shape("   ", "c c", "c c");
        botascobreRecipe.setIngredient('c', Material.COPPER_INGOT);

        //ferramenta de cobre recipe
        //espada
        ShapedRecipe espadacobreRecipe = new ShapedRecipe(new NamespacedKey(this, "espadacobrekey"), espadacobre);
        espadacobreRecipe.shape(" c ", " c ", " s ");
        espadacobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        espadacobreRecipe.setIngredient('s', Material.STICK);
        //picareta
        ShapedRecipe picaretacobreRecipe = new ShapedRecipe(new NamespacedKey(this, "picaretacobrekey"), picaretacobre);
        picaretacobreRecipe.shape("ccc", " s ", " s ");
        picaretacobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        picaretacobreRecipe.setIngredient('s', Material.STICK);
        //machado
        ShapedRecipe machadocobreRecipe = new ShapedRecipe(new NamespacedKey(this, "machadocobrekey"), machadocobre);
        machadocobreRecipe.shape(" cc", " sc", " s ");
        machadocobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        machadocobreRecipe.setIngredient('s', Material.STICK);
        //pa
        ShapedRecipe pacobreRecipe = new ShapedRecipe(new NamespacedKey(this, "pacobrekey"), pacobre);
        pacobreRecipe.shape(" c ", " s ", " s ");
        pacobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        pacobreRecipe.setIngredient('s', Material.STICK);
        //enxada
        ShapedRecipe enxadacobreRecipe = new ShapedRecipe(new NamespacedKey(this, "enxadacobrekey"), enxadacobre);
        enxadacobreRecipe.shape(" cc", " s ", " s ");
        enxadacobreRecipe.setIngredient('c', Material.COPPER_INGOT);
        enxadacobreRecipe.setIngredient('s', Material.STICK);

        //armor de steel recipe
        //capacete steel
        ShapedRecipe capacetesteelRecipe = new ShapedRecipe(new NamespacedKey(this, "capacetesteelkey"), capacetesteel);
        capacetesteelRecipe.shape("sss", "s s", "   ");
        capacetesteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        //peitoral steel
        ShapedRecipe peitoralsteelRecipe = new ShapedRecipe(new NamespacedKey(this, "peitoralsteelkey"), peitoralsteel);
        peitoralsteelRecipe.shape("s s", "sss", "sss");
        peitoralsteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        //calça steel
        ShapedRecipe calcasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "calcasteelkey"), calcasteel);
        calcasteelRecipe.shape("sss", "s s", "s s");
        calcasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        //bota steel
        ShapedRecipe botasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "botasteelkey"), botasteel);
        botasteelRecipe.shape("   ", "s s", "s s");
        botasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));

        //ferramenta de aço recipe
        //espada
        ShapedRecipe espadasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "espadasteelkey"), espadasteel);
        espadasteelRecipe.shape(" s ", " s ", " S ");
        espadasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        espadasteelRecipe.setIngredient('S', Material.STICK);
        //picareta
        ShapedRecipe picaretasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "picaretasteelkey"), picaretasteel);
        picaretasteelRecipe.shape("sss", " S ", " S ");
        picaretasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        picaretasteelRecipe.setIngredient('S', Material.STICK);
        //machado
        ShapedRecipe machadosteelRecipe = new ShapedRecipe(new NamespacedKey(this, "machadosteelkey"), machadosteel);
        machadosteelRecipe.shape(" ss", " Ss", " S ");
        machadosteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        machadosteelRecipe.setIngredient('S', Material.STICK);
        //pá
        ShapedRecipe pasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "pasteelkey"), pasteel);
        pasteelRecipe.shape(" s ", " S ", " S ");
        pasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        pasteelRecipe.setIngredient('S', Material.STICK);
        //enxada
        ShapedRecipe enxadasteelRecipe = new ShapedRecipe(new NamespacedKey(this, "enxadasteelkey"), enxadasteel);
        enxadasteelRecipe.shape(" ss", " S ", " S ");
        enxadasteelRecipe.setIngredient('s', new RecipeChoice.ExactChoice(steel));
        enxadasteelRecipe.setIngredient('S', Material.STICK);

        //add recipes
        getServer().addRecipe(arcotopRecipe);
        getServer().addRecipe(flechageloRecipe);
        getServer().addRecipe(capacetecobreRecipe);
        getServer().addRecipe(peitoralcobreRecipe);
        getServer().addRecipe(calcacobreRecipe);
        getServer().addRecipe(botascobreRecipe);
        getServer().addRecipe(espadacobreRecipe);
        getServer().addRecipe(picaretacobreRecipe);
        getServer().addRecipe(machadocobreRecipe);
        getServer().addRecipe(pacobreRecipe);
        getServer().addRecipe(enxadacobreRecipe);
        getServer().addRecipe(steelRecipe);
        getServer().addRecipe(capacetesteelRecipe);
        getServer().addRecipe(peitoralsteelRecipe);
        getServer().addRecipe(calcasteelRecipe);
        getServer().addRecipe(botasteelRecipe);
        getServer().addRecipe(espadasteelRecipe);
        getServer().addRecipe(picaretasteelRecipe);
        getServer().addRecipe(machadosteelRecipe);
        getServer().addRecipe(pasteelRecipe);
        getServer().addRecipe(enxadasteelRecipe);
        //register events
        getServer().getPluginManager().registerEvents(this, this);
        new DelayedTask(this);
    }

    @EventHandler
    public void onEntityAttack(EntityDamageByEntityEvent e){
        if(e.getDamager().getType().equals(EntityType.PLAYER)){
            try {
                Player p = (Player) e.getDamager();
                if (p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "espadacobrecheck"), PersistentDataType.BOOLEAN)) {
                Random random = new Random();
                World w = e.getEntity().getWorld();
                if(random.nextInt(19) == 1){
                w.strikeLightning(e.getEntity().getLocation());
                }
                }
            }catch(NullPointerException ignored){}
        }
    }

    @EventHandler
    public void onBowShoot(EntityShootBowEvent e){
            try{
            LivingEntity p = e.getEntity();
            World w = p.getWorld();

            if(e.getProjectile().getType().equals(EntityType.ARROW)){

            if(e.getConsumable().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "flechagelocheck"), PersistentDataType.BOOLEAN)){
                Arrow icearrow = (Arrow) e.getProjectile();
                icearrow.setPickupStatus(AbstractArrow.PickupStatus.DISALLOWED);
                icearrow.addCustomEffect(new PotionEffect(PotionEffectType.SLOW, 100, 4), true);
                e.setProjectile(icearrow);
            }}

            if(p.getEquipment().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "arcotopcheck"), PersistentDataType.BOOLEAN) || p.getEquipment().getItemInOffHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "arcotopcheck"), PersistentDataType.BOOLEAN)){
                Vector extraArrow1 = new Vector(p.getLocation().getDirection().getX() + 0.25, p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ());
                Vector extraArrow2 = new Vector(p.getLocation().getDirection().getX() - 0.25, p.getLocation().getDirection().getY(), p.getLocation().getDirection().getZ());

                w.spawnArrow(e.getProjectile().getLocation(), extraArrow1, 2.4f * e.getForce(), 8);
                w.spawnArrow(e.getProjectile().getLocation(), extraArrow2, 2.4f * e.getForce(), 8, Arrow.class);
            }}catch(NullPointerException ignored){}

    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event){
        if(event.getEntity().getType().equals(EntityType.CREEPER)){
            if (this.getConfig().getBoolean("spawnChargedCreeperNaturally")) {

            Random rand = new Random();
            if(rand.nextInt(this.getConfig().getInt("spawnChargedCreeperChance")) == 1){
                ((Creeper) event.getEntity()).setPowered(true);
            }}
        } else if(event.getEntity().getType().equals(EntityType.SKELETON)){
            Random rand1 = new Random();
            if(rand1.nextInt(128) == 1){
                try{if(event.getEntity().getEquipment().getItemInMainHand().getType().equals(Material.BOW)){
                    ItemStack arcotop = new ItemStack(Material.BOW);
                    ItemMeta arcotopMeta = arcotop.getItemMeta();
                    arcotopMeta.setDisplayName(ChatColor.AQUA + "MultiBow");
                    ArrayList<String> arcolore = new ArrayList<String>();
                    arcolore.add(ChatColor.GOLD + "Shoots multiple arrows at once.");
                    arcotopMeta.setLore(arcolore);
                    arcotopMeta.getPersistentDataContainer().set(new NamespacedKey(this, "arcotopcheck"), PersistentDataType.BOOLEAN, true);
                    arcotop.setItemMeta(arcotopMeta);

                    event.getEntity().getEquipment().setItemInMainHand(arcotop);
                }}catch(NullPointerException ignored){}
            }
        }
    }

    @EventHandler
    public void onGrindstonePrepare(PrepareGrindstoneEvent e){
        try{if(e.getInventory().getItem(0).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN) || e.getInventory().getItem(3).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "steelarmorcheck"), PersistentDataType.BOOLEAN)){
            e.setResult(new ItemStack(Material.AIR));
        }}catch(NullPointerException ignored){}
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)){
            if(e.getEntityType().equals(EntityType.PLAYER)){
                Player p = (Player) e.getEntity();
                try {
                    if(!p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "espadacobrecheck"), PersistentDataType.BOOLEAN) || !p.getInventory().getItemInMainHand().hasItemMeta()){
                    if (p.getInventory().getItem(EquipmentSlot.LEGS).getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "calcacobrecheck"), PersistentDataType.BOOLEAN)){
                        if(p.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "calcacobretimercheck"), PersistentDataType.BOOLEAN)){
                            if(p.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER) < 3) {

                            ItemStack calcacobre = p.getInventory().getLeggings();
                            LeatherArmorMeta calcacobreMeta = (LeatherArmorMeta) calcacobre.getItemMeta();
                            calcacobreMeta.setDisplayName(ChatColor.WHITE + "Copper Leggings");
                            calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER, p.getInventory().getLeggings().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER) + 1);
                            calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "calcacobrecheck"), PersistentDataType.BOOLEAN, true);
                            calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "calcacobretimercheck"), PersistentDataType.BOOLEAN, false);
                            ArrayList<String> calcacobrelore = new ArrayList<String>();
                            calcacobrelore.add(ChatColor.YELLOW + "Ability: Charging");
                            calcacobrelore.add(ChatColor.YELLOW + "Every time you get hit by lightning,");
                            calcacobrelore.add(ChatColor.YELLOW + "This item gets +10% speed. (Max 30%)");
                            calcacobrelore.add(ChatColor.GOLD + "Current bonus: " + calcacobreMeta.getPersistentDataContainer().get(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER).toString());
                            calcacobreMeta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
                            calcacobreMeta.removeAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED);
                            calcacobreMeta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "Armor", 4, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.LEGS));
                            calcacobreMeta.addAttributeModifier(Attribute.GENERIC_MOVEMENT_SPEED, new AttributeModifier(UUID.randomUUID(), "Speed", 0.1 * calcacobreMeta.getPersistentDataContainer().get(new NamespacedKey(this, "chargingabilitycount"), PersistentDataType.INTEGER), AttributeModifier.Operation.MULTIPLY_SCALAR_1, EquipmentSlot.LEGS));
                            calcacobreMeta.setColor(Color.ORANGE);
                            calcacobreMeta.setLore(calcacobrelore);
                            calcacobre.setItemMeta(calcacobreMeta);

                            p.getInventory().setLeggings(calcacobre);
                            new DelayedTask(() -> {
                                calcacobreMeta.getPersistentDataContainer().set(new NamespacedKey(this, "calcacobretimercheck"), PersistentDataType.BOOLEAN, true);
                                calcacobre.setItemMeta(calcacobreMeta);
                                p.getInventory().setLeggings(calcacobre);
                                }, 40);
                            e.setCancelled(true);}
                        }
                    }}
                }catch(NullPointerException ignored){}
            }
        }


    }

    @EventHandler
    public void onEntityDamageTwo(EntityDamageEvent e){
        if(e.getCause().equals(EntityDamageEvent.DamageCause.LIGHTNING)) {
            if (e.getEntityType().equals(EntityType.PLAYER)) {
                Player p = (Player) e.getEntity();
                try {
                    if (p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "espadacobrecheck"), PersistentDataType.BOOLEAN)) {
                        e.setDamage(e.getDamage() * 0.2);
                    }
                } catch (NullPointerException ignored) {}
            }
        }
    }

    @EventHandler
    public void onAnvilPrepare(PrepareAnvilEvent event){
        //try to define crafting for infusing xp with the anvil
        try{
          if(!event.getInventory().getItem(0).getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "xpinfused"), PersistentDataType.BOOLEAN)){
              if(event.getInventory().getItem(1).getType().equals(Material.EXPERIENCE_BOTTLE)){
                  if (event.getInventory().getItem(0).getType().equals(Material.WOODEN_SWORD) || event.getInventory().getItem(0).getType().equals(Material.STONE_SWORD) || event.getInventory().getItem(0).getType().equals(Material.IRON_SWORD) || event.getInventory().getItem(0).getType().equals(Material.GOLDEN_SWORD) || event.getInventory().getItem(0).getType().equals(Material.DIAMOND_SWORD) || event.getInventory().getItem(0).getType().equals(Material.NETHERITE_SWORD)){
                      if (event.getInventory().getItem(0).getItemMeta().hasLore()) {
                      ItemStack changedResultStack = event.getInventory().getItem(0).clone();
                      ItemMeta changedResultStackMeta = changedResultStack.getItemMeta();
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "xpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "sxpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxplevel"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppointNext"), PersistentDataType.INTEGER, XpConfigFile.getInstance().getInitialXpAmount());
                      List<String> resultLore = changedResultStackMeta.getLore();
                      resultLore.add(ChatColor.AQUA + "Infused with XP");
                      changedResultStackMeta.setLore(resultLore);
                      changedResultStack.setItemMeta(changedResultStackMeta);
                      event.setResult(changedResultStack);
                      event.getInventory().setRepairCost(10);
                  } else {
                      ItemStack changedResultStack = event.getInventory().getItem(0).clone();
                      ArrayList<String> resultLore = new ArrayList<String>();
                      resultLore.add(ChatColor.AQUA + "Infused with XP");
                      ItemMeta changedResultStackMeta = changedResultStack.getItemMeta();
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "xpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "sxpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxplevel"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppointNext"), PersistentDataType.INTEGER, XpConfigFile.getInstance().getInitialXpAmount());
                      changedResultStackMeta.setLore(resultLore);
                      changedResultStack.setItemMeta(changedResultStackMeta);
                      event.setResult(changedResultStack);
                      event.getInventory().setRepairCost(10);
                  }
              }else if (event.getInventory().getItem(0).getType().equals(Material.WOODEN_PICKAXE) || event.getInventory().getItem(0).getType().equals(Material.STONE_PICKAXE) || event.getInventory().getItem(0).getType().equals(Material.IRON_PICKAXE) || event.getInventory().getItem(0).getType().equals(Material.GOLDEN_PICKAXE) || event.getInventory().getItem(0).getType().equals(Material.DIAMOND_PICKAXE) || event.getInventory().getItem(0).getType().equals(Material.NETHERITE_PICKAXE)){
                  if (event.getInventory().getItem(0).getItemMeta().hasLore()) {
                      ItemStack changedResultStack = event.getInventory().getItem(0).clone();
                      ItemMeta changedResultStackMeta = changedResultStack.getItemMeta();
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "xpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pxpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER, XpConfigFile.getInstance().getInitialXpAmount());
                      ArrayList<String> resultLore = (ArrayList<String>) changedResultStackMeta.getLore();
                      resultLore.add(ChatColor.AQUA + "Infused with XP");
                      changedResultStackMeta.setLore(resultLore);
                      changedResultStack.setItemMeta(changedResultStackMeta);
                      event.setResult(changedResultStack);
                      event.getInventory().setRepairCost(10);
                  } else {
                      ItemStack changedResultStack = event.getInventory().getItem(0).clone();
                      ArrayList<String> resultLore = new ArrayList<String>();
                      resultLore.add(ChatColor.AQUA + "Infused with XP");
                      ItemMeta changedResultStackMeta = changedResultStack.getItemMeta();
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "xpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pxpinfused"), PersistentDataType.BOOLEAN, true);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, 0);
                      changedResultStackMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER, XpConfigFile.getInstance().getInitialXpAmount());
                      changedResultStackMeta.setLore(resultLore);
                      changedResultStack.setItemMeta(changedResultStackMeta);
                      event.setResult(changedResultStack);
                      event.getInventory().setRepairCost(10);
                  }
              }
              }
        }}catch(NullPointerException ignored){}
    }

    @EventHandler
    public void onBlockPlace(BlockPlaceEvent event){
        if(!event.isCancelled()){
          if(event.getBlock().getType().equals(Material.COAL_ORE) || event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.LAPIS_ORE) || event.getBlock().getType().equals(Material.REDSTONE_ORE) || event.getBlock().getType().equals(Material.DIAMOND_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_COAL_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_IRON_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_GOLD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_LAPIS_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_REDSTONE_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_DIAMOND_ORE) || event.getBlock().getType().equals(Material.NETHER_GOLD_ORE) || event.getBlock().getType().equals(Material.ANCIENT_DEBRIS)){
              event.getPlayer().sendMessage("you placed an ore!");
          }
        }
    }

    @EventHandler
    public void onBlockMine(BlockBreakEvent event){
        try{Player p = event.getPlayer();
        if(p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "pxpinfused"), PersistentDataType.BOOLEAN)){
            ItemStack playerPickaxe = p.getInventory().getItemInMainHand();
            ItemMeta playerPickaxeMeta = playerPickaxe.getItemMeta();
            int playerPickaxeCurrentXP = playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER);
            if(event.getBlock().getType().equals(Material.COAL_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_COAL_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + 2);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+2) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.IRON_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_IRON_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 3);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+3) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.GOLD_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_GOLD_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 4);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+4) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.LAPIS_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_LAPIS_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 4);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+4) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.REDSTONE_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_REDSTONE_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 4);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+4) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.DIAMOND_ORE) || event.getBlock().getType().equals(Material.DEEPSLATE_DIAMOND_ORE)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 5);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+5) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.ANCIENT_DEBRIS)){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 10);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+10) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }else if(event.getBlock().getType().equals(Material.STONE) || event.getBlock().getType().equals(Material.GRANITE) || event.getBlock().getType().equals(Material.ANDESITE) || event.getBlock().getType().equals(Material.DIORITE)){
                Random rand = new Random();
                if(rand.nextInt(9) == 1){
                playerPickaxeMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, playerPickaxeCurrentXP + 1);
                playerPickaxe.setItemMeta(playerPickaxeMeta);
                p.getInventory().setItemInMainHand(playerPickaxe);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER) + "/" + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER) + " (+1) | Level: " + playerPickaxeMeta.getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));}
            }
        }}catch(NullPointerException ignored){}
    }

    @EventHandler
    public void onEntityDeathByPlayer(EntityDeathEvent event){
        try{if(event.getEntity().getKiller().getType().equals(EntityType.PLAYER)){
            Player p = event.getEntity().getKiller();
            if(p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "sxpinfused"), PersistentDataType.BOOLEAN)){
                int xpGained = ((int) event.getEntity().getAttribute(Attribute.GENERIC_MAX_HEALTH).getBaseValue() / 5);
                ItemStack mainHandStack = p.getInventory().getItemInMainHand();
                ItemMeta mainHandMeta = mainHandStack.getItemMeta();
                int currentXP = mainHandMeta.getPersistentDataContainer().get(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER);
                mainHandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER, currentXP + xpGained);
                mainHandStack.setItemMeta(mainHandMeta);
                p.getInventory().setItemInMainHand(mainHandStack);
                p.spigot().sendMessage(ChatMessageType.ACTION_BAR, new TextComponent(ChatColor.AQUA + "XP: " + mainHandMeta.getPersistentDataContainer().get(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER) + "/" + mainHandMeta.getPersistentDataContainer().get(new NamespacedKey(this, "swordxppointNext"), PersistentDataType.INTEGER) + " (+" + xpGained + ") | Level: " + mainHandMeta.getPersistentDataContainer().get(new NamespacedKey(this, "swordxplevel"), PersistentDataType.INTEGER)));
                Bukkit.getServer().getPluginManager().callEvent(new LevelCheckEvent(p));
            }
        }}catch(NullPointerException ignored){}
    }

    @EventHandler
    public void onXpGain(LevelCheckEvent e){
        Player p = e.getCheckPlayer();
        try{
            if(p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "pxpinfused"), PersistentDataType.BOOLEAN)){
            int currentXP = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER);
            int nextLevelXP = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER);
            int currentLevel = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER);
            if(currentXP >= nextLevelXP) {
                int currentNewXP = currentXP - nextLevelXP;
                int currentNewLevel = currentLevel + 1;
                int newNextLevelXP = nextLevelXP + (XpConfigFile.getInstance().getAdditiveXpAmount() * currentNewLevel);
                ItemStack mainhandStack = p.getInventory().getItemInMainHand();
                ItemMeta mainhandMeta = p.getInventory().getItemInMainHand().getItemMeta();
                mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxplevel"), PersistentDataType.INTEGER, currentNewLevel);
                mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppoint"), PersistentDataType.INTEGER, currentNewXP);
                mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "pickxppointNext"), PersistentDataType.INTEGER, newNextLevelXP);
                mainhandStack.setItemMeta(mainhandMeta);
                p.getInventory().setItemInMainHand(mainhandStack);
                if(mainhandMeta.hasDisplayName()) {
                    p.sendMessage(ChatColor.AQUA + "Your " + mainhandMeta.getDisplayName() + ChatColor.AQUA + " just leveled up to level " + currentNewLevel + "!");
                }else{
                    p.sendMessage(ChatColor.AQUA + "Your " + mainhandStack.getType().name().replace("_", " ").toLowerCase() + " just leveled up to level " + currentNewLevel + "!");
                }
                if(currentNewLevel == 1 || currentNewLevel == 4 || currentNewLevel == 7 || currentNewLevel > 10){
                    //add 1 level of efficiency
                    if(mainhandMeta.hasEnchant(Enchantment.DIG_SPEED)){
                        int newEfficiencyLevel = mainhandMeta.getEnchantLevel(Enchantment.DIG_SPEED) + 1;
                        mainhandMeta.removeEnchant(Enchantment.DIG_SPEED);
                        mainhandMeta.addEnchant(Enchantment.DIG_SPEED, newEfficiencyLevel, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }else{
                        mainhandMeta.addEnchant(Enchantment.DIG_SPEED, 1, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }
                    p.sendMessage(ChatColor.AQUA + "Reward: +1 Efficiency Level");
                } else if(currentNewLevel == 2 || currentNewLevel == 5 || currentNewLevel == 8){
                    //add 1 level of unbreaking
                    if(mainhandMeta.hasEnchant(Enchantment.DURABILITY)){
                        int newUnbreakingLevel = mainhandMeta.getEnchantLevel(Enchantment.DURABILITY) + 1;
                        mainhandMeta.removeEnchant(Enchantment.DURABILITY);
                        mainhandMeta.addEnchant(Enchantment.DURABILITY, newUnbreakingLevel, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }else{
                        mainhandMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }
                    p.sendMessage(ChatColor.AQUA + "Reward: +1 Unbreaking Level");
                } else if(currentNewLevel == 3 || currentNewLevel == 6 || currentNewLevel == 9){
                    //add 1 level of fortune or silk touch
                    if(mainhandMeta.hasEnchant(Enchantment.LOOT_BONUS_BLOCKS)){
                        int newUnbreakingLevel = mainhandMeta.getEnchantLevel(Enchantment.LOOT_BONUS_BLOCKS) + 1;
                        mainhandMeta.removeEnchant(Enchantment.LOOT_BONUS_BLOCKS);
                        mainhandMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, newUnbreakingLevel, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Fortune Level");
                    }else if(mainhandMeta.hasEnchant(Enchantment.SILK_TOUCH)){
                        int newSilkLevel = mainhandMeta.getEnchantLevel(Enchantment.SILK_TOUCH) + 1;
                        mainhandMeta.removeEnchant(Enchantment.SILK_TOUCH);
                        mainhandMeta.addEnchant(Enchantment.SILK_TOUCH, newSilkLevel, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Silk Touch Level");
                    }else{
                        mainhandMeta.addEnchant(Enchantment.LOOT_BONUS_BLOCKS, 1, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Fortune Level");
                    }
                } else if(currentNewLevel == 10){
                    //add 1 level of mending
                    if(mainhandMeta.hasEnchant(Enchantment.MENDING)){
                        int newEfficiencyLevel = mainhandMeta.getEnchantLevel(Enchantment.MENDING) + 1;
                        mainhandMeta.removeEnchant(Enchantment.MENDING);
                        mainhandMeta.addEnchant(Enchantment.MENDING, newEfficiencyLevel, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }else{
                        mainhandMeta.addEnchant(Enchantment.MENDING, 1, true);
                        mainhandStack.setItemMeta(mainhandMeta);
                        p.getInventory().setItemInMainHand(mainhandStack);
                    }
                    p.sendMessage(ChatColor.AQUA + "Reward: +1 Mending Level");
                }
            }

            }else if (p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().has(new NamespacedKey(this, "sxpinfused"), PersistentDataType.BOOLEAN)){
                int currentXP = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER);
                int nextLevelXP = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "swordxppointNext"), PersistentDataType.INTEGER);
                int currentLevel = p.getInventory().getItemInMainHand().getItemMeta().getPersistentDataContainer().get(new NamespacedKey(this, "swordxplevel"), PersistentDataType.INTEGER);
                if(currentXP >= nextLevelXP) {
                    int currentNewXP = currentXP - nextLevelXP;
                    int currentNewLevel = currentLevel + 1;
                    int newNextLevelXP = nextLevelXP + (XpConfigFile.getInstance().getAdditiveXpAmount() * currentNewLevel);
                    ItemStack mainhandStack = p.getInventory().getItemInMainHand();
                    ItemMeta mainhandMeta = p.getInventory().getItemInMainHand().getItemMeta();
                    mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxplevel"), PersistentDataType.INTEGER, currentNewLevel);
                    mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppoint"), PersistentDataType.INTEGER, currentNewXP);
                    mainhandMeta.getPersistentDataContainer().set(new NamespacedKey(this, "swordxppointNext"), PersistentDataType.INTEGER, newNextLevelXP);
                    mainhandStack.setItemMeta(mainhandMeta);
                    p.getInventory().setItemInMainHand(mainhandStack);
                    if(mainhandMeta.hasDisplayName()) {
                        p.sendMessage(ChatColor.AQUA + "Your " + mainhandMeta.getDisplayName() + ChatColor.AQUA + " just leveled up to level " + currentNewLevel + "!");
                    }else{
                        p.sendMessage(ChatColor.AQUA + "Your " + mainhandStack.getType().name().replace("_", " ").toLowerCase() + " just leveled up to level " + currentNewLevel + "!");
                    }
                    if(currentNewLevel == 2 || currentNewLevel == 6 || currentNewLevel > 15){
                        //add 1 level of unbreaking
                        if(mainhandMeta.hasEnchant(Enchantment.DURABILITY)){
                            int newUnbreakingLevel = mainhandMeta.getEnchantLevel(Enchantment.DURABILITY) + 1;
                            mainhandMeta.removeEnchant(Enchantment.DURABILITY);
                            mainhandMeta.addEnchant(Enchantment.DURABILITY, newUnbreakingLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.DURABILITY, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Unbreaking Level");
                    }else if(currentNewLevel == 1 || currentNewLevel == 3 || currentNewLevel == 5 || currentNewLevel == 7 || currentNewLevel == 9){
                        //add 1 level of sharpness
                        if(mainhandMeta.hasEnchant(Enchantment.DAMAGE_ALL)){
                            int newSharpnessLevel = mainhandMeta.getEnchantLevel(Enchantment.DAMAGE_ALL) + 1;
                            mainhandMeta.removeEnchant(Enchantment.DAMAGE_ALL);
                            mainhandMeta.addEnchant(Enchantment.DAMAGE_ALL, newSharpnessLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.DAMAGE_ALL, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Sharpness Level");
                    }else if(currentNewLevel == 4 || currentNewLevel == 8 || currentNewLevel == 10){
                        //add 1 level of sweeping edge
                        if(mainhandMeta.hasEnchant(Enchantment.SWEEPING_EDGE)){
                            int newSweepingLevel = mainhandMeta.getEnchantLevel(Enchantment.SWEEPING_EDGE) + 1;
                            mainhandMeta.removeEnchant(Enchantment.SWEEPING_EDGE);
                            mainhandMeta.addEnchant(Enchantment.SWEEPING_EDGE, newSweepingLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.SWEEPING_EDGE, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Sweeping Edge Level");
                    }else if(currentNewLevel == 11 || currentNewLevel == 13){
                        //add 1 level of looting
                        if(mainhandMeta.hasEnchant(Enchantment.LOOT_BONUS_MOBS)){
                            int newLootingLevel = mainhandMeta.getEnchantLevel(Enchantment.LOOT_BONUS_MOBS) + 1;
                            mainhandMeta.removeEnchant(Enchantment.LOOT_BONUS_MOBS);
                            mainhandMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, newLootingLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.LOOT_BONUS_MOBS, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Looting Level");
                    }else if(currentNewLevel == 12 || currentNewLevel == 14){
                        //add 1 level of smite
                        if(mainhandMeta.hasEnchant(Enchantment.DAMAGE_UNDEAD)){
                            int newSmiteLevel = mainhandMeta.getEnchantLevel(Enchantment.DAMAGE_UNDEAD) + 1;
                            mainhandMeta.removeEnchant(Enchantment.DAMAGE_UNDEAD);
                            mainhandMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, newSmiteLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.DAMAGE_UNDEAD, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Smite Level");
                    }else if(currentNewLevel == 15){
                        //add 1 level of mending
                        if(mainhandMeta.hasEnchant(Enchantment.MENDING)){
                            int newSmiteLevel = mainhandMeta.getEnchantLevel(Enchantment.MENDING) + 1;
                            mainhandMeta.removeEnchant(Enchantment.MENDING);
                            mainhandMeta.addEnchant(Enchantment.MENDING, newSmiteLevel, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }else{
                            mainhandMeta.addEnchant(Enchantment.MENDING, 1, true);
                            mainhandStack.setItemMeta(mainhandMeta);
                            p.getInventory().setItemInMainHand(mainhandStack);
                        }
                        p.sendMessage(ChatColor.AQUA + "Reward: +1 Mending Level");
                    }
                }
            }
        }catch(NullPointerException ignored){}

    }
}