package helper;

import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 * Created by Andrei.Ostrovski on 22.11.2016.
 */
public class GraphUtils {
    public static boolean compareGraphs(String widgetGraphModelPath) {
        Pattern graphModel = new Pattern(widgetGraphModelPath).similar(0.95f);
        Screen s = new Screen();
        Match m = s.exists(graphModel);
        return m != null;
    }
}
