package peli1gamer.arsonclient.gui;

import net.minecraft.client.Minecraft;
/** Single entry point for opening the rebuilt UI from keybinds or menus. */
public final class ArsonScreenFactory { private ArsonScreenFactory(){} public static void open(){Minecraft.getInstance().setScreen(new ArsonClientScreen());} }
