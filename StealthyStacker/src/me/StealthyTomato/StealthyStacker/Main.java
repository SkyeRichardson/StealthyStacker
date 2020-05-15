package me.StealthyTomato.StealthyStacker;

import java.util.HashMap;
import java.util.UUID;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import me.StealthyTomato.StealthyStacker.Listeners.EntityDamageListener;
import me.StealthyTomato.StealthyStacker.Listeners.MobSpawnListener;
import me.StealthyTomato.StealthyStacker.StackClasses.EntityStack;

public class Main extends JavaPlugin {
	
	private static Main plugin;
	public FileConfiguration config = getConfig();
	private static HashMap<UUID, EntityStack> entityStacks = new HashMap<>(); 
	
	@Override
	public void onEnable() {
		//TODO
		//startup, reloads, and plugin reloads
		plugin = this;
		saveDefaultConfig();
		getServer().getPluginManager().registerEvents(new MobSpawnListener(), this);
		getServer().getPluginManager().registerEvents(new EntityDamageListener(), this);
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
