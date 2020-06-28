package com.sber.device.service.impl;


import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.XlsxParser;
import lombok.SneakyThrows;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.File;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static java.math.BigDecimal.ROUND_HALF_UP;

@Service
public class XlsxParserImpl implements XlsxParser {

    private static final int MERCHANT = 0;
    private static final int TERMINAL = 1;
    private static final int OPER_DATE = 2;
    private static final int PAYMENT_NUMBER = 3;
    private static final int DATE_PAYMENT = 4;
    private static final int OPER_SUM = 5;
    private static final int COMIS_SUM = 6;
    private static final int SUMM_PAYMENT = 7;
    private static final int CARD_NUMBER = 8;
    private static final int AUTH_CODE = 9;
    private static final int OPER_TYPE = 10;


    /**
     * возвращает List объектов, результат обработки xlsx файла
     * посредством ApachePoi
     * @return List
     */
    @SneakyThrows
    @Override
    public List<RegistryPayment> parseFile(File file) {

        List<RegistryPayment> list = new ArrayList<>();
        OPCPackage pkg = OPCPackage.open(file);
        XSSFWorkbook wb = new XSSFWorkbook(pkg);
        Sheet sheet = wb.getSheetAt(0);

        int rowStart = 3;
        int rowEnd = sheet.getLastRowNum();

        for (int i = rowStart; i < rowEnd; i++) {
            XSSFRow row = (XSSFRow) sheet.getRow(i);

            if (row != null && row.getCell(7).getCellType().equals(CellType.NUMERIC)) {
                RegistryPayment registryPayments = new RegistryPayment();

                registryPayments.setMerchant_code(String.valueOf(row.getCell(MERCHANT).getStringCellValue())); //TODO убрать каст к стрингу
                registryPayments.setTerminal(String.valueOf(row.getCell(TERMINAL).getStringCellValue()));
                registryPayments.setOper_date(row.getCell(OPER_DATE).getDateCellValue());
                registryPayments.setPayment_order_num(Integer.parseInt(row.getCell(PAYMENT_NUMBER).getStringCellValue()));
                registryPayments.setPayment_oper_date(row.getCell(DATE_PAYMENT).getDateCellValue());
                registryPayments.setOper_sum(BigDecimal.valueOf(
                        row.getCell(OPER_SUM).getNumericCellValue()).setScale(2, ROUND_HALF_UP));
                registryPayments.setComiss_sum(BigDecimal.valueOf(
                        row.getCell(COMIS_SUM).getNumericCellValue()).setScale(2, ROUND_HALF_UP));
                registryPayments.setPayment_order_sum(BigDecimal.valueOf(
                        row.getCell(SUMM_PAYMENT).getNumericCellValue()).setScale(2, ROUND_HALF_UP));
                registryPayments.setCard_num(String.valueOf(row.getCell(CARD_NUMBER).getStringCellValue()));
                XSSFCell cell = row.getCell(AUTH_CODE);
                if (cell.getCellType().equals(CellType.NUMERIC)) {
                    registryPayments.setAuth_code(String.valueOf(cell.getNumericCellValue()));
                } else {
                    registryPayments.setAuth_code(row.getCell(AUTH_CODE).getStringCellValue());
                }
                registryPayments.setOper_type(row.getCell(OPER_TYPE).getStringCellValue());

                list.add(registryPayments);
            } else break;
        }
        return list;
    }
}
