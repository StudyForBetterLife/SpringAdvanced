package hello.advanced.trace.threadlocal.code;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ThreadLocalService {

    /**
     * ThreadLocal 사용법
     * - 값 저장: ThreadLocal.set(xxx)
     * - 값 조회: ThreadLocal.get()
     * - 값 제거: ThreadLocal.remove()
     * <p>
     * <h3>주의</h3>
     * <p>
     * 해당 쓰레드가 쓰레드 로컬을 모두 사용하고 나면 ThreadLocal.remove() 를 호출해서 쓰레드 로컬에
     * 저장된 값을 제거해주어야 한다.
     * <p>
     * WAS는 사용이 끝난 thread-A 를 쓰레드 풀에 반환한다. 쓰레드를 생성하는 비용은 비싸기 때문에
     * 쓰레드를 제거하지 않고, 보통 쓰레드 풀을 통해서 쓰레드를 재사용한다.
     * <p>
     * <h3>[쓰레드 풀에서 thread-A가 살아있을 경우]</h3>
     * 사용자 B가 HTTP 요청을하여 WAS가 쓰레드 풀에서 쓰레드를 조회한다. 하필 thread-A가 할당된다면,
     * thread-A는 쓰레드 로컬에서 '기존에 존재하는 데이터'를 반환하는 문제가 발생한다.
     * 이런 문제를 예방하려면 사용자A의 요청이 끝날 때 쓰레드 로컬의 값을 ThreadLocal.remove() 를
     * 통해서 꼭 제거해야 한다.
     */
    private ThreadLocal<String> nameStore = new ThreadLocal<>();

    public String logic(String name) {
        log.info("저장 name={} -> nameStore={}", name, nameStore.get());
        nameStore.set(name);
        sleep(1000);
        log.info("조회 nameStore={}", nameStore.get());
        return nameStore.get();
    }

    private void sleep(int millis) {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
