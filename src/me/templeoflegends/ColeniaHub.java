package me.templeoflegends;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;
import org.bukkit.event.entity.FoodLevelChangeEvent;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.bukkit.event.player.PlayerChatEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerQuitEvent;
import org.bukkit.event.player.PlayerToggleFlightEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.permissions.Permission;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.potion.PotionEffect;
import org.bukkit.potion.PotionEffectType;
import org.bukkit.util.Vector;

public class ColeniaHub extends JavaPlugin implements Listener{
	
	ItemStack hubCompass = new ItemStack(Material.COMPASS, 1);
	ItemMeta hubCompassMeta = hubCompass.getItemMeta();
	ArrayList<String> lore = new ArrayList<String>();
    int timesJumped = 0;
    private ArrayList<Player> op = new ArrayList<Player>();
    private ArrayList<Player> allStaff = new ArrayList<Player>();
    private ArrayList<Player> donator = new ArrayList<Player>();
	
	
	public Permission playerPermission1 = new Permission("coleniaHub.coleniaRadio");
	public Permission playerPermission5 = new Permission("coleniaHub.chatMember");
	public Permission playerPermission7 = new Permission("coleniaHub.setSpawn");
	public Permission playerPermission2 = new Permission("coleniaHub.chatMod");
	public Permission playerPermission6 = new Permission("coleniaHub.chatDonator");
	public Permission playerPermission3 = new Permission("coleniaHub.chatAdmin");
	
	@Override
	public void onEnable() {
		PluginManager pm = this.getServer().getPluginManager();
		pm.addPermission(playerPermission1);
		pm.addPermission(playerPermission2);
		pm.addPermission(playerPermission3);
		pm.addPermission(playerPermission5);
		pm.addPermission(playerPermission7);
		pm.addPermission(playerPermission6);
	    pm.registerEvents(this, this);
	
	    
	}
	
	@Override
	public void onDisable() {
		
	}
	
public static Inventory hub = Bukkit.createInventory(null, 9, "§3§lColenia Hub");
	
	
	static {
		
		hub.setItem(2, new ItemStack(Material.DIAMOND_SWORD, 1));
		hub.setItem(6, new ItemStack(Material.POTION, 1, (byte)21));
		 
		}
		 
	
	@EventHandler
	public void onJoin(PlayerJoinEvent event) {
		Player player = event.getPlayer();
			player.teleport(player.getWorld().getSpawnLocation());
			event.setJoinMessage("");
			player.setMaxHealth(60);
			player.setHealth(60);
			player.setAllowFlight(true);	
		}
	
	@EventHandler
	public void onLeave(PlayerQuitEvent event) {
		event.setQuitMessage("");
		
	}
	
		
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onCompassClick(PlayerInteractEvent event) {
		Player player = event.getPlayer();
		
		
		 ItemMeta meta = player.getItemInHand().getItemMeta();
		 if(meta.hasDisplayName()){
		        if(event.getAction() == Action.RIGHT_CLICK_AIR || event.getAction() == Action.RIGHT_CLICK_BLOCK){
		            if(player.getInventory().getItemInHand().getType() == Material.COMPASS && meta.getDisplayName().equals(ChatColor.DARK_RED + "Server Selector")){
	
		            	player.openInventory(hub);
		            	
		
	}
	
}
		 }
	}
	
	@EventHandler
	public void onInventoryClick(InventoryClickEvent event) {
		Player player = (Player) event.getWhoClicked();
		ItemStack clicked = event.getCurrentItem();
		Inventory inventory = event.getInventory();
		if (inventory.getName().equals(hub.getName())) {
			if (clicked.getType() == Material.DIAMOND_SWORD) {
		player.closeInventory();
    	player.chat("/server");
    	player.sendMessage("Connecting To Factions.");
    	event.setCancelled(true);
			}
			else if (clicked.getType() == Material.POTION) {
				player.closeInventory();
		    	player.performCommand("server not ready yet");
		    	event.setCancelled(true);
				
			}
		}else {
			
		}
	}
	@EventHandler
	public void onFoodChange(FoodLevelChangeEvent event) {
		if (event.getEntity() instanceof Player) {
			Player player = (Player) event.getEntity();
			player.setHealth(60);
			event.setCancelled(true);
		};
	}
	
