package dk.hagendazzlers;

import com.itextpdf.text.Chunk;
import com.itextpdf.text.Font;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.Phrase;

/**
 * Date: 04/04/13
 */
public class TextFormattingUtil {

    //    public static final String Bold = "@";
    public static final String Colon = ":";
    public static final String Dash = " -";
    public static final String NewLine = "\n";
//    public static final String Italic = "#";
//    public static final String Point_Start = ">";
//    public static final String Point_End = "<";

    private static Font NormalZAPFont = new Font(Font.FontFamily.ZAPFDINGBATS, 12);
    private static Font SmallZAPFFont = new Font(Font.FontFamily.ZAPFDINGBATS, 8);
    //    private static Font SmallSymbolFont = new Font(Font.FontFamily.SYMBOL, 8);
    private static Font NormalSymbolFont = new Font(Font.FontFamily.SYMBOL, 12);
//    private static Font NormalFont = new Font(Font.FontFamily.HELVETICA, 16);

    private static List printAllZapfAndSymbolList() {
        List list = new List(false, false, 15);
        // favorites:
        list.setListSymbol(new Chunk("S", SmallZAPFFont));
        list.add(new ListItem("Mikki"));
        list.add(new ListItem("a b c d e f g h i j k l m n o p q r s t u v x y z", NormalZAPFont));
        list.add(new ListItem("A B C D E F G H I J K L M N O P Q R S T U V X Y Z", NormalZAPFont));
        list.add(new ListItem("1 2 3 4 5 6 7 8 9 0 + - * / = < > , . - _", NormalZAPFont));
        list.add(new ListItem("! # ? % & / ( ) = ? ` ^ * _ : ; M >", NormalZAPFont));
        list.add(new ListItem("a b c d e f g h i j k l m n o p q r s t u v x y z", NormalSymbolFont));
        list.add(new ListItem("Jo"));
        list.setAlignindent(true);
        list.setAutoindent(true);
        list.setIndentationLeft(10);
        return list;
    }

    public static Phrase parseText(String text) {
        Phrase phrase = new Phrase();
        if (text.contains(Colon)) {
            String[] split = text.split(Colon);
            String bold = split[0].concat(":");
            String normal = split[1];
            Chunk boldChunk = new Chunk(bold.concat(NewLine), PdfBasics.getNormalBoldFont());

            if (normal.startsWith(" ")) {
                normal = normal.replaceFirst(" ", "");
            }

            Chunk normalChunk = new Chunk(normal, PdfBasics.getNormalFont());

            phrase.add(boldChunk);
            phrase.add(normalChunk);

        } else {
            Chunk textChunk = new Chunk(text, PdfBasics.getNormalFont());
            phrase.add(textChunk);

        }
        return phrase;
    }

}
