package com.christofmeg.humbleadditions.common.event;

import com.christofmeg.humbleadditions.common.entities.SnowGolemEntity;
import com.christofmeg.humbleadditions.registry.EntityRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.network.chat.Component;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.FrogVariant;
import net.minecraft.world.entity.animal.Panda;
import net.minecraft.world.entity.animal.Panda.Gene;
import net.minecraft.world.entity.animal.Rabbit;
import net.minecraft.world.entity.animal.Sheep;
import net.minecraft.world.entity.animal.frog.Frog;
import net.minecraft.world.entity.animal.goat.Goat;
import net.minecraft.world.entity.monster.Creeper;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.util.LogicalSidedProvider;
import net.minecraftforge.event.entity.player.PlayerInteractEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.LogicalSide;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber(modid = ModConstants.MOD_ID, bus = EventBusSubscriber.Bus.FORGE)
public class VariantSpawnEggEvent {

	@SubscribeEvent
	public static void onSpawnEggRightClick(final PlayerInteractEvent.RightClickBlock event) {
		Level level = event.getLevel();
		BlockPos pos = event.getPos();
		Player player = event.getEntity();
		ItemStack stack = event.getItemStack();
		Item item = stack.getItem();
		if(!level.isClientSide) {
			if(!player.isCreative()) {
				stack.shrink(1);
			}
			Direction dir = event.getFace();
			double x = pos.getX() + 0.5;
			int y = pos.getY();
			double z = pos.getZ() + 0.5;
			if(dir == Direction.NORTH) {
				z = z - 1;
			}
			else if(dir == Direction.SOUTH) {
				z = z + 1;
			}
			else if(dir == Direction.EAST) {
				x = x + 1;
			}
			else if(dir == Direction.WEST) {
				x = x - 1;
			}
			else if(dir == Direction.DOWN) {
				y = y - 1;
			}
			else if(dir == Direction.UP) {
				y = y + 1;
			}
			MinecraftServer source = (MinecraftServer) LogicalSidedProvider.WORKQUEUE.get(LogicalSide.SERVER);
			if(item == ItemRegistry.SNOW_FOX_SPAWN_EGG.get()) { //@private void setFoxType(Fox.Type pType) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:fox " + x + " " + y + " " + z + " {Type:snow}");
			}
			if(item == ItemRegistry.BROWN_MOOSHROOM_SPAWN_EGG.get()) { //private void setMushroomType(MushroomCow.MushroomType pType) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:mooshroom " + x + " " + y + " " + z + " {Type:brown}");

			}
			if(item == ItemRegistry.PLAYFUL_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.PLAYFUL);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.WORRIED_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.WORRIED);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.AGGRESSIVE_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.AGGRESSIVE);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.WEAK_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.WEAK);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.BROWN_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.BROWN);
				panda.setHiddenGene(Gene.BROWN);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.LAZY_PANDA_SPAWN_EGG.get()) {
				Panda panda = new Panda(EntityType.PANDA, level);
				level.addFreshEntity(panda);
				panda.setMainGene(Gene.LAZY);
				panda.setPos(x, y, z);
			}
			if(item == ItemRegistry.BROWN_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(0);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.WHITE_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(1);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.BLACK_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(2);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.WHITE_SPOTCHED_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(3);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.GOLD_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(4);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.SALT_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(5);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.TOAST_RABBIT_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setCustomName(Component.translatable("Toast"));
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.KILLER_BUNNY_SPAWN_EGG.get()) {
				Rabbit rabbit = new Rabbit(EntityType.RABBIT, level);
				level.addFreshEntity(rabbit);
				rabbit.setRabbitType(99);
				rabbit.setPos(x, y, z);
			}
			if(item == ItemRegistry.BABY_VILLAGER_SPAWN_EGG.get()) {
				Villager villager = new Villager(EntityType.VILLAGER, level);
				level.addFreshEntity(villager);
				villager.setBaby(true);
				villager.setPos(x, y, z);
			}
			if(item == ItemRegistry.CHARGED_CREEPER_SPAWN_EGG.get()) {
				Creeper creeper = new Creeper(EntityType.CREEPER, level);
				level.addFreshEntity(creeper);
				ChargedCreeperEvent.chargeEntity(creeper);
				creeper.setPos(x, y, z);
			}
			if(item == ItemRegistry.SHEARED_SHEEP_SPAWN_EGG.get()) {
				Sheep sheep = new Sheep(EntityType.SHEEP, level);
				level.addFreshEntity(sheep);
				sheep.setSheared(true);
				sheep.setPos(x, y, z);
			}
			if(item == ItemRegistry.SCREAMING_GOAT_SPAWN_EGG.get()) {
				Goat goat = new Goat(EntityType.GOAT, level);
				level.addFreshEntity(goat);
				goat.setScreamingGoat(true);
				goat.setPos(x, y, z);
			}
			if(item == ItemRegistry.SNOW_GOLEM_SPAWN_EGG.get()) {
				SnowGolemEntity snowGolem = new SnowGolemEntity(EntityRegistry.SNOW_GOLEM.get(), level);
				level.addFreshEntity(snowGolem);
				snowGolem.setCarvedPumpkin(true);
				snowGolem.setPos(x, y, z);
			}
			if(item == ItemRegistry.JACK_O_SNOW_GOLEM_SPAWN_EGG.get()) {
				SnowGolemEntity snowGolem = new SnowGolemEntity(EntityRegistry.SNOW_GOLEM.get(), level);
				level.addFreshEntity(snowGolem);
				snowGolem.setJackOPumpkin(true);
				snowGolem.setPos(x, y, z);
			}
			if(item == ItemRegistry.BLUE_AXOLOTL_SPAWN_EGG.get()) { //@private void setVariant(Axolotl.Variant pVariant) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:axolotl " + x + " " + y + " " + z + " {Variant:4}");
			}
			if(item == ItemRegistry.CYAN_AXOLOTL_SPAWN_EGG.get()) { //@private void setVariant(Axolotl.Variant pVariant) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:axolotl " + x + " " + y + " " + z + " {Variant:3}");
			}
			if(item == ItemRegistry.GOLD_AXOLOTL_SPAWN_EGG.get()) { //@private void setVariant(Axolotl.Variant pVariant) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:axolotl " + x + " " + y + " " + z + " {Variant:2}");
			}
			if(item == ItemRegistry.LUCY_AXOLOTL_SPAWN_EGG.get()) { //@private void setVariant(Axolotl.Variant pVariant) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:axolotl " + x + " " + y + " " + z + " {Variant:0}");
			}
			if(item == ItemRegistry.WILD_AXOLOTL_SPAWN_EGG.get()) { //@private void setVariant(Axolotl.Variant pVariant) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:axolotl " + x + " " + y + " " + z + " {Variant:1}");
			}
			if(item == ItemRegistry.COLD_FROG_SPAWN_EGG.get()) {
				Frog frog = new Frog(EntityType.FROG, level);
				level.addFreshEntity(frog);
				frog.setVariant(FrogVariant.COLD);
				frog.setPos(x, y, z);
			}
			if(item == ItemRegistry.TEMPERATE_FROG_SPAWN_EGG.get()) {
				Frog frog = new Frog(EntityType.FROG, level);
				level.addFreshEntity(frog);
				frog.setVariant(FrogVariant.TEMPERATE);
				frog.setPos(x, y, z);
			}
			if(item == ItemRegistry.WARM_FROG_SPAWN_EGG.get()) {
				Frog frog = new Frog(EntityType.FROG, level);
				level.addFreshEntity(frog);
				frog.setVariant(FrogVariant.WARM);
				frog.setPos(x, y, z);
			}
			if(item == ItemRegistry.ARMOR_STAND_WITH_ARMS.get()) { //@private void setShowArms(boolean pShowArms) {...
				source.getCommands().performPrefixedCommand(source.createCommandSourceStack(), "summon minecraft:armor_stand " + x + " " + y + " " + z + " {ShowArms:1}");
			}

		}
	}

}


