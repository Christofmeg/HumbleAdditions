package com.christofmeg.humbleadditions.common.entities;

import javax.annotation.Nullable;

import com.christofmeg.humbleadditions.registry.BlockRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.animal.SnowGolem;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.gameevent.GameEvent;

public class SnowGolemEntity extends SnowGolem {

	private static final EntityDataAccessor<Byte> CARVED_DATA_PUMPKIN_ID = SynchedEntityData.defineId(SnowGolem.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> JACK_O_DATA_PUMPKIN_ID = SynchedEntityData.defineId(SnowGolem.class, EntityDataSerializers.BYTE);
	private static final EntityDataAccessor<Byte> JACK_O_SOUL_DATA_PUMPKIN_ID = SynchedEntityData.defineId(SnowGolem.class, EntityDataSerializers.BYTE);

	public SnowGolemEntity(EntityType<? extends SnowGolem> pEntityType, Level pLevel) {
		super(pEntityType, pLevel);
	}

	@org.jetbrains.annotations.NotNull
	@Override
	public java.util.List<ItemStack> onSheared(@Nullable Player player, @org.jetbrains.annotations.NotNull ItemStack item, Level level, BlockPos pos, int fortune) {
		level.playSound(null, this, SoundEvents.SNOW_GOLEM_SHEAR, player == null ? SoundSource.BLOCKS : SoundSource.PLAYERS, 1.0F, 1.0F);
		this.gameEvent(GameEvent.SHEAR, player);
		if (!level.isClientSide()) {
			if(hasCarvedPumpkin()) {
				setCarvedPumpkin(false);
				return java.util.Collections.singletonList(new ItemStack(Items.CARVED_PUMPKIN));
			}
			if(hasJackOPumpkin()) {
				setJackOPumpkin(false);
				return java.util.Collections.singletonList(new ItemStack(Items.JACK_O_LANTERN));
			}
			if(hasJackOSoulPumpkin()) {
				setJackOSoulPumpkin(false);
				return java.util.Collections.singletonList(new ItemStack(BlockRegistry.JACK_O_SOUL_LANTERN.get()));
			}
		}
		return java.util.Collections.emptyList();
	}

	@Override
	public boolean readyForShearing() {
		return this.isAlive() && (this.hasCarvedPumpkin() || this.hasJackOPumpkin() || this.hasJackOSoulPumpkin());
	}

	@Override
	public void addAdditionalSaveData(CompoundTag pCompound) {
		super.addAdditionalSaveData(pCompound);
		pCompound.putBoolean("Carved Pumpkin", this.hasCarvedPumpkin());
		pCompound.putBoolean("Jack O Pumpkin", this.hasJackOPumpkin());
		pCompound.putBoolean("Jack O Soul Pumpkin", this.hasJackOSoulPumpkin());
	}

	@Override
	protected void defineSynchedData() {
		super.defineSynchedData();
		this.entityData.define(CARVED_DATA_PUMPKIN_ID, (byte)16);
		this.entityData.define(JACK_O_DATA_PUMPKIN_ID, (byte)-17);
		this.entityData.define(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)-17);
	}

	public boolean hasCarvedPumpkin() {
		return (this.entityData.get(CARVED_DATA_PUMPKIN_ID) & 16) != 0;
	}
	public boolean hasJackOPumpkin() {
		return (this.entityData.get(JACK_O_DATA_PUMPKIN_ID) & 16) != 0;
	}
	public boolean hasJackOSoulPumpkin() {
		return (this.entityData.get(JACK_O_SOUL_DATA_PUMPKIN_ID) & 16) != 0;
	}

	public void setCarvedPumpkin(boolean pPumpkinEquipped) {
		byte b0 = this.entityData.get(CARVED_DATA_PUMPKIN_ID);
		if (pPumpkinEquipped) {
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 | 16));
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 & -17));
		} else {
			this.setPumpkin(false);
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 & -17));
		}
	}
	public void setJackOPumpkin(boolean pPumpkinEquipped) {
		byte b0 = this.entityData.get(JACK_O_DATA_PUMPKIN_ID);
		if (pPumpkinEquipped) {
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 & -17));;
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 | 16));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 & -17));
		} else {
			this.setPumpkin(false);
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 & -17));
		}
	}
	public void setJackOSoulPumpkin(boolean pPumpkinEquipped) {
		byte b0 = this.entityData.get(JACK_O_SOUL_DATA_PUMPKIN_ID);
		if (pPumpkinEquipped) {
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 | 16));
		} else {
			this.setPumpkin(false);
			this.entityData.set(CARVED_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_DATA_PUMPKIN_ID, (byte)(b0 & -17));
			this.entityData.set(JACK_O_SOUL_DATA_PUMPKIN_ID, (byte)(b0 & -17));
		}
	}

}
