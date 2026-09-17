package com.example.homemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.GameRules;

import java.time.DayOfWeek;
import java.time.LocalDate;

public class HomeMod implements ModInitializer {
    private static DayOfWeek lastAppliedDay;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(HomeMod::updateKeepInventory);
    }

    private static void updateKeepInventory(MinecraftServer server) {
        LocalDate today = LocalDate.now();
        DayOfWeek day = today.getDayOfWeek();

        if (day == lastAppliedDay) {
            return;
        }
        lastAppliedDay = day;

        boolean friday = day == DayOfWeek.FRIDAY;
        GameRules gameRules = server.getGameRules();
        boolean desiredValue = !friday;
        boolean currentValue = gameRules.get(GameRules.KEEP_INVENTORY).get();

        if (currentValue != desiredValue) {
            gameRules.get(GameRules.KEEP_INVENTORY).set(desiredValue, server);
        }

        if (friday) {
            server.getPlayerManager().broadcast(
                    net.minecraft.text.Text.literal("§c⚔ Piątek! Keep Inventory zostało wyłączone."),
                    false
            );
        } else if (day == DayOfWeek.SATURDAY) {
            server.getPlayerManager().broadcast(
                    net.minecraft.text.Text.literal("§a✓ Sobota! Keep Inventory zostało ponownie włączone."),
                    false
            );
        }
    }
}
