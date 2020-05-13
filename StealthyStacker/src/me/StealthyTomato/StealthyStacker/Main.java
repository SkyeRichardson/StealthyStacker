package me.StealthyTomato.StealthyStacker;

import java.util.HashMap;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Player;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;

import me.StealthyTomato.StealthyStacker.Listeners.MobSpawnListener;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	public FileConfiguration config = getConfig();
	public static HashMap<UUID, EntityStack> entityStacks = new HashMap<>(); 
	
	@Override
	public void onEnable() {
		//TODO
		//startup, reloads, and plugin reloads
		plugin = this;
		plugin.saveDefaultConfig();
		plugin.getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
	}
	
	@Override
	public void onDisable() {
		//TODO
		//shutdown, reloads, plugin reloads
	}

	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		
		//StealthyStacker
		if(args.length == 0) {
		}
		
		//StealthyStacker x
		if(args.length == 1) {
			if(args[0].equalsIgnoreCase("reload")) {
				plugin.reloadConfig();
			}
		}
		
		
		return false;
	}
	
	public static Main getPlugin() {
		return plugin;
	}
}
