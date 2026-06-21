package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;

import java.util.Date;

public record PayloadUpdate(
        int StoragelocationId,
        Date inspectionDate
) implements IPayload {
}
