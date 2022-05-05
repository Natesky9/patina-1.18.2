package com.natesky9.patina.overlay;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.BufferBuilder;
import com.mojang.blaze3d.vertex.DefaultVertexFormat;
import com.mojang.blaze3d.vertex.Tesselator;
import com.mojang.blaze3d.vertex.VertexFormat;
import com.mojang.logging.LogUtils;
import com.natesky9.patina.init.ModEffects;
import net.minecraft.client.Minecraft;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.effect.MobEffectInstance;
import org.slf4j.Logger;

public class VenomOverlay {
    public static final ResourceLocation VENOM_OUTLINE_LOCATION = new ResourceLocation("patina:textures/gui/venom_outline.png");
    private static final Logger LOGGER = LogUtils.getLogger();


    public static void renderVenomOutline(){
        //if the player does not have venom, exit
        if (!Minecraft.getInstance().player.hasEffect(ModEffects.VENOM.get())) return;

        float playerMaxHealth = Minecraft.getInstance().player.getMaxHealth();
        MobEffectInstance mobEffect = Minecraft.getInstance().player.getEffect(ModEffects.VENOM.get());
        int venomAmount = mobEffect.getAmplifier();
        float venomPercent = venomAmount/playerMaxHealth;

        renderTextureOverlay(VENOM_OUTLINE_LOCATION,venomPercent);
    }
    private static void renderTextureOverlay(ResourceLocation location,float percent){
        int screenWidth = Minecraft.getInstance().getWindow().getGuiScaledWidth();
        int screenHeight = Minecraft.getInstance().getWindow().getGuiScaledHeight();

        RenderSystem.enableBlend();

        RenderSystem.disableDepthTest();
        RenderSystem.depthMask(false);
        RenderSystem.defaultBlendFunc();
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        LOGGER.info("venomPercent: " + percent);
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, percent);
        RenderSystem.setShaderTexture(0, location);
        Tesselator tesselator = Tesselator.getInstance();
        BufferBuilder bufferbuilder = tesselator.getBuilder();
        bufferbuilder.begin(VertexFormat.Mode.QUADS, DefaultVertexFormat.POSITION_TEX);
        bufferbuilder.vertex(0.0D, screenHeight, -90.0D).uv(0.0F, 1.0F).endVertex();
        bufferbuilder.vertex(screenWidth, screenHeight, -90.0D).uv(1.0F, 1.0F).endVertex();
        bufferbuilder.vertex(screenWidth, 0.0D, -90.0D).uv(1.0F, 0.0F).endVertex();
        bufferbuilder.vertex(0.0D, 0.0D, -90.0D).uv(0.0F, 0.0F).endVertex();
        tesselator.end();
        RenderSystem.depthMask(true);
        RenderSystem.enableDepthTest();
        RenderSystem.setShaderColor(1.0F, 1.0F, 1.0F, 1.0F);
    }
}
