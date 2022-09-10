package com.christofmeg.humbleadditions.registry;

import java.util.List;

import org.jetbrains.annotations.NotNull;

import com.christofmeg.humbleadditions.common.items.FloatingBlockItem;
import com.christofmeg.humbleadditions.common.items.FloatingItem;
import com.christofmeg.humbleadditions.common.items.MilkBottleItem;
import com.christofmeg.humbleadditions.common.items.ModHorseArmorItem;
import com.christofmeg.humbleadditions.common.items.ModTier;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.HorseArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShearsItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SolidBucketItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.common.TierSortingRegistry;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemRegistry {
	
	public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MODID);
	public static final DeferredRegister<Item> ITEMS_AUTO_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MODID);
	public static final DeferredRegister<Item> BLOCK_ITEMS_AUTO_REGISTER = DeferredRegister.create(ForgeRegistries.ITEMS, ModConstants.MODID);
	
	
	public static final RegistryObject<Item> MILK_BOTTLE = ITEMS_AUTO_REGISTER.register("milk_bottle", () -> new MilkBottleItem(props().stacksTo(16).craftRemainder(Items.GLASS_BOTTLE)));
	
	public static final RegistryObject<Item> NETHERITE_HORSE_ARMOR = ITEMS_AUTO_REGISTER.register("netherite_horse_armor", 
		() -> new ModHorseArmorItem(13, 
			//Config.NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE.get(), 
			9, 
			"netherite", (props()).stacksTo(1).fireResistant()));
	
	public static final RegistryObject<Item> ENDORIUM_HORSE_ARMOR = ITEMS_AUTO_REGISTER.register("endorium_horse_armor", 
		() -> new HorseArmorItem(15,
			new ResourceLocation(ModConstants.MOD_ID + ":textures/entities/horse/armor/horse_armor_endorium.png"), (props()).stacksTo(1).fireResistant()));
	
	public static final RegistryObject<Item> ENDORITE_HORSE_ARMOR = ITEMS_AUTO_REGISTER.register("endorite_horse_armor", 
		() -> new ModHorseArmorItem(20, 
			//Config.NETHERITE_HORSE_ARMOR_KNOCKBACK_RESISTANCE.get(), 
			9, 
			"endorite", (props()).stacksTo(1).fireResistant()));
	
	public static final RegistryObject<ForgeSpawnEggItem> RED_HUSK_SPAWN_EGG = ITEMS_AUTO_REGISTER.register("red_husk_spawn_egg", () -> new ForgeSpawnEggItem(EntityRegistry.RED_HUSK, 7958625, 11098145, props()));
    
	public static final RegistryObject<SolidBucketItem> QUICK_SAND_BUCKET = ITEMS_AUTO_REGISTER.register("quick_sand_bucket", () -> new SolidBucketItem(BlockRegistry.QUICK_SAND.get(), SoundEvents.BUCKET_EMPTY_POWDER_SNOW, props().stacksTo(1)));
	
	public static final RegistryObject<FloatingBlockItem> ENDORIUM_ORE = BLOCK_ITEMS_AUTO_REGISTER.register("endorium_ore", () -> new FloatingBlockItem(BlockRegistry.ENDORIUM_ORE.get(), props()));
	public static final RegistryObject<FloatingItem> RAW_ENDORIUM = ITEMS_AUTO_REGISTER.register("raw_endorium", () -> new FloatingItem(props()));
	public static final RegistryObject<FloatingBlockItem> RAW_ENDORIUM_BLOCK = BLOCK_ITEMS_AUTO_REGISTER.register("raw_endorium_block", () -> new FloatingBlockItem(BlockRegistry.RAW_ENDORIUM_BLOCK.get(), props()));
	public static final RegistryObject<FloatingItem> ENDORIUM_INGOT = ITEMS_AUTO_REGISTER.register("endorium_ingot", () -> new FloatingItem(props()));
	public static final RegistryObject<FloatingBlockItem> ENDORIUM_BLOCK = BLOCK_ITEMS_AUTO_REGISTER.register("endorium_block", () -> new FloatingBlockItem(BlockRegistry.ENDORIUM_BLOCK.get(), props()));
																															//SwordItem(ToolTier, dmg, speed, props())
	public static final RegistryObject<SwordItem> ENDORIUM_SWORD = ITEMS_AUTO_REGISTER.register("endorium_sword", () -> new SwordItem(ToolTiers.ENDORIUM, 4, -2.4F, props().fireResistant()));
    public static final RegistryObject<PickaxeItem> ENDORIUM_PICKAXE = ITEMS_AUTO_REGISTER.register("endorium_pickaxe", () -> new PickaxeItem(ToolTiers.ENDORIUM, 2, -2.8F, props().fireResistant()));
    public static final RegistryObject<ShovelItem> ENDORIUM_SHOVEL = ITEMS_AUTO_REGISTER.register("endorium_shovel", () -> new ShovelItem(ToolTiers.ENDORIUM, 2.5F, -3.0F, props().fireResistant()));
    public static final RegistryObject<AxeItem> ENDORIUM_AXE = ITEMS_AUTO_REGISTER.register("endorium_axe", () -> new AxeItem(ToolTiers.ENDORIUM, 6.0F, -3.0F, props().fireResistant()));
    public static final RegistryObject<HoeItem> ENDORIUM_HOE = ITEMS_AUTO_REGISTER.register("endorium_hoe", () -> new HoeItem(ToolTiers.ENDORIUM, -3, 0.0F, props().fireResistant()));
    
	public static final RegistryObject<SwordItem> ROSE_GOLD_SWORD = ITEMS_AUTO_REGISTER.register("rose_gold_sword", () -> new SwordItem(ToolTiers.ROSE_GOLD, 3, -2.4F, props()));
    public static final RegistryObject<PickaxeItem> ROSE_GOLD_PICKAXE = ITEMS_AUTO_REGISTER.register("rose_gold_pickaxe", () -> new PickaxeItem(ToolTiers.ROSE_GOLD, 1, -2.8F, props()));
    public static final RegistryObject<ShovelItem> ROSE_GOLD_SHOVEL = ITEMS_AUTO_REGISTER.register("rose_gold_shovel", () -> new ShovelItem(ToolTiers.ROSE_GOLD, 1.5F, -3.0F, props()));
    public static final RegistryObject<AxeItem> ROSE_GOLD_AXE = ITEMS_AUTO_REGISTER.register("rose_gold_axe", () -> new AxeItem(ToolTiers.ROSE_GOLD, 6.0F, -3.0F, props()));
    public static final RegistryObject<HoeItem> ROSE_GOLD_HOE = ITEMS_AUTO_REGISTER.register("rose_gold_hoe", () -> new HoeItem(ToolTiers.ROSE_GOLD, 0, -3.0F, props()));
    
    public static final RegistryObject<Item> ROSE_GOLD_INGOT = ITEMS_AUTO_REGISTER.register("rose_gold_ingot", () -> new Item(props()));
    public static final RegistryObject<Item> RAW_ROSE_GOLD = ITEMS_AUTO_REGISTER.register("raw_rose_gold", () -> new Item(props()));
    
    public static final RegistryObject<FloatingItem> ENDORITE_INGOT = ITEMS_AUTO_REGISTER.register("endorite_ingot", () -> new FloatingItem(props()));
    public static final RegistryObject<FloatingBlockItem> ENDORITE_BLOCK = BLOCK_ITEMS_AUTO_REGISTER.register("endorite_block", () -> new FloatingBlockItem(BlockRegistry.ENDORITE_BLOCK.get(), props()));
    
    private static int golden = 32;
    private static int wooden = 59;
    private static int rose_gold = 71;
    private static int stone = 131;
    private static int copper = 185;
