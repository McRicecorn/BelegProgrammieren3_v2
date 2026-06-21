package cli.EventSystem.Payloads;

import administration.Customer;
import cargo.Hazard;
import cli.EventSystem.Interfaces.IPayload;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public record PayloadUnitisedCargo(
        boolean isFragile,
        BigDecimal value,
        Collection<Hazard> hazards,
        Customer customer,
        Duration durationOfStorage,
        int storageLocation
) implements IPayload {
}
