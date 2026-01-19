package net.dappls.eleshapi.Registries;

import com.mojang.serialization.MapCodec;
import net.dappls.eleshapi.Eleshapi;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.component.ComponentType;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.particle.SimpleParticleType;
import net.minecraft.potion.Potion;
import net.minecraft.registry.*;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.util.Identifier;
import java.util.function.Function;
import java.util.function.UnaryOperator;

public class RegistryHelper {

    public static Item registerItem(String name,Function<Item.Settings,Item> function) {
        return Registry.register(Registries.ITEM, Identifier.of(Eleshapi.MOD_ID,name), function.apply(
                new Item.Settings().registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Eleshapi.MOD_ID, name))
        )));}


    public static Block registerBlock(String name, Function<AbstractBlock.Settings, Block> function) {
        Block toRegister = function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Eleshapi.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(Registries.BLOCK, Identifier.of(Eleshapi.MOD_ID, name), toRegister);
    }

    public static Block registerBlockWithoutBlockItem(String name, Function<AbstractBlock.Settings, Block> function) {
        return Registry.register(Registries.BLOCK, Identifier.of(Eleshapi.MOD_ID, name),
                function.apply(AbstractBlock.Settings.create().registryKey(RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Eleshapi.MOD_ID, name)))));
    }

    public static void registerBlockItem(String name, Block block) {
        Registry.register(Registries.ITEM, Identifier.of(Eleshapi.MOD_ID, name),
                new BlockItem(block, new Item.Settings().useBlockPrefixedTranslationKey()
                        .registryKey(RegistryKey.of(RegistryKeys.ITEM, Identifier.of(Eleshapi.MOD_ID, name)))));
    }

    public static RegistryEntry<StatusEffect> registerStatusEffect(String name, StatusEffect statusEffect) {
        return Registry.registerReference(Registries.STATUS_EFFECT, Identifier.of(Eleshapi.MOD_ID, name), statusEffect);
    }

    private static <T> ComponentType<T> registerComponentType(String name, UnaryOperator<ComponentType.Builder<T>> builderOperator) {
        return Registry.register(Registries.DATA_COMPONENT_TYPE, Identifier.of(Eleshapi.MOD_ID, name),
                builderOperator.apply(ComponentType.builder()).build());
    }

    private static RegistryEntry<Potion> registerPotion(String name, Potion potion) {
        return Registry.registerReference(Registries.POTION, Identifier.of(Eleshapi.MOD_ID, name), potion);
    }

    private static SimpleParticleType registerParticle(String name, SimpleParticleType particleType) {
        return Registry.register(Registries.PARTICLE_TYPE, Identifier.of(Eleshapi.MOD_ID, name), particleType);
    }

    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.of(Eleshapi.MOD_ID, name);
        return Registry.register(Registries.SOUND_EVENT, id, SoundEvent.of(id));
    }

    public static MapCodec<? extends EnchantmentEntityEffect> registerEntityEffect(String name, MapCodec<? extends EnchantmentEntityEffect> codec) {
        return Registry.register(Registries.ENCHANTMENT_ENTITY_EFFECT_TYPE, Identifier.of(Eleshapi.MOD_ID, name), codec);
    }


}
