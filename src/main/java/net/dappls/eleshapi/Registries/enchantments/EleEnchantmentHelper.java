package net.dappls.eleshapi.Registries.enchantments;


import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;


import java.util.ArrayList;
import java.util.List;

public class EleEnchantmentHelper {
    public static List<EleEnchantmentBuilder> customEnchantmentList = new ArrayList<>();

    public static void registerEnchantment(EleEnchantmentBuilder enchantment) {
        customEnchantmentList.add(enchantment);
    }



    public static void bootstrap(Registerable<Enchantment> registerable) {

        var enchantments = registerable.getRegistryLookup(RegistryKeys.ENCHANTMENT);
        var items = registerable.getRegistryLookup(RegistryKeys.ITEM);

        for (EleEnchantmentBuilder ench : customEnchantmentList) {
            Enchantment.Builder builder = Enchantment.builder(
                    Enchantment.definition(
                            items.getOrThrow(ench.applicableItems),
                            ench.rarityWeight,
                            ench.maxlevel,
                            Enchantment.leveledCost(ench.minCostBase, ench.minCostPerLevel),
                            Enchantment.leveledCost(ench.maxCostBase, ench.maxCostPerLevel),
                            ench.anvilCost,
                            ench.attributeModifierSlot
                    )
            );

            ench.incompatibleWith.ifPresent(tag ->
                    builder.exclusiveSet(enchantments.getOrThrow(tag))
            );

            for (EleEffectBuilder effect : ench.effectBuilders) {
                builder.addEffect(
                        effect.componentType,
                        effect.source,
                        effect.target,
                        effect.effect);
            }


            register(registerable,
                    RegistryKey.of(RegistryKeys.ENCHANTMENT,
                            Identifier.of(ench.getModId(), ench.getName())),
                    builder);
        }
    }



    private static void register(Registerable<Enchantment> registry, RegistryKey<Enchantment> key, Enchantment.Builder builder) {
        registry.register(key, builder.build(key.getValue()));
    }
}
