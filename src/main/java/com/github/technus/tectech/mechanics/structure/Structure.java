package com.github.technus.tectech.mechanics.structure;

import com.github.technus.tectech.TecTech;
import com.github.technus.tectech.thing.casing.TT_Container_Casings;
import com.impact.util.multis.IGT_HatchAdder;
import gregtech.api.interfaces.metatileentity.IMetaTileEntity;
import gregtech.api.interfaces.tileentity.IGregTechTileEntity;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraftforge.common.util.ForgeDirection;
import space.impact.api.multiblocks.alignment.enumerable.ExtendedFacing;
import space.impact.api.multiblocks.structure.ICustomBlockSetting;

import static com.github.technus.tectech.loader.TecTechConfig.DEBUG_MODE;

@Deprecated
public class Structure {
    private Structure() {
    }
    
    @Deprecated
    @SafeVarargs
    public static <T> IGT_HatchAdder<T>[] adders(IGT_HatchAdder<T>... iHatchAdder) {
        return iHatchAdder;
    }
    
    //Check Machine Structure based on string[][] (effectively char[][][]), ond offset of the controller
    //This only checks for REGULAR BLOCKS!
    @Deprecated
    public static <T extends IMetaTileEntity> boolean checker(
            String[][] structure,//0-9 casing, +- air no air, A... ignore 'A'-CHAR-1 blocks
            Block[] blockType,//use numbers 0-9 for casing types
            byte[] blockMeta,//use numbers 0-9 for casing types
            IGT_HatchAdder<T>[] addingMethods,
            short[] casingTextures,
            Block[] blockTypeFallback,//use numbers 0-9 for casing types
            byte[] blockMetaFallback,//use numbers 0-9 for casing types
            int horizontalOffset, int verticalOffset, int depthOffset,
            T metaTile,
            ExtendedFacing extendedFacing,
            boolean forceCheck) {
        IGregTechTileEntity aBaseMetaTileEntity = metaTile.getBaseMetaTileEntity();
        World world = aBaseMetaTileEntity.getWorld();
        if (world.isRemote) {
            return false;
        }
        //TE Rotation
        if (extendedFacing == null) {
            extendedFacing = ExtendedFacing.of(ForgeDirection.getOrientation(aBaseMetaTileEntity.getFrontFacing()));
        }
        
        IGregTechTileEntity igt;
        
        int[] xyz = new int[3];
        int[] abc = new int[3];
        int pointer;
        int baseX = aBaseMetaTileEntity.getXCoord(),
                baseZ = aBaseMetaTileEntity.getZCoord(),
                baseY = aBaseMetaTileEntity.getYCoord();
        //a,b,c - relative to block face!
        //x,y,z - relative to block position on map!
        //yPos  - absolute height of checked block
        
        //perform your duties
        abc[2] = -depthOffset;
        for (String[] _structure : structure) {//front to back
            abc[1] = -verticalOffset;
            for (String __structure : _structure) {//top to bottom
                abc[0] = -horizontalOffset;
                for (char block : __structure.toCharArray()) {//left to right
                    if (block < ' ') {
                        //Control chars allow skipping
                        abc[1] += block;
                        break;
                    } else if (block > '@') {
                        //characters allow to skip check A-1 skip, B-2 skips etc.
                        abc[0] += block - '@';
                    }//else if (block < '+')//used to mark THINGS
                    //    a++;
                    else if (block == '.') {
                        abc[0]++;
                    } else {
                        //get x y z from rotation
                        extendedFacing.getWorldOffset(abc, xyz);
                        xyz[0] += baseX;
                        xyz[1] += baseY;
                        xyz[2] += baseZ;
                        
                        //that must be here since in some cases other axis (b,c) controls y
                        if (xyz[1] < 0 || xyz[1] >= 256) {
                            return false;
                        }
                        
                        //Check block
                        if (world.blockExists(xyz[0], xyz[1], xyz[2])) {//this actually checks if the chunk is loaded at this pos
                            switch (block) {
                                case '-'://must be air
                                    if (world.getBlock(xyz[0], xyz[1], xyz[2]).getMaterial() != Material.air) {
                                        return false;
                                    }
                                    break;
                                case '+'://must not be air
                                    if (world.getBlock(xyz[0], xyz[1], xyz[2]).getMaterial() == Material.air) {
                                        return false;
                                    }
                                    break;
                                default://check for block (countable)
                                    Block worldblock = world.getBlock(xyz[0], xyz[1], xyz[2]);
                                    int dmg = worldblock.getDamageValue(world, xyz[0], xyz[1], xyz[2]);
                                    if ((pointer = block - '0') >= 0) {
                                        //countable air -> net.minecraft.block.BlockAir
                                        if (worldblock != blockType[pointer]) {
                                            if (DEBUG_MODE) {
                                                TecTech.LOGGER.info("Struct-block-error " + xyz[0] + ' ' + xyz[1] + ' ' + xyz[2] + " / " + abc[0] + ' ' + abc[1] + ' ' + abc[2] + " / " + worldblock.getUnlocalizedName() + ' ' + blockType[pointer].getUnlocalizedName());
                                            }
                                            return false;
                                        }
                                        if (dmg != blockMeta[pointer]) {
                                            if (DEBUG_MODE) {
                                                TecTech.LOGGER.info("Struct-meta-id-error " + xyz[0] + ' ' + xyz[1] + ' ' + xyz[2] + " / " + abc[0] + ' ' + abc[1] + ' ' + abc[2] + " / " + dmg + ' ' + blockMeta[pointer]);
                                            }
                                            return false;
                                        }
                                    } else if ((pointer = block - ' ') >= 0) {
                                        igt = aBaseMetaTileEntity.getIGregTechTileEntity(xyz[0], xyz[1], xyz[2]);
                                        if (igt == null || !addingMethods[pointer].apply(metaTile, igt, casingTextures[pointer])) {
                                            if (worldblock != blockTypeFallback[pointer]) {
                                                if (DEBUG_MODE) {
                                                    TecTech.LOGGER.info("Fallback-struct-block-error " + xyz[0] + ' ' + xyz[1] + ' ' + xyz[2] + " / " + abc[0] + ' ' + abc[1] + ' ' + abc[2] + " / " + worldblock.getUnlocalizedName() + ' ' + (blockTypeFallback[pointer] == null ? "null" : blockTypeFallback[pointer].getUnlocalizedName()));
                                                }
                                                return false;
                                            }
                                            if (dmg != blockMetaFallback[pointer]) {
                                                if (DEBUG_MODE) {
                                                    TecTech.LOGGER.info("Fallback-Struct-meta-id-error " + xyz[0] + ' ' + xyz[1] + ' ' + xyz[2] + " / " + abc[0] + ' ' + abc[1] + ' ' + abc[2] + " / " + dmg + ' ' + blockMetaFallback[pointer]);
                                                }
                                                return false;
                                            }
                                        }
                                    }
                            }
                        } else if (forceCheck) {
                            return false;
                        }
                        abc[0]++;//block in horizontal layer
                    }
                }
                abc[1]++;//horizontal layer
            }
            abc[2]++;//depth
        }
        return true;
    }
    
