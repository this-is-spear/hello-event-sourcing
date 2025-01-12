package hello.tis.command;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.modelling.command.TargetAggregateIdentifier;

/**
 * @see TargetAggregateIdentifier : 해당 필드를 고유 식별자로 사용해 Bike 에 ID를 읽도록 지시한다.
 */
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegisterBikeCommand {
    @TargetAggregateIdentifier
    String bikeId;
    String bikeType;
    String location;
}
