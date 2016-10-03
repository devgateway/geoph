package org.devgateway.geoph.core.response;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        for(TransactionTypeEnum typeEnum:TransactionTypeEnum.values()){
            Map<String, Double> statusMap = new HashMap<>();
            for(TransactionStatusEnum statusEnum:TransactionStatusEnum.values()){
                statusMap.put(statusEnum.getName(), 0D);
            }
            trxAmounts.put(typeEnum.getName(), statusMap);
        }
    }

    public ChartResponse(Sector sector, int orderType, int orderStatus) {
        this.id = sector.getId();
        this.code = sector.getCode();
        this.name = sector.getName();
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        for(TransactionTypeEnum typeEnum:TransactionTypeEnum.values()){
            Map<String, Double> statusMap = new HashMap<>();
            for(TransactionStatusEnum statusEnum:TransactionStatusEnum.values()){
                statusMap.put(statusEnum.getName(), 0D);
            }
            trxAmounts.put(typeEnum.getName(), statusMap);
        }
    }

    public ChartResponse(PhysicalStatus physicalStatus, int orderType, int orderStatus) {
        this.id = physicalStatus.getId();
        this.code = physicalStatus.getCode();
        this.name = physicalStatus.getName();
        this.orderType = orderType;
        this.orderStatus = orderStatus;
        for(TransactionTypeEnum typeEnum:TransactionTypeEnum.values()){
            Map<String, Double> statusMap = new HashMap<>();
            for(TransactionStatusEnum statusEnum:TransactionStatusEnum.values()){
                statusMap.put(statusEnum.getName(), 0D);
            }
            trxAmounts.put(typeEnum.getName(), statusMap);
        }
    }

    public ChartResponse(Long id, String name, int trxType, int trxStatus) {
        this.id = id;
        this.name = name;
        this.orderType = trxType;
        this.orderStatus = trxStatus;
        for(TransactionTypeEnum typeEnum:TransactionTypeEnum.values()){
            Map<String, Double> statusMap = new HashMap<>();
            for(TransactionStatusEnum statusEnum:TransactionStatusEnum.values()){
                statusMap.put(statusEnum.getName(), 0D);
            }
            trxAmounts.put(typeEnum.getName(), statusMap);
        }
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

    @JsonIgnore
    public Double getDisbursementFunding(){
        Map<String, Double> typeMap = trxAmounts.get(TransactionTypeEnum.DISBURSEMENTS.getName());
        return typeMap!=null?typeMap.get(TransactionStatusEnum.ACTUAL.getName()):0D;
    }

    @JsonIgnore
    public Double getFunding(int type, int status){
        Map<String, Double> typeMap = trxAmounts.get(TransactionTypeEnum.getEnumById(type).getName());
        return typeMap!=null?typeMap.get(TransactionStatusEnum.getEnumById(status).getName()):0D;
    }

    @JsonIgnore
    public Double getFunding(String type, String status){
        Map<String, Double> typeMap = trxAmounts.get(TransactionTypeEnum.valueOf(type.toUpperCase()).getName());
        return typeMap!=null?typeMap.get(TransactionStatusEnum.valueOf(status.toUpperCase()).getName()):0D;
    }

    public void addTrxAmount(Double amount, String trxTypeName, String trxStatusName) {
        this.trxAmounts.get(trxTypeName).put(trxStatusName, amount);
    }

    public void addProjects(Long projectCount) {
        this.projectCount += projectCount;
    }
}
