//package com.sber.device.dao.impl;
//
//import com.sber.device.dao.abstraction.RegistryFileDao;
//import com.sber.device.model.RegistryFile;
//import org.springframework.stereotype.Repository;
//import org.springframework.transaction.annotation.Transactional;
//
//import javax.persistence.NoResultException;
//import javax.persistence.TypedQuery;
//
//@Repository
//@Transactional
//public class RegistryFileDaoImpl  implements RegistryFileDao {
//
//    RegistryFileDaoImpl() {
//        super(RegistryFile.class);
//    }
//
//    @Override
//    public RegistryFile processingFile(String fileName) {
//        TypedQuery<RegistryFile> query = entityManager
//                .createQuery("SELECT r FROM RegistryFile r WHERE r.reg_file_name = :fileName AND r.status = 1",
//                        RegistryFile.class);
//        query.setParameter("fileName", fileName);
//        RegistryFile regFile;
//        try {
//            regFile = query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//        return regFile;
//    }
//
//    @Override
//    public RegistryFile getFileByFileName(String fileName) {
//        TypedQuery<RegistryFile> query = entityManager
//                .createQuery("SELECT r FROM RegistryFile r WHERE r.reg_file_name = :fileName", RegistryFile.class);
//        query.setParameter("fileName", fileName);
//        RegistryFile regFile;
//        try {
//            regFile = query.getSingleResult();
//        } catch (NoResultException e) {
//            return null;
//        }
//        return regFile;
//    }
//
//    @Override
//    public RegistryFile getRegistryFileByReg_file_nameAndStatus(String fileName, Integer status) {
//        return null;
//    }
//}
