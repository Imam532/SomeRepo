package com.sber.device.service.impl;

import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import com.sber.device.service.abstraction.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class PrepareReconciliationImpl implements PrepareReconciliation {

    private RegistryFile registryFile;
    private List<File> files = new ArrayList<>();
    private List<RegistryPayment> registryPayments = new ArrayList<>();

    private final XlsxParser parser;
    private final FileSearcher searcher;
    private final RegistryFileService registryFileService;
    private final RegistryPaymentService registryPaymentService;


    @Autowired
    public PrepareReconciliationImpl(XlsxParser parser,
                                     FileSearcher searcher,
                                     RegistryFileService registryFileService,
                                     RegistryPaymentService registryPaymentService
    ) {
        this.parser = parser;
        this.searcher = searcher;
        this.registryFileService = registryFileService;
        this.registryPaymentService = registryPaymentService;

    }


    @Override
    public List<Integer> isReconciliationReady() throws IOException {
        List<Integer> regFileIds = new ArrayList<>();
        files = searcher.searchFile();

        for (File file : files) {                                                  //forEach file -> XlsxParser
            if (registryFileService.processingFile(file.getName()) != null) {
                System.out.println("Файл уже обработан");//TODO что делать если файл обозначен что в обработке?
            } else {
                Date date = new Date();                                         //TODO change this?
                registryPayments = parser.parseFile(file);                         //file -> list of RegistryPayment
                registryFile = new RegistryFile(file.getName(), date, 0);
                registryFileService.save(registryFile);             //save RegistryFile entity in BD, file in processing;
                registryFile = registryFileService.notProcessingFile(file.getName());  //TODO may be delete this row?
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
                    saveFile(file);
                }
                regFileIds.add(registryFile.getId());
            }
        }
        return regFileIds;
    }

    private void saveFile(File file) throws IOException {
        final String DATE_FORMAT = "ddMMYYYY";
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
        String formattedDate = formatter.format(new Date());

        String path = "src/main/uploads/changed/";
        String fileName = FilenameUtils.removeExtension(file.getName()) + "_" + formattedDate + ".res";
        File copyingFile = new File(path + fileName);
        Files.copy(file.toPath(), copyingFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

    }
}
