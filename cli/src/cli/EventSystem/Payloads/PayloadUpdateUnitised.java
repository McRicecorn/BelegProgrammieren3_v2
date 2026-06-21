package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;
import domainLogic.ImplUnitisedCargo;

import java.util.Date;

public record PayloadUpdateUnitised(

        ImplUnitisedCargo unitisedCargo,
        Date inspectionDate


) implements IPayload {
}
