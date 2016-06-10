package org.devgateway.geoph.services.exporter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawRow {
    List<RawCell> cells;

    public RawRow() {
        this.cells = new ArrayList<>();
    }

    public RawRow addCell(RawCell cell) {
        this.cells.add(cell);
        return this;
    }

    public List<RawCell> getCells() {
        return cells;
    }

    public void setCells(List<RawCell> cells) {
        this.cells = cells;
    }
}
