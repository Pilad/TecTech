package com.github.technus.tectech.compatibility.spartakcore;

import com.github.technus.tectech.recipe.TT_recipeAdder;
import com.github.technus.tectech.thing.CustomItemList;
import com.github.technus.tectech.thing.block.QuantumGlassBlock;
import com.github.technus.tectech.thing.metaTileEntity.multi.em_machine.Behaviour_Centrifuge;
import com.github.technus.tectech.thing.metaTileEntity.multi.em_machine.Behaviour_ElectromagneticSeparator;
import com.github.technus.tectech.thing.metaTileEntity.multi.em_machine.Behaviour_Recycler;
import com.github.technus.tectech.thing.metaTileEntity.multi.em_machine.GT_MetaTileEntity_EM_machine;
import com.impact.common.item.Core_Items2;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.ItemList;
import gregtech.api.enums.Materials;
import gregtech.api.enums.OrePrefixes;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_OreDictUnificator;
import gregtech.api.util.GT_Utility;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fluids.FluidRegistry;
import net.minecraftforge.fluids.FluidStack;

import static com.github.technus.tectech.loader.recipe.RecipeLoader.getOrDefault;

/**
 * Created by Spartak1997 on 28.07.2019.
 */
public class SpartakCoreRecipeLoader implements Runnable {

    final Core_Items2 CoreItems2 = Core_Items2.getInstance();

