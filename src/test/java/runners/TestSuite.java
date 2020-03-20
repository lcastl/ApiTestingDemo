package runners;


import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import testcases.GetTest;
import testcases.PostTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        GetTest.class,
        PostTest.class
})
public class TestSuite {
}
