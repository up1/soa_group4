package app.soa4.Controller;

import app.soa4.config.AzureConfiguration;
import com.microsoft.azure.storage.*;
import com.microsoft.azure.storage.blob.*;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.URI;

public class AzureBlob {

    private CloudBlobContainer container;

    public AzureBlob(String containerName){
        try
        {
            // Retrieve storage account from connection-string.
            CloudStorageAccount storageAccount = CloudStorageAccount.parse(AzureConfiguration.getStorageConnectionString());

            // Create the blob client.
            CloudBlobClient blobClient = storageAccount.createCloudBlobClient();

            // Retrieve reference to a previously created container.
            container = blobClient.getContainerReference(containerName);
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
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
            // Output the stack trace.
            e.printStackTrace();
        }

        return ""+getBlobUrl(fileName);
    }

    public URI getBlobUrl(String blobName){
        URI uri = null;
        try
        {
            // Retrieve reference to a blob named
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            // get the blob uri.
             uri = blob.getUri();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return uri;
    }

    public String deleteBlob(String blobName){
        try
        {
            // Retrieve reference to a blob named
            CloudBlockBlob blob = container.getBlockBlobReference(blobName);

            // Delete the blob.
            blob.deleteIfExists();
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
        return "file '" + blobName + "' was deleted";
    }

    public void downloadBlob(){
        try
        {
            // Loop through each blob item in the container.
            for (ListBlobItem blobItem : container.listBlobs()) {
                // If the item is a blob, not a virtual directory.
                if (blobItem instanceof CloudBlob) {
                    // Download the item and save it to a file with the same name.
                    CloudBlob blob = (CloudBlob) blobItem;
                    blob.download(new FileOutputStream("C:\\mydownloads\\" + blob.getName()));
                }
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

    public void listInContainer(){
        try
        {
            // Loop over blobs within the container and output the URI to each of them.
            for (ListBlobItem blobItem : container.listBlobs()) {
                System.out.println(blobItem.getUri());
            }
        }
        catch (Exception e)
        {
            // Output the stack trace.
            e.printStackTrace();
        }
    }

}
