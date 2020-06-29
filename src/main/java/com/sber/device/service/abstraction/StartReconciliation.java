package com.sber.device.service.abstraction;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.mail.MessagingException;
import java.io.IOException;

public interface StartReconciliation {
    boolean start() throws IOException, MessagingException, CsvRequiredFieldEmptyException, CsvDataTypeMismatchException;
}
