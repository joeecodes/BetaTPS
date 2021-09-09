package me.buryings.betatps.commands;

import me.buryings.betatps.BetaTPS;
import me.buryings.betatps.registers.CountTPS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;

public class CommandTPS implements CommandExecutor {

    private BetaTPS plugin;
    private final String tpsmessage;
    private final String noperm;

    public CommandTPS(BetaTPS plugin) {

        this.plugin = plugin;
        FileConfiguration config = plugin.getConfig();

        this.tpsmessage = config.getString("tps-message", "&aCurrent TPS: &2%tps%");
        this.noperm = config.getString("no-permission", "&cYou do not have permission");
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        final double tps = CountTPS.getTPS();
        final double readabletps = Math.round(tps * 100.0) / 100.0;

        if (!(sender instanceof Player)) {
            sender.sendMessage("This can only be used by players - Ran by CONSOLE, sending TPS to server console");
            System.out.println("\n-------------------------------------" + "\nSERVER TPS: " + readabletps + "\n-------------------------------------");
            return false;
        }

        final Player p = (Player) sender;

        if (p.hasPermission("betaptps.viewtps")) {
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', tpsmessage.replace("%tps%", String.valueOf(readabletps))));

        } else if (!(p.hasPermission("betatps.viewtps"))) {
            System.out.println("[BETA TPS]: Denied access: " + p.getName() + " - did not have permission to run /btps");
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));

        }
        {
            }

        return false;
    }
}



