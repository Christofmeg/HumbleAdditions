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
import net.minecraft.world.entity.animal.horse.AbstractHorse;
import net.minecraft.world.item.Instrument;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.DispenserBlock;
import net.minecraft.world.phys.AABB;

public class ModDispenseItemBehavior {
	
	public static void init() {
		
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
		
	}

}
