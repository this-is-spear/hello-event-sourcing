package hello.tis.query;

import hello.tis.command.BikeRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BikeStatusProjection {
    private final BikeStatusRepository bikeStatusRepository;

    /**
     * 자전거 목록을 최신 상태로 유지하려면 새 자전거 등록할 때마다 저장소에 기록한다.
     */
    @EventHandler
    public void on(BikeRegisteredEvent event) {
        var bikeStatus = new BikeStatus(event.getBikeId(), event.getBikeType(), event.getLocation());
        bikeStatusRepository.save(bikeStatus);
    }

    @QueryHandler(queryName = BikeStatusNamedQueries.FIND_ALL)
    public Iterable<BikeStatus> findAll() {
        return bikeStatusRepository.findAll();
    }

    @QueryHandler(queryName = BikeStatusNamedQueries.FIND_AVAILABLE)
    public Iterable<BikeStatus> findAvailable(String bikeType) {
        return bikeStatusRepository.findAllByBikeTypeAndStatus(bikeType, RentalStatus.AVAILABLE);
    }

    @QueryHandler(queryName = BikeStatusNamedQueries.FIND_ONE)
    public BikeStatus findOne(String bikeId) {
        return bikeStatusRepository.findById(bikeId).orElse(null);
    }
}
