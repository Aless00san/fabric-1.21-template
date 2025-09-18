package net.alpaca.mccourse.entity;

import net.alpaca.mccourse.AbsorbUtils;
import net.alpaca.mccourse.ModEntities;
import net.alpaca.mccourse.item.ModItems;
import net.minecraft.entity.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class SpongeBallEntity extends ThrownItemEntity {
    public SpongeBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public SpongeBallEntity(World world, LivingEntity owner) {
        super(ModEntities.SPONGE_BALL, owner, world);
    }

    public SpongeBallEntity(World world, double x, double y, double z) {
        super(ModEntities.SPONGE_BALL, x, y, z, world);
    }

    @Override
    protected Item getDefaultItem() {
        return ModItems.SPONGE_BALL; // referencia a tu ítem
    }

    @Override
    protected void onCollision(HitResult hitResult) {
        super.onCollision(hitResult);

        if (!this.getWorld().isClient) {
            BlockPos impactPos = BlockPos.ofFloored(hitResult.getPos());
            AbsorbUtils.absorbWater(this.getWorld(), impactPos);
            this.discard();
        }
    }


}