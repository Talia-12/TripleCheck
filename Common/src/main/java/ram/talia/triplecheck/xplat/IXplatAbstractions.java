package ram.talia.triplecheck.xplat;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import ram.talia.triplecheck.api.TripleCheckAPI;

import java.util.ServiceLoader;
import java.util.stream.Collectors;

/**
 * more like IHexPlatAbstracts lmaooooooo
 */
public interface IXplatAbstractions {
    Platform platform();

    boolean isPhysicalClient();

    boolean isModPresent(String id);

    void initPlatformSpecific();

    ResourceLocation getID(Block block);

    ResourceLocation getID(Item item);

    IXplatTags tags();

    String getModName(String namespace);

    IXplatAbstractions INSTANCE = find();

    private static IXplatAbstractions find() {
        var providers = ServiceLoader.load(IXplatAbstractions.class).stream().toList();
        if (providers.size() != 1) {
            var names = providers.stream().map(p -> p.type().getName()).collect(Collectors.joining(",", "[", "]"));
            throw new IllegalStateException(
                "There should be exactly one IXplatAbstractions implementation on the classpath. Found: " + names);
        } else {
            var provider = providers.get(0);
            TripleCheckAPI.LOGGER.debug("Instantiating xplat impl: " + provider.type().getName());
            return provider.get();
        }
    }

}
