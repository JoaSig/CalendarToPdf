package dk.hagendazzlers;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.Utilities;
import com.itextpdf.text.pdf.AcroFields;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfAcroForm;
import com.itextpdf.text.pdf.PdfAction;
import com.itextpdf.text.pdf.PdfAnnotation;
import com.itextpdf.text.pdf.PdfBorderDictionary;
import com.itextpdf.text.pdf.PdfFormField;
import com.itextpdf.text.pdf.PdfName;
import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.PdfStamper;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.PushbuttonField;
import com.itextpdf.text.pdf.TextField;
import dk.hagendazzlers.util.DateUtil;
import net.fortuna.ical4j.data.CalendarBuilder;
import net.fortuna.ical4j.data.CalendarOutputter;
import net.fortuna.ical4j.data.FoldingWriter;
import net.fortuna.ical4j.data.ParserException;
import net.fortuna.ical4j.model.DateTime;
import net.fortuna.ical4j.model.TimeZone;
import net.fortuna.ical4j.model.TimeZoneRegistry;
import net.fortuna.ical4j.model.TimeZoneRegistryFactory;
import net.fortuna.ical4j.model.ValidationException;
import net.fortuna.ical4j.model.component.VEvent;
import net.fortuna.ical4j.model.parameter.TzId;
import net.fortuna.ical4j.model.property.Created;
import net.fortuna.ical4j.model.property.DtEnd;
import net.fortuna.ical4j.model.property.DtStart;
import net.fortuna.ical4j.model.property.Sequence;
import net.fortuna.ical4j.model.property.Summary;
import net.fortuna.ical4j.util.UidGenerator;
import org.apache.commons.lang.StringUtils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Date: 18/04/13
 */
public class CalendarEvent {

    /**
     * The PDF.
     */
    public static final String fileName = "CalendarEvent.pdf";
    public static final String fileWithDatePicker = "datePicker-amr.pdf";
    public static final String newFileName = "event.pdf";

    public static final String Date = "topmostSubform[0].Page1[0].Date[0]";
    public static final String Start = "topmostSubform[0].Page1[0].StartTime[0]";
    public static final String Stop = "topmostSubform[0].Page1[0].StopTime[0]";
    public static final String Task = "topmostSubform[0].Page1[0].Task_oD00MZKQPeIAxd3PYuhuFg[0]";
    public static final String appearence = "generateAppearances";
    public static final String LocationDD = "topmostSubform[0].Page1[0].locationDD[0]";
    public static final String Location = "topmostSubform[0].Page1[0].Location_uqd-dhn6Dr2o7Z9WjlvoCQ[0]";

    public static final String Event = "VEvent";


    public static void readPdfCalendarEvent() throws IOException, DocumentException {
        PdfReader reader = new PdfReader(new FileInputStream(fileWithDatePicker));

        AcroFields form = reader.getAcroFields();
        Set<String> formFields = form.getFields().keySet();

        for (String formField : formFields) {

        }
    }

