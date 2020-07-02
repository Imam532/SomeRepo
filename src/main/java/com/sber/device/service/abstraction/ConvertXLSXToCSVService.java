package com.sber.device.service.abstraction;

import java.io.File;
import java.io.IOException;

public interface ConvertXLSXToCSVService {
     File convertXLXSFileToCSV(File xlsxFile) throws IOException;
}
