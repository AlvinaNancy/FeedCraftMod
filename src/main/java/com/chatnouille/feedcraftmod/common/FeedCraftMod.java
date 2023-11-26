package com.chatnouille.feedcraftmod.common;

import com.chatnouille.feedcraftmod.common.init.ModItems;
import com.chatnouille.feedcraftmod.common.init.ModRecipes;
import net.fabricmc.api.ModInitializer;

import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class FeedCraftMod implements ModInitializer {
	public static final String MODID = "feedcraftmod";
    public static final Logger LOGGER = LoggerFactory.getLogger(MODID);

	@Override
	public void onInitialize() {
		ModItems.register();
		ModRecipes.register();
	}

	public static Identifier id(String name) {
		return new Identifier(MODID, name);
	}
}