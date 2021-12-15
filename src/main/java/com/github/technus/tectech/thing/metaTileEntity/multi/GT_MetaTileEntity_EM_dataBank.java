package com.github.technus.tectech.thing.metaTileEntity.multi;

import com.github.technus.tectech.Reference;
import com.github.technus.tectech.mechanics.dataTransport.InventoryDataPacket;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_InputDataItems;
import com.github.technus.tectech.thing.metaTileEntity.hatch.GT_MetaTileEntity_Hatch_OutputDataItems;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_Container_MultiMachineEM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_GUIContainer_MultiMachineEM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.github.technus.tectech.util.CommonValues;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch;
import gregtech.api.metatileentity.implementations.GT_MetaTileEntity_Hatch_DataAccess;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import space.impact.api.multiblocks.alignment.constructable.IConstructable;
import space.impact.api.multiblocks.structure.IStructureDefinition;
import space.impact.api.multiblocks.structure.StructureDefinition;

import java.util.ArrayList;

import static com.github.technus.tectech.recipe.TT_recipeAdder.nullItem;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.textureOffset;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.texturePage;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sBlockCasingsTT;
import static com.github.technus.tectech.util.CommonValues.V;
import static com.impact.util.multis.GT_StructureUtility.ofHatchAdderOptional;
import static net.minecraft.util.StatCollector.translateToLocal;
import static space.impact.api.multiblocks.structure.StructureUtility.ofBlock;
import static space.impact.api.multiblocks.structure.StructureUtility.transpose;

public class GT_MetaTileEntity_EM_dataBank extends GT_MetaTileEntity_MultiblockBase_EM implements IConstructable {
	public final static ResourceLocation activitySound = new ResourceLocation(Reference.MODID + ":fx_hi_freq");
	//region structure
	private static final String[] description = new String[]{
			EnumChatFormatting.AQUA + translateToLocal("tt.keyphrase.Hint_Details") + ":",
			translateToLocal("gt.blockmachines.multimachine.em.databank.hint.0"),//1 - Classic Hatches or high power casing
			translateToLocal("gt.blockmachines.multimachine.em.databank.hint.1"),//2 - Data Access/Data Bank Master Hatches or computer casing
	};
	//endregion
	private static final IStructureDefinition<GT_MetaTileEntity_EM_dataBank> STRUCTURE_DEFINITION =
			StructureDefinition.<GT_MetaTileEntity_EM_dataBank>builder()
					.addShape("main", transpose(new String[][]{
							{"BCCCB", "BDDDB", "BDDDB"},
							{"BC~CB", "BAAAB", "BDDDB"},
							{"BCCCB", "BDDDB", "BDDDB"}
					}))
					.addElement('A', ofBlock(sBlockCasingsTT, 1))
					.addElement('B', ofBlock(sBlockCasingsTT, 2))
					.addElement('C', ofHatchAdderOptional(GT_MetaTileEntity_EM_dataBank::addClassicToMachineList, textureOffset, 1, sBlockCasingsTT, 0))
					.addElement('D', ofHatchAdderOptional(GT_MetaTileEntity_EM_dataBank::addDataBankHatchToMachineList, textureOffset + 1, 2, sBlockCasingsTT, 1))
					.build();
	//region variables
	private final ArrayList<GT_MetaTileEntity_Hatch_OutputDataItems> eStacksDataOutputs = new ArrayList<>();
	//endregion
	private final ArrayList<GT_MetaTileEntity_Hatch_DataAccess> eDataAccessHatches = new ArrayList<>();
	
