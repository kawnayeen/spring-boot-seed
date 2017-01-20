package com.kawnayeen.logger;

import org.junit.Rule;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by kawnayeen on 1/5/17.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = LoggerApplication.class)
public abstract class AbstractTest {
    @Rule
    public ExpectedException thrown = ExpectedException.none();
}
