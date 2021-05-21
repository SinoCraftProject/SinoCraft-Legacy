package cx.rain.mc.forgemod.sinocraft.crafting;

import cx.rain.mc.forgemod.sinocraft.api.crafting.IFluidIngredient;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.Fluids;
import net.minecraft.network.PacketBuffer;
import net.minecraft.tags.ITag;
import net.minecraft.tags.TagCollectionManager;
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

    public FluidIngredient(Fluid fluid, int amount) {
        this.loc = fluid.delegate.name();
        this.fluid = fluid;
        this.amount = amount;
        this.type = 0;
    }

    public FluidIngredient(ITag<Fluid> tag, int amount) {
        this.fluid = Fluids.EMPTY;
        this.loc = TagCollectionManager.getManager().getFluidTags().getValidatedIdFromTag(tag);
        this.tag = tag;
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
            ResourceLocation tagName = buffer.readResourceLocation();
            ITag<Fluid> tag = TagCollectionManager.getManager().getFluidTags().get(tagName);
            if (tag == null) {
                throw new NullPointerException("Can't find fluid tag named " + tagName);
            }
            int amount = buffer.readInt();
            return new FluidIngredient(tag, amount);
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
