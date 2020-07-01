package com.sber.device.dao.abstraction;

import com.sber.device.model.RegistryFile;
import org.apache.commons.math3.stat.descriptive.summary.Product;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class RegistryFileRepositoryTest {

    @Autowired
    public RegistryFileDao registryFileDao;

    @BeforeEach
    void setUp() {
    }

    @Test
    public void testSave() {

        //setup product
        RegistryFile registryFile = new RegistryFile();
        registryFile.setReg_file_name("7777.xlsx");
        registryFile.setLoad_time(new Date());
        registryFile.setStatus(1);

        //save product, verify has ID value after save
        assertNull(registryFile.getId()); //null before save
        registryFileDao.save(registryFile);
        assertNotNull(registryFile.getId()); //not null after save

        //fetch from DB
        RegistryFile registryFileFromDB = registryFileDao.getRegistryFileByReg_file_name(registryFile.getReg_file_name());

        //should not be null
        assertNotNull(registryFileFromDB);

        //should equal
        assertEquals(registryFile.getId(), registryFileFromDB.getId());


        //update description and save
        registryFileFromDB.setStatus(1);
        registryFileDao.save(registryFileFromDB);

        //get from DB, should be updated
        RegistryFile updatedRegistryFile = registryFileDao.findById(registryFileFromDB.getId()).orElse(null);
        assertEquals(registryFileFromDB.getStatus(), updatedRegistryFile.getStatus());

        //verify count of products in DB
        long productCount = registryFileDao.count();
        assertEquals(productCount, 1);

        //get all products, list should only have one
        Iterable<RegistryFile> registryFiles = registryFileDao.findAll();

        int count = 0;

        for(RegistryFile r : registryFiles){
            count++;
        }

        assertEquals(count, 1);
    }
}