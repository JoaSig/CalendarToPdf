package test.dk.hagendazzlers; 

import dk.hagendazzlers.util.DateUtil;
import junit.framework.Assert;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;

import java.util.Date;

/** 
* CreatePdfFromCalendar Tester. 
* 
* @author <Authors name> 
* @since <pre>May 8, 2013</pre> 
* @version 1.0 
*/ 
public class CreatePdfFromCalendarTest { 

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: main(String[] args) 
* 
*/ 
@Test
public void testMain() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: createPdf(String filename) 
* 
*/ 
@Test
public void testCreatePdf() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: tableLayout(PdfPTable table, float[][] width, float[] height, int headerRows, int rowStart, PdfContentByte[] canvas) 
* 
*/ 
@Test
public void testTableLayout() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: cellLayout(PdfPCell cell, Rectangle rect, PdfContentByte[] canvases) 
* 
*/ 
@Test
public void testCellLayout() throws Exception { 
//TODO: Test goes here... 
} 


/** 
* 
* Method: createCalendarFromEventMap(Document document) 
* 
*/ 
@Test
public void testCreateCalendarFromEventMap() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("createCalendarFromEventMap", Document.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: isSameDay(Integer intDay) 
* 
*/ 
@Test
public void testIsSameDay() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("isSameDay", Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getDate(Integer intDay) 
* 
*/ 
@Test
public void testGetDate() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getDate", Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getTime(Integer intDay) 
* 
*/ 
@Test
public void testGetTime() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getTime", Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: populateDateEventMap() 
* 
*/ 
@Test
public void testPopulateDateEventMap() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("populateDateEventMap"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
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

    public Integer getIntDate(Integer dateTime) {
        return Integer.parseInt(getDate(dateTime));
    }

/**
* 
* Method: getIntTime(Integer dateTime) 
* 
*/ 
@Test
public void testGetIntTime() throws Exception {

    Date now = new Date();

    Integer dateTime = DateUtil.formatDateAndTimeToIntegerValue(now);

    Assert.assertEquals("", DateUtil.formatDateToString(now, DateUtil.Year_Month_Day_Pattern), getIntDate(dateTime).toString());

    Assert.assertEquals("", DateUtil.formatDateToString(now, DateUtil.Hour_Min_Pattern), getIntTime(dateTime).toString());




/*
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getIntTime", Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getIntDate(Integer dateTime) 
* 
*/ 
@Test
public void testGetIntDate() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getIntDate", Integer.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getComponentList(String calendarFileName) 
* 
*/ 
@Test
public void testGetComponentList() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getComponentList", String.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: insertTableData(Property title, Property note, Location location) 
* 
*/ 
@Test
public void testInsertTableData() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("insertTableData", Property.class, Property.class, Location.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getFooterCell() 
* 
*/ 
@Test
public void testGetFooterCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getFooterCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getLocation(Location location) 
* 
*/ 
@Test
public void testGetLocation() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getLocation", Location.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getLocationImage() 
* 
*/ 
@Test
public void testGetLocationImage() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getLocationImage"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getTimeCell() 
* 
*/ 
@Test
public void testGetTimeCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getTimeCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: handleDuration(Date startDate, Date stopDate) 
* 
*/ 
@Test
public void testHandleDuration() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("handleDuration", Date.class, Date.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getTaskCell(String titleValue, Property note) 
* 
*/ 
@Test
public void testGetTaskCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getTaskCell", String.class, Property.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getLocationCell(Location location) 
* 
*/ 
@Test
public void testGetLocationCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getLocationCell", Location.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getPdfPCell() 
* 
*/ 
@Test
public void testGetPdfPCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getPdfPCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: createTableWithHeaderAndFooter(Calendar calendar) 
* 
*/ 
@Test
public void testCreateTableWithHeaderAndFooter() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("createTableWithHeaderAndFooter", Calendar.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getHeaderCell(Calendar calendar, Locale locale) 
* 
*/ 
@Test
public void testGetHeaderCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getHeaderCell", Calendar.class, Locale.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getTotalTimeFrameOfTheDayCell() 
* 
*/ 
@Test
public void testGetTotalTimeFrameOfTheDayCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getTotalTimeFrameOfTheDayCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getClockImageCell() 
* 
*/ 
@Test
public void testGetClockImageCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getClockImageCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getWeekNumberCell(Calendar calendar, Locale locale) 
* 
*/ 
@Test
public void testGetWeekNumberCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getWeekNumberCell", Calendar.class, Locale.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getWeekCell() 
* 
*/ 
@Test
public void testGetWeekCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getWeekCell"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getCurrentDateCell(Calendar calendar, Locale locale) 
* 
*/ 
@Test
public void testGetCurrentDateCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getCurrentDateCell", Calendar.class, Locale.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getActualDatePCell(Calendar calendar, Locale locale) 
* 
*/ 
@Test
public void testGetActualDatePCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getActualDatePCell", Calendar.class, Locale.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getMonthCell(Calendar calendar, Locale locale) 
* 
*/ 
@Test
public void testGetMonthCell() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getMonthCell", Calendar.class, Locale.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: createHeaderTable(int red, int green, int blue) 
* 
*/ 
@Test
public void testCreateHeaderTable() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("createHeaderTable", int.class, int.class, int.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: separator() 
* 
*/ 
@Test
public void testSeparator() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("separator"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

/** 
* 
* Method: getClock() 
* 
*/ 
@Test
public void testGetClock() throws Exception { 
//TODO: Test goes here... 
/* 
try { 
   Method method = CreatePdfFromCalendar.getClass().getMethod("getClock"); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/ 
} 

} 
