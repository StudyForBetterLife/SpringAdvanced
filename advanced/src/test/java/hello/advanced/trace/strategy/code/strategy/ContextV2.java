package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 전략을 파라미터로 전달 받는 방식
 * <p>
 * 클라이언트는 Context 를 실행하는 시점에 원하는 Strategy 를 전달할 수 있으므로
 * 이전 방식과 비교해서 원하는 전략을 더욱 유연하게 변경할 수 있다.
 * <p>
 * 테스트 코드를 보면 하나의 Context 만 생성한다.
 * 그리고 하나의 Context 에 실행 시점에 여러 전략을 인수로 전달해서 유연하게 실행하는 것을 확인할 수 있다
 */
@Slf4j
public class ContextV2 {

    /**
     * ContextV2 는 전략을 필드로 가지지 않는다. <br>
     * 대신에 전략을 execute(..) 가 호출될 때 마다 항상 파라미터로 전달 받는다.
     */
    public void execute(Strategy strategy) {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
