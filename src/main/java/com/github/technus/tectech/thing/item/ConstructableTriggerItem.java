package com.github.technus.tectech.thing.item;

import com.github.technus.tectech.util.CommonValues;
import cpw.mods.fml.common.registry.GameRegistry;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.world.World;
import space.impact.api.item.ItemConstructableTrigger;
import space.impact.api.multiblocks.alignment.constructable.ConstructableUtility;

import java.util.List;

import static com.github.technus.tectech.Reference.MODID;
import static com.github.technus.tectech.loader.gui.CreativeTabTecTech.creativeTabTecTech;
import static net.minecraft.util.StatCollector.translateToLocal;

/**
 * Created by Tec on 15.03.2017.
 */
public final class ConstructableTriggerItem extends Item {
	public static ConstructableTriggerItem INSTANCE;
	
	private ConstructableTriggerItem() {
		setUnlocalizedName("em.constructable");
		setTextureName(MODID + ":itemConstructable");
		setCreativeTab(creativeTabTecTech);
	}
	
	public static void run() {
		INSTANCE = new ConstructableTriggerItem();
		GameRegistry.registerItem(INSTANCE, INSTANCE.getUnlocalizedName());
	}
	
	@Override
	public boolean onItemUseFirst(ItemStack aStack, EntityPlayer aPlayer, World aWorld, int aX, int aY, int aZ, int aSide, float hitX, float hitY, float hitZ) {
        try {
			aStack = new ItemStack(new ItemConstructableTrigger(), aStack.stackSize, aStack.getItemDamage());
		} catch (Exception ignored){}
        return aStack.getItem() != this;
	}
	
	@Override
	public void addInformation(ItemStack aStack, EntityPlayer ep, List aList, boolean boo) {
		aList.add(CommonValues.TEC_MARK_GENERAL);
		aList.add(translateToLocal("item.em.constructable.desc.0"));//Triggers Constructable Interface
		aList.add(EnumChatFormatting.BLUE + translateToLocal("item.em.constructable.desc.1"));//Shows multiblock construction details,
		aList.add(EnumChatFormatting.BLUE + translateToLocal("item.em.constructable.desc.2"));//just Use on a multiblock controller.
		aList.add(EnumChatFormatting.BLUE + translateToLocal("item.em.constructable.desc.3"));//(Sneak Use in creative to build)
		aList.add(EnumChatFormatting.BLUE + translateToLocal("item.em.constructable.desc.4"));//Quantity affects tier/mode/type
	}
}
