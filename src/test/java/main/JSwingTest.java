package main;

import org.assertj.swing.edt.GuiActionRunner;
import org.assertj.swing.fixture.FrameFixture;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.swing.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JSwingTest {

    // Mockito Example
    public interface Dependency {
        String getData();
    }

    @Test
    public void testMockito() {
        // Create a mock instance of the Dependency interface
        Dependency mockDependency = Mockito.mock(Dependency.class);

        // Define the behavior of the mock
        Mockito.when(mockDependency.getData()).thenReturn("Mock Data");

        // Use the mock in your test
        String result = mockDependency.getData();

        // Verify the result
        assertEquals("Mock Data", result);
    }

    // AssertJ Swing Example
    private FrameFixture window;

    @BeforeEach
    public void setUp() {
        // Create the GUI on the event dispatch thread
        JFrame frame = GuiActionRunner.execute(() -> {
            JFrame jFrame = new JFrame();
            jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            jFrame.setSize(300, 200);
            JButton button = new JButton("Click me");
            jFrame.add(button);
            return jFrame;
        });

        // Initialize the FrameFixture
        window = new FrameFixture(frame);
        window.show(); // Show the frame to test
    }

    @AfterEach
    public void tearDown() {
        window.cleanUp();
    }

    @Test
    public void testButtonClick() {
        // Verify the button is visible
        window.button().requireVisible();

        // Click the button
        window.button().click();

        // Verify the button text
        window.button().requireText("Click me");
    }
}