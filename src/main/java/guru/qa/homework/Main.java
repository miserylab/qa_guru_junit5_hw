package guru.qa.homework;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> fileNames = Arrays.asList("sample.pdf", "sample-xlsx-file.xlsx", "qa_guru.csv");
        ZipFilesCreator zipFile = new ZipFilesCreator();
        zipFile.zipSomeFiles(fileNames);
    }
}
