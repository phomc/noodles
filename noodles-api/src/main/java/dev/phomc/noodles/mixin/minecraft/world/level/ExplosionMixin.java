/*
 * Copyright (c) 2023 PhoMC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package dev.phomc.noodles.mixin.minecraft.world.level;

import java.util.List;

import com.llamalad7.mixinextras.injector.ModifyExpressionValue;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import net.minecraft.world.entity.Entity;
import net.minecraft.world.level.Explosion;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;

import dev.phomc.noodles.api.explosion.NoodlesExplosion;

@Mixin(Explosion.class)
public class ExplosionMixin implements NoodlesExplosion {
	@Shadow
	@Final
	@Nullable
	private Entity source;
	@Unique
	private boolean affectSourceEntity, affectEntities, affectPlayers;

	@Redirect(method = "explode",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/Entity;ignoreExplosion()Z"
			)
	)
	private boolean skipSourceEntity(Entity instance) {
		return instance.ignoreExplosion() || (!this.isAffectSourceEntity() && this.source == instance);
	}

	@Redirect(method = "explode",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/level/Level;getEntities(Lnet/minecraft/world/entity/Entity;Lnet/minecraft/world/phys/AABB;)Ljava/util/List;"
			)
	)
	private List<Entity> skipEntities(Level instance, Entity entity, AABB aabb) {
		return this.isAffectEntities() ? instance.getEntities(entity, aabb) : List.of();
	}

	@ModifyExpressionValue(method = "explode",
			at = @At(
					value = "INVOKE",
					target = "Lnet/minecraft/world/entity/player/Player;isSpectator()Z"
			)
	)
	public boolean skipPlayersIf(boolean original) {
		return original || !this.isAffectPlayers();
	}

	@Override
	public boolean isAffectSourceEntity() {
		return this.affectSourceEntity;
	}

	@Override
	public void setAffectSourceEntity(boolean affectSourceEntity) {
		this.affectSourceEntity = affectSourceEntity;
	}

	@Override
	public boolean isAffectEntities() {
		return this.affectEntities;
	}

	@Override
	public void setAffectEntities(boolean affectEntities) {
		this.affectEntities = affectEntities;
	}

	@Override
	public boolean isAffectPlayers() {
		return this.affectPlayers;
	}

	@Override
	public void setAffectPlayers(boolean affectPlayers) {
		this.affectPlayers = affectPlayers;
	}
}
