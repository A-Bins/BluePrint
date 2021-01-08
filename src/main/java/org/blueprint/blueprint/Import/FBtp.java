package org.blueprint.blueprint.Import;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.FallingBlock;
import org.bukkit.entity.Player;

public class FBtp {
	public static Location FallingBlockCenter(Player p){
		Location toloc = p.getEyeLocation().add(p.getLocation().getDirection().multiply(4));
		toloc.setZ(toloc.toCenterLocation().getZ());
		toloc.setX(toloc.toCenterLocation().getX());
		for (Integer i = 0; i < 255; i += 1) {
			Location Yloc = toloc.clone();
			Yloc.setY(Yloc.getY() - i);
			if (Yloc.getBlock().getType() == Material.GRASS) {
				toloc.setY(Yloc.toBlockLocation().getY() + 1);
				break;
			} else if (Yloc.getBlock().getType() == Material.TALL_GRASS) {
				toloc.setY(Yloc.toBlockLocation().getY());
				break;
			} else if (!Yloc.getBlock().getType().isAir()) {
				toloc.setY(Yloc.toBlockLocation().getY() + 2);
				break;
			}
		}
		for (; ; ) {
			Location loc = toloc.clone();
			loc.setY(loc.getY() - 1);
			if(loc.getBlock().getType() == Material.GRASS || loc.getBlock().getType() == Material.TALL_GRASS) {
				if(loc.getBlock().getType() == Material.GRASS){
					loc.setY(loc.getY() + 1);
					if(!loc.getBlock().getType().isAir()){
						toloc.setY(toloc.toBlockLocation().getY() + 2);
						break;
					}else break;
				}else break;
			}
			if(!loc.getBlock().getType().isAir()) {
				toloc.setY(toloc.toBlockLocation().getY() + 1);
			} else if(!toloc.getBlock().getType().isAir()) {
				toloc.setY(toloc.toBlockLocation().getY() + 2);
				break;
			} else break;

		}
		return toloc;
	}
}
