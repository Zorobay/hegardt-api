package se.hegardt.api.jobs

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.scheduling.annotation.Async
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.Scheduled
import org.springframework.stereotype.Component
import se.hegardt.api.services.LocationService

@EnableAsync
@Component
class LocationCoordinatesJob {

    @Autowired
    private LocationService locationService

    @Async
    @Scheduled(fixedRate = 20000)
    void run() {
        locationService.processAwaitingElements()
    }
}