    @Deprecated
    public static boolean builder(String[][] structure,//0-9 casing, +- air no air, A... ignore 'A'-CHAR+1 blocks
                                  Block[] blockType,//use numbers 0-9 for casing types
                                  byte[] blockMeta,//use numbers 0-9 for casing types
                                  int horizontalOffset, int verticalOffset, int depthOffset,
                                  IGregTechTileEntity tileEntity, ExtendedFacing extendedFacing, boolean hintsOnly) {
        World world = tileEntity.getWorld();
        int baseX = tileEntity.getXCoord();
        int baseY = tileEntity.getYCoord();
        int baseZ = tileEntity.getZCoord();
        if (world == null || (!world.isRemote && hintsOnly)) {
            return false;
        }
        
        //TE Rotation
        int[] xyz = new int[3];
        int[] abc = new int[3];
        int pointer;
        
        //a,b,c - relative to block face!
        //x,y,z - relative to block position on map!
        
        //perform your duties
        abc[2] = -depthOffset;
        for (String[] _structure : structure) {//front to back
            abc[1] = -verticalOffset;
            for (String __structure : _structure) {//top to bottom
                abc[0] = -horizontalOffset;
                for (char block : __structure.toCharArray()) {//left to right
                    if (block < ' ') {//Control chars allow skipping
                        abc[1] += block;
                        break;
                    }
                    if (block > '@')//characters allow to skip check a-1 skip, b-2 skips etc.
                    {
                        abc[0] += block - '@';
                    }//else if (block < '+')//used to mark THINGS
                    //    a++;
                    else if (block == '.')// this TE
                    {
                        abc[0]++;
                    } else {
                        //get x y z from rotation
                        extendedFacing.getWorldOffset(abc, xyz);
                        xyz[0] += baseX;
                        xyz[1] += baseY;
                        xyz[2] += baseZ;
                        
                        //that must be here since in some cases other axis (b,c) controls y
                        if (xyz[1] < 0 || xyz[1] >= 256) {
                            return false;
                        }
                        
                        //Check block
                        if (world.blockExists(xyz[0], xyz[1], xyz[2])) {//this actually checks if the chunk is loaded
                            if (hintsOnly) {
                                switch (block) {
                                    case '-'://must be air
                                        TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], TT_Container_Casings.sHintCasingsTT, 13);
                                        break;
                                    case '+'://must not be air
                                        TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], TT_Container_Casings.sHintCasingsTT, 14);
                                        break;
                                    default: //check for block
                                        if ((pointer = block - '0') >= 0) {
                                            if (world.getBlock(xyz[0], xyz[1], xyz[2]) != blockType[pointer] || blockType[pointer].getDamageValue(world, xyz[0], xyz[1], xyz[2]) != blockMeta[pointer]) {
                                                TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], blockType[pointer], blockMeta[pointer]);
                                            }
                                        } else if ((pointer = block - ' ') >= 0) {
                                            if (pointer < 12) {
                                                TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], TT_Container_Casings.sHintCasingsTT, pointer);
                                            } else {
                                                TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], TT_Container_Casings.sHintCasingsTT, 12);
                                            }
                                        } else {
                                            TecTech.proxy.hint_particle(world, xyz[0], xyz[1], xyz[2], TT_Container_Casings.sHintCasingsTT, 15);
                                        }
                                }
                            } else {
                                switch (block) {
                                    case '-'://must be air
                                        world.setBlock(xyz[0], xyz[1], xyz[2], Blocks.air, 0, 2);
                                        break;
                                    case '+'://must not be air
                                        world.setBlock(xyz[0], xyz[1], xyz[2], TT_Container_Casings.sBlockCasingsTT, 14, 2);
                                        break;
                                    default: //check for block
                                        if ((pointer = block - '0') >= 0) {
                                            if (blockType[pointer] instanceof ICustomBlockSetting) {
                                                ((ICustomBlockSetting) blockType[pointer]).setBlock(world, xyz[0], xyz[1], xyz[2], blockMeta[pointer]);
                                            } else {
                                                world.setBlock(xyz[0], xyz[1], xyz[2], blockType[pointer], blockMeta[pointer], 2);
                                            }
                                        }
                                }
                            }
                        }
                        abc[0]++;//block in horizontal layer
                    }
                }
                abc[1]++;//horizontal layer
            }
            abc[2]++;//depth
        }
        return true;
    }
}