    public static void readCalendar() throws IOException, DocumentException {
        PdfReader reader = new PdfReader(new FileInputStream(fileWithDatePicker));

        AcroFields form = reader.getAcroFields();
        Set<String> formFields = form.getFields().keySet();
        for (String key : formFields) {
            System.out.print(key + ": ");
            switch (form.getFieldType(key)) {
                case AcroFields.FIELD_TYPE_CHECKBOX:
                    System.out.println("Checkbox");
                    break;
                case AcroFields.FIELD_TYPE_COMBO:
                    System.out.println("Combobox");
                    break;
                case AcroFields.FIELD_TYPE_LIST:
                    System.out.println("List");
                    break;
                case AcroFields.FIELD_TYPE_NONE:
                    System.out.println("None");
                    break;
                case AcroFields.FIELD_TYPE_PUSHBUTTON:
                    System.out.println("Pushbutton");
                    break;
                case AcroFields.FIELD_TYPE_RADIOBUTTON:
                    System.out.println("Radiobutton");
                    break;
                case AcroFields.FIELD_TYPE_SIGNATURE:
                    System.out.println("Signature");
                    break;
                case AcroFields.FIELD_TYPE_TEXT:
                    System.out.println("Text");
                    break;
                default:
                    System.out.println("?");
            }
        }

        PdfStamper stamper = new PdfStamper(reader, new FileOutputStream(newFileName));

        AcroFields acroFields = stamper.getAcroFields();

        fill(acroFields);

        PushbuttonField saveButton = new PushbuttonField(stamper.getWriter(), acroFields.getFieldPositions(LocationDD).get(1).position, "saveButton");

        saveButton.setBorderColor(BaseColor.GRAY);
        saveButton.setBorderStyle(PdfBorderDictionary.STYLE_INSET);
        saveButton.setBackgroundColor(BaseColor.LIGHT_GRAY);
        saveButton.setText("Create Event");
        saveButton.setTextColor(BaseColor.BLUE);
        saveButton.setLayout(PushbuttonField.LAYOUT_LABEL_ONLY);
        PdfAnnotation saveAsButton = saveButton.getField();
//        saveAsButton.setAction(PdfAction.createSubmitForm()
//                "app.execMenuItem('SaveAs')", stamper.getWriter()));

//        PdfWriter writer = stamper.getWriter();

//        List<AcroFields.FieldPosition> fieldPositions = acroFields.getFieldPositions(Start);

//        TextField startTime = createChoicesField("StartTime", acroFields, writer, Start, 1, times);
//        stamper.addAnnotation(startTime.getListField(), 1);
//
//        TextField stopTime = createChoicesField("StopTime", acroFields, writer, Stop, 2, times);
//        stamper.addAnnotation(stopTime.getComboField(), 1);
//
//        TextField location = createChoicesField("locationDD", acroFields, writer, Location, 0, locations);
//        stamper.addAnnotation(location.getComboField(), 1);

        stamper.close();

//        PdfAction action = PdfAction.createHide(2, new PdfDestination(PdfDestination.FIT), writer);
//        writer.setOpenAction(action);

//        Map<String, AcroFields.Item> fields = form.getFields();
//        for (AcroFields.Item item : fields.values()) {
//            String[] appearanceStates = form.getAppearanceStates(Date);
//            System.out.println(appearanceStates.toString());
//                newItem.writeToAll();
//                item.values.get(0).getKeys().iterator().next().setContent("FUCK");
//                        item.getValue(0)
//        }
//
//        for (String s : fields.keySet()) {
//
//            if (s.toLowerCase().contains("date".toLowerCase())) {
//                AcroFields.Item item = fields.get(s);
//                boolean string = item.getValue(0).getKeys().iterator().next().isString();
//                AcroFields.Item newItem = new AcroFields.Item();
//
//            }
//        }
    }

    public static void generateEvent() throws IOException, DocumentException, ValidationException, ParserException, URISyntaxException, ParseException {
        PdfReader reader = new PdfReader(new FileInputStream(fileName));
        AcroFields form = reader.getAcroFields();

        VEvent event = createEventFromFormValues(form);

//        String now = DateUtil.formatDateToString(new Date(), DateUtil.DASH_DATE_PATTERN);
//        String calFileName = now + ".ics";
        String calFileName = "MikkiCal.ics";
//        File file = new File(calFileName);

        // Create a calendar
//        net.fortuna.ical4j.model.Calendar icsCalendar = new net.fortuna.ical4j.model.Calendar();
        net.fortuna.ical4j.model.Calendar icsCalendar = getCalendar("Mikki.ics");
//        icsCalendar.getProperties().add(new ProdId("-//Events Calendar//iCal4j 2.0//EN"));
//        icsCalendar.getProperties().add(CalScale.GREGORIAN);
//        icsCalendar.getProperties().add(tz.getTimeZoneId());

//        boolean addTZ = icsCalendar.getComponents().add(tz);
        boolean addEvent = icsCalendar.getComponents().add(event);

        if (addEvent) {
            System.out.println("all added");
        }

        CalendarOutputter outputter = new CalendarOutputter(false, FoldingWriter.MAX_FOLD_LENGTH);
        FileOutputStream out = new FileOutputStream(calFileName);
//        OutputStream out = new ByteArrayOutputStream();

        outputter.setValidating(false);
        outputter.output(icsCalendar, out);
    }

