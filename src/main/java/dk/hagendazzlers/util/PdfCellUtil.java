package dk.hagendazzlers.util;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfPCell;
import dk.hagendazzlers.PdfBasics;
import dk.hagendazzlers.TextFormattingUtil;
import dk.hagendazzlers.TimeDuration;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.property.Location;

import java.io.IOException;
import java.util.Calendar;
import java.util.Locale;

/**
 * Date: 10/05/13
 */
public class PdfCellUtil extends PdfBasics {

    /**
     * Initializes the fonts and collections.
     *
     * @throws com.itextpdf.text.DocumentException
     *
     * @throws java.io.IOException
     */
    public PdfCellUtil() throws DocumentException, IOException {
    }

    public static PdfPCell getWeekNumberCell(Calendar calendar, Locale locale) {
        Paragraph weekNoParagraph = new Paragraph(new Chunk(String.format(locale, DateUtil.formatCalendarWeekNo(calendar)), normal("copperplate bold", 14, DarkGrayGreen)));
        weekNoParagraph.setAlignment(Element.ALIGN_TOP);
        PdfPCell weekNoCell = new PdfPCell(weekNoParagraph);
        weekNoCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        weekNoCell.setVerticalAlignment(Element.ALIGN_TOP);
        weekNoCell.setPaddingBottom(11);
        weekNoCell.setBorder(Rectangle.NO_BORDER);
        return weekNoCell;
    }

    public static PdfPCell getWeekCell() {
        Chunk week = new Chunk("Uge", bold(DigitFont, 14, DarkGrayGreen));
        week.setTextRise(1);
        week.setCharacterSpacing(1.1f);
        Paragraph weekParagraph = new Paragraph(week);
        weekParagraph.setAlignment(Element.ALIGN_LEFT);
        PdfPCell weekCell = new PdfPCell(weekParagraph);
        weekCell.setLeading(11, 0);
        weekCell.setPaddingTop(6);
        weekCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        weekCell.setVerticalAlignment(Element.ALIGN_TOP);
        weekCell.setBorder(Rectangle.NO_BORDER);
        return weekCell;
    }

    public static PdfPCell getCurrentDateCell(Calendar calendar, Locale locale) {
        Chunk day = new Chunk(String.format(locale, DateUtil.formatCalendarDay(calendar)), bold("copperplate bold", 20, DarkGrayGreen));
        day.setCharacterSpacing(1.1f);
        PdfPCell dayCell = new PdfPCell(new Phrase(day));
        dayCell.setLeading(12, 0);
        dayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        dayCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        dayCell.setPaddingBottom(3);
        dayCell.setBorder(Rectangle.NO_BORDER);
        return dayCell;
    }

    public static PdfPCell getActualDatePCell(Calendar calendar, Locale locale) {
        String dateString = DateUtil.formatCalendarToString(calendar, DateUtil.DATE_PATTERN);
        PdfPCell dateCell = new PdfPCell(new Phrase(new Chunk(String.format(locale, dateString), bold("copperplate bold", 16, DarkGrayGreen))));
        dateCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        dateCell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        dateCell.setPaddingBottom(3.5f);
        dateCell.setBorder(Rectangle.NO_BORDER);
        return dateCell;
    }

    public static PdfPCell getMonthCell(Calendar calendar, Locale locale) {
        Chunk monthChunk = new Chunk(String.format(locale, DateUtil.formatCalendarMonth(calendar)), bold("HoboStd", 11f, DarkGrayGreen));
        monthChunk.setCharacterSpacing(1.1f);
        Paragraph monthParagraph = new Paragraph(monthChunk);
        monthParagraph.setAlignment(Element.ALIGN_BOTTOM);
        PdfPCell monthCell = new PdfPCell(monthParagraph);
        monthCell.setPaddingTop(3);
        monthCell.setPaddingBottom(0);
        monthCell.setPaddingLeft(0);
        monthCell.setPaddingRight(3.5f);
        monthCell.setLeading(11, 0);
        monthCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        monthCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        monthCell.setRotation(90);
        monthCell.setBorder(Rectangle.NO_BORDER);
        return monthCell;
    }

