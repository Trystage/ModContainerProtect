package cn.sh1rocu.mcp;

import cn.sh1rocu.mcp.commands.MainCommand;
import cn.sh1rocu.mcp.listener.ContainerOpenListener;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Bukkit;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ModContainerProtect extends JavaPlugin {
    private static ModContainerProtect instance;
    private ProtocolManager protocolManager;

    @Override
    public void onLoad(){
        if (Bukkit.getPluginManager().getPlugin("Residence") != null && Bukkit.getPluginManager().getPlugin("ProtocolLib") != null)
            this.getLogger().log(Level.INFO, ChatColor.GREEN + "ModContainerProtect has been loaded...");
        else {
            this.getLogger().log(Level.WARNING, ChatColor.RED + "Residence or ProtocolLib is not installed, ModContainerProtect will disable...");
            Bukkit.getPluginManager().disablePlugin(this);
        }
    }
    @Override
    public void onEnable() {
        instance = this;
        loadConfig();
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ContainerOpenListener(), this);
        PluginCommand command = getCommand("mcp");
        MainCommand mainCommand = new MainCommand();
        command.setExecutor(mainCommand);
        command.setTabCompleter(mainCommand);
        protocolManager = ProtocolLibrary.getProtocolManager();
    }

    private void loadConfig() {
        saveDefaultConfig();
        reloadConfig();
        YamlConfiguration config = (YamlConfiguration) getConfig();
        if (config.get("mods") == null) {
            config.set("mods", new String[]{"IRONCHEST", "CFM", "MORECFM", "NFM"});
            saveConfig();
            reloadConfig();
        }
        if (config.get("blocks") == null) {
            config.set("blocks", new String[]{"APOTHEOSIS_ENCHANTMENT_LIBRARY","APOTHEOSIS_ENDER_LIBRARY"});
            saveConfig();
            reloadConfig();
        }
        if (config.get("message") == null) {
            config.set("message", "&a[&6ModContainerProtect&a]&cYou have no permission to open this container");
        }
    }

    public static ModContainerProtect getInstance() {
        return instance;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}