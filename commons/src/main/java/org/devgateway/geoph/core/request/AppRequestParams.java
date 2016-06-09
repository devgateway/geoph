package org.devgateway.geoph.core.request;

/**
 * @author dbianco
 *         created on jun 07 2016.
 */
public class AppRequestParams {

    private String lo;

    private String ft;

    private String fa;

    private String gr;

    private String cc;

    private String ph;

    private String pr;

    private String st;

    private String sa;

    private String ia;

    private String pt;

    private Float ro_max;

    private Float ro_min;

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

    public String getLo() {
        return lo;
    }

    public void setLo(String lo) {
        this.lo = lo;
    }

    public String getFt() {
        return ft;
    }

    public void setFt(String ft) {
        this.ft = ft;
    }

    public String getFa() {
        return fa;
    }

    public void setFa(String fa) {
        this.fa = fa;
    }

    public String getGr() {
        return gr;
    }

    public void setGr(String gr) {
        this.gr = gr;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getPh() {
        return ph;
    }

    public void setPh(String ph) {
        this.ph = ph;
    }

    public String getPr() {
        return pr;
    }

    public void setPr(String pr) {
        this.pr = pr;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getSa() {
        return sa;
    }

    public void setSa(String sa) {
        this.sa = sa;
    }

    public String getIa() {
        return ia;
    }

    public void setIa(String ia) {
        this.ia = ia;
    }

    public String getPt() {
        return pt;
    }

    public void setPt(String pt) {
        this.pt = pt;
    }

    public Float getRo_max() {
        return ro_max;
    }

    public void setRo_max(Float ro_max) {
        this.ro_max = ro_max;
    }

    public Float getRo_min() {
        return ro_min;
    }

    public void setRo_min(Float ro_min) {
        this.ro_min = ro_min;
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


    public Parameters getParameters() {
        return Parameters.getParameters(this);
    }
}
