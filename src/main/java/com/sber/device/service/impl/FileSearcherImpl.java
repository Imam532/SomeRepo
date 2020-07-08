package com.sber.device.service.impl;

import com.sber.device.service.abstraction.FileSearcher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class FileSearcherImpl implements FileSearcher {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    /**
     * возвращает List объектов, результат поиска по
     * маске *xlsx
     * @return List
     */
    @Override
    public List<File> searchFile(){
        logger.trace("Searching xlsx file in directory");
        List<File> filesInFolder = null;
        try {
            filesInFolder = Files.walk(Paths.get("src/main/uploads"))
                    .filter(Files::isRegularFile)
                    .filter(p -> !Files.isDirectory(p) && p.toString().endsWith(".xlsx"))
                    .map(Path::toFile)
                    .collect(Collectors.toList());
        } catch (IOException e) {
            logger.error("Error while searching xlsx file in directory", e);
            e.printStackTrace();
        }
        logger.trace("File searching complete, find" + filesInFolder.size());
        return filesInFolder;
    }
}
