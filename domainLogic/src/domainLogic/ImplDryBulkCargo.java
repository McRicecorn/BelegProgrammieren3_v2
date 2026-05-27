package domainLogic;

import administration.Customer;
import cargo.DryBulkCargo;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ImplDryBulkCargo implements DryBulkCargo {


    private Customer owner;//Storage-spezifische Attribute
    private Duration durationOfStorage;
    private Date lastInspectionDate;
    private int storageLocation;

    private BigDecimal value;//Cargo-spezifische Attribute
    private Collection<Hazard> hazards;
    private int grainSize; // DryBulkCargo-spezifisches Attribut



    private Date insertionDate; // Storage-spezifisches Attribut

    public ImplDryBulkCargo(int grainSize, BigDecimal value,
                            Collection<Hazard> hazards, Customer owner,
                            Duration durationOfStorage ,
                            int storageLocation) {
        this.owner = owner;
        this.durationOfStorage = durationOfStorage;
        this.lastInspectionDate = null;
        this.storageLocation = storageLocation;
        this.hazards = hazards;
        this.value = value;
        this.grainSize = grainSize;
        this.insertionDate = new Date();
    }

    @Override
    public int getGrainSize() {
        return grainSize;
    }
    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }
    @Override
    public Duration getDurationOfStorage() {
        return durationOfStorage;
    }

    @Override
    public Customer getOwner() {/**
     * liefert die vergangene Zeit seit dem Einfügen
     * @return vergangene Zeit oder null wenn kein Einfügedatum gesetzt
     */
        return owner;
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }
    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }
    @Override
    public int getStorageLocation() {
        return storageLocation;
    }
    public Date getInsertionDate() {
        return insertionDate;
    }
}
