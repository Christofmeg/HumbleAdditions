package com.christofmeg.humbleadditions.registry;

import com.christofmeg.humbleadditions.setup.ModConstants;

import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.network.IContainerFactory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class MenuTypesRegistry {

	public static final DeferredRegister<MenuType<?>> MENUS = DeferredRegister.create(ForgeRegistries.MENU_TYPES, ModConstants.MOD_ID);

	//	public static final RegistryObject<MenuType<AutoSmithingTableMenu>> AUTO_SMITHING_TABLE_MENU =
	//			registerMenuType(AutoSmithingTableMenu::new, "auto_smithing_table_menu");

	@SuppressWarnings("unused")
	private static <T extends AbstractContainerMenu> RegistryObject<MenuType<T>> registerMenuType(IContainerFactory<T> factory,
			String name) {
		return MENUS.register(name, () -> IForgeMenuType.create(factory));
	}

}
