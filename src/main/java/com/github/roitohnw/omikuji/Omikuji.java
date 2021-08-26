package com.github.roitohnw.omikuji;

import org.bukkit.Bukkit;
import org.bukkit.Sound;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Random;
import java.util.UUID;

public final class Omikuji extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("start");
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        getLogger().info("stop");
    }

    ArrayList<UUID> isRunning = new ArrayList<>();

    @Override
    public boolean onCommand(@NotNull CommandSender sender, @NotNull Command command, @NotNull String label, @NotNull String[] args) {
        if (!(sender instanceof Player))return true;
        Player player = (Player) sender;
        if (label.equals("omikuji")) {
            if (isRunning.contains(player.getUniqueId())){
                player.sendMessage("実行中だよ！");
                return true;
            }
            isRunning.add(player.getUniqueId());
            sender.sendMessage("§d§lあなたの運勢を占います。");
            player.playSound(player.getLocation(), Sound.ENTITY_WITHER_SPAWN, 0.5f, 1f);
            int mikuji = new Random().nextInt(5);
            String unt;
            switch (mikuji){
                default:
                    unt = "§4§lERROR";
                    break;
                case 0:
                    unt = "§6§l大吉";
                    break;
                case 1:
                    unt = "§d§l中吉";
                    break;
                case 2:
                    unt = "§e§l吉";
                    break;
                case 3:
                    unt = "§0§l凶";
                    break;
            }
            Bukkit.getScheduler().runTaskLater(this,()->{
            Bukkit.broadcastMessage("§f§l" + sender.getName() + "§f§lさんの今日の運勢は" + unt + "§f§lです");
            isRunning.remove(player.getUniqueId());
            },40);

        }
    return true;
    }
}