    public static VEvent createEventFromFormValues(AcroFields form) throws ParserException, IOException, ValidationException, ParseException {
        String dateInput = form.getField(Date);
        String startInput = form.getField(Start);
        String stopInput = form.getField(Stop);
        String taskInput = form.getField(Task);
        String locationInput = form.getField(LocationDD);

        //        Create a TimeZone
        TimeZoneRegistry registry = TimeZoneRegistryFactory.getInstance().createRegistry();
        TimeZone timezone = registry.getTimeZone("Europe/Copenhagen");

        DtStart start = new DtStart(new DateTime(dateInput + " " + startInput, DateUtil.WHOLE_DATE_STRING_PATTERN, timezone));
        start.getParameters().add(new TzId(timezone.getVTimeZone().getTimeZoneId().getValue()));

        DtEnd end = new DtEnd(new DateTime(dateInput + " " + stopInput, DateUtil.WHOLE_DATE_STRING_PATTERN, timezone));
        end.getParameters().add(new TzId(timezone.getVTimeZone().getTimeZoneId().getValue()));

        return createCalendarEvent(start, end, taskInput, locationInput, timezone);
    }

    private static Date getCalendarDate(String dateInput, String timeInput) {
        return DateUtil.formatStringToDate(dateInput + " " + timeInput);
    }

    public static VEvent createCalendarEvent(DtStart startDate, DtEnd endDate, String title, String location, TimeZone timezone) throws IOException, ParserException, ValidationException, ParseException {
        VEvent event = new VEvent();

        // add TimeZoneId
        event.getProperties().add(timezone.getVTimeZone().getTimeZoneId());

        // start- and end date
        event.getProperties().add(startDate);
        event.getProperties().add(endDate);

        // when it was created
        Created created = new Created(new DateTime(DateUtil.formatDateToString(new Date()), DateUtil.WHOLE_DATE_STRING_PATTERN, timezone));
        created.getParameters().add(new TzId(timezone.getVTimeZone().getTimeZoneId().getValue()));
        event.getProperties().add(created);

        // the name of the event - if none, add a fake one
        if (StringUtils.isEmpty(title)) {
            title = " Woooo Hooooo MF !!";
        }
        event.getProperties().add(new Summary(title));

        // the location of the event - if none, add the default one
        if (StringUtils.isEmpty(location)) {
            location = "Dalux";
        }
        event.getProperties().add(new net.fortuna.ical4j.model.property.Location(location));

        // sequence number
        event.getProperties().add(new Sequence(1));

        // Generate a UID for the event..
        UidGenerator ug = new UidGenerator("uidGen");
        event.getProperties().add(ug.generateUid());

        return event;
    }

    private static TextField createChoicesField(String fieldName, AcroFields acroFields, PdfWriter writer, String field, int choice, String[] choices) {
        List<AcroFields.FieldPosition> fieldPositions = acroFields.getFieldPositions(field);
        AcroFields.FieldPosition fieldPosition = fieldPositions.get(0);
        TextField startTime = new TextField(writer, fieldPosition.position, fieldName);
        startTime.setChoices(choices);
        startTime.setChoiceSelection(choice);
//        startTime.setChoiceExports(exportTimes);
        startTime.setFontSize(12);
        startTime.setBorderColor(BaseColor.WHITE);
        startTime.setBorderStyle(PdfBorderDictionary.STYLE_INSET);
        startTime.setBorderWidth(1f);
        startTime.setBackgroundColor(new BaseColor(231, 236, 255));
        startTime.setOptions(TextField.EDIT | TextField.COMB);
        return startTime;
    }

