package dk.hagendazzlers;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPCellEvent;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfPTableEvent;
import com.itextpdf.text.pdf.PdfWriter;
import dk.hagendazzlers.util.DateUtil;
import dk.hagendazzlers.util.PdfCellUtil;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.ComponentList;
import net.fortuna.ical4j.model.Property;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.property.Location;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * Date: 04/04/13
 */
public class CreatePdfFromCalendar extends PdfBasics {

    /**
     * The resulting PDF.
     */
    public static String ResultingPdf = "Calendar.pdf";
    public static String CalendarFile = "Calendar.ics";

    private TimeDuration timeDuration;

    public static Map<Integer, List<CalendarEntry>> eventsMap = new HashMap<Integer, List<CalendarEntry>>();

    /**
     * A table event.
     */
    public PdfPTableEvent tableBackground;
    /**
     * A cell event.
     */
    public PdfPCellEvent cellBackground;

    /**
     * Inner class with a table event that draws a background with rounded corners.
     */
    class TableBackground implements PdfPTableEvent {

        int red;
        int green;
        int blue;

        private TableBackground(int red, int green, int blue) {
            this.red = red;
            this.green = green;
            this.blue = blue;
        }

        public TableBackground() {
            this(251, 255, 246);
        }

        public void tableLayout(PdfPTable table, float[][] width, float[] height, int headerRows, int rowStart, PdfContentByte[] canvas) {
            PdfContentByte background = canvas[PdfPTable.BASECANVAS];
            background.saveState();

            background.setRGBColorFill(red, green, blue);
            background.roundRectangle(
                    width[0][0] - 2,
                    height[height.length - 1] - 6,
                    width[0][1] - width[0][0] + 3,
                    height[0] - height[height.length - 1] + 4,
                    4);
            background.fill();
            background.restoreState();
        }
    }

    /**
     * Inner class with a cell event that draws a background with rounded corners.
     */
    class CellBackground implements PdfPCellEvent {

        int color = 0x25;

        int red = 0;
        int green = 0;
        int blue = 0;

        public CellBackground() {
        }

        public CellBackground(int[] color) {
            this.red = color[0];
            this.green = color[1];
            this.blue = color[2];
        }

