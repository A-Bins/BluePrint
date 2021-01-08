package org.blueprint.blueprint.Events;

import net.md_5.bungee.api.ChatColor;
import org.blueprint.blueprint.Blueprint;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Arrays;
import java.util.HashMap;
import java.util.UUID;

public class RightClick implements Listener {
	private static HashMap<UUID, Inventory> BluePrintInventory = new HashMap<>();

	public static HashMap<UUID, Inventory> getBluePrintInventory() {
		return BluePrintInventory;
	}

	@EventHandler
	public void BluePrintRightClick(PlayerInteractEvent e){
		Player p = e.getPlayer();
		UUID uuid = p.getUniqueId();
		if(e.getAction() == Action.RIGHT_CLICK_AIR | e.getAction() == Action.RIGHT_CLICK_BLOCK){
			if(e.getMaterial() == Material.BLUE_DYE){
				getBluePrintInventory().put(uuid,Bukkit.createInventory(null, 9, ChatColor.of("#4374D9") +"Blue Print Info"));
				p.openInventory(getBluePrintInventory().get(uuid));
				ItemStack 토대 = new ItemStack(Material.OAK_PRESSURE_PLATE, 1);
				ItemMeta 토대메타 = 토대.getItemMeta();
				토대메타.setDisplayName(ChatColor.of("#F2CB61")+"나무 토대");
				토대메타.setLore(Arrays.asList(ChatColor.of("#F2CB61")+"가장 기본적인 나무 토대, 쓸만해보인다."));
				토대.setItemMeta(토대메타);
				getBluePrintInventory().get(uuid).setItem(0, 토대);
				e.setCancelled(true);
			}
		}
	}
}
