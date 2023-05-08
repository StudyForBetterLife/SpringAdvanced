package hello.advanced.trace;

import java.util.UUID;

public class TraceId {
    private String id; // 트랜잭션(유저의 요청이 들어온 후 나갈 때 까지) id
    private int level; // log에서 depth를 표현

    public TraceId() {
        this.id = createId();
        this.level = 0;
    }

    private TraceId(final String id, final int level) {
        this.id = id;
        this.level = level;
    }

    private String createId() {
        return UUID.randomUUID().toString().substring(0, 8);
    }

    /**
     * log에서 depth(level)를 증가시킨 TraceId
     */
    public TraceId createNextId() {
        return new TraceId(id, level + 1);
    }

    public TraceId createPreviousId() {
        return new TraceId(id, level - 1);
    }

    public boolean isFirstLevel() {
        return level == 0;
    }

    public String getId() {
        return id;
    }

    public int getLevel() {
        return level;
    }
}
