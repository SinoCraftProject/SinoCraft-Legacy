package cx.rain.mc.forgemod.sinocraft.utility;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;

import java.util.Objects;

/**
 * 记录一个物品所属玩家和所在物品槽位置，并附带存储一个额外数据不参与比较
 */
public class PlayerSlot<T> {

    public final Hand hand;
    public final int index;
    public final PlayerEntity player;
    public T extra;

    public PlayerSlot(PlayerEntity player, Hand hand) {
        this.hand = hand;
        this.index = hand == Hand.MAIN_HAND ? player.inventory.currentItem : 0;
        this.player = player;
    }

    public PlayerSlot(PlayerEntity player, Hand hand, T extra) {
        this.hand = hand;
        this.index = hand == Hand.MAIN_HAND ? player.inventory.currentItem : 0;
        this.player = player;
        this.extra = extra;
    }

    public boolean equals(PlayerEntity player, Hand hand) {
        if (hand != this.hand || !player.getGameProfile().getId().equals(this.player.getGameProfile().getId())) {
            return false;
        }
        return hand != Hand.MAIN_HAND || index == player.inventory.currentItem;
    }

    public ItemStack getItem(PlayerEntity player) {
        if (hand == Hand.OFF_HAND) {
            return player.getHeldItemOffhand();
        } else {
            return player.inventory.mainInventory.get(index);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlayerSlot<?> that = (PlayerSlot<?>) o;
        return index == that.index && hand == that.hand && Objects.equals(player.getGameProfile().getId(), that.player.getGameProfile().getId());
    }

    @Override
    public int hashCode() {
        return Objects.hash(hand, index, player);
    }
}
