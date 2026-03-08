package com.oldvirus111.businessmod.block;

import com.oldvirus111.businessmod.blockentity.CompanyTerminalBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.BaseEntityBlock;
import net.minecraft.world.level.block.RenderShape;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import org.jetbrains.annotations.Nullable;

public class CompanyTerminalBlock extends BaseEntityBlock {

    public CompanyTerminalBlock(Properties pProperties) {
        super(pProperties);
    }

    @Nullable
    @Override
    public BlockEntity newBlockEntity(BlockPos pPos, BlockState pState) {
        return new CompanyTerminalBlockEntity(pPos, pState);
    }

    @Override
    public RenderShape getRenderShape(BlockState pState) {
        return RenderShape.MODEL; // 确保方块能正常渲染模型
    }

    @Override
    public InteractionResult use(BlockState pState, Level pLevel, BlockPos pPos, Player pPlayer, InteractionHand pHand, BlockHitResult pHit) {
        if (!pLevel.isClientSide()) {
            BlockEntity entity = pLevel.getBlockEntity(pPos);
            if (entity instanceof CompanyTerminalBlockEntity && pPlayer instanceof ServerPlayer serverPlayer) {
                // 触发打开 GUI
                NetworkHooks.openScreen(serverPlayer, (CompanyTerminalBlockEntity) entity, pPos);
            }
        }
        return InteractionResult.sidedSuccess(pLevel.isClientSide());
    }
}