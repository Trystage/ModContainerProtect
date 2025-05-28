# ModContainerProtect
A plugin which can protect containers in mods from stealing.  Depends on [Residence](https://github.com/Zrips/Residence), [ProtocolLib](https://github.com/dmulloy2/ProtocolLib) and **run in Hybird Server(CatServer, Arclight, Mohist)**.<br><br>
一个能保护模组容器的插件，用于**mod插件混合服务端**，依赖于[Residence](https://github.com/Zrips/Residence)和[ProtocolLib](https://github.com/dmulloy2/ProtocolLib)。

## Command

```
/mcp reload
```
  reload config

## Config
When player is opening a container, if the mod-id of this container is in config file, plugin will check if player has the Container(Residence plugin's flag) permission of the residence where the container is in, to open the container. So you just need to write the mod-id(All Uppercase) into config file.<br>

Update: Since ver1.1, you can write the block's registry name(All Uppercase) into config file's 'blocks' field, so that the container of this block can be protected correctly.<br>

玩家打开容器时，如果这个容器所属的mod的id在配置文件中，那么插件会检测玩家是否拥有该容器所在res领地的Container权限，来判断能否打开容器，因此只要将需要检测的mod容器的modid全部用大写字母写入配置文件即可。<br>

更新：从1.1版本开始，你可以将容器方块的注册全名写入配置文件中的blocks字段中，从而确保属于这个方块的容器能被正常保护（适用于写入mods字段无效的容器）。



### Example:
- [Iron Chests](https://github.com/progwml6/ironchest) -> IRONCHEST
- [MrCrayfish's Furniture Mod](https://github.com/MrCrayfish/MrCrayfishFurnitureMod) -> CFM
- [MrCrayfish's More Furniture Mod](https://github.com/MrCrayfish/MrCrayfishMoreFurnitureMod) -> MORECFM
- [Nosiphus Furniture Mod](https://github.com/Nosiphus/nosiphus-furniture-mod) -> NFM
<br><br>
### Config File Structure
![Config File Structure](https://5pw.net/i/2025/05/27/68354a0cd55e3.png)
<br><br>
### Screenshots:
![IRONCHEST](https://pic.nanodesu.net/i/2024/10/10/6706cf1643b29.png)
![NFM](https://pic.nanodesu.net/i/2024/10/10/6706cece4e614.png)
![Some blocks from APOTHEOSIS](https://5pw.net/i/2025/05/27/68354a5d47612.png)