package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;

public record PayloadReadCargoByStorageLocation(
        int storageLocation
) implements IPayload {
}
