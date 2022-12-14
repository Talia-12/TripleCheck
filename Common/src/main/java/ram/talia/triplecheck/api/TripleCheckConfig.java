package ram.talia.triplecheck.api;

public class TripleCheckConfig {
    public interface CommonConfigAccess {
    }

    public interface ClientConfigAccess {
    }

    public interface ServerConfigAccess {
    }

    private static CommonConfigAccess common = null;
    private static ClientConfigAccess client = null;
    private static ServerConfigAccess server = null;

    public static CommonConfigAccess common() {
        return common;
    }

    public static ClientConfigAccess client() {
        return client;
    }

    public static ServerConfigAccess server() {
        return server;
    }

    public static void setCommon(CommonConfigAccess access) {
        if (common != null) {
            TripleCheckAPI.LOGGER.warn("CommonConfigAccess was replaced! Old {} New {}",
																			 common.getClass().getName(), access.getClass().getName());
        }
        common = access;
    }

    public static void setClient(ClientConfigAccess access) {
        if (client != null) {
            TripleCheckAPI.LOGGER.warn("ClientConfigAccess was replaced! Old {} New {}",
																			 client.getClass().getName(), access.getClass().getName());
        }
        client = access;
    }

    public static void setServer(ServerConfigAccess access) {
        if (server != null) {
            TripleCheckAPI.LOGGER.warn("ServerConfigAccess was replaced! Old {} New {}",
																			 server.getClass().getName(), access.getClass().getName());
        }
        server = access;
    }
}
