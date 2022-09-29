package ram.talia.triplecheck.forge;

import net.minecraftforge.common.ForgeConfigSpec;
import ram.talia.triplecheck.api.TripleCheckConfig;

public class ForgeConfig implements TripleCheckConfig.CommonConfigAccess {

    public ForgeConfig(ForgeConfigSpec.Builder builder) {
    }

    public static class Client implements TripleCheckConfig.ClientConfigAccess {

        public Client(ForgeConfigSpec.Builder builder) {
        }
    }

    public static class Server implements TripleCheckConfig.ServerConfigAccess {
        public Server(ForgeConfigSpec.Builder builder) {
        }
    }
}
