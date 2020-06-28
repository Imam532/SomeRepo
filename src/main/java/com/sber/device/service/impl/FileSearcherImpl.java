package com.sber.device.service.impl;

import com.sber.device.service.abstraction.FileSearcher;
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

    /**
     * возвращает List объектов, результат поиска по
     * маске *xlsx
     * @return List
     */
    @Override
    public List<File> searchFile() throws IOException {
        List<File> filesInFolder = Files.walk(Paths.get("src/main/uploads"))
                .filter(Files::isRegularFile)
                .filter(p -> !Files.isDirectory(p) && p.toString().endsWith(".xlsx"))
                .map(Path::toFile)
                .collect(Collectors.toList());
        return filesInFolder;
    }
}
