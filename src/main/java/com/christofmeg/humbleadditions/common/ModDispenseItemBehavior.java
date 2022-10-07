package com.christofmeg.humbleadditions.common;

import java.util.Iterator;
import java.util.Optional;

import com.christofmeg.humbleadditions.registry.ItemRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.BlockSource;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.dispenser.DefaultDispenseItemBehavior;
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.TagKey;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.DispensibleContainerItem;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.level.block.LayeredCauldronBlock;
import net.minecraft.world.level.block.entity.DispenserBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.gameevent.GameEvent;
import net.minecraft.world.level.material.Fluids;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraftforge.fluids.FluidType;

public class ModDispenseItemBehavior {
	
	public static void init() {
		
		//Horse armor dispense
		DefaultDispenseItemBehavior defaultdispenseitembehavior1 = new OptionalDispenseItemBehavior() {
			@SuppressWarnings("resource")
			protected ItemStack execute(BlockSource p_123535_, ItemStack p_123536_) {
				BlockPos blockpos = p_123535_.getPos().relative(p_123535_.getBlockState().getValue(DispenserBlock.FACING));
	            for(AbstractHorse abstracthorse : p_123535_.getLevel().getEntitiesOfClass(AbstractHorse.class, new AABB(blockpos), (p_123533_) -> {
	            	return p_123533_.isAlive() && p_123533_.canWearArmor();
	            })) {
	            	if (abstracthorse.isArmor(p_123536_) && !abstracthorse.isWearingArmor() && abstracthorse.isTamed()) {
	            		abstracthorse.getSlot(401).set(p_123536_.split(1));
	            		this.setSuccess(true);
	            		return p_123536_;
	            	}
	            }
	            return super.execute(p_123535_, p_123536_);
			}
		};
		
			DispenserBlock.registerBehavior(ItemRegistry.NETHERITE_HORSE_ARMOR.get(), defaultdispenseitembehavior1);
			DispenserBlock.registerBehavior(ItemRegistry.ENDORIUM_HORSE_ARMOR.get(), defaultdispenseitembehavior1);
			DispenserBlock.registerBehavior(ItemRegistry.ENDORITE_HORSE_ARMOR.get(), defaultdispenseitembehavior1);
		
		
		//Goat horn sound
		DefaultDispenseItemBehavior goatHornPlaySoundBehavoir = new OptionalDispenseItemBehavior() {
			protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
				Level level = blockSource.getLevel();
				Optional<Holder<Instrument>> optional = this.getInstrument(stack);
				if (!level.isClientSide()) {
					BlockPos pos = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
					if (optional.isPresent()) {
				         Instrument instrument = optional.get().value();
				         play(level, pos, instrument);
					}
				}
				
				//Item plays sound and gets ejected
//				return super.execute(blockSource, stack);
				
				//Item plays sound and stays in dispenser
				return stack;
			}
			
			private static void play(Level level, BlockPos pos, Instrument instrument) {
				SoundEvent soundevent = instrument.soundEvent();
				float f = instrument.range() / 16.0F;
				level.playSound(null, pos, soundevent, SoundSource.RECORDS, f, 1.0F);
			}

			private TagKey<Instrument> instruments;
			
			private Optional<Holder<Instrument>> getInstrument(ItemStack p_220135_) {
			      CompoundTag compoundtag = p_220135_.getTag();
			      if (compoundtag != null) {
			         ResourceLocation resourcelocation = ResourceLocation.tryParse(compoundtag.getString("instrument"));
			         if (resourcelocation != null) {
			            return Registry.INSTRUMENT.getHolder(ResourceKey.create(Registry.INSTRUMENT_REGISTRY, resourcelocation));
			         }
			      }
			      Iterator<Holder<Instrument>> iterator = Registry.INSTRUMENT.getTagOrEmpty(this.instruments).iterator();
			      return iterator.hasNext() ? Optional.of(iterator.next()) : Optional.empty();
			   }
		};
		
