package com.christofmeg.humbleadditions.data.lang;

import net.minecraft.data.DataGenerator;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Blocks;
import net.minecraftforge.common.data.LanguageProvider;

public class VanillaLanguageProvider extends LanguageProvider {

	public VanillaLanguageProvider(DataGenerator gen, String locale) {
		super(gen, "minecraft", locale);
	}

	@Override
	protected void addTranslations() {
		String locale = this.getName().replace("Languages: ", "");
		switch(locale) {
		case "en_us":			
			
			add(Blocks.CRAFTING_TABLE, "Oak Crafting Table");
			add(Items.SHEARS, "Iron Shears");

			break;
		default:
			break;
		}
	}
	
	
}
