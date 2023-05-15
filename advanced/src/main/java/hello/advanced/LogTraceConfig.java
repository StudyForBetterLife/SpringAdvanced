package hello.advanced;

import hello.advanced.trace.logtrace.LogTrace;
import hello.advanced.trace.logtrace.ThreadLocalLogTrace;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LogTraceConfig {

    /**
     * V3에서 사용되는 LogTrace의 구현체를 ThreadLocalLogTrace으로 등록
     * <p>
     * -> 쓰레드별로 로그가 정확하게 나누어 진다.
     */
    @Bean
    public LogTrace logTrace() {
        return new ThreadLocalLogTrace();
    }
}
