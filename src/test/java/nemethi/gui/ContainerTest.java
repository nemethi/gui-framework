package nemethi.gui;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.list;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class ContainerTest {

    @Mock
    private Component component1;
    @Mock
    private Component component2;
    @Mock
    private Component component3;
    @Mock
    private Component component4;

    private Container container;

    @Before
    public void setUp() {
        initMocks(this);
        container = new Container() {
            @Override
            protected String renderOwnOpeningTag() {
                return "opening";
            }

            @Override
            protected String renderOwnClosingTag() {
                return "closing";
            }
        };
    }

    @Test
    public void addNextToLastWhenListIsEmpty() {
        // given
        List<List<Component>> expected = list(list(component1));

        // when
        container.addNextToLast(component1);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addNextToLast() {
        // given
        List<List<Component>> expected = list(list(component1, component2));

        // when
        container.addNextToLast(component1);
        container.addNextToLast(component2);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addNextToLastWorksWithMoreThanTwoComponents() {
        // given
        List<List<Component>> expected = list(list(component1, component2, component3));

        // when
        container.addNextToLast(component1);
        container.addNextToLast(component2);
        container.addNextToLast(component3);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addUnderLastWhenListIsEmpty() {
        // given
        List<List<Component>> expected = list(list(component1));

        // when
        container.addUnderLast(component1);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addUnderLast() {
        // given
        List<List<Component>> expected = list(list(component1), list(component2));

        // when
        container.addUnderLast(component1);
        container.addUnderLast(component2);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addingComponentsUnderLastThenNextToLast() {
        // given
        List<List<Component>> expected = list(list(component1, component2), list(component3, component4));

        // when
        container.addUnderLast(component1);
        container.addNextToLast(component2);
        container.addUnderLast(component3);
        container.addNextToLast(component4);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void addingComponentsNextToLastThenUnderLast() {
        // given
        List<List<Component>> expected = list(list(component1), list(component2, component3), list(component4));

        // when
        container.addNextToLast(component1);
        container.addUnderLast(component2);
        container.addNextToLast(component3);
        container.addUnderLast(component4);

        // then
        assertThat(container.getComponents()).isEqualTo(expected);
    }

    @Test
    public void renderEmptyContainer() {
        // given
        String expected = "opening<table></table>closing";

        // when + then
        assertThat(container.render()).isEqualTo(expected);
    }

    @Test
    public void renderSingleRowContainer() {
        // given
        when(component1.render()).thenReturn("component1");
        container.addNextToLast(component1);
        String expected = "opening<table><tr><td>component1</td></tr></table>closing";

        // when + then
        assertThat(container.render()).isEqualTo(expected);
        verify(component1).render();
        verifyNoMoreInteractions(component1);
    }

    @Test
    public void renderMultiRowContainer() {
        // given
        when(component1.render()).thenReturn("component1");
        when(component2.render()).thenReturn("component2");
        container.addNextToLast(component1);
        container.addUnderLast(component2);
        String expected = "opening<table>" +
                "<tr><td>component1</td></tr>" +
                "<tr><td>component2</td></tr>" +
                "</table>closing";

        // when + then
        assertThat(container.render()).isEqualTo(expected);
        verify(component1).render();
        verify(component2).render();
        verifyNoMoreInteractions(component1, component2);
    }
}
