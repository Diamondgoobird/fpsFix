package com.diamondgoobird;

import com.mojang.brigadier.arguments.IntegerArgumentType;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.command.v2.ClientCommandRegistrationCallback;
import net.minecraft.client.MinecraftClient;

import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.argument;
import static net.fabricmc.fabric.api.client.command.v2.ClientCommandManager.literal;

public class FPSFixClient implements ClientModInitializer {
	@Override
	public void onInitializeClient() {
		ClientCommandRegistrationCallback.EVENT.register(
				(dispatcher, registryAccess) -> {
					dispatcher.register(
							literal("fps").then(
									argument("fps", IntegerArgumentType.integer()).executes(
											context -> {
												MinecraftClient.getInstance().options.getMaxFps().setValue(IntegerArgumentType.getInteger(context, "fps"));
												return 0;
											}
									)
							)
					);
				}
		);
	}
}