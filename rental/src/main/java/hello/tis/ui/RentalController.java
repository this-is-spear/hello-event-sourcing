package hello.tis.ui;

import hello.tis.command.RegisterBikeCommand;
import hello.tis.query.BikeStatus;
import hello.tis.query.BikeStatusNamedQueries;
import lombok.AllArgsConstructor;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.messaging.responsetypes.ResponseTypes;
import org.axonframework.queryhandling.QueryGateway;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;

@RestController
@AllArgsConstructor
public class RentalController {

    private final CommandGateway commandGateway;
    private final QueryGateway queryGateway;

    @PostMapping("/bikes")
    public CompletableFuture<String> registerBike(
            @RequestParam("bikeType") String bikeType,
            @RequestParam("location") String location
    ) {
        String bikeId = UUID.randomUUID().toString();
        var registerBikeCommand = new RegisterBikeCommand(bikeId, bikeType, location);
        var commandResult = commandGateway.<String>send(registerBikeCommand);
        return commandResult;
    }

    /**
     * CompletableFutures 반환할 때 성능 고려 사항이 있다.
     * <p>
     * 쿼리 결과 참조를 유지하며 결과가 준비되면 해당 결과를 가져온다. 이러면 블록킹 되지 않고 쿼리 버스에서 쿼리 메시지를 보낸 후 즉시 반환한다.
     */
    @GetMapping("/bikes")
    public CompletableFuture<List<BikeStatus>> findAll() {
        return queryGateway.query(
                BikeStatusNamedQueries.FIND_ALL,
                null,
                ResponseTypes.multipleInstancesOf(BikeStatus.class)
        );
    }

    @GetMapping("/bikes/{bikeId}")
    public CompletableFuture<BikeStatus> findStatus(@PathVariable("bikeId") String bikeId) {
        return queryGateway.query(BikeStatusNamedQueries.FIND_ONE, bikeId, BikeStatus.class);
    }
}