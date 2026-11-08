package com.sacos;

import java.util.ArrayList;
import java.util.List;

import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public class ModItems {
	private static final List<Item> ALL = new ArrayList<>();

	// register(nombre, filas, columnas, esEnder)
	// Bolsa comun (marron): 3 filas x 9 = 27 espacios. Se tine con cualquier tinte.
	public static final Item BOLSA = register("bolsa", 3, 9, false);
	// Bolsas de colores (15 tintes), 3 filas x 9 = 27 espacios cada una
	public static final String[] COLORES = {"white", "orange", "magenta", "light_blue", "yellow", "lime", "pink",
			"gray", "light_gray", "cyan", "purple", "blue", "green", "red", "black"};
	static {
		for (String c : COLORES) {
			register("bolsa_" + c, 3, 9, false);
		}
	}
	// Bolsa Primigenia (la beige): 5 filas x 9 = 45 espacios, no se craftea ni se tine
	public static final Item BOLSA_PRIMIGENIA = register("bolsa_primigenia", 5, 9, false);
	// Bolsa Ender: abre tu ender chest (3 filas x 9)
	public static final Item ENDER_BAG = register("ender_bag", 3, 9, true);
	// Bolsa Phora: 6 filas x 9 = 54 espacios (6 es el maximo de filas del cofre de Minecraft), no se craftea ni se tine
	public static final Item PHORA_BAG = register("phora_bag", 6, 9, false);
	// Bolsa Gleba: 6 filas x 9 = 54 espacios, no se craftea ni se tine
	public static final Item BOLSA_GLEBA = register("bolsa_gleba", 6, 9, false);

	private static Item register(String name, int rows, int cols, boolean ender) {
		Item item = Registry.register(BuiltInRegistries.ITEM, SacosMod.id(name),
				new BagItem(new Item.Properties().stacksTo(1), rows, cols, ender));
		ALL.add(item);
		return item;
	}

	public static void init() {
		// Pestana propia del modo creativo con la Bolsa Ender como icono
		CreativeModeTab tab = FabricItemGroup.builder()
				.title(Component.translatable("itemGroup.sacos"))
				.icon(() -> new ItemStack(ENDER_BAG))
				.displayItems((params, output) -> {
					for (Item item : ALL) {
						output.accept(item);
					}
				})
				.build();
		Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, SacosMod.id("sacos"), tab);
	}
}
