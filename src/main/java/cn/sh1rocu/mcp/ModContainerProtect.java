package cn.sh1rocu.mcp;

import cn.sh1rocu.mcp.commands.MainCommand;
import cn.sh1rocu.mcp.listener.ContainerOpenListener;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import org.bukkit.ChatColor;
import org.bukkit.command.PluginCommand;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.logging.Level;

public final class ModContainerProtect extends JavaPlugin {
    private static ModContainerProtect instance;
    private YamlConfiguration config;
    private ProtocolManager protocolManager;

    @Override
    public void onEnable() {
        instance = this;
        PluginManager pm = getServer().getPluginManager();
        pm.registerEvents(new ContainerOpenListener(), this);
        saveDefaultConfig();
        config = (YamlConfiguration) getConfig();
        if (config.get("mods") == null) {
            config.set("mods", new String[]{"IRONCHEST", "CFM", "MORECFM", "NFM"});
            saveConfig();
        }
        PluginCommand command = getCommand("mcp");
        MainCommand mainCommand = new MainCommand();
        command.setExecutor(mainCommand);
        command.setTabCompleter(mainCommand);
        protocolManager = ProtocolLibrary.getProtocolManager();
        this.getLogger().log(Level.INFO, ChatColor.GREEN + "ModContainerProtect has been loaded...");
    }

    public static ModContainerProtect getInstance() {
        return instance;
    }

    public YamlConfiguration getModList() {
        return config;
    }

    public void setModList(YamlConfiguration config) {
        this.config = config;
    }

    public ProtocolManager getProtocolManager() {
        return protocolManager;
    }
}