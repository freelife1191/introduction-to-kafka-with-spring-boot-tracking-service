package dev.lydtech.tracking.handler;

import dev.lydtech.dispatch.message.DispatchPreparing;
import dev.lydtech.tracking.service.TrackingService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
// 디스패치 추적 핸들러가 잠재적으로 여러 이벤트 유형을 처리하도록 상단에 배치
@KafkaListener(
        id = "dispatchTrackingConsumerClient",
        topics = "dispatch.tracking",
        groupId = "tracking.dispatch.tracking",
        containerFactory = "kafkaListenerContainerFactory"
)
public class DispatchTrackingHandler {

    private final TrackingService trackingService;

    // @KafkaHandler를 사용하여 여러 리슨 메서드를 추가할 수 있음
    @KafkaHandler
    public void listen(DispatchPreparing dispatchPreparing) {
        try {
            trackingService.process(dispatchPreparing);
        } catch (Exception e) {
            log.error("Processing failure", e);
        }
    }
}
