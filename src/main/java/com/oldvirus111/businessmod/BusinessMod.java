package com.oldvirus111.businessmod;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

// 这个注解告诉 Forge 这是一个模组的主类，里面的字符串必须和 mods.toml 里的 modId 一致
@Mod("businessmod")
public class BusinessMod {
    public static final String MOD_ID = "businessmod";

    public BusinessMod() {
        // 获取模组的事件总线
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        // 注册事件总线，让 Forge 知道我们要添加方块和物品
        ModBlocks.BLOCKS.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);

        // 注册到 Forge 的常规事件总线
        MinecraftForge.EVENT_BUS.register(this);
    }
}