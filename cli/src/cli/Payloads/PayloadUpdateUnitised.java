package cli.Payloads;

import cli.EventSystem.IPayload;
import domainLogic.ImplUnitisedCargo;

import java.util.Date;

public record PayloadUpdateUnitised(

        ImplUnitisedCargo unitisedCargo,
        Date inspectionDate


) implements IPayload {
}