    @Override
    public void run() {
        
      //Quantum Glass
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Containment.get(1),
                GT_ModHandler.getIC2Item("reinforcedGlass", 1L)
        }, Materials.Osmium.getMolten(576), new ItemStack(QuantumGlassBlock.INSTANCE, 1), 200, 500000);

        //region pipes

        //Data
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                ItemList.Circuit_Parts_GlassFiber.get(8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silver, 8)
        }, Materials.Polytetrafluoroethylene.getMolten(144), CustomItemList.DATApipe.get(1), 200, 30720);

        //endregion

        //Tunnel
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.DATApipe.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Osmiridium, 4),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Osmium, 4),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Superconductor, 2),
                ItemList.Field_Generator_MV.get(1),
                ItemList.Circuit_Quantummainframe.get(1)
        }, Materials.Osmium.getMolten(288), CustomItemList.EMpipe.get(1), 400, 500000);
        
        //Laser
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.DATApipe.get(1),
                GT_ModHandler.getIC2Item("reinforcedGlass", 1L),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Osmiridium, 2)
        }, null, CustomItemList.LASERpipe.get(1), 100, 30720);
        
        //endregoin

        //region casing

        //High Power Casing
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Iridium, 1),
                GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Iridium, 6),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 1),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Cobalt, 16),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Copper, 16),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.NiobiumTitanium, 2)
        }, Materials.TungstenSteel.getMolten(576), CustomItemList.eM_Power.get(1), 100, 30720);

        //Computer Casing
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Power.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 8),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Ultimate, 1),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.NiobiumTitanium, 2)
        }, Materials.Aluminium.getMolten(1296), CustomItemList.eM_Computer_Casing.get(1), 200, 122880);
        //Computer Vent Casing
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.StainlessSteel, 1),
                ItemList.Electric_Motor_IV.get(2),
                GT_OreDictUnificator.get(OrePrefixes.rotor, Materials.StainlessSteel, 2),
                GT_OreDictUnificator.get(OrePrefixes.pipeTiny, Materials.StainlessSteel, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Copper, 16),
                GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.SuperconductorIV, 1)
        }, Materials.SolderingAlloy.getMolten(1296), CustomItemList.eM_Computer_Vent.get(1), 100, 1920);
        //Advanced Computer Casing
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Ultimate, 1),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Cobalt, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Electrum, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorLuV, 4)
        }, Materials.Iridium.getMolten(1296), CustomItemList.eM_Computer_Bus.get(1), 200, 122880);

        //Molecular Casing
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Power.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Osmiridium, 6),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Trinium, 12),
                GT_OreDictUnificator.get(OrePrefixes.screw, Materials.TungstenSteel, 24),
                GT_OreDictUnificator.get(OrePrefixes.ring, Materials.TungstenSteel, 24),
                ItemList.Field_Generator_IV.get(1)
        }, Materials.Osmium.getMolten(1296), CustomItemList.eM_Containment.get(1), 800, 500000);

        //Hollow Casing
        TT_recipeAdder.addResearchableAssemblylineRecipe(CustomItemList.eM_Containment.get(1),
                12000,32, 500000, 6, new ItemStack[]{
                        CustomItemList.eM_Containment.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Neutronium, 2),
                        GT_OreDictUnificator.get(OrePrefixes.plateQuadruple, Materials.Plutonium, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateDouble, Materials.Lead, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plate, Materials.BlackPlutonium, 16),
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.Quantium, 16),
                }, new FluidStack[]{
                        Materials.Trinium.getMolten(1296),
                        Materials.Osmium.getMolten(1296),
                        new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                        Materials.Argon.getGas(1000),
                }, CustomItemList.eM_Hollow.get(2), 200, 2000000);

        //EM Coil
        TT_recipeAdder.addResearchableAssemblylineRecipe(CustomItemList.eM_Hollow.get(1),
                48000,128, 1000000, 16, new ItemStack[]{
                        CustomItemList.eM_Hollow.get(1),
                        ItemList.Casing_Fusion_Coil.get(4),
                        ItemList.Casing_Coil_NaquadahAlloy.get( 4),
                        GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Neutronium, 64),
                        GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Neutronium, 64),
                }, new FluidStack[]{
                        Materials.Glass.getMolten(2304),
                        Materials.Silicone.getMolten(1872),
                        new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                        Materials.Trinium.getMolten(1296),
                }, CustomItemList.eM_Coil.get(4), 800, 2000000);

        //Tesla Base
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 6),
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.NickelZincFerrite, 1)
        }, null, CustomItemList.tM_TeslaBase.get(1), 50, 16);
        GT_ModHandler.addCraftingRecipe(CustomItemList.tM_TeslaBase.get(1),
                GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE,
                new Object[]{"PhP", "PFP", "PwP",
                        'P', OrePrefixes.plate.get(Materials.NickelZincFerrite),
                        'F', OrePrefixes.frameGt.get(Materials.NickelZincFerrite)});
        //Tesla Toroid
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 6),
                GT_OreDictUnificator.get(OrePrefixes.frameGt, Materials.Aluminium, 1)
        }, null, CustomItemList.tM_TeslaToroid.get(1), 50, 16);
        GT_ModHandler.addCraftingRecipe(CustomItemList.tM_TeslaToroid.get(1),
                GT_ModHandler.RecipeBits.BUFFERED | GT_ModHandler.RecipeBits.NOT_REMOVABLE,
                new Object[]{"PhP", "PFP", "PwP",
                        'P', OrePrefixes.foil.get(Materials.Aluminium),
                        'F', OrePrefixes.frameGt.get(Materials.Aluminium)});
        //Tesla Secondary Windings
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(8, 0),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Tin, 12)
        }, Materials.Silver.getMolten(144), CustomItemList.tM_TeslaSecondary.get(1), 200, 120);
        //Tesla Primary Coils T0
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.RedstoneAlloy, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Copper, 8)
        }, Materials.RedAlloy.getMolten(144), CustomItemList.tM_TeslaPrimary_0.get(1), 200, 30);
        //Tesla Primary Coils T1
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorMV, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silver, 12)
        }, Materials.Magnesium.getMolten(144), CustomItemList.tM_TeslaPrimary_1.get(1), 200, 120);
        //Tesla Primary Coils T2
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorHV, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Gold, 16)
        }, Materials.Yttrium.getMolten(144), CustomItemList.tM_TeslaPrimary_2.get(1), 200, 480);
        //Tesla Primary Coils T3
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorEV, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 20)
        }, Materials.Platinum.getMolten(144), CustomItemList.tM_TeslaPrimary_3.get(1), 200, 1920);
        //Tesla Primary Coils T4
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorIV, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Platinum, 24)
        }, Materials.Vanadium.getMolten(144), CustomItemList.tM_TeslaPrimary_4.get(1), 200, 7680);
        //Tesla Primary Coils T5
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorLuV, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Osmium, 28)
        }, Materials.Indium.getMolten(144), CustomItemList.tM_TeslaPrimary_5.get(1), 50, 30720);
        //Tesla Primary Coils T6
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.SuperconductorZPM, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.NaquadahAlloy, 32)
        }, Materials.Naquadah.getMolten(144), CustomItemList.tM_TeslaPrimary_6.get(1), 200, 122880);

        //endregion


        //region hatches

        //Buck Converter IV-UIV
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_LuV_IV.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Elite, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.HastelloyC276, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.TungstenSteel, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.TungstenSteel, 16)}, null,
                CustomItemList.Machine_BuckConverter_IV.get(1), 100, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_ZPM_LuV.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.HastelloyN, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.NiobiumTitanium, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Chrome, 16)}, null,
                CustomItemList.Machine_BuckConverter_LuV.get(1), 100, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_UV_ZPM.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Ultimate, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Lafium, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Naquadah, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Iridium, 16)}, null,
                CustomItemList.Machine_BuckConverter_ZPM.get(1), 100, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_MAX_UV.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Superconductor, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.CinobiteA243, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.NaquadahAlloy, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Osmium, 16)}, null,
                CustomItemList.Machine_BuckConverter_UV.get(1), 100, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_UEV_UHV.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Infinite, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Pikyonium64B, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Europium, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Neutronium, 16)}, null,
                CustomItemList.Machine_BuckConverter_UHV.get(1), 100, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Transformer_UIV_UEV.get(1),
                CoreItems2.getRecipe(182, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Bio, 2),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.Phoenixite, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Diamericiumtitanium, 4),
                GT_OreDictUnificator.get(OrePrefixes.bolt, Materials.Phoenixite, 16)}, null,
                CustomItemList.Machine_BuckConverter_UEV.get(1), 100, 8000000);

        //Laser Dynamo IV-UV 256/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Emitter_IV.get(1), ItemList.Electric_Pump_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.TungstenSteel, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_dynamoTunnel1_IV.get(1), 1000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Emitter_LuV.get(1), ItemList.Electric_Pump_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.VanadiumGallium, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_dynamoTunnel1_LuV.get(1), 1000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Emitter_ZPM.get(1), ItemList.Electric_Pump_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Naquadah, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_dynamoTunnel1_ZPM.get(1), 1000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Emitter_UV.get(1), ItemList.Electric_Pump_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.NaquadahAlloy, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_dynamoTunnel1_UV.get(1), 1000, 500000);
        
        //Laser Dynamo IV-UV 1024/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Emitter_IV.get(2), ItemList.Electric_Pump_IV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.TungstenSteel, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_dynamoTunnel2_IV.get(1), 2000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Emitter_LuV.get(2), ItemList.Electric_Pump_LuV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.VanadiumGallium, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_dynamoTunnel2_LuV.get(1), 2000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Emitter_ZPM.get(2), ItemList.Electric_Pump_ZPM.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Naquadah, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_dynamoTunnel2_ZPM.get(1), 2000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Emitter_UV.get(2), ItemList.Electric_Pump_UV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.NaquadahAlloy, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_dynamoTunnel2_UV.get(1), 2000, 500000);
        
        //Laser Dynamo IV-UV 4096/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Emitter_IV.get(4), ItemList.Electric_Pump_IV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_dynamoTunnel3_IV.get(1), 4000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Emitter_LuV.get(4), ItemList.Electric_Pump_LuV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_dynamoTunnel3_LuV.get(1), 4000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Emitter_ZPM.get(4), ItemList.Electric_Pump_ZPM.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_dynamoTunnel3_ZPM.get(1), 4000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Emitter_UV.get(4), ItemList.Electric_Pump_UV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_dynamoTunnel3_UV.get(1), 4000, 500000);
        
        //Laser Dynamo IV-UV 16384/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Emitter_IV.get(8), ItemList.Electric_Pump_IV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_dynamoTunnel4_IV.get(1), 8000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Emitter_LuV.get(8), ItemList.Electric_Pump_LuV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_dynamoTunnel4_LuV.get(1), 8000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Emitter_ZPM.get(8), ItemList.Electric_Pump_ZPM.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_dynamoTunnel4_ZPM.get(1), 8000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Emitter_UV.get(8), ItemList.Electric_Pump_UV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_dynamoTunnel4_UV.get(1), 8000, 500000);
        
        //Laser Dynamo IV-UV 65536/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Emitter_IV.get(16), ItemList.Electric_Pump_IV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.TungstenSteel, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_dynamoTunnel5_IV.get(1), 16000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Emitter_LuV.get(16), ItemList.Electric_Pump_LuV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_dynamoTunnel5_LuV.get(1), 16000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Emitter_ZPM.get(16), ItemList.Electric_Pump_ZPM.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_dynamoTunnel5_ZPM.get(1), 16000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Emitter_UV.get(16), ItemList.Electric_Pump_UV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.NaquadahAlloy, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_dynamoTunnel5_UV.get(1), 16000, 500000);
        
        //Laser Dynamo IV-UV 262144/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Emitter_IV.get(32), ItemList.Electric_Pump_IV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.TungstenSteel, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_dynamoTunnel6_IV.get(1), 32000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Emitter_LuV.get(32), ItemList.Electric_Pump_LuV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_dynamoTunnel6_LuV.get(1), 32000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Emitter_ZPM.get(32), ItemList.Electric_Pump_ZPM.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_dynamoTunnel6_ZPM.get(1), 32000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Emitter_UV.get(32), ItemList.Electric_Pump_UV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.NaquadahAlloy, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_dynamoTunnel6_UV.get(1), 32000, 500000);
        
        //Laser Dynamo IV-UV 1048576/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Emitter_IV.get(64), ItemList.Electric_Pump_IV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.TungstenSteel, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_dynamoTunnel7_IV.get(1), 64000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Emitter_LuV.get(64), ItemList.Electric_Pump_LuV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.VanadiumGallium, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_dynamoTunnel7_LuV.get(1), 64000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Emitter_ZPM.get(64), ItemList.Electric_Pump_ZPM.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Naquadah, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_dynamoTunnel7_ZPM.get(1), 64000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Emitter_UV.get(64), ItemList.Electric_Pump_UV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.NaquadahAlloy, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_dynamoTunnel7_UV.get(1), 64000, 500000);
        
        //Laser Target IV-UV 256/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Sensor_IV.get(1), ItemList.Electric_Pump_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.TungstenSteel, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_energyTunnel1_IV.get(1), 1000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Sensor_LuV.get(1), ItemList.Electric_Pump_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.VanadiumGallium, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_energyTunnel1_LuV.get(1), 1000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Sensor_ZPM.get(1), ItemList.Electric_Pump_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.Naquadah, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_energyTunnel1_ZPM.get(1), 1000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 1), ItemList.Sensor_UV.get(1), ItemList.Electric_Pump_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.NaquadahAlloy, 2), GT_Utility.getIntegratedCircuit(1)}, null, CustomItemList.eM_energyTunnel1_UV.get(1), 1000, 500000);
        
        //Laser Target IV-UV 1024/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Sensor_IV.get(2), ItemList.Electric_Pump_IV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.TungstenSteel, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_energyTunnel2_IV.get(1), 2000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Sensor_LuV.get(2), ItemList.Electric_Pump_LuV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.VanadiumGallium, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_energyTunnel2_LuV.get(1), 2000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Sensor_ZPM.get(2), ItemList.Electric_Pump_ZPM.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Naquadah, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_energyTunnel2_ZPM.get(1), 2000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 2), ItemList.Sensor_UV.get(2), ItemList.Electric_Pump_UV.get(2), GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.NaquadahAlloy, 4), GT_Utility.getIntegratedCircuit(2)}, null, CustomItemList.eM_energyTunnel2_UV.get(1), 2000, 500000);
        
        //Laser Target IV-UV 4096/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Sensor_IV.get(4), ItemList.Electric_Pump_IV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_energyTunnel3_IV.get(1), 4000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Sensor_LuV.get(4), ItemList.Electric_Pump_LuV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_energyTunnel3_LuV.get(1), 4000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Sensor_ZPM.get(4), ItemList.Electric_Pump_ZPM.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_energyTunnel3_ZPM.get(1), 4000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 4), ItemList.Sensor_UV.get(4), ItemList.Electric_Pump_UV.get(4), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 4), GT_Utility.getIntegratedCircuit(3)}, null, CustomItemList.eM_energyTunnel3_UV.get(1), 4000, 500000);
        
        //Laser Target IV-UV 16384/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Sensor_IV.get(8), ItemList.Electric_Pump_IV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_energyTunnel4_IV.get(1), 8000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Sensor_LuV.get(8), ItemList.Electric_Pump_LuV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_energyTunnel4_LuV.get(1), 8000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Sensor_ZPM.get(8), ItemList.Electric_Pump_ZPM.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_energyTunnel4_ZPM.get(1), 8000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 8), ItemList.Sensor_UV.get(8), ItemList.Electric_Pump_UV.get(8), GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.NaquadahAlloy, 8), GT_Utility.getIntegratedCircuit(4)}, null, CustomItemList.eM_energyTunnel4_UV.get(1), 8000, 500000);
        
        //Laser Target IV-UV 65536/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Sensor_IV.get(16), ItemList.Electric_Pump_IV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.TungstenSteel, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_energyTunnel5_IV.get(1), 16000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Sensor_LuV.get(16), ItemList.Electric_Pump_LuV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_energyTunnel5_LuV.get(1), 16000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Sensor_ZPM.get(16), ItemList.Electric_Pump_ZPM.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_energyTunnel5_ZPM.get(1), 16000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 16), ItemList.Sensor_UV.get(16), ItemList.Electric_Pump_UV.get(16), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.NaquadahAlloy, 8), GT_Utility.getIntegratedCircuit(5)}, null, CustomItemList.eM_energyTunnel5_UV.get(1), 16000, 500000);
        
        //Laser Target IV-UV 262144/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Sensor_IV.get(32), ItemList.Electric_Pump_IV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.TungstenSteel, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_energyTunnel6_IV.get(1), 32000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Sensor_LuV.get(32), ItemList.Electric_Pump_LuV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_energyTunnel6_LuV.get(1), 32000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Sensor_ZPM.get(32), ItemList.Electric_Pump_ZPM.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_energyTunnel6_ZPM.get(1), 32000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 32), ItemList.Sensor_UV.get(32), ItemList.Electric_Pump_UV.get(32), GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.NaquadahAlloy, 16), GT_Utility.getIntegratedCircuit(6)}, null, CustomItemList.eM_energyTunnel6_UV.get(1), 32000, 500000);
        
        //Laser Target IV-UV 1048576/t
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_IV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Sensor_IV.get(64), ItemList.Electric_Pump_IV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.TungstenSteel, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_energyTunnel7_IV.get(1), 64000, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_LuV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Sensor_LuV.get(64), ItemList.Electric_Pump_LuV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.VanadiumGallium, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_energyTunnel7_LuV.get(1), 64000, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_ZPM.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Sensor_ZPM.get(64), ItemList.Electric_Pump_ZPM.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Naquadah, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_energyTunnel7_ZPM.get(1), 64000, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{ItemList.Hull_UV.get(1), GT_OreDictUnificator.get(OrePrefixes.lens, Materials.Diamond, 64), ItemList.Sensor_UV.get(64), ItemList.Electric_Pump_UV.get(64), GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.NaquadahAlloy, 16), GT_Utility.getIntegratedCircuit(7)}, null, CustomItemList.eM_energyTunnel7_UV.get(1), 64000, 500000);

        //Data Input
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Hatch_Input_Bus_LuV.get(1),
                ItemList.Circuit_Crystalcomputer.get(1),
                CustomItemList.DATApipe.get(2)
        }, Materials.Iridium.getMolten(1296), CustomItemList.dataIn_Hatch.get(1), 200, 122880);
        //Data Output
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Hatch_Output_Bus_LuV.get(1),
                ItemList.Circuit_Crystalcomputer.get(1),
                CustomItemList.DATApipe.get(2)
        }, Materials.Iridium.getMolten(1296), CustomItemList.dataOut_Hatch.get(1), 200, 122880);

        //Rack
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Bus.get(1),
                ItemList.Hatch_Input_Bus_ZPM.get(1),
                ItemList.Circuit_Crystalcomputer.get(2),
                CustomItemList.DATApipe.get(4)
        }, Materials.Iridium.getMolten(1296), CustomItemList.rack_Hatch.get(1), 800, 122880);

        //Object Holder
        GT_Values.RA.addAssemblylineRecipe(ItemList.Hatch_Input_Bus_ZPM.get(1), 10000, new Object[]{
                ItemList.Hatch_Input_Bus_ZPM.get(1),
                CustomItemList.eM_Computer_Bus.get(1),
                ItemList.Emitter_ZPM.get(8),
                ItemList.Robot_Arm_ZPM.get(1),
                ItemList.Electric_Motor_ZPM.get(2),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 1},
                new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Naquadah, 2)},
                new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Naquadah, 16)},
                CustomItemList.DATApipe.get(2),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(500),
                Materials.Iridium.getMolten(1000),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 1000)
        }, CustomItemList.holder_Hatch.get(1), 1200, 100000);

        //Parameterizer
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Circuit_Masterquantumcomputer.get(1),
                CustomItemList.DATApipe.get(4),
                ItemList.Cover_Screen.get(1),
                new ItemStack(Blocks.stone_button, 16),
                GT_Utility.getIntegratedCircuit(1),
        }, Materials.Iridium.getMolten(2592), CustomItemList.Parametrizer_Hatch.get(1), 800, 122880);
		
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Circuit_Ultimatecrystalcomputer.get(1),
                CustomItemList.DATApipe.get(6),
                ItemList.Cover_Screen.get(1),
                new ItemStack(Blocks.stone_button, 32),
                GT_Utility.getIntegratedCircuit(2),
        }, Materials.Iridium.getMolten(2592), CustomItemList.ParametrizerX_Hatch.get(1), 1000, 500000);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Circuit_Biomainframe.get(1),
                CustomItemList.DATApipe.get(8),
                ItemList.Cover_Screen.get(2),
                new ItemStack(Blocks.stone_button, 64),
                GT_Utility.getIntegratedCircuit(3),
        }, Materials.Iridium.getMolten(2592), CustomItemList.ParametrizerTXT_Hatch.get(1), 1200, 2000000);
		
        //Uncertainty
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Circuit_Ultimatecrystalcomputer.get(1),
                CustomItemList.DATApipe.get(16),
                ItemList.Cover_Screen.get(1),
                new ItemStack(Blocks.stone_button, 16),
                GT_Utility.getIntegratedCircuit(4),
        }, Materials.Iridium.getMolten(2592), CustomItemList.Uncertainty_Hatch.get(1), 1200, 122880, true);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Computer_Casing.get(1),
                ItemList.Circuit_Biomainframe.get(1),
                CustomItemList.DATApipe.get(32),
                ItemList.Cover_Screen.get(1),
                new ItemStack(Blocks.stone_button, 16),
                GT_Utility.getIntegratedCircuit(5),
        }, Materials.Iridium.getMolten(2592), CustomItemList.UncertaintyX_Hatch.get(1), 1400, 500000, true);
		
        //Elemental Input
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Containment.get(1),
                ItemList.Hatch_Input_UV.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Naquadah, 2),
                ItemList.Sensor_UV.get(1)
        }, Materials.Osmiridium.getMolten(1296), CustomItemList.eM_in_UV.get(1), 800, 500000);
        //Elemental Output
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Containment.get(1),
                ItemList.Hatch_Output_UV.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Naquadah, 2),
                ItemList.Emitter_UV.get(1)
        }, Materials.Osmiridium.getMolten(1296), CustomItemList.eM_out_UV.get(1), 800, 500000);
        //Overflow
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.eM_Containment.get(1),
                ItemList.Hatch_Muffler_UV.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeLarge, Materials.Naquadah, 1),
                ItemList.Field_Generator_UV.get(1)
        }, Materials.Osmiridium.getMolten(1296), CustomItemList.eM_muffler_UV.get(1), 800, 500000);

        //Capacitor Hatch
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                ItemList.Hatch_Input_Bus_HV.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 4),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Gold, 4),
        }, Materials.Silver.getMolten(576), CustomItemList.capacitor_Hatch.get(1), 800, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                ItemList.Hatch_Output_Bus_HV.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 4),
                GT_OreDictUnificator.get(OrePrefixes.wireGt16, Materials.Gold, 4),
        }, Materials.Silver.getMolten(576), CustomItemList.capacitor_Hatch.get(1), 800, 480);

        //endregion


        //region multi blocks

        //Tesla Tower
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_ModHandler.getIC2Item("teslaCoil", 1),
                CustomItemList.tM_TeslaSecondary.get(4),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 4),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 4),
                ItemList.Upgrade_Overclocker.get(4),
        }, Materials.Silver.getMolten(576), CustomItemList.Machine_Multi_TeslaCoil.get(1), 800, 480);

        //Microwave Grinder
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                ItemList.Machine_HV_Microwave.get(1),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.StainlessSteel, 4),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 4),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.AnnealedCopper, 16),
                ItemList.Upgrade_Overclocker.get(4),
        }, Materials.Copper.getMolten(576), CustomItemList.Machine_Multi_Microwave.get(1), 800, 480);

        //Network Switch
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.Machine_Multi_Transformer.get(1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 4),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Cobalt, 64),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Copper, 64),
                CustomItemList.DATApipe.get(4),
        }, Materials.Iridium.getMolten(1296), CustomItemList.Machine_Multi_Switch.get(1), 800, 122880);

        //Quantum Computer
        GT_Values.RA.addAssemblylineRecipe(ItemList.Tool_DataOrb.get(1), 20000, new Object[]{
                CustomItemList.Machine_Multi_Switch.get(1),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 2},
                ItemList.Tool_DataOrb.get(1),
                ItemList.Cover_Screen.get(1),
                new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.SuperconductorUV, 8)},
                CustomItemList.DATApipe.get(8),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(1000),
                Materials.Iridium.getMolten(1296),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                Materials.Hydrogen.getGas(1000),
        }, CustomItemList.Machine_Multi_Computer.get(1), 12000, 100000);

        //Research Station
        GT_Values.RA.addAssemblylineRecipe(ItemList.Machine_IV_Scanner.get(1), 80000, new Object[]{
                CustomItemList.Machine_Multi_Switch.get(1),
                ItemList.Sensor_ZPM.get(8),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 4},
                ItemList.Field_Generator_ZPM.get(1),
                ItemList.Electric_Motor_ZPM.get(2),
                new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.cableGt02, Materials.Naquadah, 4)},
                new ItemStack[]{GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Naquadah, 32)},
                CustomItemList.DATApipe.get(16),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(1000),
                Materials.Iridium.getMolten(1296),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                Materials.Osmium.getMolten(1296),
        }, CustomItemList.Machine_Multi_Research.get(1), 12000, 100000);

        //Matter Junction
        TT_recipeAdder.addResearchableAssemblylineRecipe(CustomItemList.Machine_Multi_Switch.get(1),
                8000,32, 500000, 4, new Object[]{
                CustomItemList.Machine_Multi_Transformer.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Naquadah, 4),
                ItemList.Robot_Arm_LuV.get(2),
                ItemList.Electric_Piston_LuV.get(2),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 2},
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Superconductor, 4),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(1000),
                Materials.Naquadah.getMolten(1296),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                Materials.Osmium.getMolten(1296),
        }, CustomItemList.Machine_Multi_EMjunction.get(1), 12000, 100000);

        //Matter Quantizer
        TT_recipeAdder.addResearchableAssemblylineRecipe(ItemList.Hatch_Input_UV.get(1),
                12000,32, 500000, 6, new Object[]{
                CustomItemList.Machine_Multi_Transformer.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Naquadah, 4),
                ItemList.Emitter_UV.get(2),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 1},
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Superconductor, 2),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(1000),
                Materials.Naquadah.getMolten(1296),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                Materials.Osmium.getMolten(1296),
        }, CustomItemList.Machine_Multi_MatterToEM.get(1), 12000, 100000);

        //Matter DeQuantizer
        TT_recipeAdder.addResearchableAssemblylineRecipe(ItemList.Hatch_Output_UV.get(1),
                12000,32, 500000, 6, new Object[]{
                CustomItemList.Machine_Multi_Transformer.get(1),
                GT_OreDictUnificator.get(OrePrefixes.pipeMedium, Materials.Naquadah, 4),
                ItemList.Sensor_UV.get(2),
                new Object[]{OrePrefixes.circuit.get(Materials.Superconductor), 1},
                GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Superconductor, 2),
        }, new FluidStack[]{
                Materials.UUMatter.getFluid(1000),
                Materials.Naquadah.getMolten(1296),
                new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                Materials.Osmium.getMolten(1296),
        }, CustomItemList.Machine_Multi_EMToMatter.get(1), 12000, 100000);

        //Multi Infuser
        TT_recipeAdder.addResearchableAssemblylineRecipe(CustomItemList.Machine_Multi_Transformer.get(1),
                192000,512, 2000000, 32, new ItemStack[]{
                        CustomItemList.Machine_Multi_Transformer.get(1),
                        CustomItemList.eM_Coil.get(8),
                        CustomItemList.eM_Power.get( 8),
                        GT_OreDictUnificator.get(OrePrefixes.screw, Materials.NeodymiumMagnetic, 16),
                }, new FluidStack[]{
                        Materials.Electrum.getMolten(2592),
                        Materials.Neutronium.getMolten(1872),
                        new FluidStack(FluidRegistry.getFluid("ic2coolant"), 2000),
                }, CustomItemList.Machine_Multi_Infuser.get(1), 8000, 2000000);
        
        //Dynamo Hatches IV-UHV
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_EV.get(1L),
						ItemList.EV_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.BlackSteel, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.BT6, 1)},
                Materials.Silver.getMolten(144),
                CustomItemList.eM_dynamoMulti2_EV.get(1), 50, 1920);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_IV.get(1L),
						ItemList.IV_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.TungstenSteel, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.Nitinol, 1)},
                Materials.Silver.getMolten(288),
                CustomItemList.eM_dynamoMulti2_IV.get(1), 50, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_LuV_IV.get(1L),
                        CustomItemList.eM_dynamoMulti2_IV.get(1L),
                        ItemList.Circuit_Chip_HPIC.get(2L),
						ItemList.IV_Coil.get(2),
						GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 8),
						GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Nitinol, 1)},
                Materials.Electrum.getMolten(288),
                CustomItemList.eM_dynamoMulti4_IV.get(1), 100, 7680);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_LuV.get(1),
						ItemList.LuV_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.VanadiumGallium, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.HastelloyN, 1)},
                Materials.Silver.getMolten(576),
                CustomItemList.eM_dynamoMulti2_LuV.get(1), 50, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_ZPM_LuV.get(1L),
                        CustomItemList.eM_dynamoMulti2_LuV.get(1),
                        ItemList.Circuit_Chip_UHPIC.get(2),
						ItemList.LuV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.HastelloyN, 1)},
                Materials.Electrum.getMolten(576),
                CustomItemList.eM_dynamoMulti4_LuV.get(1), 100, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_ZPM_LuV.get(1L),
                        CustomItemList.eM_dynamoMulti4_LuV.get(1),
                        ItemList.Circuit_Chip_UHPIC.get(4),
						ItemList.LuV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.HastelloyN, 2)},
                Materials.Tungsten.getMolten(576),
                CustomItemList.eM_dynamoMulti16_LuV.get(1), 200, 30720);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_ZPM.get(1),
						ItemList.ZPM_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Naquadah, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.Lafium, 1)},
                Materials.Silver.getMolten(1152),
                CustomItemList.eM_dynamoMulti2_ZPM.get(1), 50, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_UV_ZPM.get(1L),
                        CustomItemList.eM_dynamoMulti2_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(2),
						ItemList.ZPM_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 1)},
                Materials.Electrum.getMolten(1152),
                CustomItemList.eM_dynamoMulti4_ZPM.get(1), 100, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UV_ZPM.get(1L),
                        CustomItemList.eM_dynamoMulti4_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(4),
						ItemList.ZPM_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 2)},
                Materials.Tungsten.getMolten(1152),
                CustomItemList.eM_dynamoMulti16_ZPM.get(1), 200, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UV_ZPM.get(1L),
                        CustomItemList.eM_dynamoMulti16_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(6),
						ItemList.ZPM_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.Naquadah, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 4)},
                Materials.NiobiumTitanium.getMolten(1152),
                CustomItemList.eM_dynamoMulti64_ZPM.get(1), 400, 122880);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_UV.get(1),
						ItemList.UV_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.ElectrumFlux, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.CinobiteA243, 1)},
                Materials.Silver.getMolten(2304),
                CustomItemList.eM_dynamoMulti2_UV.get(1), 50, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_MAX_UV.get(1L),
                        CustomItemList.eM_dynamoMulti2_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(2),
						ItemList.UV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.ElectrumFlux, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 1)},
                Materials.Electrum.getMolten(2304),
                CustomItemList.eM_dynamoMulti4_UV.get(1), 100, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UHV_UV.get(1L),
                        CustomItemList.eM_dynamoMulti4_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(4),
						ItemList.UV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.ElectrumFlux, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 2)},
                Materials.Tungsten.getMolten(2304),
                CustomItemList.eM_dynamoMulti16_UV.get(1), 200, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UHV_UV.get(1L),
                        CustomItemList.eM_dynamoMulti16_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(6),
						ItemList.UV_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.ElectrumFlux, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 4)},
                Materials.NiobiumTitanium.getMolten(2304),
                CustomItemList.eM_dynamoMulti64_UV.get(1), 400, 500000);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Hatch_Dynamo_MAX.get(1),
						ItemList.UHV_Coil.get(1),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt02, Materials.Europium, 4),
                        GT_OreDictUnificator.get(OrePrefixes.plateTriple, Materials.Pikyonium64B, 1)},
                Materials.Silver.getMolten(4608),
                CustomItemList.eM_dynamoMulti2_UHV.get(1), 50, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_UEV_UHV.get(1L),
                        CustomItemList.eM_dynamoMulti2_UHV.get(1),
                        ItemList.Circuit_Chip_QPIC.get(2),
						ItemList.UHV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Europium, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 1)},
                Materials.Electrum.getMolten(4608),
                CustomItemList.eM_dynamoMulti4_UHV.get(1), 100, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UEV_UHV.get(1L),
                        CustomItemList.eM_dynamoMulti4_UHV.get(1),
                        ItemList.Circuit_Chip_QPIC.get(4),
						ItemList.UHV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Europium, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 2)},
                Materials.Tungsten.getMolten(4608),
                CustomItemList.eM_dynamoMulti16_UHV.get(1), 200, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UEV_UHV.get(1L),
                        CustomItemList.eM_dynamoMulti16_UHV.get(1),
                        ItemList.Circuit_Chip_QPIC.get(6),
						ItemList.UHV_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.Europium, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 4)},
                Materials.NiobiumTitanium.getMolten(4608),
                CustomItemList.eM_dynamoMulti64_UHV.get(1), 400, 2000000);

        //Energy Hatches 4A-64A  IV-UHV
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_LuV_IV.get(1L),
                        ItemList.Hatch_Energy_IV.get(1),
                        ItemList.Circuit_Chip_HPIC.get(2),
						ItemList.IV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.TungstenSteel, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Nitinol, 1)},
                Materials.Electrum.getMolten(288),
                CustomItemList.eM_energyMulti4_IV.get(1), 100, 7680);
        
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_ZPM_LuV.get(1L),
                        ItemList.Hatch_Energy_LuV.get(1),
                        ItemList.Circuit_Chip_UHPIC.get(2),
						ItemList.LuV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.VanadiumGallium, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.HastelloyN, 1)},
                Materials.Electrum.getMolten(576),
                CustomItemList.eM_energyMulti4_LuV.get(1), 100, 30720);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_ZPM_LuV.get(1L),
                        CustomItemList.eM_energyMulti4_LuV.get(1),
                        ItemList.Circuit_Chip_UHPIC.get(4),
						ItemList.LuV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.VanadiumGallium, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.HastelloyN, 2)},
                Materials.Tungsten.getMolten(576),
                CustomItemList.eM_energyMulti16_LuV.get(1), 200, 30720);
        
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_UV_ZPM.get(1L),
                        ItemList.Hatch_Energy_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(2),
						ItemList.ZPM_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Naquadah, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 1)},
                Materials.Electrum.getMolten(1152),
                CustomItemList.eM_energyMulti4_ZPM.get(1), 100, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UV_ZPM.get(1L),
                        CustomItemList.eM_energyMulti4_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(4),
						ItemList.ZPM_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Naquadah, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 2)},
                Materials.Tungsten.getMolten(1152),
                CustomItemList.eM_energyMulti16_ZPM.get(1), 200, 122880);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UV_ZPM.get(1L),
                        CustomItemList.eM_energyMulti16_ZPM.get(1),
                        ItemList.Circuit_Chip_NPIC.get(6),
						ItemList.ZPM_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.Naquadah, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Lafium, 4)},
                Materials.NiobiumTitanium.getMolten(1152),
                CustomItemList.eM_energyMulti64_ZPM.get(1), 400, 122880);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_MAX_UV.get(1L),
                        ItemList.Hatch_Energy_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(2),
						ItemList.UV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.ElectrumFlux, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 1)},
                Materials.Electrum.getMolten(2304),
                CustomItemList.eM_energyMulti4_UV.get(1), 100, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UHV_UV.get(1L),
                        CustomItemList.eM_energyMulti4_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(4),
						ItemList.UV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.ElectrumFlux, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 2)},
                Materials.Tungsten.getMolten(2304),
                CustomItemList.eM_energyMulti16_UV.get(1), 200, 500000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UHV_UV.get(1L),
                        CustomItemList.eM_energyMulti16_UV.get(1),
                        ItemList.Circuit_Chip_PPIC.get(6),
						ItemList.UV_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.ElectrumFlux, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.CinobiteA243, 4)},
                Materials.NiobiumTitanium.getMolten(2304),
                CustomItemList.eM_energyMulti64_UV.get(1), 400, 500000);

        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_UEV_UHV.get(1L),
                        ItemList.Hatch_Energy_MAX.get(1),
                        ItemList.Circuit_Chip_QPIC.get(2),
						ItemList.UHV_Coil.get(2),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt04, Materials.Europium, 8),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 1)},
                Materials.Electrum.getMolten(4608),
                CustomItemList.eM_energyMulti4_UHV.get(1), 100, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Transformer_HA_UEV_UHV.get(1L),
                        CustomItemList.eM_energyMulti4_UHV.get(1),
                        ItemList.Circuit_Chip_QPIC.get(4),
						ItemList.UHV_Coil.get(4),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt08, Materials.Europium, 16),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 2)},
                Materials.Tungsten.getMolten(4608),
                CustomItemList.eM_energyMulti16_UHV.get(1), 200, 2000000);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.WetTransformer_UEV_UHV.get(1L),
                        CustomItemList.eM_energyMulti16_UHV.get(1),
                        ItemList.Circuit_Chip_QPIC.get(6),
						ItemList.UHV_Coil.get(8),
                        GT_OreDictUnificator.get(OrePrefixes.wireGt12, Materials.Europium, 32),
                        GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Pikyonium64B, 4)},
                Materials.NiobiumTitanium.getMolten(4608),
                CustomItemList.eM_energyMulti64_UHV.get(1), 400, 2000000);

        //region singleblocks

        //Tesla Transceiver LV 1A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_1by1_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_1by1_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_1by1_LV.get(1), 400, 30);
        //Tesla Transceiver MV 1A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_1by1_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_1by1_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_1by1_MV.get(1), 400, 120);
        //Tesla Transceiver HV 1A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_1by1_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_1by1_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_1by1_HV.get(1), 400, 480);
        //Tesla Transceiver EV 1A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_1by1_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_1by1_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_1by1_EV.get(1), 400, 1920);
        //Tesla Transceiver IV 1A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_1by1_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_1by1_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_1by1_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_1by1_IV.get(1), 400, 7680);
        //Tesla Transceiver LV 4A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_2by2_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_2by2_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_2by2_LV.get(1), 400, 30);
        //Tesla Transceiver MV 4A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_2by2_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_2by2_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_2by2_MV.get(1), 400, 120);
        //Tesla Transceiver HV 4A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_2by2_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_2by2_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_2by2_HV.get(1), 400, 480);
        //Tesla Transceiver EV 4A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_2by2_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_2by2_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_2by2_EV.get(1), 400, 1920);
        //Tesla Transceiver IV 4A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_2by2_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_2by2_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_2by2_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_2by2_IV.get(1), 400, 7680);
        //Tesla Transceiver LV 9A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_3by3_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_3by3_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_3by3_LV.get(1), 400, 30);
        //Tesla Transceiver MV 9A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_3by3_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_3by3_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_3by3_MV.get(1), 400, 120);
        //Tesla Transceiver HV 9A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_3by3_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_3by3_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_3by3_HV.get(1), 400, 480);
        //Tesla Transceiver EV 9A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_3by3_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_3by3_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_3by3_EV.get(1), 400, 1920);
        //Tesla Transceiver IV 9A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_3by3_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_3by3_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_3by3_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_3by3_IV.get(1), 400, 7680);
        //Tesla Transceiver LV 16A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_4by4_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_4by4_LV.get(1), 400, 30);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_LV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_4by4_LV.get(1), 400, 30);
        //Tesla Transceiver MV 16A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_4by4_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_4by4_MV.get(1), 400, 120);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_MV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_4by4_MV.get(1), 400, 120);
        //Tesla Transceiver HV 16A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_4by4_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_4by4_HV.get(1), 400, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_HV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_4by4_HV.get(1), 400, 480);
        //Tesla Transceiver EV 16A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_4by4_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_4by4_EV.get(1), 400, 1920);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_EV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_4by4_EV.get(1), 400, 1920);
        //Tesla Transceiver IV 16A
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Lead.getMolten(576),
                CustomItemList.Machine_TeslaCoil_4by4_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.Tin.getMolten(288),
                CustomItemList.Machine_TeslaCoil_4by4_IV.get(1), 400, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                        ItemList.Battery_Buffer_4by4_IV.get(1),
                        CustomItemList.teslaCover.getWithDamage(1, 0)},
                Materials.SolderingAlloy.getMolten(144),
                CustomItemList.Machine_TeslaCoil_4by4_IV.get(1), 400, 7680);

        //endregion

        //region components

        //Tesla Winding Components
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Electrum, 32),
                GT_OreDictUnificator.get(OrePrefixes.ring, Materials.NickelZincFerrite, 8),
        }, Materials.Epoxid.getMolten(288), CustomItemList.teslaComponent.getWithDamage(1, 0), 320, 30);
        //Tesla Winding Components Ultimate (ADD BLOOD VARIANT)
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.wireGt01, Materials.SuperconductorLuV, 4),
                GT_OreDictUnificator.get(OrePrefixes.ring, Materials.NickelZincFerrite, 8),
        }, Materials.Epoxid.getMolten(576), CustomItemList.teslaComponent.getWithDamage(1, 1), 320, 7680);

        //endregion

        //region items

        //LV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Tin, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 4),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 8),
        }, Materials.Epoxid.getMolten(72), CustomItemList.teslaCapacitor.getWithDamage(1, 0), 320, 30);
        //MV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Copper, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 6),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 12),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 12),
        }, Materials.Epoxid.getMolten(144), CustomItemList.teslaCapacitor.getWithDamage(1, 1), 320, 120);
        //HV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Gold, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 8),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 16),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 16),
        }, Materials.Epoxid.getMolten(216), CustomItemList.teslaCapacitor.getWithDamage(1, 2), 320, 480);
        //EV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Aluminium, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 10),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 20),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 20),
        }, Materials.Epoxid.getMolten(288), CustomItemList.teslaCapacitor.getWithDamage(1, 3), 320, 1920);
        //IV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Tungsten, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 12),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 24),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicon, 24),
        }, Materials.Epoxid.getMolten(360), CustomItemList.teslaCapacitor.getWithDamage(1, 4), 320, 7680);
        //LuV Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.HSSG, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 14),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 28),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicone, 28),
        }, Materials.Epoxid.getMolten(432), CustomItemList.teslaCapacitor.getWithDamage(1, 5), 320, 30720);
        //ZPM Tesla Capacitor
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.cableGt01, Materials.Naquadah, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 16),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 32),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Silicone, 32),
        }, Materials.Epoxid.getMolten(504), CustomItemList.teslaCapacitor.getWithDamage(1, 6), 320, 122880);
        //Tesla Cover
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 0),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.Lead.getMolten(288), CustomItemList.teslaCover.getWithDamage(1, 0), 320, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 0),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.Tin.getMolten(144), CustomItemList.teslaCover.getWithDamage(1, 0), 320, 480);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 0),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Advanced, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Gold, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.SolderingAlloy.getMolten(72), CustomItemList.teslaCover.getWithDamage(1, 0), 320, 480);
        //Ultimate Tesla Cover
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tungsten, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.Lead.getMolten(288), CustomItemList.teslaCover.getWithDamage(1, 1), 320, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tungsten, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.Tin.getMolten(144), CustomItemList.teslaCover.getWithDamage(1, 1), 320, 7680);
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                CustomItemList.teslaComponent.getWithDamage(4, 1),
                GT_OreDictUnificator.get(OrePrefixes.circuit, Materials.Master, 2),
                GT_OreDictUnificator.get(OrePrefixes.wireFine, Materials.Tungsten, 16),
                GT_OreDictUnificator.get(OrePrefixes.plate, Materials.NickelZincFerrite, 2),
                GT_OreDictUnificator.get(OrePrefixes.foil, Materials.Aluminium, 8),
        }, Materials.SolderingAlloy.getMolten(72), CustomItemList.teslaCover.getWithDamage(1, 1), 320, 7680);
        //Ender Fluid Link Cover
        GT_Values.RA.addAssemblerRecipe(new ItemStack[]{
                GT_OreDictUnificator.get(OrePrefixes.plateDense, Materials.Enderium, 4),
                ItemList.Sensor_LuV.get(1),
                ItemList.Emitter_LuV.get(1),
                ItemList.Electric_Pump_LuV.get(1),
        }, Materials.Chrome.getMolten(288), CustomItemList.enderLinkFluidCover.getWithDamage(1, 0), 320, 30720);
        //endregion

        //region recycling

        //LV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 0),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 4), 300, 2);
        //MV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 1),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 6), 300, 2);
        //HV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 2),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 8), 300, 2);
        //EV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 3),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 10), 300, 2);
        //IV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 4),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 12), 300, 2);
        //LuV Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 5),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 14), 300, 2);
        //ZPM Tesla Capacitor
        GT_Values.RA.addExtractorRecipe(CustomItemList.teslaCapacitor.getWithDamage(1, 6),
                GT_OreDictUnificator.get(OrePrefixes.itemCasing, Materials.BatteryAlloy, 16), 300, 2);

        //endregion
        register_machine_EM_behaviours();
    }
    
    private void register_machine_EM_behaviours(){
        GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(5),ItemList.Machine_IV_Centrifuge.get(1));
        try {
        }catch (IllegalArgumentException|NullPointerException e){
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(6),ItemList.Machine_IV_Centrifuge.get(2));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(7),ItemList.Machine_IV_Centrifuge.get(4));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(8),ItemList.Machine_IV_Centrifuge.get(8));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(9),ItemList.Machine_IV_Centrifuge.get(16));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(10),ItemList.Machine_IV_Centrifuge.get(32));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(11),ItemList.Machine_IV_Centrifuge.get(48));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Centrifuge(12),ItemList.Machine_IV_Centrifuge.get(64));
        }

        GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(5),ItemList.Machine_IV_ElectromagneticSeparator.get(1));
        try {
        }catch (IllegalArgumentException|NullPointerException e){
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(6),ItemList.Machine_IV_ElectromagneticSeparator.get(2));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(7),ItemList.Machine_IV_ElectromagneticSeparator.get(4));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(8),ItemList.Machine_IV_ElectromagneticSeparator.get(8));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(9),ItemList.Machine_IV_ElectromagneticSeparator.get(16));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(10),ItemList.Machine_IV_ElectromagneticSeparator.get(32));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(11),ItemList.Machine_IV_ElectromagneticSeparator.get(48));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_ElectromagneticSeparator(12),ItemList.Machine_IV_ElectromagneticSeparator.get(64));
        }

        GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(5),ItemList.Machine_IV_Recycler.get(1));
        try {
        }catch (IllegalArgumentException|NullPointerException e){
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(6),ItemList.Machine_IV_Recycler.get(2));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(7),ItemList.Machine_IV_Recycler.get(4));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(8),ItemList.Machine_IV_Recycler.get(8));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(9),ItemList.Machine_IV_Recycler.get(16));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(10),ItemList.Machine_IV_Recycler.get(32));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(11),ItemList.Machine_IV_Recycler.get(48));
            GT_MetaTileEntity_EM_machine.registerBehaviour(()->new Behaviour_Recycler(12),ItemList.Machine_IV_Recycler.get(64));
        }
    }
}
