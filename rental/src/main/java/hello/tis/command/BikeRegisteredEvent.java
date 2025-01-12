package hello.tis.command;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public class BikeRegisteredEvent {
    private final String bikeId;
    private final String bikeType;
    private final String location;

}