//	private static int iron = 250;
    private static int diamond = 1561;
    private static int netherite = 2031;
    private static int endorium = 2539;
	private static int endorite = 3385;
    
    public static final RegistryObject<ShearsItem> GOLDEN_SHEARS = ITEMS_AUTO_REGISTER.register("golden" + "_shears", () -> new ShearsItem(props().durability(golden)));
    public static final RegistryObject<ShearsItem> WOODEN_SHEARS = ITEMS_AUTO_REGISTER.register("wooden" + "_shears", () -> new ShearsItem(props().durability(wooden)));
    public static final RegistryObject<ShearsItem> STONE_SHEARS = ITEMS_AUTO_REGISTER.register("stone" + "_shears", () -> new ShearsItem(props().durability(stone)));
    public static final RegistryObject<ShearsItem> COPPER_SHEARS = ITEMS_AUTO_REGISTER.register("copper" + "_shears", () -> new ShearsItem(props().durability(copper)));
    public static final RegistryObject<ShearsItem> ROSE_GOLD_SHEARS = ITEMS_AUTO_REGISTER.register("rose_gold" + "_shears", () -> new ShearsItem(props().durability(rose_gold)));
    public static final RegistryObject<ShearsItem> DIAMOND_SHEARS = ITEMS_AUTO_REGISTER.register("diamond" + "_shears", () -> new ShearsItem(props().durability(diamond)));
    public static final RegistryObject<ShearsItem> NETHERITE_SHEARS = ITEMS_AUTO_REGISTER.register("netherite" + "_shears", () -> new ShearsItem(props().durability(netherite).fireResistant()));
    public static final RegistryObject<ShearsItem> ENDORIUM_SHEARS = ITEMS_AUTO_REGISTER.register("endorium" + "_shears", () -> new ShearsItem(props().durability(endorium).fireResistant()));
    public static final RegistryObject<ShearsItem> ENDORITE_SHEARS = ITEMS_AUTO_REGISTER.register("endorite" + "_shears", () -> new ShearsItem(props().durability(endorite).fireResistant()));
	
	private static Item.Properties props() {
        return new Item.Properties().tab(ItemRegistry.TAB);
    }
	
	public static final CreativeModeTab TAB = new CreativeModeTab(ModConstants.MODID) {
        @Override
        public @NotNull ItemStack makeIcon() {
            return ItemRegistry.MILK_BOTTLE.get().getDefaultInstance();
        }
    };
    
    public static class ToolTiers {
    															//harvest lvl, durability, mining speed, attack dmg, enchantability
    	public static final ModTier ROSE_GOLD = new ModTier("ROSE_GOLD", 0, 71, 12.0F, 1.0F, 20, BlockTags.NEEDS_STONE_TOOL, () -> Ingredient.of(ItemRegistry.ROSE_GOLD_INGOT.get()));
        public static final ModTier ENDORIUM = new ModTier("ENDORIUM", 5, 2539, 11.25F, 5.0F, 18, BlockTags.create(new ResourceLocation("forge", "needs_endorium_tool")), () -> Ingredient.of(ItemRegistry.ENDORIUM_INGOT.get()));
        public static final ModTier ENDORITE = new ModTier("ENDORITE", 6, 3385, 17.2F, 7.65F, 28, BlockTags.create(new ResourceLocation("forge", "needs_endorite_tool")), () -> Ingredient.of(ItemRegistry.ENDORITE_INGOT.get()));
        
        static {																										//after, before	 
        	TierSortingRegistry.registerTier(ROSE_GOLD, new ResourceLocation(ModConstants.MOD_ID, "rose_gold"), List.of(TierSortingRegistry.getName(Tiers.GOLD)), List.of(TierSortingRegistry.getName(Tiers.STONE)));
        	TierSortingRegistry.registerTier(ENDORIUM, new ResourceLocation(ModConstants.MOD_ID, "endorium"), List.of(TierSortingRegistry.getName(Tiers.NETHERITE)), List.of());
        	TierSortingRegistry.registerTier(ENDORITE, new ResourceLocation(ModConstants.MOD_ID, "enderite"), List.of(TierSortingRegistry.getName(ENDORIUM)), List.of()); 
        }
        

        
/* WOOD(0, 59, 2.0F, 0.0F, 15, () -> {
      return Ingredient.of(ItemTags.PLANKS);
   }),
   STONE(1, 131, 4.0F, 1.0F, 5, () -> {
      return Ingredient.of(ItemTags.STONE_TOOL_MATERIALS);
   }),
   IRON(2, 250, 6.0F, 2.0F, 14, () -> {
      return Ingredient.of(Items.IRON_INGOT);
   }),
   DIAMOND(3, 1561, 8.0F, 3.0F, 10, () -> {
      return Ingredient.of(Items.DIAMOND);
   }),
   GOLD(0, 32, 12.0F, 0.0F, 22, () -> {
      return Ingredient.of(Items.GOLD_INGOT);
   }),
   NETHERITE(4, 2031, 9.0F, 4.0F, 15, () -> {
      return Ingredient.of(Items.NETHERITE_INGOT);
   });
         */
        
    }
	
}
