package org.devgateway.geoph.services.exporter;

import org.devgateway.geoph.core.export.RawCell;
import org.devgateway.geoph.core.export.RawRow;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sebastian Dimunzio on 6/8/2016.
 */
public class RawRowImpl implements RawRow {

    List<RawCell> cells;

    public RawRowImpl() {
        this.cells = new ArrayList<>();
    }

    public RawRow addCell(RawCell cell) {
        this.cells.add(cell);
        return this;
    }

    @Override
    public List<RawCell> getCells() {
        return cells;
    }

    public void setCells(List<RawCell
            > cells) {
        this.cells = cells;
    }
}
