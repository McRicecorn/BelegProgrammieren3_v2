package cli.Payloads;

import cli.EventSystem.IPayload;
import domainLogic.ImplDryBulkCargo;

import java.util.Date;

public record PayloadUpdateDry(
        ImplDryBulkCargo dryBulkCargo,
        Date InspectionDate




) implements IPayload {
}