	public GT_MetaTileEntity_EM_dataBank(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);
	}
	
	public GT_MetaTileEntity_EM_dataBank(String aName) {
		super(aName);
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_EM_dataBank(mName);
	}
	
	@Override
	public boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
		eDataAccessHatches.clear();
		eStacksDataOutputs.clear();
		return structureCheck_EM("main", 2, 1, 0);
	}
	
	@Override
	public boolean checkRecipe_EM(ItemStack itemStack) {
		if (eDataAccessHatches.size() > 0 && eStacksDataOutputs.size() > 0) {
			mEUt                = -(int) V[4];
			eAmpereFlow         = 1 + eStacksDataOutputs.size() * eDataAccessHatches.size();
			mMaxProgresstime    = 20;
			mEfficiencyIncrease = 10000;
			return true;
		}
		return false;
	}
	
	@Override
	public void outputAfterRecipe_EM() {
		ArrayList<ItemStack> stacks = new ArrayList<>();
		for (GT_MetaTileEntity_Hatch_DataAccess dataAccess : eDataAccessHatches) {
			int count = dataAccess.getSizeInventory();
			for (int i = 0; i < count; i++) {
				ItemStack stack = dataAccess.getStackInSlot(i);
				if (stack != null) {
					stacks.add(stack);
				}
			}
		}
		if (stacks.size() > 0) {
			ItemStack[] arr = stacks.toArray(nullItem);
			for (GT_MetaTileEntity_Hatch_OutputDataItems hatch : eStacksDataOutputs) {
				hatch.q = new InventoryDataPacket(arr);
			}
		} else {
			for (GT_MetaTileEntity_Hatch_OutputDataItems hatch : eStacksDataOutputs) {
				hatch.q = null;
			}
		}
	}
	
	@Override
	public String[] getDescription() {
		return new String[]{
				CommonValues.TEC_MARK_EM,
				translateToLocal("gt.blockmachines.multimachine.em.databank.desc.0"),//Remote assembly data delivery
				EnumChatFormatting.AQUA.toString() + EnumChatFormatting.BOLD + translateToLocal("gt.blockmachines.multimachine.em.databank.desc.1")//Apply directly to the forehead
		};
	}
	
	@Override
	public Object getServerGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
		return new GT_Container_MultiMachineEM(aPlayerInventory, aBaseMetaTileEntity, true, false, true);
	}
	
	@Override
	public Object getClientGUI(int aID, InventoryPlayer aPlayerInventory, IGregTechTileEntity aBaseMetaTileEntity) {
		return new GT_GUIContainer_MultiMachineEM(aPlayerInventory, aBaseMetaTileEntity, getLocalName(), "EMDisplay.png", true, false, true);//todo texture
	}
	
	@Override
	public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			return new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][1], new TT_RenderedExtendedFacingTexture(aActive ? GT_MetaTileEntity_MultiblockBase_EM.ScreenON : GT_MetaTileEntity_MultiblockBase_EM.ScreenOFF)};
		}
		return new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][1]};
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected ResourceLocation getActivitySound() {
		return activitySound;
	}
	
	public final boolean addDataBankHatchToMachineList(IGregTechTileEntity aTileEntity, int aBaseCasingIndex) {
		if (aTileEntity == null) {
			return false;
		}
		IMetaTileEntity aMetaTileEntity = aTileEntity.getMetaTileEntity();
		if (aMetaTileEntity == null) {
			return false;
		}
		if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_OutputDataItems) {
			((GT_MetaTileEntity_Hatch) aMetaTileEntity).updateTexture(aBaseCasingIndex);
			return eStacksDataOutputs.add((GT_MetaTileEntity_Hatch_OutputDataItems) aMetaTileEntity);
		} else if (aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_DataAccess && !(aMetaTileEntity instanceof GT_MetaTileEntity_Hatch_InputDataItems)) {
			((GT_MetaTileEntity_Hatch) aMetaTileEntity).updateTexture(aBaseCasingIndex);
			return eDataAccessHatches.add((GT_MetaTileEntity_Hatch_DataAccess) aMetaTileEntity);
		}
		return false;
	}
	
	@Override
	public void construct(ItemStack stackSize, boolean hintsOnly) {
		structureBuild_EM("main", 2, 1, 0, hintsOnly, stackSize);
	}
	
	@Override
	public IStructureDefinition<GT_MetaTileEntity_EM_dataBank> getStructure_EM() {
		return STRUCTURE_DEFINITION;
	}
	
	@Override
	public String[] getStructureDescription(ItemStack stackSize) {
		return description;
	}
}