import io.jbotsim.core.Topology;
import io.jbotsim.ui.painting.UIComponent;
import io.jbotsim.ui.painting.BackgroundPainter;

import java.awt.*;

public class MyBackgroundPainter implements BackgroundPainter {
    @Override
    public void paintBackground(UIComponent c, Topology tp) {
        Graphics2D g = (Graphics2D) c.getComponent();


        // Setting a background image
        Toolkit tk = Toolkit.getDefaultToolkit();
        Image image = tk.getImage(getClass().getResource("/route.png"));
        g.drawImage(image, 0, 0, null);

    }
}