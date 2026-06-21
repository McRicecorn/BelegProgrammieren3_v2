package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;
import domainLogic.ImplDryBulkCargo;

import java.util.Date;

public record PayloadUpdateDry(
        ImplDryBulkCargo dryBulkCargo,
        Date InspectionDate




) implements IPayload {
}