		@SuppressWarnings("deprecation")
		@EventHandler
		public void onPlayerChat(PlayerChatEvent event) {
			event.getFormat();
			Player p = event.getPlayer();
			
			if (!(p.isOp())) {
			  if(p.hasPermission("coleniaHub.chatMember")) {
				    event.setFormat(ChatColor.GRAY + "[" + ChatColor.WHITE + p.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getMessage());
				    //This will give the player with that permission node that Chat format.
				  }else if(p.hasPermission("coleniaHub.chatMod")){
				    event.setFormat(ChatColor.GRAY + "[" + ChatColor.YELLOW + p.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getMessage());
				    //This will give the player with that permission node that Chat format.
				  }else if(p.hasPermission("coleniaHub.chatAdmin")){
				    event.setFormat(ChatColor.GRAY + "[" + ChatColor.DARK_PURPLE + p.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getMessage());
				    //This will give the player with that permission node that Chat format.
				  }else if (p.hasPermission("coleniaHub.chatDonator")) {
					  event.setFormat(ChatColor.GRAY + "[" + ChatColor.GREEN + p.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getMessage());
				  }
			}else {
				event.setFormat(ChatColor.GRAY + "[" + ChatColor.DARK_RED + p.getName() + ChatColor.GRAY + "] " + ChatColor.WHITE + event.getMessage());
			}
				
		}
	
	

	       
	        
	      
	

		
	
public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		

		Player player = (Player) sender;
		
		if(cmd.getName().equalsIgnoreCase("selector") && sender instanceof Player) {
			
			
			player.sendMessage(ChatColor.GOLD + "Compass is still WIP");
			return true;
			}
		else if (cmd.getName().equalsIgnoreCase("coleniaradio") || cmd.getName().equalsIgnoreCase("cr") && sender instanceof Player) {
			
			if (!sender.hasPermission("coleniaHub.coleniaRadio")){
				sender.sendMessage(ChatColor.AQUA + "You must be Developer rank or higher to use this command.");
				return true; 
			}else {
			
			String message = "";
			for (int i = 0; i < args.length; i++) message += (i > 0 ? " " : "") + args[i];
			
			if (args.length < 1) {
				sender.sendMessage(ChatColor.RED + "Ussage: /cr <message>");
				return true;
			}
			if (args.length > 0) {
				
			Bukkit.getServer().broadcastMessage(ChatColor.GRAY + "[" + ChatColor.DARK_AQUA + "Colenia" + ChatColor.AQUA + "Radio" + ChatColor.GRAY + "] " + ChatColor.GOLD + message.replaceAll("&", "§"));
			}
			
		}
		}else if (cmd.getName().equalsIgnoreCase("setspawn") && sender instanceof Player) {
			
			if (!sender.hasPermission("coleniaHub.setSpawn")){
				sender.sendMessage(ChatColor.AQUA + "You must be Developer rank or higher to use this command.");
				return true;
			}else {
			player.sendMessage(ChatColor.BLUE + "Spawn set!");
			player.getWorld().setSpawnLocation(player.getLocation().getBlockX(), player.getLocation().getBlockY() + 1, player.getLocation().getBlockZ());
			}
			
		}
		else if (cmd.getName().equalsIgnoreCase("spawn") && sender instanceof Player) {
			
			
			player.sendMessage(ChatColor.BLUE + "Teleporting to spawn!");
			player.teleport(player.getWorld().getSpawnLocation());
			
			
		}
		
		return true;
	}
}
