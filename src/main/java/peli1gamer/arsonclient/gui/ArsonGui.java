package peli1gamer.arsonclient.gui;
import net.minecraft.client.Minecraft;
public final class ArsonGui { private ArsonGui(){} public static void open(){Minecraft.getInstance().setScreen(new ArsonClientScreen());} }
