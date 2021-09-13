package lq2007.gradle.mod_src_gen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ClassTypes {

    // mod
    public static final ClassName SinoCraft = ClassName.get("cx.rain.mc.forgemod.sinocraft", "SinoCraft");
    public static final ClassName PlantType = ClassName.get("cx.rain.mc.forgemod.sinocraft.utility.enumerate", "PlantType");
    public static final ClassName ModGroups = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModGroups");
    public static final ClassName ModItems = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModItems");
    public static final ClassName BlockPlant = ClassName.get("cx.rain.mc.forgemod.sinocraft.block.base", "BlockPlant");
    public static final ClassName ModBlocks = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlocks");
    public static final ClassName ModBlockItems = ClassName.get("cx.rain.mc.forgemod.sinocraft.block", "ModBlockItems");

    // net.minecraft.*
    public static final ClassName Item = ClassName.get("net.minecraft.item", "Item");
    public static final ClassName Food = ClassName.get("net.minecraft.item", "Food");
    public static final ClassName Items = ClassName.get("net.minecraft.item", "Items");
    public static final ClassName ItemTier = ClassName.get("net.minecraft.item", "ItemTier");
    public static final ClassName BlockItem = ClassName.get("net.minecraft.item", "BlockItem");
    public static final ClassName Block = ClassName.get("net.minecraft.block", "Block");
    public static final ClassName Material = ClassName.get("net.minecraft.block.material", "Material");

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
}
