package domainLogic;


import cargo.Hazard;


import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

public class CargoManager  {


    //Create
    List<ImplDryBulkCargo> dryBulkCargoList = new ArrayList<>();
    List<ImplUnitisedCargo> unitisedCargoList = new ArrayList<>();
    List<ImplDryBulkAndUnitisedCargo> dryBulkAndUnitisedCargoList = new ArrayList<>();
    private List<Customer> customers = new ArrayList<>();


    public boolean createDryBulkCargo(
            Customer customer, Duration durationOfStorage,
            int storageLocation, BigDecimal value,
            Collection<Hazard> hazard, int grainSize ) {

        if (customer == null || durationOfStorage == null ||
                storageLocation < 0 || value == null ||
                hazard == null || hazard.isEmpty()||grainSize < 0) {

            return false;
        }
        if (!customers.contains(customer)) {
            return false;
        }
        if (isStorageLocationUsed(storageLocation)){
            return false;
        }


         else {

            ImplDryBulkCargo cargo = new ImplDryBulkCargo(grainSize, value, hazard, customer, durationOfStorage, storageLocation);

            dryBulkCargoList.add(cargo);


            return true;
        }
    }

    public boolean createUnitisedCargo(
            boolean isFragile , BigDecimal value,
            Collection<Hazard> hazard, Customer customer,
            Duration durationOfStorage, int storageLocation
            ) {

        if (customer == null || durationOfStorage == null ||
            storageLocation < 0 || value == null ||
            hazard == null || hazard.isEmpty()) {

        return false;
        }
        if (!customers.contains(customer)) {
            return false;
        }
        if (isStorageLocationUsed(storageLocation)){
            return false;
        }
         else {


            ImplUnitisedCargo cargo = new ImplUnitisedCargo(isFragile, value, hazard, customer, durationOfStorage, storageLocation);

            unitisedCargoList.add(cargo);

            return true;

        }

    }

    public boolean createDryBulkCargoAndUnitisedCargo(
            int grainSize, BigDecimal value,
            Collection<Hazard> hazards, Customer customer,
            Duration durationOfStorage ,
            int storageLocation, boolean isFragile) {

        if (customer == null || durationOfStorage == null ||
            storageLocation < 0 || value == null ||
            hazards == null || hazards.isEmpty()|| grainSize < 0) {
        return false;
    }
        if (!customers.contains(customer)) {
            return false;
        }
        if (isStorageLocationUsed(storageLocation)){
            return false;
        }


         else {
            ImplDryBulkAndUnitisedCargo cargo = new ImplDryBulkAndUnitisedCargo(grainSize, value, hazards, customer, durationOfStorage, storageLocation, isFragile);

            dryBulkAndUnitisedCargoList.add(cargo);

            return true;
        }
    }

    //Read
    public List<ImplDryBulkCargo> readDryBulkCargo() {

        return new ArrayList<>(dryBulkCargoList);
    }

    public List<ImplUnitisedCargo> readUnitisedCargo() {

        return new ArrayList<>(unitisedCargoList) ;
    }

    public List<ImplDryBulkAndUnitisedCargo> readDryBulkAndUnitisedCargo() {

        return new ArrayList<>(dryBulkAndUnitisedCargoList);
    }

    //Update
    public boolean updateDryBulkCargo(ImplDryBulkCargo cargo, Date lastInspectionDate) {
        if (cargo == null || lastInspectionDate == null) return false;

        if (!dryBulkCargoList.contains(cargo)) return false;

        cargo.setLastInspectionDate(lastInspectionDate);
        return true;

    }

    public boolean updateUnitisedCargo(ImplUnitisedCargo cargo, Date lastInspectionDate) {
        if (cargo == null || lastInspectionDate == null) return false;

        if (!unitisedCargoList.contains(cargo)) return false;

        cargo.setLastInspectionDate(lastInspectionDate);
        return true;

    }

    public boolean updateDryBulkAndUnitisedCargo(ImplDryBulkAndUnitisedCargo cargo, Date lastInspectionDate) {
        if (cargo == null || lastInspectionDate == null) return false;

        if (!dryBulkAndUnitisedCargoList.contains(cargo)) return false;

        cargo.setLastInspectionDate(lastInspectionDate);
        return true;

    }

        //Delete
    public boolean deleteDryBulkCargo(ImplDryBulkCargo cargo) {
        if (cargo == null || !dryBulkCargoList.contains(cargo)) {
            return false;
        }

        return dryBulkCargoList.remove(cargo);
    }

    public boolean deleteUnitisedCargo(ImplUnitisedCargo cargo) {
        if (cargo == null || !unitisedCargoList.contains(cargo)) {
            return false;
        }

        return unitisedCargoList.remove(cargo);
    }

    public boolean deleteDryBulkAndUnitisedCargo(ImplDryBulkAndUnitisedCargo cargo) {
        if (cargo == null || !dryBulkAndUnitisedCargoList.contains(cargo)) {
            return false;
        }

        return dryBulkAndUnitisedCargoList.remove(cargo);
    }



    private boolean isStorageLocationUsed(int location) {
        return dryBulkCargoList.stream().anyMatch(c -> c.getStorageLocation() == location)
                || unitisedCargoList.stream().anyMatch(c -> c.getStorageLocation() == location)
                || dryBulkAndUnitisedCargoList.stream().anyMatch(c -> c.getStorageLocation() == location);
    }

    public void registerCustomer(Customer c) {
        customers.add(c);
    }

}
