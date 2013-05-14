package dk.hagendazzlers.util;

import com.itextpdf.text.BaseColor;
import org.apache.commons.io.IOUtils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Date: 10/05/13
 */
@SuppressWarnings("unchecked")
public class CssUtil {

    public static String colorsFile = "colors.css";

    public static Map<String, int[]> colorMap = new HashMap<String, int[]>();

    public final static String White = "white";
    public final static String DarkGrayGreen = "darkGrayGreen";
    public final static String Header = "header";
    public final static String Footer = "footer";
    public final static String Even = "even";
    public final static String Odd = "odd";

    public static List<String> readCss(String fileName) {
        List<String> readLines = new ArrayList<String>();
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            BufferedInputStream bufferedInputStream = new BufferedInputStream(fileInputStream);
            readLines = IOUtils.readLines(bufferedInputStream);
        } catch (FileNotFoundException e) {
            e.getLocalizedMessage();
        } catch (IOException e) {
            e.getLocalizedMessage();
        }
        return readLines;
    }

    public static void initializeColorMap() {
        boolean colorNext = false;
        String colorName = "";
        List<String> lines = readCss(colorsFile);
        for (String line : lines) {
            if (line.startsWith(".")) {
                colorName = line.split(" ")[0].replace(".", "").trim();
            }

            if (line.contains("color : rgb")) {
                colorNext = true;
                continue;
            }

            if (colorNext) {
                colorNext = false;
                int[] colors = new int[3];
                String[] split = line.split(",");
                for (int i = 0; i < split.length; i++) {
                    colors[i] = Integer.parseInt(split[i].trim());
                }
                colorMap.put(colorName, colors);
            }
        }
    }

    public static BaseColor getBaseColor(String colorName) {
        if (colorMap.isEmpty()) {
            initializeColorMap();
        }
        int[] colors = colorMap.get(colorName);
        return new BaseColor(colors[0], colors[1], colors[2]);
    }

    public static int[] getColor(String colorName) {
        if (colorMap.isEmpty()) {
            initializeColorMap();
        }
        return colorMap.get(colorName);
    }
}
