package org.blueprint.blueprint.Events;

import org.blueprint.blueprint.Blueprint;
import org.blueprint.blueprint.Import.FBtp;
import org.bukkit.Bukkit;
import org.bukkit.Location;
import net.md_5.bungee.api.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityChangeBlockEvent;
import org.bukkit.event.entity.EntityDropItemEvent;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.ArrayList;
import java.util.Objects;
import java.util.UUID;

public class InventoryClick implements Listener {
	@EventHandler
	public void InventoryClickEvent(InventoryClickEvent e){
		Player p = (Player) e.getWhoClicked();
		UUID uuid = p.getUniqueId();
		if(e.getCurrentItem() != null && e.getClickedInventory() == RightClick.getBluePrintInventory().get(uuid)){
			e.setCancelled(true);
			if(Objects.requireNonNull(e.getCurrentItem()).getType() == Material.OAK_PRESSURE_PLATE){
				p.closeInventory();
				p.sendMessage(ChatColor.of("#F2CB61")+"나무 토대를 선택 하셨습니다.");


				// 중심 폴링블럭 시작
				Location Centerloc = FBtp.FallingBlockCenter(p);
				FallingBlock Centerfb = p.getWorld().spawnFallingBlock(Centerloc, Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
				// 중심 폴링블럭 끝


				// 중심 에서의 X좌표 +1 폴링블럭 시작
				Location Xloc = Centerloc.clone();
				Xloc.setX(Centerloc.getX() + 1);
				FallingBlock Xfb = p.getWorld().spawnFallingBlock(Xloc, Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
				// 중심 에서의 X좌표 +1 폴링블럭 끝


				// 중심 에서의 X좌표 -1 폴링블럭 시작
				Location XXloc = Centerloc.clone();
				XXloc.setX(Centerloc.getX() - 1);
				FallingBlock XXfb = p.getWorld().spawnFallingBlock(XXloc, Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
				// 중심 에서의 X좌표 -1폴링블럭 끝


				// 중심 에서의 Z좌표 +1 폴링블럭 시작
				Location Zloc = Centerloc.clone();
				Zloc.setZ(Centerloc.getZ() + 1);
				FallingBlock Zfb = p.getWorld().spawnFallingBlock(Zloc, Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
				// 중심 에서의 Z좌표 +1 폴링블럭 끝


				// 중심 에서의 Z좌표 -1 폴링블럭 시작
				Location ZZloc = Centerloc.clone();
				ZZloc.setZ(Centerloc.getZ() - 1);
				FallingBlock ZZfb = p.getWorld().spawnFallingBlock(ZZloc, Material.LIGHT_BLUE_STAINED_GLASS.createBlockData());
				// 중심 에서의 Z좌표 -1폴링블럭 끝






				Centerfb.setGravity(false);
				Xfb.setGravity(false);
				XXfb.setGravity(false);
				Zfb.setGravity(false);
				ZZfb.setGravity(false);


				ArrayList<FallingBlock> array = new ArrayList<>();

				array.add(Centerfb);

				array.add(Xfb);
				array.add(XXfb);
				array.add(Zfb);
				array.add(ZZfb);

				Bukkit.getScheduler().runTaskTimer(Blueprint.getInstance(), () -> {
					if(p.getInventory().getItemInMainHand().getType() == Material.BLUE_DYE) {




						// 중심 폴링블럭 시작
						Location toCentorloc = FBtp.FallingBlockCenter(p);
						Centerfb.teleportAsync(toCentorloc);
						// 중심 폴링블럭 끝


						// 중심 에서의 X좌표 +1 폴링블럭 시작
						Location ScXloc = toCentorloc.clone();
						ScXloc.setX(toCentorloc.getX() + 1);
						Xfb.teleportAsync(ScXloc);
						// 중심 에서의 X좌표 +1 폴링블럭 끝


						// 중심 에서의 X좌표 -1 폴링블럭 시작
						Location ScXXloc = toCentorloc.clone();
						ScXXloc.setX(toCentorloc.getX() - 1);
						XXfb.teleportAsync(ScXXloc);
						// 중심 에서의 X좌표 -1폴링블럭 끝


						// 중심 에서의 Z좌표 +1 폴링블럭 시작
						Location ScZloc = toCentorloc.clone();
						ScZloc.setZ(toCentorloc.getZ() + 1);
						Zfb.teleportAsync(ScZloc);
						// 중심 에서의 Z좌표 +1 폴링블럭 끝


						// 중심 에서의 Z좌표 -1 폴링블럭 시작
						Location ScZZloc = toCentorloc.clone();
						ScZZloc.setZ(toCentorloc.getZ() - 1);
						ZZfb.teleportAsync(ScZZloc);
						// 중심 에서의 Z좌표 -1폴링블럭 끝






						for(FallingBlock fb : array) {
							if (fb.isDead()) {
								for(FallingBlock ffb : array){
									ffb.remove();
								}

								p.sendMessage(ChatColor.of("#F2CB61") + "취소하셨습니다.");
								Bukkit.getScheduler().cancelTasks(Blueprint.getInstance());
								break;
							}
						}

					}else{
						for(FallingBlock fb : array) {
							fb.remove();
						}
						p.sendMessage(ChatColor.of("#F2CB61") + "취소하셨습니다.");
						Bukkit.getScheduler().cancelTasks(Blueprint.getInstance());



					}
				}, 1,1);
			}
		}
	}
	@EventHandler
	public void EntityDropItemEvent(EntityDropItemEvent e) {
		if(e.getEntity().getType() == EntityType.FALLING_BLOCK) {
			e.setCancelled(true);
			e.getEntity().remove();
		}
	}
	@EventHandler
	public void EntityChangeBlockEvent(EntityChangeBlockEvent e) {
		if(e.getEntity().getType() == EntityType.FALLING_BLOCK) {
			e.setCancelled(true);
			e.getEntity().remove();

		}
	}
}