        public void cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvases) {
            PdfContentByte canvas = canvases[PdfPTable.BACKGROUNDCANVAS];

            canvas.roundRectangle(
                    rect.getLeft() + 1f, // x
                    rect.getBottom() - 2f, // y
                    rect.getWidth() - 4, // w
                    rect.getHeight() - 3, 4); // h

            if (red == 0 && green == 0 && blue == 0) {
                canvas.setCMYKColorFill(0x00, 0x00, 0x00, color);

            } else {
                canvas.setRGBColorFill(red, green, blue);

            }
            canvas.fill();
        }
    }

    /**
     * Main method creating the PDF.
     *
     * @param args no arguments needed
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     *
     */
    public static void main(String[] args) throws Exception {
        FontFactory.registerDirectory("/Library/Fonts");
        if (args == null || args.length == 0) {
            new CreatePdfFromCalendar().createPdf(ResultingPdf);

        } else {
            String calendarFileName = args[0];
            if (!StringUtils.isEmpty(calendarFileName)) {
                if (!calendarFileName.endsWith("ics")) {
                    CalendarFile = calendarFileName + ".ics";
                }
            }
            if (args.length > 1) {
                String pdfFileName = args[1];
                if (!StringUtils.isEmpty(pdfFileName)) {
                    if (!pdfFileName.endsWith("pdf")) {
                        ResultingPdf = pdfFileName + ".pdf";
                    } else {
                        ResultingPdf = pdfFileName;
                    }
                }
            }
            new CreatePdfFromCalendar().createPdf(ResultingPdf);
        }
    }

    /**
     * Initializes the fonts and collections.
     *
     * @throws com.itextpdf.text.DocumentException
     *
     * @throws java.io.IOException
     */
    public CreatePdfFromCalendar() throws DocumentException, IOException {
        super();
        tableBackground = new TableBackground();
        cellBackground = new CellBackground();
    }


    public void createPdf(String filename) throws Exception {
        Document document = new Document(PageSize.A4.rotate());
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(filename));

        document.open();

        writer.getDirectContent();

        addMetaData(document);
        addHeader(document);

        newLine(document);
        newLine(document);
        newLine(document);

        createCalendarFromEventMap(document);
        document.close();
    }

    private void createCalendarFromEventMap(Document document) throws Exception {
        populateDateEventMap();

        List<Integer> intDays = new ArrayList<Integer>(eventsMap.keySet());
        Collections.sort(intDays);

        List<PdfPCell> cells = new ArrayList<PdfPCell>();
        GregorianCalendar gregorianCalendar = new GregorianCalendar();

        for (Integer intDay : intDays) {
            List<CalendarEntry> entries = eventsMap.get(intDay);

            Collections.sort(entries, new Comparator<CalendarEntry>() {
                @Override
                public int compare(CalendarEntry calendarEntry, CalendarEntry calendarEntry2) {
                    return calendarEntry.getStartTime().compareTo(calendarEntry2.getStartTime());
                }
            });

            for (CalendarEntry entry : entries) {

                VEvent event = entry.getEvent();

                Date startDate = event.getStartDate().getDate();
                Date endDate = event.getEndDate().getDate();

                Property title = event.getSummary();
                Property note = event.getDescription();
                Location location = event.getLocation();

                handleDuration(startDate, endDate);

                int[] color = getBackgroundColor(entries.indexOf(entry));
                cells.addAll(insertTableData(title, note, location, color));
                gregorianCalendar.setTime(startDate);
            }

            if (timeDuration != null) {
                Paragraph paragraph = new Paragraph();
                paragraph.setAlignment(Element.ALIGN_CENTER);
                PdfPTable table = createTableWithHeaderAndFooter(gregorianCalendar);

                for (PdfPCell cell : cells) {
                    table.addCell(cell);
                }
                paragraph.add(table);
                document.add(paragraph);

                newLine(document);
                newLine(document);

                timeDuration = null;
                cells = new ArrayList<PdfPCell>();
            }
        }
    }

    private int[] getBackgroundColor(int index) {
        if (index % 2 == 0) {
            return EvenColor.getColor();
        }
        return OddColor.getColor();
    }

    private void populateDateEventMap() throws Exception {
        ComponentList events = getComponentList(CalendarFile);

        if (events == null) {
            throw new Exception("No Events found !!");
        }

        for (Object component : events) {
            VEvent event = (VEvent) component;
            Date startDate = event.getStartDate().getDate();
            Integer dateTime = DateUtil.formatDateAndTimeToIntegerValue(startDate);
            Integer date = getIntDate(dateTime);
            Integer time = getIntTime(dateTime);
            CalendarEntry calendarEntry = new CalendarEntry(time, event);
            if (eventsMap.containsKey(date)) {
                eventsMap.get(date).add(calendarEntry);
            } else {
                List<CalendarEntry> entries = new ArrayList<CalendarEntry>();
                entries.add(calendarEntry);
                eventsMap.put(date, entries);
            }
        }
    }

    private ComponentList getComponentList(String calendarFileName) {
        try {
            FileInputStream fin = new FileInputStream(calendarFileName);
            CalendarBuilder builder = new CalendarBuilder();
            net.fortuna.ical4j.model.Calendar calendar = builder.build(fin);
            return calendar.getComponents(Event);
        } catch (FileNotFoundException e) {
            System.out.println("e " + e.getLocalizedMessage());
        } catch (ParserException e) {
            System.out.println("e " + e.getLocalizedMessage());
        } catch (IOException e) {
            System.out.println("e " + e.getLocalizedMessage());
        }
        return null;
    }

    private List<PdfPCell> insertTableData(Property title, Property note, Location location, int[] color) throws IOException, DocumentException {
        List<PdfPCell> cells = new ArrayList<PdfPCell>(3);
        cells.add(getLocationCell(location, color));
        cells.add(getTaskCell(title.getValue(), note, color));
        cells.add(getTimeCell(color));
        return cells;
    }

    private PdfPCell getFooterCell() throws IOException, DocumentException {
        PdfPCell cell = getPdfPCell();
        cell.setColspan(5);
        cell.setPadding(5);
        cell.setCellEvent(new CellBackground(FooterColor.getColor()));

        // the footer (as a table)
        PdfPTable footerTable = createFooterTable();

        // Mikki's name
        footerTable.addCell(PdfCellUtil.getMikkiCell());

        // 'Log Bog'
        footerTable.addCell(PdfCellUtil.getLogBogCell());

        // clock image
        footerTable.addCell(PdfCellUtil.getWhiteClockCell());

        // total time duration
        String totalDurationInHrsAndMins = timeDuration.getTotalDurationInHrsAndMins();

        // total duration fro the whole day
        PdfPCell totalDurationCell = PdfCellUtil.getTotalDurationCell(totalDurationInHrsAndMins);

        footerTable.addCell(totalDurationCell);
        cell.addElement(footerTable);
        return cell;
    }

    private PdfPTable createFooterTable() throws DocumentException {
        PdfPTable headerTable = new PdfPTable(4);
        headerTable.setTableEvent(new TableBackground());
        headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        headerTable.setKeepTogether(true);

        // add width & header width
        float[] headerWidths = {15.5f, 70f, 3.5f, 13f};
        headerTable.setWidths(headerWidths);
        headerTable.setWidthPercentage(100);

        // set default alignments
        headerTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // space
        headerTable.setSpacingBefore(0);
        headerTable.setSpacingAfter(0);

        return headerTable;
    }

    private void handleDuration(Date startDate, Date stopDate) {
        long milliSec = stopDate.getTime() - startDate.getTime();

        if (timeDuration == null) {
            timeDuration = new TimeDuration(milliSec);
        } else {
            timeDuration.addSubDuration(milliSec);
        }
        timeDuration.addStartTime(DateUtil.formatDateToString(startDate, DateUtil.TIME_ONLY_PATTERN));
        timeDuration.addEndTime(DateUtil.formatDateToString(stopDate, DateUtil.TIME_ONLY_PATTERN));
    }

    private PdfPCell getTaskCell(String titleValue, Property note, int[] color) {
        PdfPCell cell = getPdfPCell(new CellBackground(color));
        return PdfCellUtil.getTaskCell(cell, titleValue, note);
    }

    private PdfPCell getLocationCell(Location location, int[] color) throws IOException, DocumentException {
        PdfPCell cell = getPdfPCell(new CellBackground(color));
        return PdfCellUtil.getLocationCell(cell, location);
    }

    private PdfPCell getPdfPCell() {
        return getPdfPCell(cellBackground);
    }

    private PdfPCell getTimeCell(int[] color) {
        PdfPCell cell = getPdfPCell(new CellBackground(color));
        return PdfCellUtil.getTimeCell(cell, timeDuration);
    }

    private PdfPCell getPdfPCell(PdfPCellEvent cellBackgroundColor) {
        PdfPCell cell = new PdfPCell();
        cell.setBorder(Rectangle.NO_BORDER);
        cell.setCellEvent(cellBackgroundColor);

        //padding
        cell.setPadding(3f);

        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
        return cell;
    }

    private PdfPTable createTableWithHeaderAndFooter(Calendar calendar) throws IOException, DocumentException {
        PdfPTable table = new PdfPTable(5);
        table.setTableEvent(tableBackground);
        table.getDefaultCell().setCellEvent(cellBackground);
        table.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        table.setKeepTogether(true);

        // add width & header width
        float[] headerWidths = {13, 1, 68, 5f, 13f};
        table.setWidths(headerWidths);
        table.setWidthPercentage(100);

        // set default alignments
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        //padding
        table.getDefaultCell().setPaddingBottom(1);
        table.getDefaultCell().setPaddingTop(1);
        table.getDefaultCell().setPaddingLeft(2);
        table.getDefaultCell().setPaddingRight(2);

        table.getDefaultCell().setLeading(11, 0);

        // space
        table.setSpacingBefore(0);
        table.setSpacingAfter(0);

        // add cells
        table.addCell(getHeaderCell(calendar, new Locale("da")));
        table.addCell(getFooterCell());
        table.setHeaderRows(2);
        table.setFooterRows(1);

        return table;
    }

    private PdfPCell getHeaderCell(Calendar calendar, Locale locale) throws IOException, DocumentException {
        PdfPCell cell = getPdfPCell();
        cell.setColspan(5);
        cell.setFixedHeight(33.7f);
        cell.setLeading(20, 0);

        cell.setCellEvent(new CellBackground(HeaderColor.getColor()));

        // nested header table
        PdfPTable headerTable = createHeaderTable();
        cell.setLeading(11, 0);

        // add cells

        // month
        headerTable.addCell(PdfCellUtil.getMonthCell(calendar, locale));

        // date
        PdfPCell dateCell = PdfCellUtil.getActualDatePCell(calendar, locale);
        headerTable.addCell(dateCell);

        // day
        PdfPCell dayCell = PdfCellUtil.getCurrentDateCell(calendar, locale);
        headerTable.addCell(dayCell);

        // week
        PdfPCell weekCell = PdfCellUtil.getWeekCell();
        headerTable.addCell(weekCell);

        // week number
        PdfPCell weekNoCell = PdfCellUtil.getWeekNumberCell(calendar, locale);
        headerTable.addCell(weekNoCell);

        // clock image
        PdfPCell clockImageCell = PdfCellUtil.getClockImageCell();
        headerTable.addCell(clockImageCell);

        // start- and end time of the day
        PdfPCell totalTimeframeOfTheDayCell = PdfCellUtil.getTotalTimeFrameOfTheDayCell(timeDuration);
        headerTable.addCell(totalTimeframeOfTheDayCell);

        cell.addElement(headerTable);

        return cell;
    }

    private PdfPTable createHeaderTable() throws DocumentException {
        PdfPTable headerTable = new PdfPTable(7);
        headerTable.setTableEvent(new TableBackground());
        headerTable.getDefaultCell().setBorder(Rectangle.NO_BORDER);
        headerTable.setKeepTogether(true);

        // add width & header width
        float[] headerWidths = {3, 7, 65f, 5, 4, 6, 10f};
        headerTable.setWidths(headerWidths);
        headerTable.setWidthPercentage(100);

        // set default alignments
        headerTable.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        headerTable.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        // space
        headerTable.setSpacingBefore(0);
        headerTable.setSpacingAfter(0);

        return headerTable;
    }

}
