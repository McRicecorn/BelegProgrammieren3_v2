package cli.EventSystem.Payloads;

import cli.EventSystem.Interfaces.IPayload;

public record PayloadDeleteCargo(

int StoragelocationId


) implements IPayload {

}
