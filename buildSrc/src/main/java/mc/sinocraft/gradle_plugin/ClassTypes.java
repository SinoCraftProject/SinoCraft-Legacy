package mc.sinocraft.gradle_plugin;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ClassTypes {

    // java
    public static final ClassName Any = ClassName.get("", "?");
    // cx.rain.mc.forgemod.sinocraft.*
    public static final ClassName SinoCraft = ClassName.get("cx.rain.mc.forgemod.sinocraft", "SinoCraft");
    public static final ClassName ModBlocks = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlocks");
    public static final ClassName ModBlock2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlock2");
    public static final ClassName ModBlockItems = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlockItems");
    public static final ClassName ModBlockItems2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlockItems2");
    public static final ClassName BlockLeaves = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLeaves");
    public static final ClassName BlockLog = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLog");
    public static final ClassName BlockMarble = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockMarble");
    public static final ClassName BlockPlant = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockPlant");
    public static final ClassName BlockPlank = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockPlank");
    public static final ClassName BlockSapling = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockSapling");
    public static final ClassName BlockLeavesGrowable = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockLeavesGrowable");
    public static final ClassName ModTileEntities = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.tileentity", "ModTileEntities");
    public static final ClassName ModTileEntities2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.tileentity", "ModTileEntities2");
    public static final ClassName ModFluids = ClassName.get("cx.rain.mc.forgemod.sinocraft.fluid", "ModFluids");
    public static final ClassName ModGroups = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModGroups");
    public static final ClassName ModItems = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModItems");
    public static final ClassName ModItems2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModItems2");
    public static final ClassName ItemKnife = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ItemKnife");
    public static final ClassName ItemSeed = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemSeed");
    public static final ClassName ItemBucket = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.base", "ItemBucket");
    public static final ClassName EnumFoods = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "EnumFoods");
    public static final ClassName ItemFood2 = ClassName.get("cx.rain.mc.forgemod.sinocraft.item.food", "ItemFood2");
    public static final ClassName MarbleType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "MarbleType");
    public static final ClassName PlantType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "PlantType");
    public static final ClassName TreeType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "TreeType");
    // net.minecraft.*
    public static final ClassName FlowingFluidBlock = ClassName.get("net.minecraft.block", "FlowingFluidBlock");
    public static final ClassName Material = ClassName.get("net.minecraft.block.material", "Material");
    public static final ClassName Item = ClassName.get("net.minecraft.item", "Item");
    public static final ClassName Food = ClassName.get("net.minecraft.item", "Food");
    public static final ClassName Items = ClassName.get("net.minecraft.item", "Items");
    public static final ClassName ItemTier = ClassName.get("net.minecraft.item", "ItemTier");
    public static final ClassName BlockItem = ClassName.get("net.minecraft.item", "BlockItem");
    public static final ClassName BlockNamedItem = ClassName.get("net.minecraft.item", "BlockNamedItem");
    public static final ClassName Block = ClassName.get("net.minecraft.block", "Block");
    public static final ClassName TileEntityType = ClassName.get("net.minecraft.tileentity", "TileEntityType");
    // net.minecraftforge.*
    public static final ClassName ForgeRegistries = ClassName.get("net.minecraftforge.registries", "ForgeRegistries");
    public static final ClassName DeferredRegister = ClassName.get("net.minecraftforge.registries", "DeferredRegister");
    public static final ClassName RegistryObject = ClassName.get("net.minecraftforge.fml", "RegistryObject");
    public static final ClassName IEventBus = ClassName.get("net.minecraftforge.eventbus.api", "IEventBus");

    public static ParameterizedTypeName DeferredRegister(TypeName type) {
        return ParameterizedTypeName.get(DeferredRegister, type);
    }

    public static ParameterizedTypeName RegistryObject(TypeName type) {
        return ParameterizedTypeName.get(RegistryObject, type);
    }

    public static ParameterizedTypeName TileEntityType(TypeName type) {
        return ParameterizedTypeName.get(TileEntityType, type);
    }
}
