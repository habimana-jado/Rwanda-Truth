
package com.ukurirwanda.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import org.primefaces.event.FileUploadEvent;

public class FileUpload {

    public String Upload(FileUploadEvent event,String path) {
        try {
            String format = event.getFile().getContentType();
            System.out.println("format is " + format);
            String extension = (format.equalsIgnoreCase("image/jpeg") ? ".jpg" : (format.equalsIgnoreCase("image/png")) ? ".png" : format.equalsIgnoreCase("image/gif") ? ".gif" : format.equalsIgnoreCase("application/pdf") ? ".pdf" : "");
            String newName = UUID.randomUUID().toString() + extension;
            copyFile(newName, event.getFile().getInputstream(),path);
            return newName;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private void copyFile(String fileName, InputStream in,String concatinationPath) {
        try {
            
            OutputStream out = new FileOutputStream(new File(concatinationPath+fileName));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
