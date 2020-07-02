package com.sber.device.service.impl;

import com.opencsv.CSVWriter;
import com.sber.device.service.abstraction.ConvertXLSXToCSVService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.DateUtil;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConvertXLSXToCSVServiceImpl implements ConvertXLSXToCSVService {

    /**
     * Преобразовывает входной .xlsx файл в .cvs попутно убирая не нужные значения ячеек в таблице.
     * Сохраняет полученый файл в корне, для дальнейшей обработки( получение бинов RegistryPayment)
     * @param xlsxFile
     * @throws IOException
     * @return
     */
    public File convertXLXSFileToCSV(File xlsxFile) throws IOException {
        FileInputStream fileInStream = new FileInputStream(xlsxFile);

        // Open the xlsx and get the requested sheet from the workbook
        XSSFWorkbook workBook = new XSSFWorkbook(fileInStream);
        XSSFSheet sheet = workBook.getSheetAt(0);
        File csvFile = new File("convertedCSVFile.csv"); //TODO save another path? now don't write to directory
        // OpenCSV writer object to create CSV file
        CSVWriter writer = new CSVWriter(new FileWriter(csvFile));
        DataFormatter formatter = new DataFormatter();
        for (Row row : sheet) {
            newRow:
            {
                StringBuilder sb = new StringBuilder();
                for (Cell cell : row) {
                    if (sb.length() != 0) {
                        sb.append("#");
                    }
                    switch (cell.getCellType()) {
                        case STRING:
                            sb.append(cell.getRichStringCellValue().getString());
                            break;
                        case NUMERIC:
                            if (DateUtil.isCellDateFormatted(cell)) {
                                sb.append(cell.getDateCellValue());
                            } else {
                                sb.append(formatter.formatCellValue(cell));
                            }
                            break;
                        case BLANK:
                            sb.append("#");
                            break newRow;
                        default:
                            break;
                    }
                }
                String[] entries = sb.toString().split("#");
                if (entries.length >= 10) {
                    writer.writeNext(entries);
                }
            }
        }
        writer.close();
        workBook.close();
        return csvFile;
    }
}
