# ModContainerProtect
A plugin which can protect containers in mods from stealing.  Depends on [Residence](https://github.com/Zrips/Residence), [ProtocolLib](https://github.com/dmulloy2/ProtocolLib) and **run in Hybird Server(CatServer, Arclight, Mohist)**.<br>
一个能保护模组容器的插件，用于**mod插件混合服务端**，依赖于[Residence](https://github.com/Zrips/Residence)和[ProtocolLib](https://github.com/dmulloy2/ProtocolLib)。

## Command

```
/mcp reload
```
  reload config

## Config
When player is opening a container, if the registry name of the mod which this container belongs to, is in config file, plugin will check if player has the Container(Residence plugin's flag) permission of the residence where the container is in, to open the container. So you just need to write the mod's registry name(All Uppercase) into config file.<br>
玩家打开容器时，如果这个容器所属的mod注册名在配置文件中，那么插件会检测玩家是否拥有该容器所在res领地的Container权限，来判断能否打开容器，因此只要将需要检测的mod容器的mod注册名写入配置文件即可。
### Example:
- [Iron Chests](https://github.com/progwml6/ironchest) -> IRONCHEST
- [MrCrayfish's Furniture Mod](https://github.com/MrCrayfish/MrCrayfishFurnitureMod) -> CFM
- [MrCrayfish's More Furniture Mod](https://github.com/MrCrayfish/MrCrayfishMoreFurnitureMod) -> MORECFM
- [Nosiphus Furniture Mod](https://github.com/Nosiphus/nosiphus-furniture-mod) -> NFM
<br><br>
![Config File Structure](https://cdn.modrinth.com/data/cached_images/ace893687e1896b782a0ddc4d6d8f6e6273971ed.png)


