package com.sber.device.service.abstraction;


import com.sber.device.model.RegistryPayment;

import java.io.File;
import java.util.List;

public interface XlsxParser {
    List<RegistryPayment> parseFile(File file);
}