    public static void fill(AcroFields form) throws IOException, DocumentException {
//        form.setField(Start, DateUtil.formatDateToString(new Date(), DateUtil.SHORT_TIMELESS_DATE_PATTERN));
//        form.setField(Stop, "later");
        form.setField(Task, "Freakin fun frrrrttt .... ");
//        form.setField(Duration, "Later");
//        form.setField(Date, DateUtil.formatDateToString(new Date(), DateUtil.SHORT_TIMELESS_DATE_PATTERN));
//        form.setField(appearence, DateUtil.formatDateToString(new Date(), DateUtil.SHORT_TIMELESS_DATE_PATTERN));
//        form.setField("director", getDirectors(movie));
//        form.setField("year",
//                String.valueOf(movie.getYear()));
//        form.setField("duration",
//                String.valueOf(movie.getDuration()));
//        form.setField("category",
//                movie.getEntry().getCategory().getKeyword());
//        for (Screening screening :
//                movie.getEntry().getScreenings()) {
//            form.setField(
//                    screening.getLocation().replace('.', '_'), "Yes");
//        }
    }

    private static net.fortuna.ical4j.model.Calendar getCalendar(String calendarFileName) throws IOException, ParserException {
        FileInputStream fin = new FileInputStream(calendarFileName);
        CalendarBuilder builder = new CalendarBuilder();
        return builder.build(fin);
    }

    public static String[] locations = {
            "Dalux",
            "Novo"
    };


    public static String[] times = {
            "08:00",
            "08:15",
            "08:30",
            "08:45",

            "09:00",
            "09:15",
            "09:30",
            "09:45",

            "10:00",
            "10:15",
            "10:30",
            "10:45",

            "11:00",
            "11:15",
            "11:30",
            "11:45",

            "12:00",
            "12:15",
            "12:30",
            "12:45",

            "13:00",
            "13:15",
            "13:30",
            "13:45",

            "14:00",
            "14:15",
            "14:30",
            "14:45",

            "15:00",
            "15:15",
            "15:30",
            "15:45",

            "16:00",
            "16:15",
            "16:30",
            "16:45",

            "17:00",
            "17:15",
            "17:30",
            "17:45"
    };

    public static String[] exportTimes = {
            "09:00",
            "09:15",
            "09:30",
            "09:45",

            "10:00",
            "10:15",
            "10:30",
            "10:45",

            "11:00",
            "11:15",
            "11:30",
            "11:45",

            "12:00",
            "12:15",
            "12:30",
            "12:45",

            "13:00",
            "13:15",
            "13:30",
            "13:45",

            "14:00",
            "14:15",
            "14:30",
            "14:45",

            "15:00",
            "15:15",
            "15:30",
            "15:45",

            "16:00",
            "16:15",
            "16:30",
            "16:45",

            "17:00",
            "17:15",
            "17:30",
            "17:45"
    };

    HashMap<String, TextField> cache = new HashMap<String, TextField>();

    private static TextField createTextArea(String fieldName, PdfWriter writer) {
        TextField text = new TextField(writer, rectangle, fieldName);
        text.setBackgroundColor(new GrayColor(0.95f));
        text.setBorderStyle(PdfBorderDictionary.STYLE_INSET);
        text.setBorderColor(BaseColor.BLACK);
        text.setBorderWidth(15);
        text.setFontSize(8);
        text.setOptions(TextField.MULTILINE | TextField.REQUIRED);
        return text;

    }

    static Rectangle rectangle = new Rectangle(36, 806, 126, 780);
    static Rectangle rectangle2 = new Rectangle(1, 20, 46, 30);
    static Rectangle rectangle3 = new Rectangle(300, 806, 360, 788);