    public static PdfPCell getTotalTimeFrameOfTheDayCell(TimeDuration timeDuration) {
        PdfPCell totalTimeFrameOfTheDayCell = new PdfPCell();
        totalTimeFrameOfTheDayCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalTimeFrameOfTheDayCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        totalTimeFrameOfTheDayCell.setLeading(11, 0);

        Chunk totalTimeChunk = new Chunk(String.format("%s - %s", timeDuration.getStartTimeOfTheDay(), timeDuration.getEndOfDayTime()), bold(11, CssUtil.getColor(CssUtil.DarkGrayGreen)));

        Paragraph totalTimeParagraph = new Paragraph(totalTimeChunk);
        totalTimeParagraph.setAlignment(Element.ALIGN_CENTER);
        totalTimeFrameOfTheDayCell.addElement(totalTimeParagraph);
        totalTimeFrameOfTheDayCell.setPaddingLeft(0);
        totalTimeFrameOfTheDayCell.setPaddingRight(10);
        totalTimeFrameOfTheDayCell.setPaddingBottom(6);
        totalTimeFrameOfTheDayCell.setBorder(Rectangle.NO_BORDER);
        return totalTimeFrameOfTheDayCell;
    }

    public static PdfPCell getClockImageCell() throws BadElementException, IOException {
        Image clock = getGreenClock();
        clock.scalePercent(55);
        Paragraph clockParagraph = new Paragraph(new Chunk(clock, -9f, -14));
        clockParagraph.setAlignment(Element.ALIGN_RIGHT);
        PdfPCell clockImageCell = new PdfPCell(clockParagraph);
        clockImageCell.setLeading(10, 0);
        clockImageCell.setPaddingTop(3);
        clockImageCell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        clockImageCell.setBorder(Rectangle.NO_BORDER);
        clockParagraph.add(Chunk.createTabspace(1f));
        return clockImageCell;
    }

    public static PdfPCell getWhiteClockCell() throws BadElementException, IOException {
//        Image clock = getClock();
//        clock.scalePercent(60);
//        clock.setAlignment(Element.ALIGN_BOTTOM);
//        Paragraph clockParagraph = new Paragraph(new Phrase(new Chunk(clock, 1, -15)));
        Paragraph clockParagraph = new Paragraph(new Phrase(new Chunk(" ")));
//        clockParagraph.setAlignment(Element.ALIGN_LEFT);
//        clockParagraph.setSpacingBefore(8);
        PdfPCell clockCell = new PdfPCell(clockParagraph);
//        clockCell.setPaddingTop(4);
//        clockCell.setPaddingLeft(4);
//        clockCell.setVerticalAlignment(Element.ALIGN_CENTER);
//        clockCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        clockCell.setBorder(Rectangle.NO_BORDER);
        return clockCell;
    }

    public static PdfPCell getLogBogCell() {
        Paragraph logBogParagraph = new Paragraph(new Phrase(new Chunk(" --  Log Bog  -- ", bold(9f, DarkGrayGreen))));
        PdfPCell logBogCell = new PdfPCell(logBogParagraph);
        logBogCell.setBorder(Rectangle.NO_BORDER);
        logBogCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        logBogCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        logBogCell.setPaddingRight(112f);
        return logBogCell;
    }

    public static PdfPCell getMikkiCell() {
        Chunk mikki = new Chunk("Mikkel T. Mathiasen", normal(9f, DarkGrayGreen));
        mikki.setCharacterSpacing(1.1f);
        Paragraph mikkiParagraph = new Paragraph(mikki);
        mikkiParagraph.setAlignment(Element.ALIGN_BOTTOM);
        PdfPCell mikkiCell = new PdfPCell(mikkiParagraph);
        mikkiCell.setPaddingLeft(2);
        mikkiCell.setPaddingTop(7);
        mikkiCell.setHorizontalAlignment(Element.ALIGN_LEFT);
        mikkiCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        mikkiCell.setBorder(Rectangle.NO_BORDER);
        return mikkiCell;
    }

