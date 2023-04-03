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
package dev.phomc.noodles.api.virtual;

import org.jetbrains.annotations.NotNull;

import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.Hopper;

/**
 * A virtual hopper is a hopper that does not have a container.
 * This is used to create a virtual hopper that can be used to
 * simulate the behavior of a hopper.
 */
public interface VirtualHopper extends Hopper {
	@Override
	default int getContainerSize() {
		return 1;
	}

	@Override
	default boolean isEmpty() {
		return true;
	}

	@NotNull @Override
	default ItemStack getItem(int slot) {
		return ItemStack.EMPTY;
	}

	@NotNull @Override
	default ItemStack removeItem(int slot, int amount) {
		return ItemStack.EMPTY;
	}

	@NotNull @Override
	default ItemStack removeItemNoUpdate(int slot) {
		return ItemStack.EMPTY;
	}

	@Override
	default void setItem(int slot, ItemStack stack) {
		// NO-OP
	}

	@Override
	default void setChanged() {
		// NO-OP
	}

	@Override
	default boolean stillValid(Player player) {
		return true;
	}

	@Override
	default void clearContent() {
		// NO-OP
	}
}
