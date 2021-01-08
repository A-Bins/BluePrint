package org.blueprint.blueprint;

import net.md_5.bungee.api.ChatColor;
import org.blueprint.blueprint.Commands.GainBlueprint;
import org.blueprint.blueprint.Events.InventoryClick;
import org.blueprint.blueprint.Events.RightClick;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.UUID;

public final class Blueprint extends JavaPlugin {
	private static HashMap<UUID, Inventory> invhashmap = new HashMap<>();

	public static HashMap<UUID, Inventory> getInvhashmap() {
		return invhashmap;
	}

	private static Blueprint instance;
	public static Blueprint getInstance(){
		return instance;
	}
	@Override
	public void onEnable() {
		Bukkit.getConsoleSender().sendMessage("[  "+ ChatColor.AQUA +"Blue Print"+ChatColor.RESET+"  ]" + ChatColor.GREEN +" 플러그인 활성화 copyright ©  < Bins#1004 > all rights reserved");
		instance = this;
			getServer().getPluginManager().registerEvents(new RightClick(), this);
		getServer().getPluginManager().registerEvents(new InventoryClick(), this);


		getServer().getPluginCommand("청사진").setExecutor(new GainBlueprint());

	}

	@Override
	public void onDisable() {
		Bukkit.getConsoleSender().sendMessage("[  "+ ChatColor.AQUA +"Blue Print"+ChatColor.RESET+"  ]" + ChatColor.RED +" 플러그인 비활성화 copyright ©  < Bins#1004 > all rights reserved");

	}
}
