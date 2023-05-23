package hello.advanced.trace.template;

import hello.advanced.trace.TraceStatus;
import hello.advanced.trace.logtrace.LogTrace;

/**
 * <h2>탬플릿 메소드 패턴의 단점</h2>
 * 상속을 받는 다는 것은 특정 부모 클래스를 의존하고 있다는 것이다
 * 부모 클래스의 기능을 사용하든 사용하지 않든 간에 부모클래스를 강하게 의존하게 된다.
 * 자식 클래스 입장에서는 부모 클래스의 기능을 전혀 사용하지 않는데, 부모 클래스를 알아야한다.
 * 이것은 좋은 설계가 아니다.
 * <p>
 * <h2>전략 패턴</h2>
 * 템플릿 메서드 패턴과 비슷한 역할을 하면서
 * 상속의 단점을 제거할 수 있는 디자인 패턴이 바로 전략 패턴 (Strategy Pattern)이다.
 */
public abstract class AbstractTemplate<T> {
    private final LogTrace trace;

    protected AbstractTemplate(final LogTrace trace) {
        this.trace = trace;
    }

    public T execute(String message) {
        TraceStatus status = null;
        try {
            status = trace.begin(message);

            // 로직 호출
            T result = call();
            trace.end(status);
            return result;
        } catch (Exception e) {
            trace.exception(status, e);
            throw e;
        }
    }

    protected abstract T call();
}
