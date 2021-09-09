package me.buryings.betatps.commands;

import me.buryings.betatps.BetaTPS;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

public class CommandTPSReload implements CommandExecutor {

    private BetaTPS plugin;

    public CommandTPSReload(BetaTPS plugin) {

        this.plugin = plugin;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

        Player p = (Player) sender;

        if (p.hasPermission("betatps.reload")) {
            plugin.reloadConfig();
            p.sendMessage(ChatColor.translateAlternateColorCodes('&', plugin.getConfig().getString("plugin-reloaded")));

        }

        return false;
    }
}
