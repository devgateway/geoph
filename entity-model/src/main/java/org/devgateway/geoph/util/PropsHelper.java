package org.devgateway.geoph.util;

/**
 * @author dbianco
 *         created on may 03 2016.
 */
public class PropsHelper {

    private static String screenCaptureDir = "/tmp/";

    private static long screenCaptureTimeToWait = 25000;

    private static String screenFirefoxExe = null;

    private PropsHelper(){

    }

    public static String getScreenCaptureDir() {
        return screenCaptureDir;
    }

    public static void setScreenCaptureDir(String screenCaptureDir) {
        PropsHelper.screenCaptureDir = screenCaptureDir;
    }

    public static long getScreenCaptureTimeToWait() {
        return screenCaptureTimeToWait;
    }

    public static void setScreenCaptureTimeToWait(long screenCaptureTimeToWait) {
        PropsHelper.screenCaptureTimeToWait = screenCaptureTimeToWait;
    }

    public static String getScreenFirefoxExe() {
        return screenFirefoxExe;
    }

    public static void setScreenFirefoxExe(String screenFirefoxExe) {
        PropsHelper.screenFirefoxExe = screenFirefoxExe;
    }
}
