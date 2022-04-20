package me.give_me_moneyz.theant.core.block;

import net.minecraft.block.*;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.ItemStack;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import javax.annotation.Nullable;
import java.util.Random;

public class AntBlock extends Block {
    public static final DirectionProperty FACING = DirectionalBlock.FACING;

    @Override
    protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
        builder.add(BlockStateProperties.FACING);
    }

    @Override
    public BlockState getStateForPlacement(BlockItemUseContext context) {
        return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing());
    }

    @Override
    @Deprecated
    public void onBlockAdded(BlockState state, World worldIn, BlockPos pos, BlockState oldState, boolean isMoving) {
        worldIn.getPendingBlockTicks().scheduleTick(pos, this, 1);
    }

    @Override
    @Deprecated
    public void tick(BlockState cdt, ServerWorld yn, BlockPos fo, Random random) {
        BlockState cdt6 = yn.getBlockState(fo.down());
        if (cdt6.getBlock() == Blocks.WHITE_CONCRETE) {
            this.move(cdt, yn, fo, Step.CW);
        } else if (cdt6.getBlock() == Blocks.BLACK_CONCRETE) {
            this.move(cdt, yn, fo, Step.CCW);
        }
    }

    private void move(BlockState cdt, ServerWorld yn, BlockPos fo, Step a) {
        Direction ft6 = cdt.get(FACING);
        Direction ft7 = a == Step.CW ? ft6.rotateY() : ft6.rotateYCCW();
        BlockPos fo8 = fo.offset(ft7);
        if (yn.isBlockLoaded(fo8)) {
            switch (a) {
                case CW: {
                    yn.setBlockState(fo.down(), Blocks.BLACK_CONCRETE.getDefaultState(), 19);
                    yn.setBlockState(fo, Blocks.AIR.getDefaultState(), 3);
                    yn.setBlockState(fo8, cdt.with(FACING, ft7), 3);
                    break;
                }
                case CCW: {
                    yn.setBlockState(fo.down(), Blocks.WHITE_CONCRETE.getDefaultState(), 19);
                    yn.setBlockState(fo, Blocks.AIR.getDefaultState(), 3);
                    yn.setBlockState(fo8, cdt.with(FACING, ft7), 3);
                }
            }
        }
    }

    /*
    @Override
    public void onPlace(BlockState cdt1, ServerWorld bnx, BlockPos fo, BlockState cdt4, boolean boolean5) {
        bnx.getPendingBlockTicks().scheduleTick(fo, this, 1);
    }
     */


    enum Step {
        CW,
        CCW
    }

    public AntBlock(AbstractBlock.Properties properties) {
        super(properties);
        this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
    }
}