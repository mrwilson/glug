package uk.co.probablyfine.glug;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class GlugTest {

    @Mock private GlugTask mockTask;

    @Test
    public void shouldRunRegisteredTask() throws NoSuchTaskNameException {
        String taskName = "default";

        Glug.task(taskName, mockTask);
        Glug.run (taskName);

        verify(mockTask).run();
    }

    @Test(expected = NoSuchTaskNameException.class)
    public void shouldThrowExceptionIfTaskDoesNotExist() throws NoSuchTaskNameException {
        Glug.run("does-not-exist");
    }
}
