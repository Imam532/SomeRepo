package com.sber.device.service.abstraction;

import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

public interface FirstStageService {
    void startFirstStage (List<Integer> regFileIds);
}
