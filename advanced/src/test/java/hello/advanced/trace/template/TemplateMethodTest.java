package hello.advanced.trace.template;

import hello.advanced.trace.template.code.AbstractTemplate;
import hello.advanced.trace.template.code.SubClassLogic1;
import hello.advanced.trace.template.code.SubClassLogic2;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

@Slf4j
public class TemplateMethodTest {

    /**
     * logic1, logic2는 비지니스 로직 부분을 제외하고 코드가 중복된다.
     */
    @Test
    void templateMethodV0() {
        logic1();
        logic2();
    }

    private void logic1() {
        final long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        log.info("비지니스 로직1 실행");
        // 비지니스 로직 종료

        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }

    private void logic2() {
        final long startTime = System.currentTimeMillis();

        // 비지니스 로직 실행
        log.info("비지니스 로직2 실행");
        // 비지니스 로직 종료

        final long endTime = System.currentTimeMillis();
        final long resultTime = endTime - startTime;

        log.info("resultTime={}", resultTime);
    }


    /**
     * 템플릿 메서드 패턴 적용
     * <p>
     * 기존에 구현되어있는 구상 클래스를 활용.
     */
    @Test
    void templateMethodV1() {
        AbstractTemplate template1 = new SubClassLogic1();
        template1.execute();

        AbstractTemplate template2 = new SubClassLogic2();
        template2.execute();
    }

    /**
     * 익명의 구상 클래스를 구현.
     */
    @Test
    void templateMethodV2() {
        AbstractTemplate template1 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직1 실행");
            }
        };
        log.info("클래스 이름1={}", template1.getClass()); // TemplateMethodTest$1
        template1.execute();

        AbstractTemplate template2 = new AbstractTemplate() {
            @Override
            protected void call() {
                log.info("비즈니스 로직2 실행");
            }
        };
        log.info("클래스 이름2={}", template2.getClass()); // TemplateMethodTest$2
        template2.execute();
    }
}
