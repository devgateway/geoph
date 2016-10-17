package org.devgateway.geoph.importer.processing;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author dbianco
 *         created on jul 04 2016.
 */
@Service
public class ImportStats {

    private Set<String> failedProjects = new HashSet<>();
    private List<String> errors = new ArrayList<>();
    private List<String> warnings = new ArrayList<>();

    private int successfulProjectsCount = 0;
    private int transactionsCount = 0;
    
    private int errorsCount = 0;
    private int warningsCount = 0;

    private long start;


    public ImportStats(){
        start = System.currentTimeMillis();
    }

    public Set<String> getFailedProjects() {
        return failedProjects;
    }

    public void addFailedProject(String project){
        failedProjects.add(project);
    }

    public void addSuccessProjectAndTransactions(int trxNumber){
        successfulProjectsCount++;
        transactionsCount += trxNumber;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void addError(String error){
        errors.add(error);
        errorsCount++;
    }

    public List<String> getWarnings() {
        return warnings;
    }

    public void addWarning(String warning){
        warnings.add(warning);
        warningsCount++;
    }

    public int getSuccessfulProjectsCount() {
        return successfulProjectsCount;
    }

    public int getTransactionsCount() {
        return transactionsCount;
    }

    public int getErrorsCount() {
        return errorsCount;
    }

    public int getWarningsCount() {
        return warningsCount;
    }

    public String toString(){
        return "\n*************************************************\n" +
                "Successful Projects Count: " + successfulProjectsCount + "\n" +
                "Transactions Count:        " + transactionsCount + "\n" +
                "Failed Projects Count:     " + failedProjects.size() + "\n" +
                (failedProjects.size()>0?failedProjects.stream().map(s -> s + "\n"):"") +
                "Errors:                    " + errorsCount + "\n" +
                (errors.size()>0?errors.stream().map(s -> s + "\\n"):"") +
                "Warnings:                  " + warningsCount + "\n" +
                (warnings.size()>0?warnings.stream().map(s -> s + "\\n"):"") +
                "Time (in seconds):         " + ((System.currentTimeMillis() - start) / 1000) + "\n" +
                "*************************************************\n";
    }

    public List<String> toStringList(){
        List<String> ret = new ArrayList<>();
        ret.add("*************************************************");
        ret.add("Successful Projects Count: " + successfulProjectsCount);
        ret.add("Failed Projects Count:     " + failedProjects.size());
        failedProjects.stream().forEach(s -> ret.add(s));
        ret.add("Transactions Count:        " + transactionsCount);
        ret.add("Errors:                    " + errorsCount);
        errors.stream().forEach(s -> ret.add(s));
        ret.add("Warnings:                  " + warningsCount);
        warnings.stream().forEach(s -> ret.add(s));
        ret.add("Time (in seconds):         " + ((System.currentTimeMillis() - start) / 1000));
        ret.add("*************************************************");
        return ret;
    }

}
