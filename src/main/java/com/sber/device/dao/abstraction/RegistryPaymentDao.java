package com.sber.device.dao.abstraction;

import com.sber.device.model.Payment;
import com.sber.device.model.RegistryFile;
import com.sber.device.model.RegistryPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


public interface RegistryPaymentDao extends JpaRepository<RegistryPayment, Integer> {

////    @Query("SELECT a FROM  WHERE a.registryFile.reg_file_name = :name AND a.registryFile.status = 1")
//    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END FROM RegistryPayment a WHERE a.registryFile.reg_file_name = :name AND a.registryFile.status = 1")
//    boolean isFileInProcessing(@Param("name") String fileName);
//    //TODO возможно убрать в registryFile
//
//    @Modifying
//    @Query("update RegistryPayment u set u.payment.id = ?1")
//    void updatePaymentId(Integer id);
//
//    List<RegistryPayment> getAllRecordsByRegistryFileId(Integer fileId);
//
    @Query("SELECT rp FROM RegistryPayment rp WHERE rp.reg_file_id = :#{#registryFile}")
    List<RegistryPayment> findAllByReg_file_id(RegistryFile registryFile);

    @Modifying
    @Query("update RegistryPayment rp set rp.merchant_code = ?1")
    void updateMerchantCode(String merchantCode);

    @Modifying
    @Query("update RegistryPayment rp set rp.payment_id = ?1")
    void updatePaymentId(Payment payment);

}
