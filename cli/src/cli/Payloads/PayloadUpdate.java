package cli.Payloads;

import cli.EventSystem.IPayload;

import java.util.Date;

public record PayloadUpdate(
        int StoragelocationId,
        Date inspectionDate
) implements IPayload {
}
