package helper;

import org.sikuli.script.Match;
import org.sikuli.script.Pattern;
import org.sikuli.script.Screen;

/**
 * Created by Andrei.Ostrovski on 22.11.2016.
 */
public class GraphUtils {
    public static boolean compareGraphs(String GraphModelPath) {
        Pattern graphModel = new Pattern(GraphModelPath).similar(0.92f);
        Screen s = new Screen();
        Match m = s.exists(graphModel);
        return m != null;
    }
}
