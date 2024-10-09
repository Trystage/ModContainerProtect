package cn.sh1rocu.mcp.commands;

import cn.sh1rocu.mcp.ModContainerProtect;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.command.TabExecutor;
import org.bukkit.configuration.file.YamlConfiguration;

import java.util.ArrayList;
import java.util.List;

public class MainCommand implements TabExecutor {
    private static ModContainerProtect mcpInstance;

    public MainCommand() {
        mcpInstance = ModContainerProtect.getInstance();
    }

    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        if (args.length > 0) {
            switch (args[0]) {
                case "reload":
                    mcpInstance.reloadConfig();
                    commandSender.sendMessage("reload successfully");
                    break;
                default:
                    commandSender.sendMessage("invalid params");
            }
        }
        return true;
    }

    @Override
    public List<String> onTabComplete(CommandSender commandSender, Command command, String s, String[] args) {
        List<String> tab = new ArrayList<>();
        if (args.length == 1) tab.add("reload");
        return tab;
    }
}
