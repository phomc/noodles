package dev.phomc.noodles.common.entity;

import dev.phomc.noodles.common.entity.throwing.ThrowingItemEntity;
import org.jetbrains.annotations.ApiStatus;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityDimensions;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import dev.phomc.noodles.NoodlesMod;

/**
 * Contains pre-made entities.
 * <p>
 * Entity types which contain {@link ApiStatus.Internal} are not
 * intended to be used by other mods, but it is still possible
 * to use them.
 */
public final class NoodlesEntities {
}
