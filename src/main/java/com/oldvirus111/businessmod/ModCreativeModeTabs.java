package com.oldvirus111.businessmod;

import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {
    // 创建一个专门用于注册创造模式标签页的 DeferredRegister
    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, BusinessMod.MOD_ID);

    // 注册我们自定义的标签页
    public static final RegistryObject<CreativeModeTab> BUSINESS_TAB = CREATIVE_MODE_TABS.register("business_tab",
            () -> CreativeModeTab.builder()
                    // 设置标签页的图标为我们之前做的“公司终端”
                    .icon(() -> new ItemStack(ModBlocks.COMPANY_TERMINAL.get()))
                    // 设置标签页的名字（需要在语言文件中配置）
                    .title(Component.translatable("creativetab.businessmod.business_tab"))
                    // 在这里向该标签页中添加物品
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.COMPANY_TERMINAL_ITEM.get()); // 添加公司终端

                        // 以后如果有新物品，继续在这里添加即可，例如：
                        // output.accept(ModItems.NEW_ITEM.get());
                    })
                    .build());
}