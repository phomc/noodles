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

package dev.phomc.noodles.api.explosion;

import net.minecraft.world.level.Explosion;

/**
 * General-purpose Noodles-provided extensions for {@link Explosion} subclasses.
 *
 * <p>Note: This interface is automatically implemented on all blocks via Mixin and interface injection.
 */
public interface NoodlesExplosion {
	/**
	 * Whether the explosion will affect the source entity.
	 *
	 * @return true if the explosion will affect the source entity, false otherwise
	 */
	default boolean isAffectSourceEntity() {
		return true;
	}

	/**
	 * Sets whether the explosion will affect the source entity.
	 *
	 * @param affectSourceEntity true if the explosion will affect the source entity, false otherwise
	 */
	default void setAffectSourceEntity(boolean affectSourceEntity) {
		throw new UnsupportedOperationException("Cannot set affect source entity on this explosion");
	}

	/**
	 * Whether the explosion will affect entities.
	 *
	 * @return true if the explosion will affect entities, false otherwise
	 */
	default boolean isAffectEntities() {
		return true;
	}

	/**
	 * Sets whether the explosion will affect entities.
	 *
	 * @param affectEntities true if the explosion will affect entities, false otherwise
	 */
	default void setAffectEntities(boolean affectEntities) {
		throw new UnsupportedOperationException("Cannot set affect entities on this explosion");
	}

	/**
	 * Whether the explosion will affect players.
	 *
	 * @return true if the explosion will affect players, false otherwise
	 */
	default boolean isAffectPlayers() {
		return true;
	}

	/**
	 * Sets whether the explosion will affect players.
	 *
	 * @param affectPlayers true if the explosion will affect players, false otherwise
	 */
	default void setAffectPlayers(boolean affectPlayers) {
		throw new UnsupportedOperationException("Cannot set affect players on this explosion");
	}
}
