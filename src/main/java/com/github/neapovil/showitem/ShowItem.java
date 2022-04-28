package com.github.neapovil.showitem;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.java.JavaPlugin;

import dev.jorel.commandapi.CommandAPI;
import dev.jorel.commandapi.CommandAPICommand;
import net.kyori.adventure.text.Component;

public final class ShowItem extends JavaPlugin
{
    private static ShowItem instance;

    @Override
    public void onEnable()
    {
        instance = this;

        new CommandAPICommand("showitem")
                .withPermission("showitem.command")
                .executesPlayer((player, args) -> {
                    final ItemStack itemstack = player.getInventory().getItemInMainHand();

                    if (itemstack.getType().equals(Material.AIR))
                    {
                        throw CommandAPI.fail("You must have an item in your main hand");
                    }

                    final Component component = player.displayName()
                            .append(Component.text(" is showing an item in chat: "))
                            .append(itemstack.displayName().hoverEvent(itemstack.asHoverEvent()));

                    this.getServer().sendMessage(component);
                })
                .register();
    }

    @Override
    public void onDisable()
    {
    }

    public static ShowItem getInstance()
    {
        return instance;
    }
}
