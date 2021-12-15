package com.github.technus.tectech.thing.metaTileEntity.multi;

import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;
import space.impact.api.multiblocks.alignment.constructable.IConstructable;

@Deprecated
public class GT_MetaTileEntity_TM_proccessingStack extends GT_MetaTileEntity_MultiblockBase_EM implements IConstructable {
	
	protected GT_MetaTileEntity_TM_proccessingStack(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);
	}
	
	@Override
	public void construct(ItemStack stackSize, boolean hintsOnly) {
	
	}
	
	@Override
	public String[] getStructureDescription(ItemStack stackSize) {
		return new String[0];
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return null;
	}
}