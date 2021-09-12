package lq2007.gradle.mod_src_gen;

import com.squareup.javapoet.ClassName;
import com.squareup.javapoet.ParameterizedTypeName;
import com.squareup.javapoet.TypeName;

public class ClassTypes {

    // mod
    public static final ClassName SinoCraft = ClassName.get("cx.rain.mc.forgemod.sinocraft", "SinoCraft");
    public static final ClassName ModGroups = ClassName.get("cx.rain.mc.forgemod.sinocraft.item", "ModGroups");

    // net.minecraft.*
    public static final ClassName Item = ClassName.get("net.minecraft.item", "Item");
    public static final ClassName ItemTier = ClassName.get("net.minecraft.item", "ItemTier");
    public static final ClassName FlowingFluid = ClassName.get("net.minecraft.fluid", "FlowingFluid");

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
