package com.oldvirus111.businessmod.blockentity;

import com.oldvirus111.businessmod.ModBlockEntities;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class CompanyTerminalBlockEntity extends BlockEntity implements MenuProvider {
    private String companyName = "";
    private boolean isRegistered = false;
    private int currentProfit = 0;
    // 使用数组或列表存储最近几天的盈利数据，用于折线图 (例如存储最近7天)
    private int[] dailyProfits = new int[7];

    public CompanyTerminalBlockEntity(BlockPos pPos, BlockState pBlockState) {
        super(ModBlockEntities.COMPANY_TERMINAL_BE.get(), pPos, pBlockState);
    }

    // 保存数据到NBT，确保退出游戏后数据不丢失
    @Override
    protected void saveAdditional(CompoundTag pTag) {
        super.saveAdditional(pTag);
        pTag.putString("CompanyName", companyName);
        pTag.putBoolean("IsRegistered", isRegistered);
        pTag.putInt("CurrentProfit", currentProfit);
        pTag.putIntArray("DailyProfits", dailyProfits);
    }

    // 从NBT加载数据
    @Override
    public void load(CompoundTag pTag) {
        super.load(pTag);
        this.companyName = pTag.getString("CompanyName");
        this.isRegistered = pTag.getBoolean("IsRegistered");
        this.currentProfit = pTag.getInt("CurrentProfit");
        if (pTag.contains("DailyProfits")) {
            this.dailyProfits = pTag.getIntArray("DailyProfits");
        }
    }

    // Getter 和 Setter (省略部分标准getter/setter以保持整洁)
    public boolean isRegistered() { return isRegistered; }
    public String getCompanyName() { return companyName; }

    public void registerCompany(String name) {
        this.companyName = name;
        this.isRegistered = true;
        setChanged(); // 标记数据已更改，需要保存
    }

    @Nullable
    @Override
    public AbstractContainerMenu createMenu(int pContainerId, Inventory pPlayerInventory, Player pPlayer) {
        return new CompanyTerminalMenu(pContainerId, pPlayerInventory, this);
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable("block.businessmod.company_terminal");
    }

    // 同步数据到客户端（用于GUI显示）
    @Override
    public CompoundTag getUpdateTag() {
        CompoundTag tag = new CompoundTag();
        saveAdditional(tag);
        return tag;
    }

    @Nullable
    @Override
    public Packet<ClientGamePacketListener> getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }
}