package org.devgateway.geoph.deployer;

/**
 * @author dbianco
 *         created on mar 03 2016.
 */
public enum DeployProfile {

    DEV    ("create-drop"),
    RELEASE("none");

    private final String hibernateDdl;

    private DeployProfile(String hibernateDdl) {
        this.hibernateDdl = hibernateDdl;
    }

    public String getHibernateDdl() {
        return hibernateDdl;
    }
}
