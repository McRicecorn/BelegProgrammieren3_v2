package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;
import domainLogic.ImplCustomer;

public record PayloadCustomer(
        ImplCustomer name
) implements IPayload {
}
