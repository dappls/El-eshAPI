package net.dappls.eleshapi.Testing;

import net.dappls.eleshapi.Registries.RegistryHelper;
import net.dappls.eleshapi.events.PreventFallDamageEvent;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.block.Block;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;

public class TestRegisitries {

    public static final Item item = RegistryHelper.registerItem("item",nofalldamageItem::new);
    public static final Block block = RegistryHelper.registerBlock("block", Block::new);

    public static void bootstrap(){
    }
}
