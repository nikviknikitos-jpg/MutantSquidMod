package com.example.mutantsquid;

import com.example.mutantsquid.entity.RitualShellEntity;
import net.minecraft.server.world.ServerWorld;

import java.util.ArrayList;
import java.util.List;

public class RitualManager {
    public static final List<RitualShellEntity> ACTIVE = new ArrayList<>();

    public static void tick(ServerWorld world) {
        ACTIVE.removeIf(e -> !e.isAlive());
        for (RitualShellEntity shell : new ArrayList<>(ACTIVE)) {
            if (shell.getWorld() == world) shell.tickRitual();
        }
    }

    public static void skipAll(ServerWorld world) {
        for (RitualShellEntity shell : ACTIVE) {
            if (shell.getWorld() == world) shell.skipToEnd();
        }
    }
}
