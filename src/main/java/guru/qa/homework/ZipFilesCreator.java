package guru.qa.homework;

import org.jetbrains.annotations.NotNull;

import java.io.*;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class ZipFilesCreator {
    private static final String RESOURCES_PATH = "src/test/resources/";
    private static StringBuilder sb = new StringBuilder();


    private String name;

    public ZipFilesCreator(String name) {
        this.name = RESOURCES_PATH + name;
    }

    public ZipFilesCreator() {
        this("homework.zip");
        System.out.println(this.name);
    }

    public void zipSomeFiles(List<String> fileNames) {
        try (FileOutputStream fos = new FileOutputStream(this.name);
            ZipOutputStream zos = new ZipOutputStream(fos)) {

            if (fileNames != null) {
                for (String fileName : fileNames) {
                    String filePath = joinStrings(RESOURCES_PATH, fileName);
                    File file = new File(fileName);
                    try (FileInputStream fis = new FileInputStream(filePath)) {
                        ZipEntry zipEntry = new ZipEntry(file.getName());
                        zos.putNextEntry(zipEntry);

                        byte[] bytes = new byte[1024];
                        int length;
                        while ((length = fis.read(bytes)) >= 0) {
                            zos.write(bytes, 0, length);
                        }
                    }
                }
            }
        }
         catch (IOException e) {
            System.out.println("Something's wrong with " + this.name + ", here's the message: " + e.getMessage());
            return;
        }

    }

    @NotNull
    public static String joinStrings(String @NotNull ... strings) {
        for (String s: strings){
            sb.append(s);
        }
        String result = sb.toString();
        sb.setLength(0);
        return result;
    }
}
