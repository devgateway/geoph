package org.devgateway.geoph.core.export;

import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/10/2016.
 */
public interface RawRow {

    RawRow addCell(RawCell cell);

    List<RawCell> getCells();

    void setCells(List<RawCell> cells);
}
