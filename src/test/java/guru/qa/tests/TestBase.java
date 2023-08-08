package guru.qa.tests;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static java.nio.file.Files.createTempDirectory;

public class TestBase {
    public static final ClassLoader cl = TestBase.class.getClassLoader();
    public static final String zipFileName = "homework.zip";
    public static final Path tempDir;

    static {
        try {
            tempDir = createTempDirectory("tempDir");
        } catch (IOException e) {
            System.out.println("Couldn't create tempDir: " + e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @BeforeAll
    public static void unzipFilesToTempDirectory() throws IOException {
        try (InputStream stream = cl.getResourceAsStream(zipFileName);
             ZipInputStream zis = new ZipInputStream(stream)) {

                ZipEntry entry;
                while ((entry = zis.getNextEntry()) != null) {
                    Path filePath = tempDir.resolve(entry.getName());
                    Files.copy(zis, filePath);
                }
        }
    }

    @AfterAll
    public static void deleteTempDir() throws IOException {

        Files.walkFileTree(tempDir, new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                Files.delete(file);
                return FileVisitResult.CONTINUE;
            }

            @Override
            public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
                Files.delete(dir);
                return FileVisitResult.CONTINUE;
            }
        });
    }
}
