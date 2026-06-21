package cli.Payloads;

import cli.EventSystem.IPayload;
import domainLogic.ImplCustomer;

public record PayloadCustomer(
        ImplCustomer name
) implements IPayload {
}
