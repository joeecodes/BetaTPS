package me.buryings.betatps.commands;

import me.buryings.betatps.BetaTPS;
import me.buryings.betatps.registers.CountTPS;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPS implements CommandExecutor {

    private BetaTPS plugin;

    public CommandTPS(BetaTPS plugin) {
        this.plugin = plugin;
    }

    private final String tpsmessage = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("tps-message"));
    private final String noperm = ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("no-permission"));

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        if (command.getName().equalsIgnoreCase("btps")) {
            final Player p = (Player) sender;
            final double tps = CountTPS.getTPS();
            final double readabletps = Math.round(tps * 100.0) / 100.0;

            if (p.hasPermission("betaptps.viewtps")) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', tpsmessage.replace("%tps%", String.valueOf(readabletps))));

            } else if (!(p.hasPermission("betatps.viewtps"))) {
                p.sendMessage(ChatColor.translateAlternateColorCodes('&', noperm));
            }
        }
        return false;
    }
}
