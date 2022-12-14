package ram.talia.triplecheck.framework;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Annotation used to mark integration tests.
 * These are tests which are conducted by loading a world, building a structure from a saved structure NBT, and referencing a integration test class and method which set out requirements for what a success or fail look like.
 *
 * Any test method this is used on MUST take one parameter, of type {@link IntegrationTestHelper}
 * This is the link to the outside {@link net.minecraft.world.level.Level}. It has various methods for performing actions, or codifying failure or success conditions.
 *
 * @see IntegrationTestHelper
 * @see IntegrationTestClass
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface IntegrationTest
{
    /**
     * If omitted the method name will be used instead
     * The NBT structure representing the test will be searched for at {testClass}/{testName}.nbt
     *
     * @return The name of this test.
     */
    String value () default "";

    /**
     * How long before this test will be deemed failed by time out.
     *
     * @return A number of ticks > 0, or -1 to indicate there is no timeout.
     */
    int timeoutTicks () default 200;

    /**
     * How often this test's conditions should be checked.
     * By default, they will be re-evaluated every 10 ticks.
     *
     * @return A number of ticks > 0
     */
    int refreshTicks () default 10;
}
