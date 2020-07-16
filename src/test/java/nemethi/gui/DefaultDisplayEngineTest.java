package nemethi.gui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class DefaultDisplayEngineTest {

    @Mock
    private Component component;

    @Before
    public void setUp() {
        initMocks(this);
    }

    @Test
    public void displayComponentInHtmlCode() {
        // given
        String expected = "<!DOCTYPE html><html><head></head><body>test</body></html>";
        when(component.render()).thenReturn("test");

        // when
        String html = new DefaultDisplayEngine().display(component);

        // then
        assertThat(html).isEqualTo(expected);
        verify(component).render();
        verifyNoMoreInteractions(component);
    }
}
