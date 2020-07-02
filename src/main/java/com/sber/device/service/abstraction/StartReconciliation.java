package com.sber.device.service.abstraction;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.text.ParseException;

public interface StartReconciliation {
    boolean start() throws IOException, MessagingException, CsvException, ParseException;
}
