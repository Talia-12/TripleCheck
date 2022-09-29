package ram.talia.triplecheck.forge;

import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.common.ForgeConfigSpec;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.registries.IForgeRegistry;
import net.minecraftforge.registries.IForgeRegistryEntry;
import ram.talia.triplecheck.api.TripleCheckAPI;
import ram.talia.triplecheck.api.TripleCheckConfig;
import thedarkcolour.kotlinforforge.KotlinModLoadingContext;

import java.util.function.BiConsumer;
import java.util.function.Consumer;

@Mod(TripleCheckAPI.MOD_ID)
public class ForgeInitializer {
    public ForgeInitializer() {
        initConfig();
        initRegistry();
        initListeners();
    }

    private static void initConfig() {
        var config = new ForgeConfigSpec.Builder().configure(ForgeConfig::new);
        var clientConfig = new ForgeConfigSpec.Builder().configure(ForgeConfig.Client::new);
        var serverConfig = new ForgeConfigSpec.Builder().configure(ForgeConfig.Server::new);
        TripleCheckConfig.setCommon(config.getLeft());
        TripleCheckConfig.setClient(clientConfig.getLeft());
        TripleCheckConfig.setServer(serverConfig.getLeft());
        var mlc = ModLoadingContext.get();
        mlc.registerConfig(ModConfig.Type.COMMON, config.getRight());
        mlc.registerConfig(ModConfig.Type.CLIENT, clientConfig.getRight());
        mlc.registerConfig(ModConfig.Type.SERVER, serverConfig.getRight());
    }

    private static void initRegistry() {
    }

    // https://github.com/VazkiiMods/Botania/blob/1.18.x/Forge/src/main/java/vazkii/botania/forge/ForgeCommonInitializer.java
    private static <T extends IForgeRegistryEntry<T>> void bind(IForgeRegistry<T> registry,
        Consumer<BiConsumer<T, ResourceLocation>> source) {
        getModEventBus().addGenericListener(registry.getRegistrySuperType(),
            (RegistryEvent.Register<T> event) -> {
                IForgeRegistry<T> forgeRegistry = event.getRegistry();
                source.accept((t, rl) -> {
                    t.setRegistryName(rl);
                    forgeRegistry.register(t);
                });
            });
    }

    private static void initListeners() {
        var modBus = getModEventBus();
        var evBus = MinecraftForge.EVENT_BUS;

        //modBus.register(ForgeClientInitializer.class);

        //modBus.addListener((FMLCommonSetupEvent evt) -> evt.enqueueWork(() -> {
        //    }));

        // We have to do these at some point when the registries are still open
        //modBus.addGenericListener(Item.class, (RegistryEvent<Item> evt) -> {});

        //modBus.addListener();

        //evBus.addListener();

        //DistExecutor.unsafeRunWhenOn(Dist.CLIENT, () -> () -> modBus.addListener());
    }

    // aaaauughhg
    private static IEventBus getModEventBus() {
        return KotlinModLoadingContext.Companion.get().getKEventBus();
    }
}
