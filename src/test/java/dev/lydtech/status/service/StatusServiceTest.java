package dev.lydtech.status.service;

import dev.lydtech.status.message.OrderCreated;
import dev.lydtech.status.message.OrderDispatched;
import dev.lydtech.status.util.TestEventData;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;

public class StatusServiceTest {
    private StatusService service;

    @BeforeEach
    public void setup() {
        service = new StatusService();
    }

    @Test
    public void testProcessOrderCreated() {
        OrderCreated testEvent = TestEventData.buildOrderCreatedEvent(UUID.randomUUID(), RandomStringUtils.randomAlphabetic(8));
        service.processOrderCreated(testEvent);
    }

    @Test
    public void testProcessOrderDispatched() {
        OrderDispatched testEvent = TestEventData.buildOrderDispatchedEvent(UUID.randomUUID());
        service.processOrderDispatched(testEvent);
    }
}