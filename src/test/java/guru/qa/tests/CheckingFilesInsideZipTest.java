package guru.qa.tests;

import com.codeborne.pdftest.PDF;
import com.opencsv.CSVReader;
import com.opencsv.exceptions.CsvException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.jupiter.api.*;

import java.io.*;
import java.util.List;

import static guru.qa.helpers.ExcelHelper.getStringCellValue;
import static guru.qa.helpers.ExcelHelper.getDoubleCellValue;

public class CheckingFilesInsideZipTest extends TestBase {


    @Test
    void checkPdfContentInZip() throws IOException {
        File file = new File(tempDir + "/sample.pdf");
        PDF pdf = new PDF(file);

        String textToCheck = "A Simple PDF File";
        Assertions.assertTrue(pdf.text.contains(textToCheck),
                "PDF file doesn't contain text \"" + textToCheck + "\"");
    }

    @Test
    void checkXlsxContentInZip() throws IOException, InvalidFormatException {
        File file = new File(tempDir + "/sample-xlsx-file.xlsx");
        XSSFWorkbook wb = new XSSFWorkbook(file);

        Sheet sheet = wb.getSheetAt(0);

        Assertions.assertAll(
                () -> Assertions.assertEquals("Roma",
                        getStringCellValue(sheet, "B47")),
                () -> Assertions.assertEquals(2654,
                        getDoubleCellValue(sheet,"H47"))
        );
    }

    @Test
    void checkCsvContentInZip() throws IOException, CsvException {
        File file = new File(tempDir + "/qa_guru.csv");
        try (Reader reader = new FileReader(file)) {
            CSVReader csvReader = new CSVReader(reader);

            List<String[]> content = csvReader.readAll();

            Assertions.assertEquals(3, content.size());

            String[] firstRow = content.get(0);
            String[] secondRow = content.get(1);
            String[] thirdRow = content.get(2);

            Assertions.assertArrayEquals(new String[]{"Teacher", "lesson"}, firstRow);
            Assertions.assertArrayEquals(new String[]{"Tuchs", "Files"}, secondRow);
            Assertions.assertArrayEquals(new String[]{"Vasenkov", "REST Assured"}, thirdRow);
        }
    }
}


