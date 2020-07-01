package com.sber.device.service.impl;

import com.sber.device.service.abstraction.FileSearcher;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class FileSearcherImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void searchFile() throws IOException {
        FileSearcher searcher = new FileSearcherImpl();
        List<File> files = null;
        try {
            files = searcher.searchFile();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for(File file: files) {
            String path = "src/main/uploads/changed/";
            String fileName = FilenameUtils.removeExtension(file.getName()) + ".res";
            System.out.println(file.getPath());
            File file1 = new File(path + fileName);
            System.out.println(file1.getPath());
            Files.copy(file.toPath(), file1.toPath(),
                    StandardCopyOption.REPLACE_EXISTING);
        }
//        System.out.println(files.toString());
        assertNotNull(files);
    }

    @Test
    void testing() {
        final String DATE_FORMAT = "ddMMYYYY";
        DateFormat formatter = new SimpleDateFormat(DATE_FORMAT);



        String formattedDate = formatter.format(new Date());

        System.out.println(formattedDate);
    }
}