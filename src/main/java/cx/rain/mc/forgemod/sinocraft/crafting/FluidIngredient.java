package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IFluidIngredient;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.FluidTags;
import net.minecraft.tags.ITag;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fluids.FluidStack;

public class FluidIngredient implements IFluidIngredient {

    private final ResourceLocation loc;
    // fluid
    private final Fluid fluid;
    // tag
    private ITag<Fluid> tag;

    private final int amount;
    private final int type;

    FluidIngredient(Fluid fluid, int amount) {
        this.loc = fluid.delegate.name();
        this.fluid = fluid;
        this.amount = amount;
        this.type = 0;
    }

    FluidIngredient(ResourceLocation tag, int amount) {
        this.fluid = Fluids.EMPTY;
        this.loc = tag;
        this.tag = FluidTags.getCollection().getTagByID(tag);
        this.amount = amount;
        this.type = 1;
    }

    @Override
    public int getType() {
        return type;
    }

    @Override
    public Fluid getFluid() {
        return fluid;
    }

    @Override
    public ITag<Fluid> getTag() {
        return tag;
    }

    @Override
    public ResourceLocation getResourceLocation() {
        return loc;
    }

    @Override
    public int getAmount() {
        return amount;
    }

    @Override
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

    @Override
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
