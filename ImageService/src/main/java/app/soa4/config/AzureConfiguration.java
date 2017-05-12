package app.soa4.config;

public class AzureConfiguration {
    private static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
                    "AccountName=tinnerimg;" +
                    "AccountKey=unB19cS6ZlpDDEr2cOVI9pVWutfaezWsL76yoQvfreXNisZR/PsEzxdabSDYLXyDpn3Hw4KpjqUQ88ZfJVHWAA==;" +
                    "EndpointSuffix=core.windows.net";

    public static String getStorageConnectionString() {
        return storageConnectionString;
    }

}
