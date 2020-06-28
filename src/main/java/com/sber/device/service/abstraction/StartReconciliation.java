package com.sber.device.service.abstraction;

import javax.persistence.criteria.CriteriaBuilder;
import java.io.IOException;
import java.util.List;

public interface StartReconciliation {
    List<Integer> isReconciliationReady() throws IOException;
}
