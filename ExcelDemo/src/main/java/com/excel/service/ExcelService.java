package com.excel.service;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

@Service
public class ExcelService {

    public List<String[]> getData() {
        List<String[]> data = new ArrayList<>();

        try (InputStream inputStream = new FileInputStream("C:\\Users\\ragul\\Downloads\\ExcelSheet.xlsx"
);
             XSSFWorkbook workbook = new XSSFWorkbook(inputStream)) {

            Sheet sheet = workbook.getSheetAt(0); // Assuming you want data from the first sheet

            DataFormatter dataFormatter = new DataFormatter();
            for (Row row : sheet) {
                String[] rowData = new String[row.getLastCellNum()];
                for (int i = 0; i < row.getLastCellNum(); i++) {
                    rowData[i] = dataFormatter.formatCellValue(row.getCell(i));
                }
                data.add(rowData);
            }

        } catch (IOException e) {
            e.printStackTrace(); // Handle the exception properly in your application
        }

        return data;
    }
}
