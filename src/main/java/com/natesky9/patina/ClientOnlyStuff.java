package com.natesky9.patina;

import com.mojang.logging.LogUtils;
import com.natesky9.patina.overlay.VenomOverlay;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import org.slf4j.Logger;

public class ClientOnlyStuff {
    private static final Logger LOGGER = LogUtils.getLogger();

    @SubscribeEvent
    public void RenderGameOverlayEvent(RenderGameOverlayEvent.Post event){
        VenomOverlay.renderVenomOutline();
        LOGGER.info("Running from Client stuff");

    }
}
