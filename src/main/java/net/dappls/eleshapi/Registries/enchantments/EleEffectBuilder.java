package net.dappls.eleshapi.Registries.enchantments;

import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffect;

import java.util.List;

public class EleEffectBuilder {
    ComponentType<List<TargetedEnchantmentEffect<EnchantmentEntityEffect>>> componentType;
    EnchantmentEffectTarget source;
    EnchantmentEffectTarget target;
    EnchantmentEntityEffect effect;
    public EleEffectBuilder(ComponentType<List<TargetedEnchantmentEffect<EnchantmentEntityEffect>>> componentType,EnchantmentEffectTarget source, EnchantmentEffectTarget target) {
        this.componentType = componentType;
        this.source = source;
        this.target = target;
    }

    public EleEffectBuilder effects(EnchantmentEntityEffect effect) {
        this.effect = effect;
        return this;
    }

}

