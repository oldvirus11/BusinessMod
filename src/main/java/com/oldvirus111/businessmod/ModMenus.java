package com.oldvirus111.businessmod;

import com.oldvirus111.businessmod.gui.CompanyTerminalMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenus {
    public static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, BusinessMod.MOD_ID);

    public static final RegistryObject<MenuType<CompanyTerminalMenu>> COMPANY_TERMINAL_MENU =
            MENUS.register("company_terminal_menu", () -> IForgeMenuType.create(CompanyTerminalMenu::new));
}