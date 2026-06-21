package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;
import domainLogic.ImplDryBulkAndUnitisedCargo;

import java.util.Date;

public record PayloadUpdateDryAndUnitised(
        ImplDryBulkAndUnitisedCargo dryAndUnitisedCargo,
        Date inspectionDate
) implements IPayload {
}
