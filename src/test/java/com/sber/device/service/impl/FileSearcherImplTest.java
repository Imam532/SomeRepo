package com.sber.device.service.impl;

import com.sber.device.service.abstraction.FileSearcher;
import org.apache.commons.io.FilenameUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class FileSearcherImplTest {

    @BeforeEach
    void setUp() {
    }

    @Test
    void searchFile() throws IOException {
        FileSearcher searcher = new FileSearcherImpl();
        List<File> files = null;

        files = searcher.searchFile();

        for (File file : files) {
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
        String number = "-4334.00";
        Long d = Long.valueOf(number.replace(".", "").trim());

        System.out.println(d);


    }
}