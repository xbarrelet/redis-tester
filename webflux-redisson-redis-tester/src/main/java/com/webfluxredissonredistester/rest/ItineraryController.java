package com.webfluxredissonredistester.rest;

import com.webfluxredissonredistester.entities.Itinerary;
import com.webfluxredissonredistester.repositories.RedissonRepository;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.BatchResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Slf4j
public class ItineraryController {

    @Autowired
    private RedissonRepository repository;


    @PostMapping(value = "/find", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<String> findItinerary(@RequestBody String hash) {
        log.info("Hello from find from the controller");
        return repository.find(hash);
    }

    @PostMapping(value = "/findAll", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public Mono<List<String>> findItineraries(@RequestBody List<String> hashes) {
        return repository.findAll(hashes);
    }

    @PostMapping(value = "/itinerary", consumes = APPLICATION_JSON_VALUE)
    public Mono<Boolean> saveItinerary(@RequestBody Itinerary itinerary) {
        return repository.save(itinerary);
    }

    @PostMapping(value = "/itineraries", consumes = APPLICATION_JSON_VALUE)
    public Mono<Boolean> saveItineraries(@RequestBody List<Itinerary> itineraries) {
        return repository.saveAll(Flux.fromIterable(itineraries));
    }

    @PostMapping(value = "/itineraries/pipeline", consumes = APPLICATION_JSON_VALUE)
    public Mono<BatchResult<?>> saveItinerariesWithPipeline(@RequestBody List<Itinerary> itineraries) {
        return repository.saveAllWithPipelining(Flux.fromIterable(itineraries));
    }

    //HASHMAP
    @PostMapping(value = "/find/hashmap", produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<String> findItineraryInHashmap(@RequestBody String hash) {
        return repository.findInHashmap(hash);
    }

    @PostMapping(value = "/findAll/hashmap", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public Mono<List<String>> findItinerariesInHashmap(@RequestBody List<String> hashes) {
        return repository.findAllInHashmap(hashes);
    }

    @PostMapping(value = "/itinerary/hashmap", consumes = APPLICATION_JSON_VALUE)
    public Mono<Boolean> saveItineraryInHashmap(@RequestBody Itinerary itinerary) {
        return repository.saveInHashMap(itinerary);
    }

    @PostMapping(value = "/itineraries/hashmap", consumes = APPLICATION_JSON_VALUE)
    public Mono<Boolean> saveItinerariesInHashmap(@RequestBody List<Itinerary> itineraries) {
        return repository.saveAllInHashMap(Flux.fromIterable(itineraries));
    }

    @PostMapping(value = "/itineraries/hashmap/pipeline", consumes = APPLICATION_JSON_VALUE)
    public Mono<BatchResult<?>> saveItinerariesInHashmapWithPipeline(@RequestBody List<Itinerary> itineraries) {
        return repository.saveAllInHashMapWithPipelining(Flux.fromIterable(itineraries));
    }

    //MISC
    @DeleteMapping(value = "/itineraries")
    public Mono<Long> deleteAll() {
        return repository.deleteAll();
    }
}
