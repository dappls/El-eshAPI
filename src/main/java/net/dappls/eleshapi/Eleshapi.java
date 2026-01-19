package net.dappls.eleshapi;


import net.dappls.eleshapi.Registries.RegistryHelper;
import net.dappls.eleshapi.Testing.TestRegisitries;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;

public class Eleshapi implements ModInitializer {
    public static final String MOD_ID = "eleshapi";
    @Override
    public void onInitialize() {
        ServerTickEvents.END_SERVER_TICK.register(minecraftServer -> {
        });

        TestRegisitries.bootstrap();
    }
}
