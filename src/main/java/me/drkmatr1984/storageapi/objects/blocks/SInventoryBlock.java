package me.drkmatr1984.storageapi.objects.blocks;

import java.io.IOException;
import java.io.Serializable;

import org.bukkit.Location;
import org.bukkit.block.Block;
import org.bukkit.entity.Entity;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryHolder;
import org.bukkit.inventory.ItemStack;

import me.drkmatr1984.base64utilslib.InventoryBase64Utils;
import me.drkmatr1984.storageapi.enums.BlockTypes;
import me.drkmatr1984.storageapi.utils.ChatUtils;

public class SInventoryBlock extends SBaseBlock implements Serializable{

	/**
	 * 	   Constructor for serializable inventory holder blocks
	 */
	private static final long serialVersionUID = -8304194076933725859L;
	//info for storing Inventories
	private Byte inventoryData = null;
	private String inventory = null;
	private String inventoryType = null;
	
	@SuppressWarnings("deprecation")
	public SInventoryBlock(Block block) {
		super(block, BlockTypes.INVENTORY);
		if(block.getState() instanceof InventoryHolder) {
			inventoryData = block.getState().getData().getData();
			inventoryType = ((InventoryHolder) block.getState()).getInventory().getType().toString();
			ItemStack[] inv = ((InventoryHolder) block.getState()).getInventory().getContents();
			inventory = InventoryBase64Utils.toBase64(inv);			
		}
	}
	
	@SuppressWarnings("deprecation")
	public SInventoryBlock(Block block, Entity entity) {
		super(block, entity, BlockTypes.INVENTORY);
		if(block.getState() instanceof InventoryHolder) {
			inventoryData = block.getState().getData().getData();
			inventoryType = ((InventoryHolder) block.getState()).getInventory().getType().toString();
			ItemStack[] inv = ((InventoryHolder) block.getState()).getInventory().getContents();
			inventory = InventoryBase64Utils.toBase64(inv);			
		}
	}
	
	@SuppressWarnings("deprecation")
	public SInventoryBlock(Location loc) {
		super(loc, BlockTypes.INVENTORY);
		Block block = loc.getBlock();
		if(block.getState() instanceof InventoryHolder) {
			inventoryData = block.getState().getData().getData();
			inventoryType = ((InventoryHolder) block.getState()).getInventory().getType().toString();
			ItemStack[] inv = ((InventoryHolder) block.getState()).getInventory().getContents();
			inventory = InventoryBase64Utils.toBase64(inv);			
		}
	}
	
	@SuppressWarnings("deprecation")
	public SInventoryBlock(Location loc, Entity entity) {
		super(loc, entity, BlockTypes.INVENTORY);
		Block block = loc.getBlock();
		if(block.getState() instanceof InventoryHolder) {
			inventoryData = block.getState().getData().getData();
			inventoryType = ((InventoryHolder) block.getState()).getInventory().getType().toString();
			ItemStack[] inv = ((InventoryHolder) block.getState()).getInventory().getContents();
			inventory = InventoryBase64Utils.toBase64(inv);			
		}
	}
	
	public byte getByteData() {
		return this.inventoryData;
	}
	
	public InventoryType getInventoryType() {
		return InventoryType.valueOf(this.inventoryType);
	}
	
	public Inventory getInventory() {
		try {
			return InventoryBase64Utils.inventoryFromBase64(this.inventory);
		} catch (IOException e) {
			ChatUtils.sendColoredLog("&cERROR: &eCannot de-serialize Inventory");
		}
		return null;
	}
}