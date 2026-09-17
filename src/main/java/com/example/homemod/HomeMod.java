package com.example.homemod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.server.MinecraftServer;
import net.minecraft.text.Text;
import net.minecraft.world.GameRules;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

public class HomeMod implements ModInitializer {
    private static DayOfWeek lastAppliedDay;
    private static LocalDate lastFridayWarningDate;

    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(HomeMod::updateKeepInventory);
    }

    private static void updateKeepInventory(MinecraftServer server) {
        LocalDateTime now = LocalDateTime.now();
        LocalDate today = now.toLocalDate();
        DayOfWeek day = today.getDayOfWeek();

        // 10 minut przed piątkiem: czwartek 23:50-23:59:59.
        if (day == DayOfWeek.THURSDAY
                && !now.toLocalTime().isBefore(LocalTime.of(23, 50))
                && lastFridayWarningDate != today) {
            lastFridayWarningDate = today;
            server.getPlayerManager().broadcast(
                    Text.literal("§e⚠ Uwaga! Za 10 minut zaczyna się piątek — Keep Inventory zostanie wyłączone."),
                    false
            );
        }

        // Zabezpieczenie przed wykonywaniem logiki co tick.
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
                    Text.literal("§c⚔ Piątek! Keep Inventory zostało wyłączone."),
                    false
            );
        } else if (day == DayOfWeek.SATURDAY) {
            server.getPlayerManager().broadcast(
                    Text.literal("§a✓ Sobota! Keep Inventory zostało ponownie włączone."),
                    false
            );
        }
    }
}
