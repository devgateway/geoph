package org.devgateway.geoph.core.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.devgateway.geoph.dao.ResultDao;
import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;
import org.devgateway.geoph.model.Agency;
import org.devgateway.geoph.model.PhysicalStatus;
import org.devgateway.geoph.model.Sector;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dbianco
 *         created on jun 08 2016.
 */
public class ChartResponse implements Comparable {

    private Long id;

    private String name;

    private String code;

    private Long projectCount = 0L;

    private Map<String, Map<String, Double>> trxAmounts = new HashMap<>();

    private Long transactionCount = 0L;

    @JsonIgnore
    private int orderType;

    @JsonIgnore
    private int orderStatus;

    public ChartResponse() {
    }

    public ChartResponse(Agency agency, int orderType, int orderStatus) {
        this.id = agency.getId();
        this.code = agency.getCode();
        this.name = agency.getName();
    }

    public ChartResponse(Sector sector, int orderType, int orderStatus) {
        this.id = sector.getId();
        this.code = sector.getCode();
        this.name = sector.getName();
        this.orderType = orderType;
        this.orderStatus = orderStatus;
    }

    public ChartResponse(PhysicalStatus physicalStatus, int orderType, int orderStatus) {
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

    public void add(ResultDao helper, TransactionTypeEnum trxType, TransactionStatusEnum trxStatus) {
        this.projectCount = helper.getProjectCount();
        this.transactionCount += helper.getTransactionCount();
        Map<String, Double> trxStatusMap;
        Double newAmount = 0D;
        Double trxAmount = helper.getTransactionAmount()!=null?helper.getTransactionAmount():0;
        if (trxAmounts.get(trxType.getName()) != null) {
            trxStatusMap = trxAmounts.get(trxType.getName());
            if (trxStatusMap.get(trxStatus.getName()) != null) {
                newAmount = trxStatusMap.get(trxStatus.getName()) + trxAmount;
            } else {
                newAmount = trxAmount;
            }
        } else {
            trxStatusMap = new HashMap<>();
            newAmount = trxAmount;
        }
        trxStatusMap.put(trxStatus.getName(), newAmount);
        trxAmounts.put(trxType.getName(), trxStatusMap);
    }

    public Double retrieveMaxFunding(){
        Double maxFunding = 0D;
        if(orderType>0 && orderStatus>0) {
            Map<String, Double> typeMap = trxAmounts.get(TransactionTypeEnum.getEnumById(orderType).getName());
            if(typeMap.get(TransactionStatusEnum.getEnumById(orderStatus).getName())!=null){
                maxFunding = typeMap.get(TransactionStatusEnum.getEnumById(orderStatus).getName());
            }
        } else {
            for(Map<String, Double> typeMap : trxAmounts.values()){
                for(Double fundingValue : typeMap.values()){
                    if(fundingValue!=null && fundingValue>maxFunding){
                        maxFunding=fundingValue;
                    }
                }
            }
        }

        return maxFunding;
    }

    @Override
    public int compareTo(Object o) {
        return ((ChartResponse)o).retrieveMaxFunding().compareTo(retrieveMaxFunding());
    }
}
