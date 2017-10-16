package core;

import com.google.inject.Inject;
import org.jenkinsci.test.acceptance.junit.AbstractJUnitTest;
import org.jenkinsci.test.acceptance.junit.SmokeTest;
import org.jenkinsci.test.acceptance.po.Slave;
import org.jenkinsci.test.acceptance.slave.SlaveController;
import org.junit.Test;
import org.junit.experimental.categories.Category;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ScriptTest extends AbstractJUnitTest {
    @Inject
    SlaveController slave;

    @Test
    @Category(SmokeTest.class)
    public void execute_system_script() throws Exception {
        String output = jenkins.runScript("println Jenkins.instance.displayName;");
        assertThat(output, is("Jenkins"));

        Slave s = slave.install(jenkins).get();
        output = s.runScript("println 6 * 7");
        assertThat(output, is("42"));
    }
}
