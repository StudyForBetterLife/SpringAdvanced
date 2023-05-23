package hello.advanced.trace.strategy.code.strategy;

import lombok.extern.slf4j.Slf4j;

/**
 * 필드에 전략을 보관하는 방식.
 * <p>
 * ContextV1 은 변하지 않는 로직을 가지고 있는 템플릿 역할을 하는 코드이다.
 * 전략 패턴에서는 이것을 컨텍스트(문맥)이라 한다.
 * <p>
 * 전략 패턴의 핵심은 Context 는 Strategy 인터페이스에만 의존한다는 점이다.
 * <strong>덕분에 Strategy의 구현체를 변경하거나 새로 만들어도 Context 코드에는 영향을 주지 않는다.</strong>
 * <p>
 *
 * <h2>바로 스프링에서 의존관계 주입에서 사용하는 방식이 바로 전략패턴이다.</h2>
 */
@Slf4j
public class ContextV1 {

    private final Strategy strategy;

    /**
     * <h2>선 조립, 후 실행</h2>
     * Context 의 내부 필드에 Strategy 를 두고 사용하는 방식은
     * Context 와 Strategy 를 실행 전에 원하는 모양으로 조립해두고, 그 다음에 Context 를 실행하는
     * 선 조립, 후 실행 방식에서 매우 유용하다.
     * <p>
     * <h2>선 조립, 후 실행 방식의 단점</h2>
     * Context 와 Strategy 를 조립한 이후에는 전략을 변경하기가 번거롭다.
     * Context 에 setter 를 제공해서 Strategy 를 넘겨 받아 변경하면 되지만,
     * Context 를 싱글톤으로 사용할 때는 동시성 이슈 등 고려할 점이 많다.
     */
    public ContextV1(Strategy strategy) {
        this.strategy = strategy;
    }

    public void execute() {
        long startTime = System.currentTimeMillis();
        //비즈니스 로직 실행
        strategy.call(); //위임
        //비즈니스 로직 종료
        long endTime = System.currentTimeMillis();
        long resultTime = endTime - startTime;
        log.info("resultTime={}", resultTime);
    }
}
