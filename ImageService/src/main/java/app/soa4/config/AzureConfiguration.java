package app.soa4.config;

public class AzureConfiguration {
    private static final String storageConnectionString =
            "DefaultEndpointsProtocol=https;" +
            "AccountName=tinnerimg;" +
            "AccountKey=QpFG3PFIYq5OfoscoeWJvHz8KjfboLVgFGHTJIlUKoExJxOnmtqVKO/eCbNAaXD0pOxDCvMh4rOnGkFS0/ap1g==;" +
            "EndpointSuffix=core.windows.net";

    public static String getStorageConnectionString() {
        return storageConnectionString;
    }

}
