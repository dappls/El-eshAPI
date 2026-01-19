package net.dappls.eleshapi.Registries.enchantments;

import net.minecraft.component.ComponentType;
import net.minecraft.component.type.AttributeModifierSlot;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.effect.EnchantmentEffectTarget;
import net.minecraft.enchantment.effect.EnchantmentEntityEffect;
import net.minecraft.enchantment.effect.TargetedEnchantmentEffect;
import net.minecraft.item.Item;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.util.Identifier;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class EnchantmentHelper {
    public record CustomEffect(
            ComponentType<List<TargetedEnchantmentEffect<EnchantmentEntityEffect>>> componentType,
            EnchantmentEffectTarget source,
            EnchantmentEffectTarget target,
            EnchantmentEntityEffect effect
    ) {}

    private static List<CustomEnchantment> customEnchantmentList = new ArrayList<>();

    public static void registerEnchantment(CustomEnchantment enchantment) {
        customEnchantmentList.add(enchantment);
    }

    public record CustomEnchantment(String modId, String name,
                                    TagKey<Item> itemtags, int weight, int maxlevel, int baselevelcost1, int perlevelcost1, int baselevelcost2, int perlevelcost2, int anvilCost,
                                    AttributeModifierSlot attributeModifierSlot,Optional<TagKey<Enchantment>> exclusiveTag,
                                    Optional<List<CustomEffect>> effects) {
    }

    public static void bootstrap(Registerable<Enchantment> registerable) {
        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        for (CustomEnchantment ench : customEnchantmentList) {

            Enchantment.Builder builder = Enchantment.builder(
                    Enchantment.definition(
                            items.getOrThrow(ench.itemtags),
                            ench.weight(),
                            ench.maxlevel,
                            Enchantment.leveledCost(ench.baselevelcost1, ench.perlevelcost1),
                            Enchantment.leveledCost(ench.baselevelcost2, ench.perlevelcost2),
                            ench.anvilCost(),
                            ench.attributeModifierSlot
                    )
            );

            ench.exclusiveTag().ifPresent(tag ->
                    builder.exclusiveSet(enchantments.getOrThrow(tag))
            );

            if(ench.effects.isPresent()) {
                for (CustomEffect effect : ench.effects().orElseThrow()) {
                    builder.addEffect(
                            effect.componentType(),
                            effect.source(),
                            effect.target(),
                            effect.effect()
                    );
                }
            }

            register(registerable,
                    RegistryKey.of(RegistryKeys.ENCHANTMENT,
                            Identifier.of(ench.modId(), ench.name())),
                    builder);
        }

    }


    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
