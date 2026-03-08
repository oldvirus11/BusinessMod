package com.oldvirus111.businessmod.gui;

import com.oldvirus111.businessmod.ModBlocks;
import com.oldvirus111.businessmod.ModMenus;
import com.oldvirus111.businessmod.blockentity.CompanyTerminalBlockEntity;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.entity.BlockEntity;

public class CompanyTerminalMenu extends AbstractContainerMenu {
    public final CompanyTerminalBlockEntity blockEntity;

    public CompanyTerminalMenu(int pContainerId, Inventory inv, FriendlyByteBuf extraData) {
        this(pContainerId, inv, inv.player.level().getBlockEntity(extraData.readBlockPos()));
    }

    public CompanyTerminalMenu(int pContainerId, Inventory inv, BlockEntity entity) {
        super(ModMenus.COMPANY_TERMINAL_MENU.get(), pContainerId);
        this.blockEntity = (CompanyTerminalBlockEntity) entity;
    }

    @Override
    public ItemStack quickMoveStack(Player pPlayer, int pIndex) {
        return ItemStack.EMPTY; // 我们没有物品槽，所以直接返回空
    }

    @Override
    public boolean stillValid(Player pPlayer) {
        return stillValid(ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos()),
                pPlayer, ModBlocks.COMPANY_TERMINAL.get());
    }
}