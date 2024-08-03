package net.alpaca.mccourse;

import net.alpaca.mccourse.block.ModBlocks;
import net.alpaca.mccourse.item.ModItemGroups;
import net.alpaca.mccourse.item.ModItems;
import net.fabricmc.api.ModInitializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MCCourseMod implements ModInitializer {

	public static final String MOD_ID = "mccourse";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModItemGroups.registerItemGroups();
		ModItems.registerModItems();
		ModBlocks.registerModBlocks();
	}
}