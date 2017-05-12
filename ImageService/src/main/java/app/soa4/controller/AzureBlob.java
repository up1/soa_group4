package app.soa4.controller;

import app.soa4.config.AzureConfiguration;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;
import org.apache.log4j.Logger;

import java.io.InputStream;
import java.net.URI;

public class AzureBlob {

    private CloudBlobContainer container;
    static Logger log = Logger.getLogger(AzureBlob.class.getName());

    public AzureBlob(String containerName){
        try
        {
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(AzureConfiguration.getStorageConnectionString());
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference(containerName);
        }
        catch (Exception e)
        {
            log.error("Azure connect containner error:" + e);
        }
    }

    public String uploadToAzureBlob(InputStream file, String fileName, Integer fileLength){
        try
        {
            CloudBlockBlob blob = container.getBlockBlobReference(fileName);
            blob.upload(file , fileLength);
        }
        catch (Exception e)
        {
            log.error("Azure upload error:" + e);
        }

        return ""+getBlobUrl(fileName);
    }

    public URI getBlobUrl(String blobName){
        URI uri = null;
        try
        {
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);
            uri = blob.getUri();
        }
        catch (Exception e)
        {
            log.error("Azure get image url error:" + e);
        }
        return uri;
    }

    public String deleteBlob(String blobName){
        try
        {
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);
            log.error("yooooo");
            blob.deleteIfExists();
        }
        catch (Exception e)
        {
            log.error("Azure delete image error:" + e);
        }
        return "file '" + blobName + "' was deleted";
    }
}
