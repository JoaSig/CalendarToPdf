package dk.hagendazzlers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.draw.VerticalPositionMark;
import dk.hagendazzlers.util.CssUtil;

import java.io.IOException;

/**
 * Date: 04/04/13
 */
public class PdfBasics {

    public static BaseColor White = CssUtil.getBaseColor(CssUtil.White);
    public static BaseColor DarkGrayGreen = CssUtil.getBaseColor(CssUtil.DarkGrayGreen);

    public static Paragraph NewLine = new Paragraph(Chunk.NEWLINE);

    public final static String Event = "VEVENT";
    public final static String MainFontName = "Osaka";
    public final static String DigitFont = "Khmer MN";

    public static FontColor HeaderColor = new FontColor(CssUtil.getColor(CssUtil.Header));
    public static FontColor FooterColor = new FontColor(CssUtil.getColor(CssUtil.Footer));
    public static FontColor EvenColor = new FontColor(CssUtil.getColor(CssUtil.Even));
    public static FontColor OddColor = new FontColor(CssUtil.getColor(CssUtil.Odd));

    /**
     * Initializes the fonts and collections.
     *
     * @throws com.itextpdf.text.DocumentException
     *
     * @throws java.io.IOException
     */
    public PdfBasics() throws DocumentException, IOException {
    }

    public static Font getNormalBoldFont() {
        return bold(11, DarkGrayGreen);
    }

    public static Font getNormalFont() {
        return normal(11, DarkGrayGreen);
    }

    // iText allows to add metadata to the PDF which can be viewed in your Adobe Reader under File -> Properties
    public static void addMetaData(Document document) {
        document.left();
        document.addTitle("Log-bog");
        document.addSubject("Aktivitets logging");
        document.addAuthor("Mikkel Thura Mathiasen");
        document.addCreator("Mikkel Thura Mathiasen");
    }

    public static void addHeader(Document document) throws DocumentException {
        Chunk header = new Chunk("Log bog", bold("copperplate bold", 24, DarkGrayGreen));
        header.setBackground(new BaseColor(172, 204, 157), 50, 10, 720, 10);
        Phrase phrase = new Phrase(header);
        Paragraph headerParagraph = new Paragraph(phrase);
        headerParagraph.setSpacingBefore(5);
        headerParagraph.setExtraParagraphSpace(0);
        headerParagraph.setFirstLineIndent(5);
        headerParagraph.setKeepTogether(true);
        headerParagraph.setAlignment(Element.ALIGN_JUSTIFIED);
        document.add(headerParagraph);
    }

    public static Chunk separator() {
        return new Chunk(new VerticalPositionMark());
    }

    public static void newLine(Document document) throws DocumentException {
        document.add(NewLine);
    }

    public String getDate(Integer intDay) {
        return String.valueOf(intDay).substring(0, 6);
    }

    public String getTime(Integer intDay) {
        String stringDay = String.valueOf(intDay);
        return stringDay.substring(6, stringDay.length());
    }


    public Integer getIntTime(Integer dateTime) {
        return Integer.parseInt(getTime(dateTime));
    }

    public int getIntDate(Integer dateTime) {
        return Integer.parseInt(getDate(dateTime));
    }

    // ------------- Normal -----------------

    public static Font normal(String fontName, float size, BaseColor color) {
        return FontFactory.getFont(fontName, size, Font.NORMAL, color);
    }

    public static Font normal(float size, BaseColor color) {
        return normal(MainFontName, size, color);
    }

    // ------------- Bold -----------------

    public static Font bold(String fontName, float size, int[] color) {
        return FontFactory.getFont(fontName, size, Font.BOLD, new BaseColor(color[0], color[1], color[2]));
    }

    public static Font bold(float size, int[] color) {
        return bold(MainFontName, size, color);
    }

    public static Font bold(String fontName, float size, BaseColor color) {
        return FontFactory.getFont(fontName, size, Font.BOLD, color);
    }

    public static Font bold(float size, BaseColor color) {
        return bold(MainFontName, size, color);
    }

    // ------------- Italic -----------------

    public static Font italic(String fontName, float size, BaseColor color) {
        return FontFactory.getFont(fontName, size, Font.ITALIC, color);
    }

    // ------------- BoldItalic -----------------

    public static Font boldItalic(float size, BaseColor color) {
        return FontFactory.getFont(MainFontName, size, Font.BOLDITALIC, color);
    }

}
