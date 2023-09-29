package com.natesky9.patina.Block.ApplianceIcebox;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.datafixers.util.Pair;
import com.natesky9.patina.Patina;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.client.resources.MobEffectTextureManager;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.StringUtil;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffectUtil;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SuspiciousStewItem;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;

import java.awt.*;
import java.sql.Time;
import java.time.Clock;
import java.util.List;

public class ApplianceIceboxScreen extends AbstractContainerScreen<ApplianceIceboxMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(Patina.MODID,"textures/gui/appliance_icebox.png");
    private static final ResourceLocation FOOD =
            new ResourceLocation("textures/gui/icons.png");
    //food icon coords
    final int fullx = 52;
    final int fully = 27;
    final int halfx = 61;
    final int halfy = 27;



    public ApplianceIceboxScreen(ApplianceIceboxMenu pMenu, Inventory pPlayerInventory, Component pTitle) {
        super(pMenu, pPlayerInventory, pTitle);
    }

    @Override
    public void render(PoseStack pPoseStack, int pMouseX, int pMouseY, float pPartialTick) {
        super.render(pPoseStack, pMouseX, pMouseY, pPartialTick);
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);

        //

        imageWidth = 175;
        imageHeight = 183;
        int x = (width-imageWidth)/2+8;
        int y = (height-imageHeight)/2+8;
        //draw the background
        //blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);

        if (this.hoveredSlot != null && this.hoveredSlot.hasItem())
        {
            //draw the tooltip off to the side

            //renderTooltip(pPoseStack,hoveredSlot.getItem(),getGuiLeft()+imageWidth,getGuiTop());

            int yoffset = 0;
            ItemStack stack = hoveredSlot.getItem();
            MobEffectTextureManager mobeffecttexturemanager = this.minecraft.getMobEffectTextures();

            if (stack.getItem() instanceof SuspiciousStewItem stewItem)
            {
                //do stew stuff?
                //why is stew so fucked
                //mojank moment
            }
            //check if food
            if (stack.getFoodProperties(minecraft.player) != null)
            {
                RenderSystem.setShaderTexture(0,FOOD);
                y += 8;
                //draw the hunger haunches
                int hunger = stack.getFoodProperties(minecraft.player).getNutrition();
                for (int i = 0; i < hunger; i += 2) {
                    if (i != hunger - 1)
                        blit(pPoseStack, x + i * 4, y, fullx, fully, 8,8);
                    else
                        blit(pPoseStack, x + i * 4, y, halfx, halfy, 8,8);
                }
                //check if food effects
                List<Pair<MobEffectInstance, Float>> effects = stack.getFoodProperties(minecraft.player).getEffects();
                if (!effects.isEmpty()) {
                    yoffset += 8;
                    int i = 0;
                    for (Pair<MobEffectInstance, Float> pair : effects) {
                        MobEffect effect = pair.getFirst().getEffect();
                        float chance = pair.getSecond()*100;
                        TextureAtlasSprite textureAtlasSprite = mobeffecttexturemanager.get(effect);
                        RenderSystem.setShaderTexture(0, textureAtlasSprite.atlasLocation());
                        blit(pPoseStack, x, y + yoffset+ i * 16, 0, 16, 16, textureAtlasSprite);
                        drawString(pPoseStack,font,chance + "%",x+16+4,y+yoffset+i * 16+4, Color.WHITE.getRGB());
                        i++;
                    }
                }
            }
            Potion potion = PotionUtils.getPotion(stack);
            if (potion != Potions.EMPTY)
            {
                yoffset += 8;
                int i = 0;
                for (MobEffectInstance instance:potion.getEffects())
                {
                    MobEffect effect = instance.getEffect();
                    int potency = instance.getAmplifier();
                    TextureAtlasSprite textureAtlasSprite = mobeffecttexturemanager.get(effect);
                    RenderSystem.setShaderTexture(0,textureAtlasSprite.atlasLocation());
                    int p;
                    for (p = 0;p < potency+1;p++) {
                        blit(pPoseStack, x+p*4, y + yoffset + i * 16, 0, 16, 16, textureAtlasSprite);
                    }

                    if (!instance.getEffect().isInstantenous()) {
                        String duration = StringUtil.formatTickDuration(instance.getDuration());
                        drawString(pPoseStack, font, duration, x+p*4+20, y + yoffset + i * 16 +4, 16777215);
                    }
                    i++;
                }
            }
        }
    }

    @Override
    protected void renderBg(PoseStack pPoseStack, float pPartialTick, int pMouseX, int pMouseY) {
        RenderSystem.setShader(GameRenderer::getPositionTexShader);
        RenderSystem.setShaderColor(1,1,1,1);
        RenderSystem.setShaderTexture(0,TEXTURE);
        int x = (width-imageWidth)/2;
        int y = (height-imageHeight)/2;
        //draw the background
        blit(pPoseStack,x,y,0,0,imageWidth,imageHeight);
    }
}
