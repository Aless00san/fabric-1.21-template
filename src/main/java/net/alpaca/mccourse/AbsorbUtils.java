package net.alpaca.mccourse;

import net.minecraft.block.*;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

public class AbsorbUtils {
    private static final Direction[] DIRECTIONS = Direction.values();

    public static boolean absorbWater(World world, BlockPos pos) {
        // Recorre bloques alrededor
        return BlockPos.iterateRecursively(
                pos,
                6, // radio horizontal
                65, // radio vertical
                (currentPos, queuer) -> {
                    for (Direction direction : DIRECTIONS) {
                        queuer.accept(currentPos.offset(direction));
                    }
                },
                currentPos -> {
                    if (currentPos.equals(pos)) {
                        return true;
                    } else {
                        BlockState blockState = world.getBlockState(currentPos);
                        FluidState fluidState = world.getFluidState(currentPos);

                        // Si no es agua -> no hace nada
                        if (!fluidState.isIn(FluidTags.WATER)) {
                            return false;
                        } else {
                            // Intenta drenar
                            if (blockState.getBlock() instanceof FluidDrainable fluidDrainable
                                    && !fluidDrainable.tryDrainFluid(null, world, currentPos, blockState).isEmpty()) {
                                return true;
                            }

                            // Elimina agua
                            if (blockState.getBlock() instanceof FluidBlock) {
                                world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                            } else {
                                // Elimina vegetación marina
                                if (!blockState.isOf(Blocks.KELP)
                                        && !blockState.isOf(Blocks.KELP_PLANT)
                                        && !blockState.isOf(Blocks.SEAGRASS)
                                        && !blockState.isOf(Blocks.TALL_SEAGRASS)) {
                                    return false;
                                }

                                BlockEntity blockEntity = blockState.hasBlockEntity() ? world.getBlockEntity(currentPos) : null;
                                Block.dropStacks(blockState, world, currentPos, blockEntity);
                                world.setBlockState(currentPos, Blocks.AIR.getDefaultState(), Block.NOTIFY_ALL);
                            }
                            return true;
                        }
                    }
                }
        ) > 1;
    }
}
