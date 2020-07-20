package com.sber.device.service.impl;

import com.opencsv.exceptions.CsvException;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.*;
import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrepareReconciliationImpl implements PrepareReconciliation {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RegistryFile registryFile;
    private List<File> files = new ArrayList<>();
    private List<RegistryPayment> registryPayments = new ArrayList<>();

    private final FileSearcher searcher;
    private final CSVParserService parser;
    private final RegistryFileService registryFileService;
    private final RegistryPaymentService registryPaymentService;


    @Autowired
    public PrepareReconciliationImpl(FileSearcher searcher,
                                     CSVParserService parser,
                                     RegistryFileService registryFileService,
                                     RegistryPaymentService registryPaymentService
    ) {
        this.parser = parser;
        this.searcher = searcher;
        this.registryFileService = registryFileService;
        this.registryPaymentService = registryPaymentService;

    }

    /**
     * Подготовка к сверке. Поиск файлов по маске, сохранение параметров файла в БД(RegistryFile)
     * Парсинг файла для получения списков RegistryPayment и сохранение их в БД
     * @return List Registry File id's
     * @throws IOException
     * @throws CsvException
     * @throws ParseException
     */
    @Override
    public List<Integer> isReconciliationReady() {
        logger.trace("Preparing to reconciliation");

        List<Integer> regFileIds = new ArrayList<>();
        files = searcher.searchFile();

        for (File file : files) {                                                  //forEach file -> XlsxParser
            if (registryFileService.processingFile(file.getName()) != null) {
                logger.warn("Not fount  RegistryFile's for processing");
                System.out.println("Файл уже обработан");
            } else {
                Date date = new Date();
                registryPayments = parser.parseFile(file);                         //file -> list of RegistryPayment
                registryFile = new RegistryFile(file.getName(), date, 0);   //status still 0, because Reconciliation not finished
                registryFileService.save(registryFile);             //save RegistryFile entity in BD, file in processing;
                registryFile = registryFileService.notProcessingFile(file.getName());

                if (registryFile != null) {
                    for (RegistryPayment registryPayment : registryPayments) {
                        registryPayment.setReg_file_id(registryFile);
                        registryPaymentService.save(registryPayment);       //save RegistryPayment entity in BD
                    }
                    /**
                     * согласно confluence просто меняем имя файла
                     * Но в dev используем копирование в новую директорию
                     */
//                file.renameTo(new File(file.getParentFile(), FilenameUtils.removeExtension(file.getName()) + ".res"));

                    try {
                        saveFile(file);
                    } catch (IOException e) {
                        logger.error("Error while trying save renamed file to directory", e);
                        e.printStackTrace();
                    }
                }
                regFileIds.add(registryFile.getId());
            }
        }
        logger.trace("Files is proceeded and saved. Beans created and persist to database");
        return regFileIds;
    }

    private void saveFile(File file) throws IOException {
        final String DATE_FORMAT = "ddMMYYYY";
        String formattedDate = new SimpleDateFormat(DATE_FORMAT).format(new Date());

        String path = "src/main/uploads/changed/";
        String fileName = FilenameUtils.removeExtension(file.getName()) + "_" + formattedDate + ".res";
        File copyingFile = new File(path + fileName);
        Files.copy(file.toPath(), copyingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
    }
}
