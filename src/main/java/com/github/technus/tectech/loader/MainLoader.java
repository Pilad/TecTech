package com.github.technus.tectech.loader;

import com.github.technus.tectech.Reference;
import com.github.technus.tectech.TecTech;
import com.github.technus.tectech.compatibility.thaumcraft.elementalMatter.definitions.AspectDefinitionCompat;
import com.github.technus.tectech.compatibility.thaumcraft.elementalMatter.definitions.AspectDefinitionCompatEnabled;
import com.github.technus.tectech.compatibility.thaumcraft.thing.metaTileEntity.multi.EssentiaCompat;
import com.github.technus.tectech.compatibility.thaumcraft.thing.metaTileEntity.multi.EssentiaCompatEnabled;
import com.github.technus.tectech.loader.gui.CreativeTabTecTech;
import com.github.technus.tectech.loader.gui.ModGuiHandler;
import com.github.technus.tectech.loader.recipe.RecipeLoader;
import com.github.technus.tectech.loader.thing.ComponentLoader;
import com.github.technus.tectech.loader.thing.CoverLoader;
import com.github.technus.tectech.loader.thing.MachineLoader;
import com.github.technus.tectech.loader.thing.ThingsLoader;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.github.technus.tectech.thing.metaTileEntity.Textures;
import com.github.technus.tectech.thing.metaTileEntity.multi.GT_MetaTileEntity_EM_collider;
import cpw.mods.fml.common.Loader;
import cpw.mods.fml.common.ProgressManager;
import cpw.mods.fml.common.network.NetworkRegistry;
import cpw.mods.fml.common.registry.GameData;
import cpw.mods.fml.common.registry.GameRegistry;
import gregtech.api.GregTech_API;
import gregtech.api.enums.GT_Values;
import gregtech.api.enums.Materials;
import gregtech.api.util.GT_ModHandler;
import gregtech.api.util.GT_Recipe;
import gregtech.api.util.GT_Utility;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.util.DamageSource;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.FluidStack;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import static com.github.technus.tectech.util.CommonValues.*;
import static com.github.technus.tectech.TecTech.*;
import static com.github.technus.tectech.compatibility.thaumcraft.elementalMatter.definitions.AspectDefinitionCompat.aspectDefinitionCompat;
import static com.github.technus.tectech.compatibility.thaumcraft.thing.metaTileEntity.multi.EssentiaCompat.essentiaContainerCompat;
import static com.github.technus.tectech.loader.TecTechConfig.DEBUG_MODE;
import static com.github.technus.tectech.loader.gui.CreativeTabTecTech.creativeTabTecTech;
import static gregtech.api.enums.GT_Values.W;

public final class MainLoader {
    public static DamageSource microwaving, elementalPollution,subspace;

    private MainLoader(){}

    public static void staticLoad(){
        for(int i=0;i<16;i++){
            GT_Values.V[i]=V[i];
            GT_Values.VN[i]=VN[i];
            GT_Values.VOLTAGE_NAMES[i]=VOLTAGE_NAMES[i];
        }
        new ComponentLoader();
    }

    public static void preLoad(){
        creativeTabTecTech =new CreativeTabTecTech("TecTech");

        //set expanded texture arrays for tiers
        try {
            new Textures();
        }catch (Throwable t){
            LOGGER.error("Loading textures...",t);
        }
    }

    public static void load() {

        new ElementalLoader().run();
        LOGGER.info("Elemental Init Done");

        if (Loader.isModLoaded(Reference.THAUMCRAFT)) {
            essentiaContainerCompat = new EssentiaCompatEnabled();
        } else {
            essentiaContainerCompat = new EssentiaCompat();
        }
        LOGGER.info("Thaumcraft Compatibility Done");

        new ThingsLoader().run();
        LOGGER.info("Block/Item Init Done");

        new MachineLoader().run();
        LOGGER.info("Machine Init Done");

        new CoverLoader().run();
        LOGGER.info("Cover Init Done");

        new EntityLoader().run();
        LOGGER.info("Entities registered");

        microwaving =new DamageSource("microwaving").setDamageBypassesArmor();
        elementalPollution =new DamageSource("elementalPollution").setDamageBypassesArmor();
        subspace =new DamageSource("subspace").setDamageBypassesArmor().setDamageIsAbsolute();
        LOGGER.info("Damage types addition Done");

        new NetworkDispatcher();
        LOGGER.info("Packet Dispatcher registered");

        NetworkRegistry.INSTANCE.registerGuiHandler(instance, new ModGuiHandler());
        proxy.registerRenderInfo();
        LOGGER.info("GUI Handler registered");

    }

