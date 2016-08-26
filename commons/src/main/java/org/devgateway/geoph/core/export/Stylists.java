package org.devgateway.geoph.core.export;

/**
 * @author dbianco
 *         created on ago 26 2016.
 */
public interface Stylists {

    Stylist getDateStylist(String wbName);

    Stylist getAmountStylist(String wbName);

    Stylist getRegularStylist(String wbName);

    Stylist getBoldStylist(String wbName);

    Stylist getDecimalStylist(String wbName);

    Stylist getNumberStylist(String wbName);
}
