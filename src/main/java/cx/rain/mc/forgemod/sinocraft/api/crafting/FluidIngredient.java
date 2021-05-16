package cx.rain.mc.forgemod.sinocraft.api.crafting;

import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class FluidIngredient {

    public final ResourceLocation loc;
    // fluid
    public final Fluid fluid;
    // tag
    private ITag<Fluid> tag;

    public final int amount;
    public final int type;

    public FluidIngredient(Fluid fluid, int amount) {
        this.loc = fluid.delegate.name();
        this.fluid = fluid;
        this.amount = amount;
        this.type = 0;
    }

    public FluidIngredient(ResourceLocation tag, int amount) {
        this.fluid = Fluids.EMPTY;
        this.loc = tag;
        this.tag = FluidTags.getCollection().getTagByID(tag);
        this.amount = amount;
        this.type = 1;
    }

    public boolean match(FluidStack stack) {
        if (stack.getAmount() >= amount) {
            if (type == 0) {
                return stack.getFluid() == fluid;
            } else {
                return tag.contains(stack.getFluid());
            }
        }
        return false;
    }

    public static FluidIngredient read(PacketBuffer buffer) {
        if (buffer.readBoolean()) {
            Fluid fluid = buffer.readRegistryId();
            int amount = buffer.readInt();
            return new FluidIngredient(fluid, amount);
        } else {
            ResourceLocation loc = buffer.readResourceLocation();
            int amount = buffer.readInt();
            return new FluidIngredient(loc, amount);
        }
    }

    public void write(PacketBuffer buffer) {
        buffer.writeBoolean(type == 0);
        if (type == 0) {
            buffer.writeRegistryId(fluid);
        } else {
            buffer.writeResourceLocation(loc);
        }
        buffer.writeInt(amount);
    }
}
