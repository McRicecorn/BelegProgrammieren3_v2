package cli.Payloads;

import cli.EventSystem.IPayload;

public record PayloadReadCargoByStorageLocation(
        int storageLocation
) implements IPayload {
}
