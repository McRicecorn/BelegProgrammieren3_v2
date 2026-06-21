package cli.Payloads;

import administration.Customer;
import cargo.Hazard;
import cli.EventSystem.IPayload;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public record PayloadDryAndUnitisedCargo(
        int grainSize,
        BigDecimal value,
        Collection<Hazard> hazards,
        Customer owner,
        Duration durationOfStorage,
        int storageLocation,
        boolean isFragile
) implements IPayload {
}
