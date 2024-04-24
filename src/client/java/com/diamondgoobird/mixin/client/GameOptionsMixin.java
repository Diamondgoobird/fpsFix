package com.diamondgoobird.mixin.client;

import com.mojang.serialization.Codec;
import net.minecraft.client.option.GameOptions;
import net.minecraft.client.option.SimpleOption;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(GameOptions.class)
public class GameOptionsMixin {
    @ModifyArgs(
            method = "<init>",
            at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/option/SimpleOption;<init>(Ljava/lang/String;Lnet/minecraft/client/option/SimpleOption$TooltipFactory;Lnet/minecraft/client/option/SimpleOption$ValueTextGetter;Lnet/minecraft/client/option/SimpleOption$Callbacks;Lcom/mojang/serialization/Codec;Ljava/lang/Object;Ljava/util/function/Consumer;)V")
    )
    private void changeOption(Args args) {
        if (args.get(0).equals("options.framerateLimit")) {
            args.set(3, new SimpleOption.ValidatingIntSliderCallbacks(1,60).withModifier(value -> value * 10, value -> value / 10));
            args.set(4, Codec.intRange(10, 600));
        }
    }
}
