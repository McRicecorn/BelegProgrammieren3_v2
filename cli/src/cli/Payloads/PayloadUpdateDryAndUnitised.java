package cli.Payloads;

import cli.EventSystem.IPayload;
import domainLogic.ImplDryBulkAndUnitisedCargo;

import java.util.Date;

public record PayloadUpdateDryAndUnitised(
        ImplDryBulkAndUnitisedCargo dryAndUnitisedCargo,
        Date inspectionDate
) implements IPayload {
}
