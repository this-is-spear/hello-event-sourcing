package hello.tis.command;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.axonframework.commandhandling.CommandHandler;
import org.axonframework.eventsourcing.EventSourcingHandler;
import org.axonframework.modelling.command.AggregateIdentifier;
import org.axonframework.spring.stereotype.Aggregate;

import java.time.Instant;

import static org.axonframework.modelling.command.AggregateLifecycle.apply;

/**
 * @see Aggregate : 명령 모델 컴포넌트에서 수신한 명령 및 이벤트에 따라 Bike 인스턴스의 라이프사이클을 처리한다.
 */
@Aggregate
@NoArgsConstructor
@Getter
public class Bike {
    @AggregateIdentifier
    private String bikeId;
    private boolean isAvailable;
    private String reservedBy;
    private boolean reservationConfirmed;

    /**
     * 명령을 처리한다. 처리한 후 상태 변경을 알리기 위해 이벤트를 발행한다.
     *
     * @see CommandHandler : 주석이 달린 클래스를 명령 버스에 등록한다.
     * @see org.axonframework.commandhandling.AnnotationCommandHandlerAdapter : 명령 버스에 등록하는 주체다.
     * @see org.axonframework.modelling.command.AggregateLifecycle : 시스템(자전거 등록) 상태 변경을 알리기 위해 호출한다.
     *
     * @param command
     */
    @CommandHandler
    public Bike(RegisterBikeCommand command) {
        var seconds = Instant.now().getEpochSecond();
        if (seconds % 5 ==0) {
            throw new IllegalStateException("Can't accept new bikes right now");
        }

        apply(new BikeRegisteredEvent(command.getBikeId(), command.getBikeType(), command.getLocation()));
    }

    /**
     * 발행된 이벤트를 처리한다.
     * EventSourcingHandler 는 이미 처리됐기 때문에 수신된 이벤트를 검증하거나 무시해선 안된다.
     *
     * @param event
     */
    @EventSourcingHandler
    protected void handle(BikeRegisteredEvent event) {
        this.bikeId = event.getBikeId();
        this.isAvailable = true;
    }
}
