package com.github.technus.tectech.thing.metaTileEntity.multi;

import com.github.technus.tectech.Reference;
import com.github.technus.tectech.TecTech;
import com.github.technus.tectech.mechanics.elementalMatter.core.cElementalInstanceStackMap;
import com.github.technus.tectech.mechanics.elementalMatter.core.stacks.cElementalInstanceStack;
import com.github.technus.tectech.mechanics.elementalMatter.core.stacks.iHasElementalDefinition;
import com.github.technus.tectech.mechanics.elementalMatter.core.transformations.aFluidQuantizationInfo;
import com.github.technus.tectech.mechanics.elementalMatter.core.transformations.aItemQuantizationInfo;
import com.github.technus.tectech.mechanics.elementalMatter.core.transformations.aOredictQuantizationInfo;
import com.github.technus.tectech.mechanics.elementalMatter.core.transformations.bTransformationInfo;
import com.github.technus.tectech.thing.block.QuantumGlassBlock;
import com.github.technus.tectech.thing.metaTileEntity.multi.base.GT_MetaTileEntity_MultiblockBase_EM;
import com.github.technus.tectech.util.CommonValues;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import gregtech.api.GregTech_API;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;
import net.minecraftforge.oredict.OreDictionary;
import space.impact.api.multiblocks.alignment.constructable.IConstructable;
import space.impact.api.multiblocks.structure.IStructureDefinition;
import space.impact.api.multiblocks.structure.StructureDefinition;

import java.util.ArrayList;

import static com.github.technus.tectech.loader.TecTechConfig.DEBUG_MODE;
import static com.github.technus.tectech.mechanics.elementalMatter.core.templates.iElementalDefinition.DEFAULT_ENERGY_LEVEL;
import static com.github.technus.tectech.mechanics.elementalMatter.core.templates.iElementalDefinition.STABLE_RAW_LIFE_TIME;
import static com.github.technus.tectech.mechanics.elementalMatter.definitions.complex.dAtomDefinition.refMass;
import static com.github.technus.tectech.mechanics.elementalMatter.definitions.complex.dAtomDefinition.refUnstableMass;
import static com.github.technus.tectech.recipe.TT_recipeAdder.nullFluid;
import static com.github.technus.tectech.recipe.TT_recipeAdder.nullItem;
import static com.github.technus.tectech.thing.casing.GT_Block_CasingsTT.textureOffset;
import static com.github.technus.tectech.thing.casing.TT_Container_Casings.sBlockCasingsTT;
import static com.github.technus.tectech.util.CommonValues.V;
import static com.github.technus.tectech.util.Util.isInputEqual;
import static com.impact.util.multis.GT_StructureUtility.ofHatchAdder;
import static com.impact.util.multis.GT_StructureUtility.ofHatchAdderOptional;
import static net.minecraft.util.StatCollector.translateToLocal;
import static space.impact.api.multiblocks.structure.StructureUtility.ofBlock;
import static space.impact.api.multiblocks.structure.StructureUtility.transpose;

/**
 * Created by danie_000 on 17.12.2016.
 */
public class GT_MetaTileEntity_EM_quantizer extends GT_MetaTileEntity_MultiblockBase_EM implements IConstructable {
	public final static ResourceLocation activitySound = new ResourceLocation(Reference.MODID + ":fx_mid_freq");
	//region structure
	//use multi A energy inputs, use less power the longer it runs
	private static final IStructureDefinition<GT_MetaTileEntity_EM_quantizer> STRUCTURE_DEFINITION =
			StructureDefinition.<GT_MetaTileEntity_EM_quantizer>builder()
					.addShape("main", transpose(new String[][]{
							{"CCC", "BAB", "EEE", "DBD"},
							{"C~C", "ABA", "EBE", "BFB"},
							{"CCC", "BAB", "EEE", "DBD"}
					}))
					.addElement('A', ofBlock(sBlockCasingsTT, 0))
					.addElement('B', ofBlock(sBlockCasingsTT, 4))
					.addElement('C', ofHatchAdderOptional(GT_MetaTileEntity_EM_quantizer::addClassicToMachineList, textureOffset, 1, sBlockCasingsTT, 0))
					.addElement('D', ofBlock(QuantumGlassBlock.INSTANCE, 0))
					.addElement('E', ofHatchAdderOptional(GT_MetaTileEntity_EM_quantizer::addElementalMufflerToMachineList, textureOffset + 4, 3, sBlockCasingsTT, 4))
					.addElement('F', ofHatchAdder(GT_MetaTileEntity_EM_quantizer::addElementalOutputToMachineList, textureOffset + 4, 2))
					.build();
	//endregion
	private static final String[] description = new String[]{
			EnumChatFormatting.AQUA + translateToLocal("tt.keyphrase.Hint_Details") + ":",
			translateToLocal("gt.blockmachines.multimachine.em.mattertoem.hint.0"),//1 - Classic Hatches or High Power Casing
			translateToLocal("gt.blockmachines.multimachine.em.mattertoem.hint.1"),//2 - Elemental Output Hatch
			translateToLocal("gt.blockmachines.multimachine.em.mattertoem.hint.2"),//3 - Elemental Overflow Hatches or Molecular Casing
	};
	
	public GT_MetaTileEntity_EM_quantizer(int aID, String aName, String aNameRegional) {
		super(aID, aName, aNameRegional);
	}
	
	public GT_MetaTileEntity_EM_quantizer(String aName) {
		super(aName);
	}
	
