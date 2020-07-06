package cx.rain.mc.forgemod.culturecraft.kitchen;

import cx.rain.mc.forgemod.culturecraft.api.annotation.ModBlock;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.state.BooleanProperty;
import net.minecraft.state.IProperty;
import net.minecraft.state.IntegerProperty;

/**
 * You need put a pot on it
 *
 * @author jirufengyu
 */
@ModBlock(name="hearth")
public class Hearth extends Block {
    static IProperty<Integer> STATE= IntegerProperty.create("act",0,2);//if a fan
    public Hearth(){
        super(Properties.create(Material.ROCK));
    }
    public Hearth(Properties properties) {
        super(properties);
    }

}
