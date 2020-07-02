package com.sber.device.service.abstraction;


import com.opencsv.exceptions.CsvException;
import com.sber.device.model.RegistryPayment;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface CSVParserService {
    List<RegistryPayment> parseFile(File file) throws IOException, CsvException, ParseException;
}
