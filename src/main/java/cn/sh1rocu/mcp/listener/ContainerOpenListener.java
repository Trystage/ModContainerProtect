package cn.sh1rocu.mcp.listener;

import cn.sh1rocu.mcp.ModContainerProtect;
import com.bekvon.bukkit.residence.Residence;
import com.bekvon.bukkit.residence.containers.Flags;
import com.bekvon.bukkit.residence.protection.ClaimedResidence;
import com.bekvon.bukkit.residence.protection.FlagPermissions;
import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.wrappers.BlockPosition;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.inventory.InventoryOpenEvent;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.event.player.PlayerInteractEvent;

public class ContainerOpenListener implements Listener {
    private static ModContainerProtect mcpInstance;

    public ContainerOpenListener() {
        mcpInstance = ModContainerProtect.getInstance();
    }

    @EventHandler
    public void openChestEvent(InventoryOpenEvent event) {

        //if player opens a loom, then maybe lag the server in some ticks caused by "event.getInventory().getLocation()"
        if (event.getInventory().getType() == InventoryType.LOOM)
            return;
        Location loc = event.getInventory().getLocation();
        if (loc != null && !event.isCancelled() && mcpInstance.getConfig().getStringList("mods").contains(loc.getBlock().getType().name().split("_")[0])) {
            Block block = loc.getBlock();
            Residence resAPI = Residence.getInstance();
            Player player = (Player) event.getPlayer();
            ClaimedResidence res = resAPI.getResidenceManager().getByLoc(block.getLocation());
            if (res != null && !player.isOp() &&
                    !res.getPermissions().playerHas(player, Flags.container, FlagPermissions.FlagCombo.OnlyTrue)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',mcpInstance.getConfig().getString("message","&a[&6ModContainerProtect&a]&cYou have no permission to open this container")));
                //delay to send close animation packet
                Bukkit.getServer().getScheduler().runTaskLater(mcpInstance, () -> {
                    PacketContainer packet = new PacketContainer(PacketType.Play.Server.BLOCK_ACTION);
                    packet.getBlockPositionModifier().write(0, new BlockPosition(loc.getBlockX(), loc.getBlockY(), loc.getBlockZ()));
                    packet.getBlocks().write(0, block.getType());
                    packet.getIntegers().write(0, 1).write(1, 0);
                    mcpInstance.getProtocolManager().sendServerPacket(player, packet);
   /*                 //vanilla
                    PacketPlayOutBlockAction packet = new PacketPlayOutBlockAction(
                            new BlockPosition(loc.getX(), loc.getY(), loc.getZ()),
                            ((CraftBlock) block).getNMS().g.getBlock(),
                            1, //Action ID: the number of players who can see the packet animation, it is always 1
                            0   //Action Parameters: 0 -> close animation; ≥1 -> opening animation
                    );
                    ((CraftPlayer) player).getHandle().playerConnection.sendPacket(packet);*/
                }, 5);
            }
        }
    }

    @EventHandler
    public void openBlockContainer(PlayerInteractEvent event) {
        if (event.getClickedBlock() == null) return;
        Location loc = event.getClickedBlock().getLocation();
        if (!event.isCancelled() && mcpInstance.getConfig().getStringList("blocks").contains(loc.getBlock().getType().name())) {
            Block block = loc.getBlock();
            Residence resAPI = Residence.getInstance();
            Player player = event.getPlayer();
            ClaimedResidence res = resAPI.getResidenceManager().getByLoc(block.getLocation());
            if (res != null && !player.isOp() &&
                    !res.getPermissions().playerHas(player, Flags.container, FlagPermissions.FlagCombo.OnlyTrue)) {
                event.setCancelled(true);
                player.sendMessage(ChatColor.translateAlternateColorCodes('&',mcpInstance.getConfig().getString("message","&a[&6ModContainerProtect&a]&cYou have no permission to open this container")));
            }
        }
    }
}
