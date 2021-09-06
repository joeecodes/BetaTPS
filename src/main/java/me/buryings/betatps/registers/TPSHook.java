package me.buryings.betatps.registers;

import org.bukkit.entity.Player;

public class TPSHook  {

    public String getAuthor() {
        return "Buryings";
    }
    public String getPlugin() {
        return null;
    }
    public String getIdentifier() {
        return "betatps";
    }
    public String getVersion() {
        return "1.0.0";
    }
    public String onPlaceholderRequest(final Player player, final String identifier) {
        if (identifier.equalsIgnoreCase("btps")) {
            return String.valueOf(CountTPS.getTPS());
        }
        return identifier;
    }
}
