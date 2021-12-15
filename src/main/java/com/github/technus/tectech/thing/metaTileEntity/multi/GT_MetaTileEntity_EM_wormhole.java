package com.github.technus.tectech.thing.metaTileEntity.multi;

import com.github.technus.tectech.thing.block.QuantumGlassBlock;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.render.TT_RenderedExtendedFacingTexture;
import com.github.technus.tectech.util.CommonValues;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.enums.Textures;
import gregtech.api.interfaces.ITexture;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import space.impact.api.multiblocks.alignment.constructable.IConstructable;
import space.impact.api.multiblocks.structure.IStructureDefinition;
import space.impact.api.multiblocks.structure.StructureDefinition;

import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.textureOffset;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.texturePage;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sBlockCasingsTT;
import static com.impact.util.multis.GT_StructureUtility.ofHatchAdderOptional;
import static net.minecraft.util.StatCollector.translateToLocal;
import static space.impact.api.multiblocks.structure.StructureUtility.ofBlock;
import static space.impact.api.multiblocks.structure.StructureUtility.transpose;

/**
 * Created by danie_000 on 17.12.2016.
 */
public class GT_MetaTileEntity_EM_wormhole extends GT_MetaTileEntity_MultiblockBase_EM implements IConstructable {
	//region structure
	private static final String[] description = new String[]{
			EnumChatFormatting.AQUA + translateToLocal("tt.keyphrase.Hint_Details") + ":",
			translateToLocal("gt.blockmachines.multimachine.em.wormhole.hint.0"),//1 - Classic Hatches or High Power Casing
			translateToLocal("gt.blockmachines.multimachine.em.wormhole.hint.1"),//2 - Elemental Hatches or Molecular Casing
	};
	private static final IStructureDefinition<GT_MetaTileEntity_EM_wormhole> STRUCTURE_DEFINITION =
			StructureDefinition.<GT_MetaTileEntity_EM_wormhole>builder()
					.addShape("main", transpose(new String[][]{
							{"         ", "         ", "         ", "         ", "         ", "    D    ", "  DDDDD  ", "  DGGGD  ", "  DGGGD  ", "  DGGGD  ", "  DDDDD  "},
							{"         ", "         ", "         ", "    D    ", "    D    ", "    D    ", " DDAAADD ", " EABBBAE ", " EABBBAE ", " EABBBAE ", " DDAAADD "},
							{"         ", "    D    ", "    D    ", "    D    ", "         ", "         ", "DDA   ADD", "DABFFFBAD", "DABCCCBAD", "DABFFFBAD", "DDA   ADD"},
							{"   EEE   ", "   DDD   ", "   F F   ", "         ", "         ", "         ", "DA     AD", "GBF   FBG", "GBC   CBG", "GBF   FBG", "DA     AD"},
							{"   E~E   ", "  DDBDD  ", "  D   D  ", " DD   DD ", " D     D ", "DD     DD", "DA     AD", "GBF   FBG", "GBC   CBG", "GBF   FBG", "DA     AD"},
							{"   EEE   ", "   DDD   ", "   F F   ", "         ", "         ", "         ", "DA     AD", "GBF   FBG", "GBC   CBG", "GBF   FBG", "DA     AD"},
							{"         ", "    D    ", "    D    ", "    D    ", "         ", "         ", "DDA   ADD", "DABFFFBAD", "DABCCCBAD", "DABFFFBAD", "DDA   ADD"},
							{"         ", "         ", "         ", "    D    ", "    D    ", "    D    ", " DDAAADD ", " EABBBAE ", " EABBBAE ", " EABBBAE ", " DDAAADD "},
							{"         ", "         ", "         ", "         ", "         ", "    D    ", "  DDDDD  ", "  DGGGD  ", "  DGGGD  ", "  DGGGD  ", "  DDDDD  "}
					}))
					.addElement('A', ofBlock(sBlockCasingsTT, 5))
					.addElement('B', ofBlock(sBlockCasingsTT, 10))
					.addElement('C', ofBlock(sBlockCasingsTT, 11))
					.addElement('D', ofBlock(sBlockCasingsTT, 12))
					.addElement('E', ofHatchAdderOptional(GT_MetaTileEntity_EM_wormhole::addClassicToMachineList, textureOffset, 1, sBlockCasingsTT, 0))
					.addElement('F', ofBlock(QuantumGlassBlock.INSTANCE, 0))
					.addElement('G', ofHatchAdderOptional(GT_MetaTileEntity_EM_wormhole::addElementalToMachineList, textureOffset + 4, 2, sBlockCasingsTT, 4))
					.build();
	//endregion
	//region variables
	private static Textures.BlockIcons.CustomIcon ScreenOFF;
	private static Textures.BlockIcons.CustomIcon ScreenON;
	//endregion
	
	public GT_MetaTileEntity_EM_wormhole(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);
	}
	
	public GT_MetaTileEntity_EM_wormhole(String aName) {
		super(aName);
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_EM_wormhole(mName);
	}
	
	@Override
	public boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
		return structureCheck_EM("main", 4, 4, 0);
	}
	
	@Override
	public String[] getDescription() {
		return new String[]{
				CommonValues.TEC_MARK_EM,
				translateToLocal("gt.blockmachines.multimachine.em.wormhole.desc.0"),//It is not full of worms.
				EnumChatFormatting.AQUA.toString() + EnumChatFormatting.BOLD + translateToLocal("gt.blockmachines.multimachine.em.wormhole.desc.1")//It is full of anti-worms!!!
		};
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister aBlockIconRegister) {
		ScreenOFF = new Textures.BlockIcons.CustomIcon("iconsets/EM_WH");
		ScreenON  = new Textures.BlockIcons.CustomIcon("iconsets/EM_WH_ACTIVE");
		super.registerIcons(aBlockIconRegister);
	}
	
	@Override
	public ITexture[] getTexture(IGregTechTileEntity aBaseMetaTileEntity, byte aSide, byte aFacing, byte aColorIndex, boolean aActive, boolean aRedstone) {
		if (aSide == aFacing) {
			return new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][4], new TT_RenderedExtendedFacingTexture(aActive ? ScreenON : ScreenOFF)};
		}
		return new ITexture[]{Textures.BlockIcons.casingTexturePages[texturePage][4]};
	}
	
	@Override
	public void construct(ItemStack stackSize, boolean hintsOnly) {
		structureBuild_EM("main", 4, 4, 0, hintsOnly, stackSize);
	}
	
	@Override
	public IStructureDefinition<GT_MetaTileEntity_EM_wormhole> getStructure_EM() {
		return STRUCTURE_DEFINITION;
	}
	
	@Override
	public String[] getStructureDescription(ItemStack stackSize) {
		return description;
	}
}