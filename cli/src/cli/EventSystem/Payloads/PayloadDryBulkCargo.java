package cli.EventSystem.Payloads;


import cargo.Hazard;
import cli.EventSystem.Interfaces.IPayload;
import domainLogic.ImplCustomer;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.Collection;

public record PayloadDryBulkCargo(

        int grainSize, BigDecimal value,
        Collection<Hazard> hazards, ImplCustomer owner,
        Duration durationOfStorage ,
        int storageLocation
) implements IPayload {
}
