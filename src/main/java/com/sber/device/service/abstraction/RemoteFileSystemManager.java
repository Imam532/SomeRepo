package com.sber.device.service.abstraction;

import java.io.File;
import java.io.IOException;
import java.util.List;

public interface RemoteFileSystemManager {

    void scanFiles() throws IOException;
}

