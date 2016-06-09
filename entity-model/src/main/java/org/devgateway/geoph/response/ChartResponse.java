package org.devgateway.geoph.response;

import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Sector;
import org.devgateway.geoph.util.TransactionStatusEnum;
import org.devgateway.geoph.util.TransactionTypeEnum;
import org.devgateway.geoph.util.queries.ResultQueryHelper;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class ChartResponse {

    private Long id;

    private String name;

    private String code;

    private Long projectCount = 0L;

    private Map<String, Map<String, Double>> trxAmounts = new HashMap<>();

    private Long transactionCount = 0L;

    public ChartResponse() {
    }

    public ChartResponse(Agency agency) {
        this.id = agency.getId();
        this.code = agency.getCode();
        this.name = agency.getName();
    }

    public ChartResponse(Sector sector) {
        this.id = sector.getId();
        this.code = sector.getCode();
        this.name = sector.getName();
    }

    public ChartResponse(PhysicalStatus physicalStatus) {
        this.id = physicalStatus.getId();
        this.code = physicalStatus.getCode();
        this.name = physicalStatus.getName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getProjectCount() {
        return projectCount;
    }

    public void setProjectCount(Long projectCount) {
        this.projectCount = projectCount;
    }

    public Map<String, Map<String, Double>> getTrxAmounts() {
        return trxAmounts;
    }

    public void setTrxAmounts(Map<String, Map<String, Double>> trxAmounts) {
        this.trxAmounts = trxAmounts;
    }

    public Long getTransactionCount() {
        return transactionCount;
    }

    public void setTransactionCount(Long transactionCount) {
        this.transactionCount = transactionCount;
    }

    public void add(ResultQueryHelper helper, TransactionTypeEnum trxType, TransactionStatusEnum trxStatus) {
        this.projectCount = helper.getProjectCount();
        this.transactionCount += helper.getTransactionCount();
        Map<String, Double> trxStatusMap;
        Double newAmount = 0D;
        if(trxAmounts.get(trxType.name().toLowerCase())!=null){
            trxStatusMap = trxAmounts.get(trxType.name().toLowerCase());
            if(trxStatusMap.get(trxStatus.name().toLowerCase())!=null){
                newAmount = trxStatusMap.get(trxStatus.name().toLowerCase()) + helper.getTransactionAmount();
            } else {
                newAmount = helper.getTransactionAmount();
            }
        } else {
            trxStatusMap = new HashMap<>();
            newAmount = helper.getTransactionAmount();
        }
        trxStatusMap.put(trxStatus.name().toLowerCase(), newAmount);
        trxAmounts.put(trxType.name().toLowerCase(), trxStatusMap);
    }

}