    public static PdfPCell getTotalDurationCell(String totalDurationInHrsAndMins) {
        Chunk totalDuration = new Chunk(String.format("Ialt: %s", totalDurationInHrsAndMins), bold(10f, DarkGrayGreen));
        totalDuration.setCharacterSpacing(1.1f);
        Paragraph totalDurationParagraph = new Paragraph(new Phrase(totalDuration));
        totalDurationParagraph.setAlignment(Element.ALIGN_BOTTOM);
        PdfPCell totalDurationCell = new PdfPCell(totalDurationParagraph);
        totalDurationCell.setBorder(Rectangle.NO_BORDER);
        totalDurationCell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        totalDurationCell.setHorizontalAlignment(Element.ALIGN_CENTER);
        totalDurationCell.setPaddingRight(0);
        return totalDurationCell;
    }


    public static PdfPCell getTaskCell(PdfPCell cell, String titleValue, Property note) {
        cell.setColspan(3);
        cell.setPaddingLeft(6);
        cell.setPaddingRight(6);
        cell.setPaddingTop(4);

        Phrase text = TextFormattingUtil.parseText(titleValue);
        text.setLeading(text.getFont().getCalculatedSize() + 1);
        Paragraph taskParagraph = new Paragraph(text);
        taskParagraph.setAlignment(Element.ALIGN_LEFT);
        if (note != null) {
            taskParagraph.add(new Chunk(note.getValue()));
        }
        cell.addElement(taskParagraph);
        return cell;
    }

    public static PdfPCell getLocationCell(PdfPCell cell, Location location) throws IOException, DocumentException {
        cell.setPaddingLeft(10);
        cell.setLeading(12, 0);
        Paragraph locationParagraph = new Paragraph(new Chunk(PdfCellUtil.getLocationImage(), 0, 0));
        locationParagraph.setAlignment(Element.ALIGN_LEFT);

        if (location != null) {
            locationParagraph.add(Chunk.createTabspace(3f));
            locationParagraph.add(PdfCellUtil.getLocation(location));
        }
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.addElement(locationParagraph);
        return cell;
    }

    public static PdfPCell getTimeCell(PdfPCell cell, TimeDuration timeDuration) {
        String timeString = String.format("%s - %s", timeDuration.getStartTime(), timeDuration.getEndTime());
        Chunk time = new Chunk(timeString, normal(10f, DarkGrayGreen));
        Paragraph timeParagraph = new Paragraph(time);
        timeParagraph.setAlignment(Element.ALIGN_CENTER);
        timeParagraph.setLeading(12);
        cell.addElement(timeParagraph);

        Chunk totalDuration = new Chunk(String.format("Varighed: %s", timeDuration.getDurationInHrsAndMins()), italic(MainFontName, 9, CssUtil.getBaseColor(CssUtil.DarkGrayGreen)));
        Paragraph durationParagraph = new Paragraph(totalDuration);
        durationParagraph.setLeading(12f);
        durationParagraph.setAlignment(Element.ALIGN_CENTER);
        cell.addElement(durationParagraph);
        return cell;
    }

    public static Phrase getLocation(Location location) throws IOException, BadElementException {
        Phrase locationP = new Phrase();
        if (location != null) {
            locationP.add(new Chunk(location.getValue(), getNormalFont()));
        }
        return locationP;
    }

    public static Image getLocationImage() throws BadElementException, IOException {
//        Image image = Image.getInstance("green-location.png");
        Image image = Image.getInstance("hus.png");
        image.setAlignment(Image.LEFT);
        image.scaleAbsolute(12f, 12f);
//        image.scalePercent(30);
//        image.setAbsolutePosition(0, 0);
        return image;
    }

//    public static Image getClock() throws BadElementException, IOException {
//        Image image = Image.getInstance("white-clock.gif");
//        image.setAlignment(Image.MIDDLE);
//        image.scaleAbsolute(20f, 20f);
//        return image;
//    }

    public static Image getGreenClock() throws BadElementException, IOException {
        Image image = Image.getInstance("green-clock.gif");
        image.setAlignment(Image.MIDDLE);
        image.scaleAbsolute(20f, 20f);
        return image;
    }


}
