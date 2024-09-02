package net.yon.firstmod.block.custom;

import net.minecraft.world.level.ItemLike;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.CropBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.yon.firstmod.item.ModItems;

public class StarfruitCropBlock extends CropBlock {
    public static final int MAX_AGE =4;
    public static final IntegerProperty AGE = BlockStateProperties.AGE_4;

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> pBuilder) {
        pBuilder.add(AGE);
    }

    @Override
    public int getMaxAge() {
        return MAX_AGE;
    }

    @Override
    public IntegerProperty getAgeProperty() {
        return AGE;
    }

    @Override
    protected ItemLike getBaseSeedId() {
        return ModItems.STARFRUIT_SEEDS.get();
    }

    public StarfruitCropBlock(Properties pProperties) {
        super(pProperties);
    }
}
