package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import dev.lydtech.tracking.util.TestEventData;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.UUID;
import java.util.concurrent.ExecutionException;

import static org.mockito.Mockito.*;

public class DispatchPreparingHandlerTest {

    private TrackingService trackingServiceMock;

    private DispatchTrackingHandler handler;

    @BeforeEach
    public void setup() {
        trackingServiceMock = mock(TrackingService.class);
        handler = new DispatchTrackingHandler(trackingServiceMock);
    }

    @Test
    public void listen_Success() throws ExecutionException, InterruptedException {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(UUID.randomUUID());
        handler.listen(testEvent);
        verify(trackingServiceMock, times(1)).process(testEvent);
    }

    @Test
    public void listen_ServiceThrowsException() throws Exception {
        DispatchPreparing testEvent = TestEventData.buildDispatchPreparingEvent(UUID.randomUUID());
        doThrow(new RuntimeException("Service failure")).when(trackingServiceMock).process(testEvent);

        handler.listen(testEvent);

        verify(trackingServiceMock, times(1)).process(testEvent);
    }
}
