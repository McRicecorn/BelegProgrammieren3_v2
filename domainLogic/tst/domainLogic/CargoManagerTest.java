package domainLogic;

import cargo.Hazard;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CargoManagerTest {

    private final Customer customer = new Customer("John Doe");
    private final BigDecimal value = new BigDecimal(100);
    private final List<Hazard> hazards = List.of(Hazard.FLAMMABLE);

    // ------------------------------------------------------------
    // CREATE TESTS
    // ------------------------------------------------------------
    @Nested
    class CreateDryBulkCargoTest {

        @Test
        void createDryBulkCargo_nullCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createDryBulkCargo(null, Duration.ofDays(10), 1, value, hazards, 5);
            assertFalse(result);
        }

        @Test
        void createDryBulkCargo_unregisteredCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);
            assertFalse(result);
        }

        @Test
        void createDryBulkCargo_registeredCustomer_returnsTrue() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            boolean result = manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);
            assertTrue(result);
        }

        @Test
        void createDryBulkCargo_duplicateStorageLocation_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            boolean result = manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);
            assertFalse(result);
        }

        @Test
        void createDryBulkCargo_setsInsertionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            var cargo = manager.readDryBulkCargo().get(0);
            assertNotNull(cargo.getInsertionDate());
        }
    }

    // ------------------------------------------------------------
    // READ TESTS
    // ------------------------------------------------------------
    @Nested
    class ReadDryBulkCargoTest {

        @Test
        void readDryBulkCargo_emptyList_returnsEmpty() {
            CargoManager manager = new CargoManager();
            assertTrue(manager.readDryBulkCargo().isEmpty());
        }

        @Test
        void readDryBulkCargo_afterCreate_returnsOne() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            assertEquals(1, manager.readDryBulkCargo().size());
        }
    }

    // ------------------------------------------------------------
    // UPDATE TESTS
    // ------------------------------------------------------------
    @Nested
    class UpdateDryBulkCargoTest {

        @Test
        void updateDryBulkCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.updateDryBulkCargo(null, new Date());
            assertFalse(result);
        }

        @Test
        void updateDryBulkCargo_nullDate_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            var cargo = manager.readDryBulkCargo().get(0);
            boolean result = manager.updateDryBulkCargo(cargo, null);

            assertFalse(result);
        }

        @Test
        void updateDryBulkCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplDryBulkCargo foreignCargo = mock(ImplDryBulkCargo.class);

            boolean result = manager.updateDryBulkCargo(foreignCargo, new Date());
            assertFalse(result);
        }

        @Test
        void updateDryBulkCargo_validCargo_updatesInspectionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            var cargo = manager.readDryBulkCargo().get(0);
            Date date = new Date();

            manager.updateDryBulkCargo(cargo, date);

            assertEquals(date, cargo.getLastInspectionDate());
        }
    }

    // ------------------------------------------------------------
    // DELETE TESTS
    // ------------------------------------------------------------
    @Nested
    class DeleteDryBulkCargoTest {

        @Test
        void deleteDryBulkCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            assertFalse(manager.deleteDryBulkCargo(null));
        }

        @Test
        void deleteDryBulkCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplDryBulkCargo foreignCargo = mock(ImplDryBulkCargo.class);

            assertFalse(manager.deleteDryBulkCargo(foreignCargo));
        }

        @Test
        void deleteDryBulkCargo_existingCargo_removesCargo() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargo(customer, Duration.ofDays(10), 1, value, hazards, 5);

            var cargo = manager.readDryBulkCargo().get(0);
            manager.deleteDryBulkCargo(cargo);

            assertTrue(manager.readDryBulkCargo().isEmpty());
        }
    }

    @Nested
    class CreateUnitisedCargoTest {

        @Test
        void createUnitisedCargo_nullCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createUnitisedCargo(true, value, hazards, null, Duration.ofDays(10), 1);
            assertFalse(result);
        }

        @Test
        void createUnitisedCargo_unregisteredCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);
            assertFalse(result);
        }

        @Test
        void createUnitisedCargo_registeredCustomer_returnsTrue() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            boolean result = manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);
            assertTrue(result);
        }

        @Test
        void createUnitisedCargo_duplicateStorageLocation_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);
            boolean result = manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);

            assertFalse(result);
        }

        @Test
        void createUnitisedCargo_hazardsEmpty_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            boolean result = manager.createUnitisedCargo(true, value, List.of(), customer, Duration.ofDays(10), 1);
            assertFalse(result);
        }

        @Test
        void createUnitisedCargo_setsInsertionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);
            var cargo = manager.readUnitisedCargo().get(0);

            assertNotNull(cargo.getInsertionDate());
        }
    }

    @Nested
    class CreateDryBulkAndUnitisedCargoTest {

        @Test
        void createCombinedCargo_nullCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, null, Duration.ofDays(10), 1, true);
            assertFalse(result);
        }

        @Test
        void createCombinedCargo_unregisteredCustomer_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);
            assertFalse(result);
        }

        @Test
        void createCombinedCargo_registeredCustomer_returnsTrue() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            boolean result = manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);
            assertTrue(result);
        }

        @Test
        void createCombinedCargo_duplicateStorageLocation_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);
            boolean result = manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);

            assertFalse(result);
        }

        @Test
        void createCombinedCargo_grainSizeNegative_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            boolean result = manager.createDryBulkCargoAndUnitisedCargo(-1, value, hazards, customer, Duration.ofDays(10), 1, true);
            assertFalse(result);
        }

        @Test
        void createCombinedCargo_setsInsertionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);

            manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);
            var cargo = manager.readDryBulkAndUnitisedCargo().get(0);

            assertNotNull(cargo.getInsertionDate());
        }
    }

    @Nested
    class UpdateUnitisedCargoTest {

        @Test
        void updateUnitisedCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.updateUnitisedCargo(null, new Date());
            assertFalse(result);
        }

        @Test
        void updateUnitisedCargo_nullDate_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);

            var cargo = manager.readUnitisedCargo().get(0);
            boolean result = manager.updateUnitisedCargo(cargo, null);

            assertFalse(result);
        }

        @Test
        void updateUnitisedCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplUnitisedCargo foreignCargo = mock(ImplUnitisedCargo.class);

            boolean result = manager.updateUnitisedCargo(foreignCargo, new Date());
            assertFalse(result);
        }

        @Test
        void updateUnitisedCargo_validCargo_updatesInspectionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);

            var cargo = manager.readUnitisedCargo().get(0);
            Date date = new Date();

            manager.updateUnitisedCargo(cargo, date);

            assertEquals(date, cargo.getLastInspectionDate());
        }
    }
    @Nested
    class UpdateCombinedCargoTest {

        @Test
        void updateCombinedCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            boolean result = manager.updateDryBulkAndUnitisedCargo(null, new Date());
            assertFalse(result);
        }

        @Test
        void updateCombinedCargo_nullDate_returnsFalse() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);

            var cargo = manager.readDryBulkAndUnitisedCargo().get(0);
            boolean result = manager.updateDryBulkAndUnitisedCargo(cargo, null);

            assertFalse(result);
        }

        @Test
        void updateCombinedCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplDryBulkAndUnitisedCargo foreignCargo = mock(ImplDryBulkAndUnitisedCargo.class);

            boolean result = manager.updateDryBulkAndUnitisedCargo(foreignCargo, new Date());
            assertFalse(result);
        }

        @Test
        void updateCombinedCargo_validCargo_updatesInspectionDate() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);

            var cargo = manager.readDryBulkAndUnitisedCargo().get(0);
            Date date = new Date();

            manager.updateDryBulkAndUnitisedCargo(cargo, date);

            assertEquals(date, cargo.getLastInspectionDate());
        }
    }

    @Nested
    class DeleteUnitisedCargoTest {

        @Test
        void deleteUnitisedCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            assertFalse(manager.deleteUnitisedCargo(null));
        }

        @Test
        void deleteUnitisedCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplUnitisedCargo foreignCargo = mock(ImplUnitisedCargo.class);

            assertFalse(manager.deleteUnitisedCargo(foreignCargo));
        }

        @Test
        void deleteUnitisedCargo_existingCargo_removesCargo() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createUnitisedCargo(true, value, hazards, customer, Duration.ofDays(10), 1);

            var cargo = manager.readUnitisedCargo().get(0);
            manager.deleteUnitisedCargo(cargo);

            assertTrue(manager.readUnitisedCargo().isEmpty());
        }
    }
    @Nested
    class DeleteCombinedCargoTest {

        @Test
        void deleteCombinedCargo_nullCargo_returnsFalse() {
            CargoManager manager = new CargoManager();
            assertFalse(manager.deleteDryBulkAndUnitisedCargo(null));
        }

        @Test
        void deleteCombinedCargo_notInList_returnsFalse() {
            CargoManager manager = new CargoManager();
            ImplDryBulkAndUnitisedCargo foreignCargo = mock(ImplDryBulkAndUnitisedCargo.class);

            assertFalse(manager.deleteDryBulkAndUnitisedCargo(foreignCargo));
        }

        @Test
        void deleteCombinedCargo_existingCargo_removesCargo() {
            CargoManager manager = new CargoManager();
            manager.registerCustomer(customer);
            manager.createDryBulkCargoAndUnitisedCargo(5, value, hazards, customer, Duration.ofDays(10), 1, true);

            var cargo = manager.readDryBulkAndUnitisedCargo().get(0);
            manager.deleteDryBulkAndUnitisedCargo(cargo);

            assertTrue(manager.readDryBulkAndUnitisedCargo().isEmpty());
        }
    }

}
