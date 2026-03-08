package com.oldvirus111.businessmod;

import com.oldvirus111.businessmod.block.CompanyTerminalBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, "businessmod");

    // 注册公司终端方块
    public static final RegistryObject<Block> COMPANY_TERMINAL = BLOCKS.register("company_terminal",
            () -> new CompanyTerminalBlock(BlockBehaviour.Properties.copy(Blocks.IRON_BLOCK).noOcclusion()));

    public static final RegistryObject<Item> COMPANY_TERMINAL_ITEM = ModItems.ITEMS.register("company_terminal",
            () -> new BlockItem(COMPANY_TERMINAL.get(), new Item.Properties()));
}
