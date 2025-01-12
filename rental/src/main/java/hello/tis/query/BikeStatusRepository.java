package hello.tis.query;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BikeStatusRepository extends JpaRepository<BikeStatus, String> {
    List<BikeStatus> findAllByBikeTypeAndStatus(String bikeType, RentalStatus rentalStatus);
}