	@Override
	public IMetaTileEntity newMetaEntity(IGregTechTileEntity aTileEntity) {
		return new GT_MetaTileEntity_EM_quantizer(mName);
	}
	
	@Override
	public boolean checkMachine_EM(IGregTechTileEntity iGregTechTileEntity, ItemStack itemStack) {
		return structureCheck_EM("main", 1, 1, 0);
	}
	
	@Override
	public boolean checkRecipe_EM(ItemStack itemStack) {//TODO implement instance quantization
		if (GregTech_API.sPostloadFinished) {
			ArrayList<ItemStack> storedInputs = getStoredInputs();
			ItemStack[] inI = storedInputs.toArray(nullItem);
			if (inI.length > 0) {
				for (ItemStack is : inI) {
					//ITEM STACK quantization
					aItemQuantizationInfo aIQI = bTransformationInfo.itemQuantization.get(new aItemQuantizationInfo(is, false, null));
					if (aIQI == null) {
						aIQI = bTransformationInfo.itemQuantization.get(new aItemQuantizationInfo(is, true, null));//todo check if works?
					}
					if (aIQI == null) {
						//ORE DICT quantization //todo fix for uranium?
						int[] oreIDs = OreDictionary.getOreIDs(is);
						for (int ID : oreIDs) {
							if (DEBUG_MODE) {
								TecTech.LOGGER.info("Quantifier-Ore-recipe " + is.getItem().getUnlocalizedName() + '.' + is.getItemDamage() + ' ' + OreDictionary.getOreName(ID));
							}
							aOredictQuantizationInfo aOQI = bTransformationInfo.oredictQuantization.get(ID);
							if (aOQI == null) {
								continue;
							}
							iHasElementalDefinition into = aOQI.output();
							if (into != null && isInputEqual(true, false, nullFluid, new ItemStack[]{new ItemStack(is.getItem(), aOQI.amount, is.getItemDamage())}, null, inI)) {
								startRecipe(into);
								return true;
							}
						}
					} else {
						//Do ITEM STACK quantization
						if (DEBUG_MODE) {
							TecTech.LOGGER.info("Quantifier-Item-recipe " + is.getItem().getUnlocalizedName() + '.' + is.getItemDamage());
						}
						iHasElementalDefinition into = aIQI.output();
						if (into != null && isInputEqual(true, false, nullFluid, new ItemStack[]{new ItemStack(is.getItem(), aIQI.input().stackSize, is.getItemDamage())}, null, inI)) {
							startRecipe(into);
							return true;
						}
					}
				}
			}
			ArrayList<FluidStack> storedFluids = getStoredFluids();
			FluidStack[] inF = storedFluids.toArray(nullFluid);
			if (inF.length > 0) {
				for (FluidStack fs : inF) {
					aFluidQuantizationInfo aFQI = bTransformationInfo.fluidQuantization.get(fs.getFluid().getID());
					if (aFQI == null) {
						continue;
					}
					iHasElementalDefinition into = aFQI.output();
					if (into != null && fs.amount >= aFQI.input().amount && isInputEqual(true, false,
							new FluidStack[]{aFQI.input()}, nullItem, inF, (ItemStack[]) null
					)) {
						startRecipe(into);
						return true;
					}
				}
			}
		}
		return false;
	}
	
	private void startRecipe(iHasElementalDefinition into) {
		mMaxProgresstime = 20;
		mEfficiencyIncrease = 10000;
		double mass = into.getMass();
		double euMult = Math.abs(mass / refMass);
		eAmpereFlow = (int) Math.ceil(Math.sqrt(Math.sqrt(euMult)));
		if (mass > refUnstableMass || into.getDefinition().getRawTimeSpan(DEFAULT_ENERGY_LEVEL) < STABLE_RAW_LIFE_TIME) {
			mEUt = (int) -V[8];
		} else {
			mEUt = (int) -V[6];
		}
		outputEM = new cElementalInstanceStackMap[]{
				into instanceof cElementalInstanceStack ?
						new cElementalInstanceStackMap((cElementalInstanceStack) into) :
						new cElementalInstanceStackMap(new cElementalInstanceStack(into.getDefinition(), into.getAmount()))
		};
	}
	
	@Override
	public void outputAfterRecipe_EM() {
		if (eOutputHatches.size() < 1) {
			stopMachine();
			return;
		}
		eOutputHatches.get(0).getContainerHandler().putUnifyAll(outputEM[0]);
		outputEM = null;
	}
	
	@Override
	public String[] getDescription() {
		return new String[]{
				CommonValues.TEC_MARK_EM,
				translateToLocal("gt.blockmachines.multimachine.em.mattertoem.desc.0"),//Conveniently convert regular stuff into quantum form.
				EnumChatFormatting.AQUA.toString() + EnumChatFormatting.BOLD + translateToLocal("gt.blockmachines.multimachine.em.mattertoem.desc.1")//To make it more inconvenient.
		};
	}
	
	@Override
	@SideOnly(Side.CLIENT)
	protected ResourceLocation getActivitySound() {
		return activitySound;
	}
	
	@Override
	public void construct(ItemStack stackSize, boolean hintsOnly) {
		structureBuild_EM("main", 1, 1, 0, hintsOnly, stackSize);
	}
	
	@Override
	public IStructureDefinition<GT_MetaTileEntity_EM_quantizer> getStructure_EM() {
		return STRUCTURE_DEFINITION;
	}
	
	@Override
	public String[] getStructureDescription(ItemStack stackSize) {
		return description;
	}
}