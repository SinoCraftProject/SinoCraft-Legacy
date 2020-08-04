package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.block.Block;
import net.minecraft.item.AxeItem;
import net.minecraftforge.fml.common.ObfuscationReflectionHelper;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.HashMap;
import java.util.Map;

public class ProtectedHelper {
    public static void addAxeStrippingMap(Block originWood, Block outputWood) {
        try {

            Field field = ObfuscationReflectionHelper.findField(AxeItem.class,"field_203176_a");
            field.setAccessible(true);

            Field modifiersField = Field.class.getDeclaredField("modifiers");
            modifiersField.setAccessible(true);
            modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);

            Map<Block, Block> tmpStrippingMap = new HashMap<>();
            tmpStrippingMap.putAll((Map<Block, Block>) field.get(null));
            tmpStrippingMap.put(originWood, outputWood);
            field.set(null, tmpStrippingMap);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            ex.printStackTrace();
        }
    }

    public static <T> Object getStaticField(Class<? super T> clazz,String name,boolean isFinal){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            if(isFinal){
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            return field.get(null);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object getStaticField(Class<? super T> clazz, String name){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            return field.get(null);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object getField(Class<? super T> clazz,Object obj,String name,boolean isFinal){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            if(isFinal){
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            return field.get(obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> Object getField(Class<? super T> clazz,Object obj,String name){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            return field.get(obj);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static <T> void setStaticField(Class<? super T> clazz,String name,Object value,boolean isFinal){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            if(isFinal){
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            field.set(null,value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void setStaticField(Class<? super T> clazz,String name,Object value){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            field.set(null,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void setField(Class<? super T> clazz,Object obj,String name,Object value,boolean isFinal){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            if(isFinal){
                Field modifiersField = Field.class.getDeclaredField("modifiers");
                modifiersField.setAccessible(true);
                modifiersField.setInt(field, field.getModifiers() & ~Modifier.FINAL);
            }

            field.set(obj,value);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static <T> void setField(Class<? super T> clazz,Object obj,String name,Object value){
        try {
            Field field = ObfuscationReflectionHelper.<T>findField(clazz,name);
            field.setAccessible(true);

            field.set(obj,value);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
