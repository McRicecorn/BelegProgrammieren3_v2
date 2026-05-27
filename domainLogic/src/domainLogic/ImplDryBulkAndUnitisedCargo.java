package domainLogic;

import administration.Customer;
import cargo.Hazard;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ImplDryBulkAndUnitisedCargo {

    private boolean isFragile;  //UnitisedCargo-spezifische Attribute
    private Customer owner;     //Storage-spezifische Attribute
    private Duration durationOfStorage;
    private Date lastInspectionDate;
    private int storageLocation;
    private BigDecimal value;//Cargo-spezifische Attribute
    private Collection<Hazard> hazards;
    private int grainSize; //DryBulkCargo-spezifische Attribute


    private Date insertionDate; // Storage-spezifisches Attribut

    public ImplDryBulkAndUnitisedCargo(int grainSize, BigDecimal value,
                                      Collection<Hazard> hazards, Customer owner,
                                      Duration durationOfStorage ,
                                      int storageLocation, boolean isFragile) {
        this.isFragile = isFragile;
        this.owner = owner;
        this.durationOfStorage = durationOfStorage;
        this.lastInspectionDate = null;
        this.storageLocation = storageLocation;
        this.hazards = hazards;
        this.value = value;
        this.grainSize = grainSize;
        this.insertionDate = new Date();
    }
    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    public boolean isFragile() {
        return isFragile;
    }

    public Customer getOwner() {
        return owner;
    }

    public Duration getDurationOfStorage() {
        return durationOfStorage;
    }

    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    public int getStorageLocation() {
        return storageLocation;
    }

    public BigDecimal getValue() {
        return value;
    }

    public Collection<Hazard> getHazards() {
        return hazards;
    }

    public int getGrainSize() {
        return grainSize;
    }

    public Date getInsertionDate() {
        return insertionDate;
    }



}
