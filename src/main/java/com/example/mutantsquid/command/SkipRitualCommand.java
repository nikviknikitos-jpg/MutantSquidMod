package com.example.mutantsquid.command;

import com.example.mutantsquid.RitualManager;
import com.mojang.brigadier.CommandDispatcher;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.Text;

public class SkipRitualCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("skipritual").executes(ctx -> {
            ServerCommandSource src = ctx.getSource();
            ServerPlayerEntity player = src.getPlayer();
            if (player != null && player.getWorld() instanceof ServerWorld sw) {
                RitualManager.skipAll(sw);
                src.sendFeedback(() -> Text.literal("Ritual skipped to last 3 seconds."), false);
                return 1;
            }
            return 0;
        }));
    }
}
