package ram.talia.triplecheck.api;

import com.google.common.base.Suppliers;
import net.minecraft.resources.ResourceLocation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.function.Supplier;

public interface TripleCheckAPI {
    String MOD_ID = "triplecheck";
    Logger LOGGER = LogManager.getLogger(MOD_ID);

    Supplier<TripleCheckAPI> INSTANCE = Suppliers.memoize(() -> {
        try {
            return (TripleCheckAPI) Class.forName("ram.talia.triplecheck.common.impl.TripleCheckImpl")
                                         .getDeclaredConstructor().newInstance();
        } catch (ReflectiveOperationException e) {
            LogManager.getLogger().warn("Unable to find TripleCheckImpl, using a dummy");
            return new TripleCheckAPI() {
            };
        }
    });

    static TripleCheckAPI instance() {
        return INSTANCE.get();
    }

    static ResourceLocation modLoc(String s) {
        return new ResourceLocation(MOD_ID, s);
    }
}