    private static TextField createChoicesField(String fieldName, PdfWriter writer) throws IOException, DocumentException {
        TextField startTime = new TextField(writer, rectangle2, fieldName);
        startTime.setChoices(times);
        startTime.setChoiceSelection(2);
        startTime.setOptions(TextField.EDIT);
        startTime.setChoiceExports(exportTimes);
        startTime.setFontSize(8);
        startTime.setBorderColor(BaseColor.GRAY);
        startTime.setBorderWidth(2f);
        startTime.setBorderStyle(PdfBorderDictionary.STYLE_INSET);
        startTime.setBackgroundColor(BaseColor.PINK);
//        ArrayList<Integer> selections = new ArrayList<Integer>();
//        selections.add(0);
//        selections.add(2);
//        startTime.setChoiceSelections(selections);
//        writer.addAnnotation(startTime.getListField());
        return startTime;

    }

    static int llx = 36;
    static int lly = 806;
    static int urx = 126;
    static int ury = 780;

    private static TextField createDateField(String fieldName, PdfWriter writer) throws IOException, DocumentException {
        TextField timeField = new TextField(writer, new Rectangle(llx * 2, lly + 30, urx, ury), fieldName);
        timeField.setBorderColor(new GrayColor(0.2f));
        timeField.setText("10:30");
        PdfFormField datefield = timeField.getTextField();
        datefield.setAdditionalActions(
                PdfName.V, PdfAction.javaScript(
                "AFTime_Format(0);", writer));
        writer.addAnnotation(datefield);
        return timeField;
    }

    private static String javascript = "";

    /**
     * Main method creating the PDF.
     *
     * @param args no arguments needed
     * @throws java.io.IOException
     * @throws com.itextpdf.text.DocumentException
     *
     */
    public static void main(String[] args) throws IOException, DocumentException, ParserException, ValidationException, URISyntaxException, ParseException {
        FontFactory.registerDirectory("/Users/Jo/Downloads/SFM_3.0.2_Magic/system/fonts");
        FontFactory.registerDirectory("/Library/Fonts");

//        readPdfCalendarEvent();
//        readCalendar();

        generateEvent();

//        Document document = new Document(PageSize.A4);
//        writeTestDocument(document);


//        createDateField("startTime", writer);
//        startTime.setText("09:00");
//        document.close();


    }

    private static void writeTestDocument(Document document) throws DocumentException, IOException {
        // step 2
        PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("fields.pdf"));
        // step 3
        document.open();

        writer.addJavaScript(Utilities.readFileToString(javascript));

        PdfAcroForm acroForm = writer.getAcroForm();
        acroForm.setNeedAppearances(true);

//        TextField Task = createTextArea("Task", writer);
//        Task.setText("IIIhhhaaaa");

//        acroForm.addHiddenField("hidden", "ghost");

//        writer.addAnnotation(Task.getTextField());


        TextField list = createChoicesField("list", writer);
        writer.addAnnotation(list.getListField());

        acroForm.addMultiLineTextField("task2", "buhu", BaseFont.createFont(), 12, 50, 50, 200, 100);
        PdfFormField select = acroForm.addSelectList("select", times, "07:00", BaseFont.createFont(), 8, 20, 10, 60, 20);
        select.setFieldFlags(TextField.EDIT | TextField.COMB);
        select.setBorderStyle(new PdfBorderDictionary(1, PdfBorderDictionary.STYLE_DASHED));

        acroForm.drawButton(PdfFormField.createPushButton(writer), "Takk", BaseFont.createFont(), 8, llx, lly, urx, ury);

        PushbuttonField button = new PushbuttonField(writer, rectangle3, "testButton");
        PdfFormField field = button.getField();
        field.setAction(
                PdfAction.javaScript("this.showButtonState()", writer));
        writer.addAnnotation(field);
    }

    /**
     * Initializes the fonts and collections.
     *
     * @throws com.itextpdf.text.DocumentException
     *
     * @throws java.io.IOException
     */
    public CalendarEvent() throws DocumentException, IOException {
        super();
//        tableBackground = new TableBackground();
//        cellBackground = new CellBackground();
//        darkRoundRectangle = new RoundRectangle(new int[]{0x00, 0x00, 0x00, 0x48});
//        lightRoundRectangle = new RoundRectangle(new int[]{0x00, 0x00, 0x00, 0x25});
    }


}
