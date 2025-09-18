package net.alpaca.mccourse;

import net.alpaca.mccourse.entity.SpongeBallEntity;
import net.alpaca.mccourse.item.custom.SpongeBallItem;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEntities {
    public static final EntityType<SpongeBallEntity> SPONGE_BALL = Registry.register(
            Registries.ENTITY_TYPE,
            Identifier.of(MCCourseMod.MOD_ID, "sponge_ball"),
            FabricEntityTypeBuilder.<SpongeBallEntity>create(SpawnGroup.MISC, SpongeBallEntity::new)
                    .dimensions(EntityDimensions.fixed(0.25f, 0.25f))
                    .trackRangeBlocks(64)
                    .trackedUpdateRate(10)
                    .build()
    );

    public static void registerModEntities() {
        System.out.println("Registering entities for " + MCCourseMod.MOD_ID);
    }
}
