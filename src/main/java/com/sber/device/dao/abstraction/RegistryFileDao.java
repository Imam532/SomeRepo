package com.sber.device.dao.abstraction;


import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistryFileDao extends CrudRepository<RegistryFile, Integer> {

        @Query("select rf from RegistryFile rf where rf.reg_file_name = :fileName and rf.status = 1")
        RegistryFile getRegistryFileByReg_file_nameAndStatus(@Param("fileName") String fileName);

        @Query("select rf from RegistryFile rf where rf.reg_file_name = :fileName and rf.status = 0")
        RegistryFile getRegistryFileByReg_file_name(@Param("fileName") String fileName);

        @Modifying
        @Query("update RegistryFile rf set rf.status= ?1")
        void updateStatus(int status);
}