    public static void postLoad() {

        if(Loader.isModLoaded(Reference.DREAMCRAFT)){
            try {
                Class<?> clazz = Class.forName("com.dreammaster.gthandler.casings.GT_Container_CasingsNH");
                TT_Container_Casings.sBlockCasingsNH = (Block)clazz.getField("sBlockCasingsNH").get(null);

                if(TT_Container_Casings.sBlockCasingsNH==null){
                    throw new NullPointerException("sBlockCasingsNH Is not set at this time");
                }
            }catch (Exception e){
                throw new Error("Unable to get NH casings",e);
            }
        }

        if (Loader.isModLoaded(Reference.THAUMCRAFT)) {
            aspectDefinitionCompat = new AspectDefinitionCompatEnabled();
            aspectDefinitionCompat.run();
        } else {
            aspectDefinitionCompat = new AspectDefinitionCompat();
        }

        new RecipeLoader().run();
        TecTech.LOGGER.info("Recipe Init Done");

        registerExtraHazmats();
        TecTech.LOGGER.info("Hazmat additions done");

        new ConstructableLoader().run();
        TecTech.LOGGER.info("Constructable initialized");

    }

    private static void registerExtraHazmats() {
        ItemStack EMT_iqC=GT_ModHandler.getModItem("EMT","itemArmorQuantumChestplate",1,W);
        ItemStack GRAVI_gC=GT_ModHandler.getModItem("GraviSuite","graviChestPlate",1,W);
        ItemStack GRAVI_anC=GT_ModHandler.getModItem("GraviSuite", "advNanoChestPlate", 1, W);

        ItemStack IC2_qH=GT_ModHandler.getIC2Item("quantumHelmet", 1L, W);
        ItemStack IC2_qC=GT_ModHandler.getIC2Item("quantumBodyarmor", 1L, W);
        ItemStack IC2_qL=GT_ModHandler.getIC2Item("quantumLeggings", 1L, W);
        ItemStack IC2_qB=GT_ModHandler.getIC2Item("quantumBoots", 1L, W);

        ItemStack IC2_nH=GT_ModHandler.getIC2Item("nanoHelmet", 1L, W);
        ItemStack IC2_nC=GT_ModHandler.getIC2Item("nanoBodyarmor", 1L, W);
        ItemStack IC2_nL=GT_ModHandler.getIC2Item("nanoLeggings", 1L, W);
        ItemStack IC2_nB=GT_ModHandler.getIC2Item("nanoBoots", 1L, W);

        GregTech_API.sFrostHazmatList.add(EMT_iqC);
        GregTech_API.sFrostHazmatList.add(GRAVI_gC);
        GregTech_API.sFrostHazmatList.add(IC2_qH);
        GregTech_API.sFrostHazmatList.add(IC2_qC);
        GregTech_API.sFrostHazmatList.add(IC2_qL);
        GregTech_API.sFrostHazmatList.add(IC2_qB);

        GregTech_API.sHeatHazmatList.add(EMT_iqC);
        GregTech_API.sHeatHazmatList.add(GRAVI_gC);
        GregTech_API.sHeatHazmatList.add(IC2_qH);
        GregTech_API.sHeatHazmatList.add(IC2_qC);
        GregTech_API.sHeatHazmatList.add(IC2_qL);
        GregTech_API.sHeatHazmatList.add(IC2_qB);

        GregTech_API.sBioHazmatList.add(EMT_iqC);
        GregTech_API.sBioHazmatList.add(GRAVI_gC);
        GregTech_API.sBioHazmatList.add(IC2_qH);
        GregTech_API.sBioHazmatList.add(IC2_qC);
        GregTech_API.sBioHazmatList.add(IC2_qL);
        GregTech_API.sBioHazmatList.add(IC2_qB);
        
        GregTech_API.sBioHazmatList.add(GRAVI_anC);
        GregTech_API.sBioHazmatList.add(IC2_nH);
        GregTech_API.sBioHazmatList.add(IC2_nC);
        GregTech_API.sBioHazmatList.add(IC2_nL);
        GregTech_API.sBioHazmatList.add(IC2_nB);

        GregTech_API.sGasHazmatList.add(EMT_iqC);
        GregTech_API.sGasHazmatList.add(GRAVI_gC);
        GregTech_API.sGasHazmatList.add(IC2_qH);
        GregTech_API.sGasHazmatList.add(IC2_qC);
        GregTech_API.sGasHazmatList.add(IC2_qL);
        GregTech_API.sGasHazmatList.add(IC2_qB);
        
        GregTech_API.sGasHazmatList.add(GRAVI_anC);
        GregTech_API.sGasHazmatList.add(IC2_nH);
        GregTech_API.sGasHazmatList.add(IC2_nC);
        GregTech_API.sGasHazmatList.add(IC2_nL);
        GregTech_API.sGasHazmatList.add(IC2_nB);

        GregTech_API.sRadioHazmatList.add(EMT_iqC);
        GregTech_API.sRadioHazmatList.add(GRAVI_gC);
        GregTech_API.sRadioHazmatList.add(IC2_qH);
        GregTech_API.sRadioHazmatList.add(IC2_qC);
        GregTech_API.sRadioHazmatList.add(IC2_qL);
        GregTech_API.sRadioHazmatList.add(IC2_qB);

        GregTech_API.sElectroHazmatList.add(EMT_iqC);
        GregTech_API.sElectroHazmatList.add(GRAVI_gC);
        GregTech_API.sElectroHazmatList.add(IC2_qH);
        GregTech_API.sElectroHazmatList.add(IC2_qC);
        GregTech_API.sElectroHazmatList.add(IC2_qL);
        GregTech_API.sElectroHazmatList.add(IC2_qB);
        
        //todo add GC GS stuff
    }