			DispenserBlock.registerBehavior(Items.GOAT_HORN, goatHornPlaySoundBehavoir);
		
		
		//Fill buckets
		DefaultDispenseItemBehavior fillBucketBehavior = new OptionalDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
			protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
				Level level = blockSource.getLevel();
				if (!level.isClientSide()) {
					BlockPos blockPosInFrontOfDispenser = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
					BlockState stateInFrontOfDispenser = level.getBlockState(blockPosInFrontOfDispenser);
					Block blockInFrontOfDispenser = stateInFrontOfDispenser.getBlock();
					Item itemInDispenser = stack.getItem();
					if(blockInFrontOfDispenser == Blocks.LAVA_CAULDRON && itemInDispenser == Items.BUCKET) {
						level.setBlock(blockPosInFrontOfDispenser, Blocks.CAULDRON.defaultBlockState(), 3);
						if(stack.getCount() >= 2) {
							stack.shrink(1);
							this.defaultDispenseItemBehavior.dispense(blockSource, new ItemStack(Items.LAVA_BUCKET));
						}
						else {
							return new ItemStack(Items.LAVA_BUCKET);
						}	
					}
					else if(blockInFrontOfDispenser == Blocks.POWDER_SNOW_CAULDRON && itemInDispenser == Items.BUCKET) {
						if(((LayeredCauldronBlock) blockInFrontOfDispenser).isFull(stateInFrontOfDispenser)) {
							level.setBlock(blockPosInFrontOfDispenser, Blocks.CAULDRON.defaultBlockState(), 3);
							if(stack.getCount() >= 2) {
								stack.shrink(1);
								this.defaultDispenseItemBehavior.dispense(blockSource, new ItemStack(Items.POWDER_SNOW_BUCKET));
							}
							else {
								return new ItemStack(Items.POWDER_SNOW_BUCKET);
							}
						}
					}
					else if(blockInFrontOfDispenser == Blocks.WATER_CAULDRON && itemInDispenser == Items.BUCKET) {
						if(((LayeredCauldronBlock) blockInFrontOfDispenser).isFull(stateInFrontOfDispenser)) {
							level.setBlock(blockPosInFrontOfDispenser, Blocks.CAULDRON.defaultBlockState(), 3);
							if(stack.getCount() >= 2) {
								stack.shrink(1);
								this.defaultDispenseItemBehavior.dispense(blockSource, new ItemStack(Items.WATER_BUCKET));
							}
							else {
								return new ItemStack(Items.WATER_BUCKET);
							}
						}
					}
					else if(blockInFrontOfDispenser instanceof BucketPickup) {
						ItemStack itemstack = ((BucketPickup)blockInFrontOfDispenser).pickupBlock(level, blockPosInFrontOfDispenser, stateInFrontOfDispenser);
						Item item = itemstack.getItem();
						if(itemstack.isEmpty()) {
							return super.execute(blockSource, stack);
						} 
						else {
							level.gameEvent((Entity)null, GameEvent.FLUID_PICKUP, blockPosInFrontOfDispenser);
							stack.shrink(1);
							if (stack.isEmpty()) {
								return new ItemStack(item);
							}
							else {
								if(blockSource.<DispenserBlockEntity>getEntity().addItem(new ItemStack(item)) < 0) {
									this.defaultDispenseItemBehavior.dispense(blockSource, new ItemStack(item));
								}
								return stack;
							}
						}
					}
					else {
						return super.execute(blockSource, stack);
					}
				}
				return stack;
			}
		};
		
			DispenserBlock.registerBehavior(Items.BUCKET, fillBucketBehavior);
		
		
		//TODO PRIORITIZE EMPTY BUCKETS IF CAULDRON IS FULL
		//Empty buckets
		DefaultDispenseItemBehavior emptyBucketBehavior = new OptionalDispenseItemBehavior() {
			private final DefaultDispenseItemBehavior defaultDispenseItemBehavior = new DefaultDispenseItemBehavior();
			protected ItemStack execute(BlockSource blockSource, ItemStack stack) {
				Level level = blockSource.getLevel();
				if (!level.isClientSide()) {
					BlockPos blockPosInFrontOfDispenser = blockSource.getPos().relative(blockSource.getBlockState().getValue(DispenserBlock.FACING));
					BlockState stateInFrontOfDispenser = level.getBlockState(blockPosInFrontOfDispenser);
					Block blockInFrontOfDispenser = stateInFrontOfDispenser.getBlock();
					Item item = stack.getItem();
					DispensibleContainerItem dispensiblecontaineritem = (DispensibleContainerItem) item;
					FluidType fluidType = level.getFluidState(blockPosInFrontOfDispenser).getFluidType();
					if(blockInFrontOfDispenser == Blocks.CAULDRON) {
						if(item == Items.LAVA_BUCKET) {
							level.setBlock(blockPosInFrontOfDispenser, Blocks.LAVA_CAULDRON.defaultBlockState(), 3);
							return new ItemStack(Items.BUCKET);
						}
						if(item == Items.POWDER_SNOW_BUCKET) {
							level.setBlock(blockPosInFrontOfDispenser, Blocks.POWDER_SNOW_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), 3);
							return new ItemStack(Items.BUCKET);
						}
						if(item == Items.WATER_BUCKET) {
							level.setBlock(blockPosInFrontOfDispenser, Blocks.WATER_CAULDRON.defaultBlockState().setValue(LayeredCauldronBlock.LEVEL, Integer.valueOf(3)), 3);
							return new ItemStack(Items.BUCKET);
						}
					}
					else if(item == Items.LAVA_BUCKET && fluidType == Fluids.LAVA.getFluidType()) {
						return stack;
					}
					else if(item == Items.WATER_BUCKET && fluidType == Fluids.WATER.getFluidType()) {
						return stack;
					}
					else if((item == Items.POWDER_SNOW_BUCKET && blockInFrontOfDispenser == Blocks.POWDER_SNOW) || 
							item == Items.POWDER_SNOW_BUCKET && fluidType == Fluids.LAVA.getFluidType()) {
						return stack;
					}
					else if(dispensiblecontaineritem.emptyContents((Player)null, level, blockPosInFrontOfDispenser, (BlockHitResult)null)) {
						dispensiblecontaineritem.checkExtraContent((Player)null, level, stack, blockPosInFrontOfDispenser);
						return new ItemStack(Items.BUCKET);
					}
					else {
						return this.defaultDispenseItemBehavior.dispense(blockSource, stack);
					}
				}
				return stack;
			}
		};
		
			DispenserBlock.registerBehavior(Items.LAVA_BUCKET, emptyBucketBehavior);
			DispenserBlock.registerBehavior(Items.POWDER_SNOW_BUCKET, emptyBucketBehavior);
			DispenserBlock.registerBehavior(Items.WATER_BUCKET, emptyBucketBehavior);
		
		
	}

}
