package com.christofmeg.humbleadditions.common.blocks;

import java.util.Optional;

import org.jetbrains.annotations.NotNull;

import com.christofmeg.humbleadditions.registry.BlockRegistry;
import com.christofmeg.humbleadditions.registry.ItemRegistry;
import com.christofmeg.humbleadditions.registry.TagRegistry;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.particles.BlockParticleOption;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.item.FallingBlockEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.GameRules;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BucketPickup;
import net.minecraft.world.level.block.SandBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.Vec3;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.EntityCollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

public class QuickSandBlock extends SandBlock implements BucketPickup {
	private static final VoxelShape FALLING_COLLISION_SHAPE = Shapes.box(0.0D, 0.0D, 0.0D, 1.0D, (double)0.9F, 1.0D);
	
	public QuickSandBlock(int dustColor, BlockBehaviour.Properties properties) {
		super(dustColor, properties);
	}

	@Override
	public void onPlace(BlockState oldState, Level level, BlockPos pos, BlockState newState, boolean bool) {
		level.scheduleTick(pos, this, this.getDelayAfterPlace());
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public boolean skipRendering(@NotNull BlockState p_154268_, BlockState p_154269_, @NotNull Direction p_154270_) {
		return p_154269_.is(this) ? true : super.skipRendering(p_154268_, p_154269_, p_154270_);
	}

	@Override
	public VoxelShape getOcclusionShape(BlockState state, BlockGetter blockGetter, BlockPos pos) {
		return Shapes.empty();
	}

	@Override
	public void entityInside(BlockState state, Level level, BlockPos pos, Entity entity) {
		if (!(entity instanceof LivingEntity) || entity.getFeetBlockState().is(this)) {
			entity.makeStuckInBlock(state, new Vec3((double)0.9F, 1.5D, (double)0.9F));
			if (level.isClientSide) {
				RandomSource randomsource = level.getRandom();
				boolean flag = entity.xOld != entity.getX() || entity.zOld != entity.getZ();
				if (flag && randomsource.nextBoolean()) {
					level.addParticle(new BlockParticleOption(ParticleTypes.FALLING_DUST, state).setPos(new BlockPos(entity.getX(), entity.getY() + 1, entity.getZ())), entity.getX(), entity.getY() + 1, entity.getZ(), (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F), 0.05F, (Mth.randomBetween(randomsource, -1.0F, 1.0F) * 0.083333336F));
					
				}
			}
		}	
//		entity.setIsInPowderSnow(true);
		if(entity instanceof LivingEntity) {
			entity.tickCount++;
			if(entity.isVisuallySwimming() || level.getBlockState(pos.above()) == BlockRegistry.QUICK_SAND.get().defaultBlockState() || pos.getY() == entity.getOnPos().above().above().getY()) {
				((LivingEntity) entity).addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 60, 30, true, true));
				if(entity.tickCount >= 40) {
					if(((LivingEntity) entity).hasEffect(MobEffects.MOVEMENT_SPEED)) {
						float speed = ((LivingEntity) entity).getEffect(MobEffects.MOVEMENT_SPEED).getAmplifier();
						((LivingEntity) entity).hurt(DamageSource.DROWN, 0.1f + (speed / 25));
					} else {
						((LivingEntity) entity).hurt(DamageSource.DROWN, 0.1f);
					}
					entity.tickCount = 0;
				}
			}
		}
		if (!level.isClientSide) {
			if (entity.isOnFire() && (level.getGameRules().getBoolean(GameRules.RULE_MOBGRIEFING) || entity instanceof Player) && entity.mayInteract(level, pos)) {
				level.destroyBlock(pos, false);
			}
			entity.setSharedFlagOnFire(false);
		}
	}
	
	@Override
	public void fallOn(Level level, BlockState state, BlockPos pos, Entity entity, float f) {
		if (!((double)f < 4.0D) && entity instanceof LivingEntity livingentity) {
			LivingEntity.Fallsounds $$7 = livingentity.getFallSounds();
			SoundEvent soundevent = (double)f < 7.0D ? $$7.small() : $$7.big();
			entity.playSound(soundevent, 1.0F, 1.0F);
		}
	}
	
	@Override
    protected void falling(FallingBlockEntity entity)
    {
        entity.dropItem = false;
    }

	@Override
	@SuppressWarnings("deprecation")
	public VoxelShape getCollisionShape(BlockState state, BlockGetter blockGetter, BlockPos pos, CollisionContext collisionContext) {
		if (collisionContext instanceof EntityCollisionContext entitycollisioncontext) {
			Entity entity = entitycollisioncontext.getEntity();
			if (entity != null) {
				if (entity.fallDistance > 2.5F) {
					return FALLING_COLLISION_SHAPE;
				}
	            boolean flag = entity instanceof FallingBlockEntity;
	            if (flag || canEntityWalkOnQuickSand(entity) && collisionContext.isAbove(Shapes.block(), pos, false) && !collisionContext.isDescending()) {
	               return super.getCollisionShape(state, blockGetter, pos, collisionContext);
	            }
			}
		}
		return Shapes.empty();
	}

	@Override
	public VoxelShape getVisualShape(BlockState state, BlockGetter p_154277_, BlockPos p_154278_, CollisionContext p_154279_) {
		return Shapes.empty();
	}

	public static boolean canEntityWalkOnQuickSand(Entity entity) {
		if (entity.getType().is(TagRegistry.EntityTypes.QUICK_SAND_WALKABLE_MOBS)) {
			return true;
		} else {
			return false;
		}
	}

	@Override
	public ItemStack pickupBlock(LevelAccessor p_154281_, BlockPos p_154282_, BlockState p_154283_) {
		p_154281_.setBlock(p_154282_, Blocks.AIR.defaultBlockState(), 11);
		if (!p_154281_.isClientSide()) {
			p_154281_.levelEvent(2001, p_154282_, Block.getId(p_154283_));
		}
		return new ItemStack(ItemRegistry.QUICK_SAND_BUCKET.get());
	}

	@Override
	public Optional<SoundEvent> getPickupSound() {
		return Optional.of(SoundEvents.BUCKET_FILL_POWDER_SNOW);
	}

	@Override
	public boolean isPathfindable(BlockState p_154258_, BlockGetter p_154259_, BlockPos p_154260_, PathComputationType p_154261_) {
		return true;
	}
}