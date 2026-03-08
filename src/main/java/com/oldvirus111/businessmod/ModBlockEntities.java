package com.oldvirus111.businessmod;

import com.oldvirus111.businessmod.blockentity.CompanyTerminalBlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, "businessmod");

    // 注册方块实体
    public static final RegistryObject<BlockEntityType<CompanyTerminalBlockEntity>> COMPANY_TERMINAL_BE =
            BLOCK_ENTITIES.register("company_terminal_be", () ->
                    BlockEntityType.Builder.of(CompanyTerminalBlockEntity::new,
                            ModBlocks.COMPANY_TERMINAL.get()).build(null));
}