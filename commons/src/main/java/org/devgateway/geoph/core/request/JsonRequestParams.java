package org.devgateway.geoph.core.request;

import java.util.List;

/**
 * @author dbianco
 *         created on ago 09 2016.
 */
public class JsonRequestParams {

    private List<Long> lo;

    private List<String> ft;

    private List<Long> fa;

    private List<Long> gr;

    private List<Long> cc;

    private List<Long> ph;

    private List<Long> pr;

    private List<Long> cl;

    private List<Long> st;

    private List<Long> sa;

    private List<Long> ia;

    private String pt;

    private Double php_max;

    private Double php_min;

    private Double ao_max;

    private Double ao_min;

    private Double to_max;

    private Double to_min;

    private String dt_start_max;

    private String dt_start_min;

    private String dt_end_max;

    private String dt_end_min;

    private Double fin_amount_max;

    private Double fin_amount_min;

    private String pp_start_max;

    private String pp_start_min;

    private String pp_end_max;

    private String pp_end_min;

    private Integer trx_type;

    private Integer trx_status;

    public List<Long> getLo() {
        return lo;
    }

    public void setLo(List<Long> lo) {
        this.lo = lo;
    }

    public List<String> getFt() {
        return ft;
    }

    public void setFt(List<String> ft) {
        this.ft = ft;
    }

    public List<Long> getFa() {
        return fa;
    }

    public void setFa(List<Long> fa) {
        this.fa = fa;
    }

    public List<Long> getGr() {
        return gr;
    }

    public void setGr(List<Long> gr) {
        this.gr = gr;
    }

    public List<Long> getCc() {
        return cc;
    }

    public void setCc(List<Long> cc) {
        this.cc = cc;
    }

    public List<Long> getPh() {
        return ph;
    }

    public void setPh(List<Long> ph) {
        this.ph = ph;
    }

    public List<Long> getPr() {
        return pr;
    }

    public void setPr(List<Long> pr) {
        this.pr = pr;
    }

    public List<Long> getSt() {
        return st;
    }

    public void setSt(List<Long> st) {
        this.st = st;
    }

    public List<Long> getSa() {
        return sa;
    }

    public void setSa(List<Long> sa) {
        this.sa = sa;
    }

    public List<Long> getIa() {
        return ia;
    }

    public void setIa(List<Long> ia) {
        this.ia = ia;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public Double getPhp_max() {
        return php_max;
    }

    public void setPhp_max(Double php_max) {
        this.php_max = php_max;
    }

    public Double getPhp_min() {
        return php_min;
    }

    public void setPhp_min(Double php_min) {
        this.php_min = php_min;
    }

    public Double getAo_max() {
        return ao_max;
    }

    public void setAo_max(Double ao_max) {
        this.ao_max = ao_max;
    }

    public Double getAo_min() {
        return ao_min;
    }

    public void setAo_min(Double ao_min) {
        this.ao_min = ao_min;
    }

    public Double getTo_max() {
        return to_max;
    }

    public void setTo_max(Double to_max) {
        this.to_max = to_max;
    }

    public Double getTo_min() {
        return to_min;
    }

    public void setTo_min(Double to_min) {
        this.to_min = to_min;
    }

    public String getDt_start_max() {
        return dt_start_max;
    }

    public void setDt_start_max(String dt_start_max) {
        this.dt_start_max = dt_start_max;
    }

    public String getDt_start_min() {
        return dt_start_min;
    }

    public void setDt_start_min(String dt_start_min) {
        this.dt_start_min = dt_start_min;
    }

    public String getDt_end_max() {
        return dt_end_max;
    }

    public void setDt_end_max(String dt_end_max) {
        this.dt_end_max = dt_end_max;
    }

    public String getDt_end_min() {
        return dt_end_min;
    }

    public void setDt_end_min(String dt_end_min) {
        this.dt_end_min = dt_end_min;
    }

    public Double getFin_amount_max() {
        return fin_amount_max;
    }

    public void setFin_amount_max(Double fin_amount_max) {
        this.fin_amount_max = fin_amount_max;
    }

    public Double getFin_amount_min() {
        return fin_amount_min;
    }

    public void setFin_amount_min(Double fin_amount_min) {
        this.fin_amount_min = fin_amount_min;
    }

    public String getPp_start_max() {
        return pp_start_max;
    }

    public void setPp_start_max(String pp_start_max) {
        this.pp_start_max = pp_start_max;
    }

    public String getPp_start_min() {
        return pp_start_min;
    }

    public void setPp_start_min(String pp_start_min) {
        this.pp_start_min = pp_start_min;
    }

    public String getPp_end_max() {
        return pp_end_max;
    }

    public void setPp_end_max(String pp_end_max) {
        this.pp_end_max = pp_end_max;
    }

    public String getPp_end_min() {
        return pp_end_min;
    }

    public void setPp_end_min(String pp_end_min) {
        this.pp_end_min = pp_end_min;
    }

    public List<Long> getCl() {
        return cl;
    }

    public void setCl(List<Long> cl) {
        this.cl = cl;
    }

    public Integer getTrx_type() {
        return trx_type;
    }

    public void setTrx_type(Integer trx_type) {
        this.trx_type = trx_type;
    }

    public Integer getTrx_status() {
        return trx_status;
    }

    public void setTrx_status(Integer trx_status) {
        this.trx_status = trx_status;
    }
}
