package hello.advanced.trace;

/**
 * 로그를 시작할 때의 상태 정보를 가지고 있는 클래스.
 * 이 상태 정보들은 로그를 종료할 때 사용된다.
 */
public class TraceStatus {
    private TraceId traceId;
    private Long startTimeMs;
    private String message;

    public TraceStatus(final TraceId traceId, final Long startTimeMs, final String message) {
        this.traceId = traceId;
        this.startTimeMs = startTimeMs;
        this.message = message;
    }

    public TraceId getTraceId() {
        return traceId;
    }

    public Long getStartTimeMs() {
        return startTimeMs;
    }

    public String getMessage() {
        return message;
    }
}
