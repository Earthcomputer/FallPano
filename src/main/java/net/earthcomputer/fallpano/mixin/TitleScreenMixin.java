package net.earthcomputer.fallpano.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.screen.TitleScreen;
import net.minecraft.client.options.NarratorMode;
import net.minecraft.client.util.NarratorManager;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(TitleScreen.class)
public class TitleScreenMixin {

    @Inject(method = "init", at = @At("HEAD"))
    private void onInit(CallbackInfo ci) {
        NarratorMode prevMode = MinecraftClient.getInstance().options.narrator;
        MinecraftClient.getInstance().options.narrator = NarratorMode.ALL;
        NarratorManager.INSTANCE.narrate("Ooooo Spooky");
        MinecraftClient.getInstance().options.narrator = prevMode;
    }

}
