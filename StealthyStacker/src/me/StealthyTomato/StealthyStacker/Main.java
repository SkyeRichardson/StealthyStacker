package me.StealthyTomato.StealthyStacker;

import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.Location;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.StealthyTomato.StealthyStacker.Listeners.BlockBreakListener;
import me.StealthyTomato.StealthyStacker.Listeners.BlockPlaceListener;
import me.StealthyTomato.StealthyStacker.Listeners.EntityDamageListener;
import me.StealthyTomato.StealthyStacker.Listeners.InventoryPickupListener;
import me.StealthyTomato.StealthyStacker.Listeners.ItemSpawnListener;
import me.StealthyTomato.StealthyStacker.Listeners.MobSpawnListener;
import me.StealthyTomato.StealthyStacker.Listeners.PlayerPickupItemListener;
import me.StealthyTomato.StealthyStacker.Listeners.SpawnerSpawnListener;
import me.StealthyTomato.StealthyStacker.StackClasses.BlockStack;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	public FileConfiguration config = getConfig();
	public static HashMap<UUID, EntityStack> entityStacks = new HashMap<>(); 
	public static HashMap<Location, BlockStack> blockStacks = new HashMap<>(); 

	@Override
	public void onEnable() {
		//TODO
		//startup, reloads, and plugin reloads
		plugin = this;
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
		getServer().getPluginManager().registerEvents(new ItemSpawnListener(), this);
		getServer().getPluginManager().registerEvents(new PlayerPickupItemListener(), this);
		getServer().getPluginManager().registerEvents(new BlockPlaceListener(), this);
		getServer().getPluginManager().registerEvents(new BlockBreakListener(), this);
		getServer().getPluginManager().registerEvents(new InventoryPickupListener(), this);
		getServer().getPluginManager().registerEvents(new SpawnerSpawnListener(), this);
		getLogger().info("StealthyStacker Enabled");
	}
	
	@Override
	public void onDisable() {
		//TODO
		//shutdown, reloads, plugin reloads
	}
	
	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		Player player = (Player) sender;
		if (sender instanceof Player) {
			if(args.length > 0 && cmd.getName().equalsIgnoreCase("StealthyStacker")) {
				switch (args.length) {
					case 1:
						if(args[0].equalsIgnoreCase("reload")) {
							plugin.reloadConfig();
							getLogger().info("reloaded plugin");
						}
						if(args[0].equalsIgnoreCase("kill")) {
							Main.entityStacks.clear();
							getLogger().info("removed entity stacks");
						}
					default:
				}
			
				return true;
			}
		}
		return false;
	}
	
	public static Main getPlugin() {
		return plugin;
	}
	
	public static HashMap<UUID, EntityStack> getEntityStacks() {
		return entityStacks;
	}
	
}
