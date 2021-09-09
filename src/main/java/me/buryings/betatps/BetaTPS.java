package me.buryings.betatps;

import me.buryings.betatps.commands.CommandTPS;
import me.buryings.betatps.commands.CommandTPSReload;
import me.buryings.betatps.registers.CountTPS;
import me.buryings.betatps.registers.TPSHook;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.event.Listener;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

public final class BetaTPS extends JavaPlugin implements Listener {

    public static BetaTPS instance;

    public static BetaTPS getInstace() {
        return instance;
    }
    public static void setInstace(BetaTPS instance) {
        BetaTPS.instance = instance;
    }


    @Override
    public void onEnable() {

        // Run Task Event
        // Runs Tick Per Second count every 10s
        Bukkit.getScheduler().scheduleAsyncRepeatingTask(this, new CountTPS(), 100L, 1L);
        // Hooking TPSHook

        System.out.println("[BETA TPS]: Loading config...");
        loadConfig();
        System.out.println("[BETA TPS]: Loading registers...");
        registers();
        System.out.println("[BETA TPS]: Loading commands...");
        registerCommands();

        System.out.println("[BETA TPS]: Plugin enabled!");

    }

    @Override
    public void onDisable() {

        // saveConfig();
        System.out.println("[BETA TPS]: Plugin disabled!");
    }

    public void registerCommands() {
        getCommand("btps").setExecutor(new CommandTPS(this));
        getCommand("btpsreload").setExecutor(new CommandTPSReload(this));
        System.out.println("[BETA TPS]: Commands loaded!");
    }

    private void loadConfig() {
        System.out.println("[BETA TPS]: Config loaded!");
        // this.getConfig().options().copyDefaults(true);
        this.saveDefaultConfig();


    }

    private void registers() {

        if (Bukkit.getPluginManager().getPlugin("PlaceHolderAPI") != null) {
            Bukkit.getPluginManager().registerEvents((Listener) this, this);
        } else {
            getLogger().warning("Could not find PlaceHoldearAPI! This plugin is required.");
            // Bukkit.getPluginManager().disablePlugin(this);
            System.out.println("[BETA TPS]: Disabled plugin - Needs PlaceHolderAPI");
        }
        if (Bukkit.getPluginManager().isPluginEnabled("PlaceHolderAPI")) {
            new TPSHook();
            System.out.println("[BETA TPS]: PlaceHolderaAPI is enabled");

        }
    }
}
