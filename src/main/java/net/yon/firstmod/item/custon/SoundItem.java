package net.yon.firstmod.item.custon;

import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.yon.firstmod.item.ModItems;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SoundItem extends Item {

    private final SoundEvent sound;
    private final Item item;

    public SoundItem(Item item, SoundEvent sound, Properties pProperties) {
        super(pProperties);
        this.sound = sound;
        this.item = item;
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        pPlayer.playSound(sound,1f, 1f);

        if(!pLevel.isClientSide){
            pLevel.playLocalSound(new BlockPos(pPlayer.getBlockX(), pPlayer.getBlockY(), pPlayer.getBlockZ()),
                    sound, SoundSource.MASTER, 1f, 1f, true);
            pPlayer.spawnAtLocation(item, 10);
            pPlayer.getItemInHand(pUsedHand).hurtAndBreak(1, pPlayer, player -> player.broadcastBreakEvent(player.getUsedItemHand()));
        }

        return super.use(pLevel, pPlayer, pUsedHand);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.firstmod.meow.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }
}
