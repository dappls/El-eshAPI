package net.dappls.eleshapi.Testing;

import net.dappls.eleshapi.events.PreventFallDamageEvent;
import net.fabricmc.fabric.api.util.TriState;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.world.World;


public class nofalldamageItem extends Item {
    public nofalldamageItem(Settings settings) {
        super(settings);

        PreventFallDamageEvent.EVENT.register(new PreventFallDamageEvent() {
            @Override
            public TriState preventsFallDamage(World world, LivingEntity entity, double fallDistance, float damagePerDistance, DamageSource damageSource) {
                if (entity instanceof PlayerEntity player) {
                    boolean isHeld =
                            player.getMainHandStack().getItem() == nofalldamageItem.this ||
                                    player.getOffHandStack().getItem() == nofalldamageItem.this;

                    if (isHeld) {
                        return TriState.FALSE;
                    }
                }
                return TriState.DEFAULT;

            }

            @Override
            public int getPriority() {
               return 500;
            }
        });
    }

}
