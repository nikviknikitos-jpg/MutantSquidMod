package com.example.mutantsquid;

import com.example.mutantsquid.command.SkipRitualCommand;
import com.example.mutantsquid.entity.MutantSquidEntity;
import com.example.mutantsquid.entity.RitualShellEntity;
import com.example.mutantsquid.registry.ModEntities;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.fabricmc.fabric.api.event.player.UseBlockCallback;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.Vec3d;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MutantSquidMod implements ModInitializer {
    public static final String MODID = "mutantsquid";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

    @Override
    public void onInitialize() {
        ModEntities.register();
        FabricDefaultAttributeRegistry.register(ModEntities.MUTANT_SQUID, MutantSquidEntity.createAttributes());
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> SkipRitualCommand.register(dispatcher));
        ServerTickEvents.END_WORLD_TICK.register(world -> RitualManager.tick(world));
        RitualDropHandler.register();

        // Запуск ритуала при нажатии ПКМ раковиной наутилуса по любому блоку
        UseBlockCallback.EVENT.register((player, world, hand, hitResult) -> {
            ItemStack stack = player.getStackInHand(hand);
            if (stack.isOf(Items.NAUTILUS_SHELL)) {
                if (!world.isClient) {
                    Vec3d pos = hitResult.getPos();
                    RitualShellEntity shell = new RitualShellEntity(ModEntities.RITUAL_SHELL, world);
                    shell.refreshPositionAndAngles(pos.x, pos.y + 0.1, pos.z, 0, 0);
                    world.spawnEntity(shell);
                    if (!player.isCreative()) {
                        stack.decrement(1);
                    }
                }
                return ActionResult.SUCCESS;
            }
            return ActionResult.PASS;
        });

        LOGGER.info("MutantSquidMod initialized");
    }
}
