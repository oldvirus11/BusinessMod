package com.oldvirus111.businessmod.gui;

import com.mojang.blaze3d.systems.RenderSystem;
import com.oldvirus111.businessmod.BusinessMod;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.Button;
import net.minecraft.client.gui.components.EditBox;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;

public class CompanyTerminalScreen extends AbstractContainerScreen<CompanyTerminalMenu> {
    // 准备一张 256x256 的材质，画一个原版灰白色的底板
    private static final ResourceLocation TEXTURE = new ResourceLocation(BusinessMod.MOD_ID, "textures/gui/company_terminal.png");
    private EditBox nameInput;

    public CompanyTerminalScreen(CompanyTerminalMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
        this.imageWidth = 176;
        this.imageHeight = 166;
    }

    @Override
    protected void init() {
        super.init();
        if (!menu.blockEntity.isRegistered()) {
            // 初始化输入框和注册按钮
            this.nameInput = new EditBox(this.font, leftPos + 38, topPos + 50, 100, 20, Component.literal("Company Name"));
            this.addRenderableWidget(this.nameInput);

            this.addRenderableWidget(Button.builder(Component.literal("注册"), button -> {
                // TODO: 下一步我们需要发包给服务器来保存这个名字
                System.out.println("注册公司: " + nameInput.getValue());
                // 模拟注册成功，真实情况需要等服务器回调
                menu.blockEntity.registerCompany(nameInput.getValue());
                this.rebuildWidgets(); // 刷新界面
            }).bounds(leftPos + 58, topPos + 80, 60, 20).build());
        }
    }

    @Override
    protected void renderBg(GuiGraphics pGuiGraphics, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
        pGuiGraphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);
    }

    @Override
    public void render(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY, float pPartialTick) {
        renderBackground(pGuiGraphics);
        super.render(pGuiGraphics, pMouseX, pMouseY, pPartialTick);
        renderTooltip(pGuiGraphics, pMouseX, pMouseY);
    }

    @Override
    protected void renderLabels(GuiGraphics pGuiGraphics, int pMouseX, int pMouseY) {
        if (!menu.blockEntity.isRegistered()) {
            pGuiGraphics.drawString(this.font, "请先注册您的公司:", 40, 30, 0x404040, false);
        } else {
            // 已注册：仪表盘界面
            pGuiGraphics.drawString(this.font, "公司: " + menu.blockEntity.getCompanyName(), 10, 10, 0x000000, false);
            pGuiGraphics.drawString(this.font, "当前盈利: $" + menu.blockEntity.getCurrentProfit(), 10, 25, 0x007700, false);
            pGuiGraphics.drawString(this.font, "全服排行: 第 1 名", 10, 40, 0x0000AA, false);

            // 绘制简易每日盈利折线图
            drawProfitChart(pGuiGraphics, 10, 60, 150, 80);
        }
    }

    private void drawProfitChart(GuiGraphics graphics, int x, int y, int width, int height) {
        // 画坐标轴底色 (深灰色背景)
        graphics.fill(x, y, x + width, y + height, 0xFF222222);

        // 模拟/读取每日数据
        int[] profits = {10, 50, 30, 90, 40, 120, 200}; // 示例数据，真实应通过 menu.blockEntity 获取
        int maxProfit = 200; // 动态计算最大值

        // 绘制折线（利用连点成线，或使用 GuiGraphics 的内部方法绘制线段）
        for (int i = 0; i < profits.length - 1; i++) {
            int x1 = x + (i * (width / (profits.length - 1)));
            int y1 = y + height - (int) ((profits[i] / (float) maxProfit) * height);

            int x2 = x + ((i + 1) * (width / (profits.length - 1)));
            int y2 = y + height - (int) ((profits[i+1] / (float) maxProfit) * height);

            // 在 Forge 1.20 中画线需要利用 Tesselator 或直接填充极细的矩形
            // 这里我们用一个简易的小正方形代表数据点
            graphics.fill(x1 - 1, y1 - 1, x1 + 2, y1 + 2, 0xFF00FF00); // 绿色的点
        }
        // 最后一个点
        int lastX = x + width;
        int lastY = y + height - (int) ((profits[profits.length-1] / (float) maxProfit) * height);
        graphics.fill(lastX - 1, lastY - 1, lastX + 2, lastY + 2, 0xFF00FF00);
    }
}
