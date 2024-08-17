package net.yon.firstmod.item.custon;

import net.minecraft.client.resources.language.I18n;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.yon.firstmod.util.ModTags;
import org.jetbrains.annotations.Nullable;

import java.util.List;


public class RubyDetectorItem extends Item {

    public RubyDetectorItem(Properties pProperties) {
        super(pProperties);
    }

    @Override
    public InteractionResult useOn(UseOnContext pContext) {
        if(!pContext.getLevel().isClientSide()){
            BlockPos positionClicked = pContext.getClickedPos();
            Player player = pContext.getPlayer();
            boolean foundBlock = false;

            for(int i=0; i<=positionClicked.getY()+64; i++){
                BlockState state = pContext.getLevel().getBlockState(positionClicked.below(i));

                //outputValueableCoordinates(positionClicked.below(i), player, state.getBlock());

                if(isValuableBlock(state)){
                    outputValueableCoordinates(positionClicked.below(i), player, state.getBlock());
                    foundBlock = true;
                    break;
                }

            }

            if(!foundBlock){
                player.sendSystemMessage(Component.literal("No Valuable Block Found!"));
            }

            pContext.getItemInHand().hurtAndBreak(1, player, player1 -> player.broadcastBreakEvent(player.getUsedItemHand()));

        }

        return InteractionResult.SUCCESS;
    }

    private void outputValueableCoordinates(BlockPos blockPos, Player player, Block block) {
        player.sendSystemMessage(Component.literal("Found " + I18n.get(block.getDescriptionId()) + " at " +
                "(" + blockPos.getX() + " , " + blockPos.getY() + " , " + blockPos.getZ() + ")"));

    }

    private boolean isValuableBlock(BlockState state) {
        return state.is(ModTags.Blocks.RUBY_DETECTOR_VALUABLES);
    }

    @Override
    public void appendHoverText(ItemStack pStack, @Nullable Level pLevel, List<Component> pTooltipComponents, TooltipFlag pIsAdvanced) {
        pTooltipComponents.add(Component.translatable("tooltip.firstmod.ruby_detector.tooltip"));
        super.appendHoverText(pStack, pLevel, pTooltipComponents, pIsAdvanced);
    }



}