    public static void addAfterGregTechPostLoadRunner() {
        GregTech_API.sAfterGTPostload.add(() -> {
            if(TecTech.configTecTech.NERF_FUSION) {
                FixBrokenFusionRecipes();
            }
            GT_MetaTileEntity_EM_collider.setValues(getFuelValue(Materials.Helium.getPlasma(125)));
        });
    }

    private static void FixBrokenFusionRecipes() {
        HashMap<Fluid, Fluid> binds = new HashMap<>();
        for (Materials material : Materials.values()) {
            FluidStack p = material.getPlasma(1);
            if (p != null) {
                if (DEBUG_MODE) {
                    LOGGER.info("Found Plasma of " + material.mName);
                }
                if (material.mElement != null &&
                        (material.mElement.mProtons >= Materials.Iron.mElement.mProtons ||
                                -material.mElement.mProtons >= Materials.Iron.mElement.mProtons ||
                                material.mElement.mNeutrons >= Materials.Iron.mElement.mNeutrons ||
                                -material.mElement.mNeutrons >= Materials.Iron.mElement.mNeutrons)) {
                    if (DEBUG_MODE) {
                        LOGGER.info("Attempting to bind " + material.mName);
                    }
                    if (material.getMolten(1) != null) {
                        binds.put(p.getFluid(), material.getMolten(1).getFluid());
                    } else if (material.getGas(1) != null) {
                        binds.put(p.getFluid(), material.getGas(1).getFluid());
                    } else if (material.getFluid(1) != null) {
                        binds.put(p.getFluid(), material.getFluid(1).getFluid());
                    } else {
                        binds.put(p.getFluid(), Materials.Iron.getMolten(1).getFluid());
                    }
                }
            }
        }
        for (GT_Recipe r : GT_Recipe.GT_Recipe_Map.sFusionRecipes.mRecipeList) {
            Fluid fluid = binds.get(r.mFluidOutputs[0].getFluid());
            if (fluid != null) {
                if (DEBUG_MODE) {
                    LOGGER.info("Nerfing Recipe " + r.mFluidOutputs[0].getUnlocalizedName());
                }
                r.mFluidOutputs[0] = new FluidStack(fluid, r.mFluidOutputs[0].amount);
            }
            fluid = binds.get(r.mFluidInputs[0].getFluid());
            if (fluid != null) {
                if (DEBUG_MODE) {
                    LOGGER.info("Fixing plasma use in Recipe " + r.mFluidInputs[0].getUnlocalizedName());
                }
                r.mFluidInputs[0] = new FluidStack(fluid, r.mFluidInputs[0].amount);
            }
            fluid = binds.get(r.mFluidInputs[1].getFluid());
            if (fluid != null) {
                if (DEBUG_MODE) {
                    LOGGER.info("Fixing plasma use in Recipe " + r.mFluidInputs[1].getUnlocalizedName());
                }
                r.mFluidInputs[1] = new FluidStack(fluid, r.mFluidInputs[1].amount);
            }
        }
    }

    public static int getFuelValue(FluidStack aLiquid) {
        if (aLiquid == null || GT_Recipe.GT_Recipe_Map.sTurbineFuels == null) return 0;
        FluidStack tLiquid;
        Collection<GT_Recipe> tRecipeList = GT_Recipe.GT_Recipe_Map.sPlasmaFuels.mRecipeList;
        if (tRecipeList != null) for (GT_Recipe tFuel : tRecipeList)
            if ((tLiquid = GT_Utility.getFluidForFilledItem(tFuel.getRepresentativeInput(0), true)) != null)
                if (aLiquid.isFluidEqual(tLiquid)) return tFuel.mSpecialValue;
        return 0;
    }

}
