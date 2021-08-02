package com.premonition.lc.ch07.domain.queries;

import com.premonition.lc.ch07.domain.events.LCApplicationStartedEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.axonframework.eventhandling.EventHandler;
import org.axonframework.queryhandling.QueryHandler;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Log4j2
@RequiredArgsConstructor
public class LCApplicationStartedEventHandler {

    private final LCApplicationViewRepository repository;

    @EventHandler
    public void on(LCApplicationStartedEvent event) {
        repository.save(new LCView(event.getId(), event.getClientReference()));
        log.info("New LC with client reference '{}' started!", event.getClientReference());
    }

    @QueryHandler
    public List<LCView> on(AllQuery query) {
        log.info("Finding all LCs!");
        return repository.findAll();
    }

}
