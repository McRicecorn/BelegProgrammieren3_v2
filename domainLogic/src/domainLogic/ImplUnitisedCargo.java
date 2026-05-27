package domainLogic;

import administration.Customer;
import cargo.Hazard;
import cargo.UnitisedCargo;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;
import java.util.Date;

public class ImplUnitisedCargo implements UnitisedCargo {
    private boolean isFragile;  // UnitisedCargo-spezifisches Attribut
    private BigDecimal value;  // Cargo-spezifisches Attribut
    private Collection<Hazard> hazards;
    private Customer owner; // Storage-spezifisches Attribut
    private Duration durationOfStorage;


    private Date lastInspectionDate;
    private int storageLocation;



    private Date insertionDate; // Storage-spezifisches Attribut

    public ImplUnitisedCargo(boolean isFragile, BigDecimal value, Collection<Hazard> hazards,
                             Customer customer, Duration durationOfStorage, int storageLocation) {
        this.isFragile = isFragile;
        this.value = value;
        this.hazards = hazards;
        this.owner = customer;
        this.durationOfStorage = durationOfStorage;
        this.lastInspectionDate = null; // Initialwert
        this.storageLocation = storageLocation;
        this.insertionDate = new Date();
    }
    public void setLastInspectionDate(Date lastInspectionDate) {
        this.lastInspectionDate = lastInspectionDate;
    }

    @Override
    public boolean isFragile() {
        return isFragile;
    }

    @Override
    public BigDecimal getValue() {
        return value;
    }

    @Override
    public Collection<Hazard> getHazards() {
        return hazards;
    }

    public Customer getOwner() {
        return owner;
    }

    @Override
    public Duration getDurationOfStorage() {
        return durationOfStorage;
    }

    @Override
    public Date getLastInspectionDate() {
        return lastInspectionDate;
    }

    @Override
    public int getStorageLocation() {
        return storageLocation;
    }
    public Date getInsertionDate() {
        return insertionDate;
    }
}
