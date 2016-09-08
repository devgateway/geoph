package org.devgateway.geoph.dao;

import org.devgateway.geoph.enums.TransactionStatusEnum;
import org.devgateway.geoph.enums.TransactionTypeEnum;

import java.util.HashMap;
import java.util.Map;

/**
 * @author dbianco
 *         created on ago 26 2016.
 */
public class ProjectMiniSummaryDao {

    private Long id;

    private String title;

    private String fundingAgency;

    private Map<String, Map<String, Double>> trxAmounts = new HashMap<>();

    public ProjectMiniSummaryDao(ProjectMiniDao projectMiniDao){
        this.id = projectMiniDao.getId();
        this.title = projectMiniDao.getTitle();
        this.fundingAgency = projectMiniDao.getFundingAgency();

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

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFundingAgency() {
        return fundingAgency;
    }

    public void setFundingAgency(String fundingAgency) {
        this.fundingAgency = fundingAgency;
    }

    public Map<String, Map<String, Double>> getTrxAmounts() {
        return trxAmounts;
    }

    public void setTrxAmounts(Map<String, Map<String, Double>> trxAmounts) {
        this.trxAmounts = trxAmounts;
    }

    public void addTrxAmount(ProjectMiniDao projectMiniDao) {
        String trxTypeName = TransactionTypeEnum.getEnumById(projectMiniDao.getTransactionTypeId()).getName();
        String trxStatusName = TransactionStatusEnum.getEnumById(projectMiniDao.getTransactionStatusId()).getName();
        this.trxAmounts.get(trxTypeName).put(trxStatusName, projectMiniDao.getTrxAmount());
    }
}
