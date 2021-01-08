package org.blueprint.blueprint.Commands;

import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.OfflinePlayer;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.UUID;

public class GainBlueprint implements CommandExecutor {
	@Override
	public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
		Player p = (Player) sender;
		ItemStack BluePrint = new ItemStack(Material.BLUE_DYE, 1);
		ItemMeta Meta = BluePrint.getItemMeta();

		Meta.setDisplayName(ChatColor.of("#4374D9")+"Blue Print ( 청사진 )");
		Meta.setLore(Arrays.asList(ChatColor.of("#4374D9")+"조금 낡아보이는 청사진이다. 쓸만해 보인다."));
		BluePrint.setItemMeta(Meta);
		p.getInventory().addItem(BluePrint);
		return false;

	}
}