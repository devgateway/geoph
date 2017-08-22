package org.devgateway.geoph.core.request;

import org.devgateway.geoph.core.response.ChartResponse;
import org.devgateway.geoph.dao.ProjectStatsResultsDao;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by dbianco on 16/08/2017.
 */
public class PrintData {

    private String trxType;

    private String trxStatus;

    private Map<String, Set<String>> filters;

    private Map<String, List<Map <String, String>>> visibleLayers;

    Map<String, Collection<ChartResponse>> allChartsData;

    Map<String, List<ProjectStatsResultsDao>> stats;

    public String getTrxType() {
        return trxType;
    }

    public void setTrxType(final String trxType) {
        this.trxType = trxType;
    }

    public String getTrxStatus() {
        return trxStatus;
    }

    public void setTrxStatus(final String trxStatus) {
        this.trxStatus = trxStatus;
    }

    public Map<String, Set<String>> getFilters() {
        return filters;
    }

    public void setFilters(final Map<String, Set<String>> filters) {
        this.filters = filters;
    }

    public Map<String, List<Map<String, String>>> getVisibleLayers() {
        return visibleLayers;
    }

    public void setVisibleLayers(final Map<String, List<Map<String, String>>> visibleLayers) {
        this.visibleLayers = visibleLayers;
    }

    public Map<String, Collection<ChartResponse>> getAllChartsData() {
        return allChartsData;
    }

    public void setAllChartsData(final Map<String, Collection<ChartResponse>> allChartsData) {
        this.allChartsData = allChartsData;
    }

    public Map<String, List<ProjectStatsResultsDao>> getStats() {
        return stats;
    }

    public void setStats(final Map<String, List<ProjectStatsResultsDao>> stats) {
        this.stats = stats;
    }
}
