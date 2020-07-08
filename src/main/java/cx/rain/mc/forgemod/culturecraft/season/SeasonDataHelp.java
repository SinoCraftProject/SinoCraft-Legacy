package cx.rain.mc.forgemod.culturecraft.season;

import cx.rain.mc.forgemod.culturecraft.season.interfaces.ISeasonState;
import net.minecraft.world.World;

/*
*帮忙修复isClientSide不知道写什么
 */
/*
public class SeasonDataHelp {
    public static SeasonDataHelp.ISeasonDataProvider dataProvider;

    public SeasonDataHelp() {
    }

    public static ISeasonState getSeasonState(World world) {
        ISeasonState data;
        if (!world.isClientSide) {
            data = dataProvider.getServerSeasonState(world);
        } else {
            data = dataProvider.getClientSeasonState();
        }

        return data;
    }

    public interface ISeasonDataProvider {
        ISeasonState getServerSeasonState(World var1);

        ISeasonState getClientSeasonState();
    }
}*/