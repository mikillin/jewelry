package com.project.controllers.orders;

import com.project.model.Order;
import com.project.model.OrderMaterial;
import com.project.repository.orders.OrderDAO;
import com.project.utils.NumberTOWords;
import jxl.Workbook;
import jxl.read.biff.BiffException;
import jxl.write.*;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.math.BigDecimal;
import java.util.*;

/**
 * User: S.Rogachevsky
 * Date: 20.02.14
 * Time: 14:37
 */

@Controller
@RequestMapping(value = "service")
public class OrderServiceImpl {//implements OrderService {
    public static final String TEMP_FILENAME = "excelOrder.xls";
    public static final String RESULT_FILE_EXTENSION = "xls";
    public static final Integer SOURCE_ORDER_SHEET = 0;
    public static final String CELERITY_COST_MULTIPLIER = "2";
    public static final BigDecimal SILVER_CONTENT_TEST_COST = new BigDecimal("600");
    public static final BigDecimal GOLD_CONTENT_TEST_COST = new BigDecimal("1400");
    public static final BigDecimal SERVICE_COST = new BigDecimal("9000");
    public static final BigDecimal SILVER_BASE_CONTENT = new BigDecimal("875");
    public static final BigDecimal GOLD_BASE_CONTENT = new BigDecimal("583");
    public static final String GOLD_CODE = "1";
    public static final int ROUND_SCALE = 2;
    public static final Float ZERO_VALUE = 0.0f;
    public static final BigDecimal GOLD_CONTENT_500 = new BigDecimal("500");
    public static final BigDecimal GOLD_CONTENT_583 = new BigDecimal("583");
    public static final BigDecimal GOLD_CONTENT_885 = new BigDecimal("885");
    public static final BigDecimal GOLD_CONTENT_9999 = new BigDecimal("999.9");
    public static final BigDecimal SILVER_CONTENT_875 = new BigDecimal("875");
    public static final BigDecimal SILVER_CONTENT_9999 = new BigDecimal("999.9");
    public static final String XLS_GOLD_ORDER_DOC_PATH = "xls/goldOrder.xls";
    public static final String XLS_SILVER_ORDER_DOC_PATH = "xls/silverOrder.xls";
    private static WritableFont arial10ptBordered, arial10pt,arial10ptRight, arial10ptBold, arial12ptBold, arial10ptBoldLeft, arial10ptBoldBordered;
    private static WritableCellFormat arial10ptBorderedFmt, arial10ptFmt,arial10ptRightFmt, arial10ptBoldFmt, arial12ptBoldFmt, arial10ptBoldLeftFmt, arial10ptBoldBorderedFmt;


    public static LinkedList<String> monthsRussian = new LinkedList(
            Arrays.asList("января", "февраля", "марта", "апреля", "мая", "июня", "июля", "августа", "сентября", "октября", "ноября", "декабря"));
    @Autowired
    OrderDAO ordersDAO;


    @RequestMapping(value = "/saveOrder")
    public void saveOrder(
            @RequestParam String material,
            @RequestParam String orderDateCompletion,
            @RequestParam String fio,
            @RequestParam String homeAddress,
            @RequestParam String phone,
            @RequestParam String losses,
            @RequestParam String productName,
            @RequestParam String productCost,
            @RequestParam String advance,
            @RequestParam(defaultValue = "0", required = false) BigDecimal gold500,
            @RequestParam(defaultValue = "0", required = false) BigDecimal gold583,
            @RequestParam(defaultValue = "0", required = false) BigDecimal gold885,
            @RequestParam(defaultValue = "0", required = false) BigDecimal gold9999,
            @RequestParam(defaultValue = "0", required = false) BigDecimal silver875,
            @RequestParam(defaultValue = "0", required = false) BigDecimal silver9999,
            @RequestParam(defaultValue = "none", required = false) String celerity,
            @RequestParam(defaultValue = "none", required = false) String materialContentTest,
            @RequestParam(defaultValue = "none", required = false) String service,
            HttpServletResponse response
    ) {

        BigDecimal overallMetalWeight = BigDecimal.ZERO, overallCost = BigDecimal.ZERO, partResult;

        Order order = new Order();

        order.setOrderNumber(1);

        OrderMaterial om = new OrderMaterial();
        om.setMaterialContent(12);

        order.setOrderMaterial(om);
        ordersDAO.saveOrder(order);


        InputStream inputStream = null;
        Calendar calendar = GregorianCalendar.getInstance();
        try {
            String sourceName = GOLD_CODE.equals(material) ? XLS_GOLD_ORDER_DOC_PATH : XLS_SILVER_ORDER_DOC_PATH;
            inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream(sourceName);

            File tmp = new File(TEMP_FILENAME);
            WritableWorkbook workbook = Workbook.createWorkbook(tmp, Workbook.getWorkbook(inputStream));
            WritableSheet sheet = workbook.getSheet(SOURCE_ORDER_SHEET);
            initFormats();

            Label temporalLabel = new Label(35, 14, String.valueOf(ordersDAO.getLastOrderNumber() + 1));
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(17, 19, String.valueOf(calendar.get(Calendar.DAY_OF_MONTH)));
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(22, 19, monthsRussian.get(calendar.get(Calendar.MONTH)));
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(33, 19, String.valueOf(calendar.get(Calendar.YEAR)));
            sheet.addCell(temporalLabel);


            temporalLabel = new Label(30, 23, fio, arial10ptBoldLeftFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(14, 25, homeAddress, arial10ptBoldLeftFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(38, 25, phone, arial10ptBoldFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(54, 34, losses, arial10ptBoldBorderedFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(6, 47, productName, arial12ptBoldFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(31, 51, productCost, arial10ptBorderedFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(20, 76, advance, arial10ptBoldFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(4, 79, new NumberTOWords().toWords(new BigDecimal(advance)), arial10ptRightFmt);
            sheet.addCell(temporalLabel);


            String[] dateCompletion = orderDateCompletion.split("-");
            temporalLabel = new Label(40, 21, dateCompletion[0],arial10ptBoldFmt);
            sheet.addCell(temporalLabel);
            temporalLabel = new Label(45, 21, monthsRussian.get(Integer.parseInt(dateCompletion[1]) - 1), arial10ptBoldFmt);
            sheet.addCell(temporalLabel);
            temporalLabel = new Label(54, 21, dateCompletion[2],arial10ptBoldFmt);
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(48, 81, dateCompletion[0] + " " + monthsRussian.get(Integer.parseInt(dateCompletion[1]) - 1) + " " + dateCompletion[2]);
            sheet.addCell(temporalLabel);

            overallCost = overallCost.add(new BigDecimal(productCost));

            if (!"none".equals(service)) {
                temporalLabel = new Label(31, 55, SERVICE_COST.toString(),arial10ptBorderedFmt);
                sheet.addCell(temporalLabel);
                overallCost = overallCost.add(SERVICE_COST);
            }

            if (GOLD_CODE.equals(material)) {
                //gold

                //TODO: check if it can be dobubled when it has celerity property
                if (!"none".equals(materialContentTest)) {
                    BigDecimal contentTest = GOLD_CONTENT_TEST_COST.multiply(new BigDecimal("none".equals(celerity) ? "1" : CELERITY_COST_MULTIPLIER));
                    temporalLabel = new Label(31, 53, contentTest.toString(),arial10ptBorderedFmt);
                    sheet.addCell(temporalLabel);
                    overallCost = overallCost.add(contentTest);
                }


                if (!ZERO_VALUE.equals(gold583)) {
                    temporalLabel = new Label(29, 34, gold583.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);
                    partResult = getContentRecount(GOLD_CONTENT_583, gold583, GOLD_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);
                    temporalLabel = new Label(33, 34, String.valueOf(partResult),arial10ptFmt);
                    sheet.addCell(temporalLabel);
                }

                if (!ZERO_VALUE.equals(gold500)) {
                    temporalLabel = new Label(29, 36, gold500.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                    partResult = getContentRecount(GOLD_CONTENT_500, gold500, GOLD_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);

                    temporalLabel = new Label(33, 36, String.valueOf(partResult),arial10ptFmt);
                    sheet.addCell(temporalLabel);
                }

                if (!ZERO_VALUE.equals(gold885)) {
                    temporalLabel = new Label(29, 38, gold885.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                    partResult = getContentRecount(GOLD_CONTENT_885, gold885, GOLD_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);

                    temporalLabel = new Label(33, 38, String.valueOf(partResult),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                }
                if (!ZERO_VALUE.equals(gold9999)) {
                    temporalLabel = new Label(29, 40, gold9999.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                    partResult = getContentRecount(GOLD_CONTENT_9999, gold9999, GOLD_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);

                    temporalLabel = new Label(33, 40, String.valueOf(partResult),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                }

            } else {
                //silver

                //TODO: check if it can be dobubled when it has celerity property
                if (!"none".equals(materialContentTest)) {
                    BigDecimal contentTest = SILVER_CONTENT_TEST_COST.multiply(new BigDecimal("none".equals(celerity) ? "1" : "2"));
                    temporalLabel = new Label(31, 53, contentTest.toString(),arial10ptBorderedFmt);
                    sheet.addCell(temporalLabel);
                    overallCost = overallCost.add(contentTest);
                }


                if (!ZERO_VALUE.equals(silver875)) {
                    temporalLabel = new Label(29, 34, silver875.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                    partResult = getContentRecount(SILVER_CONTENT_875, silver875, SILVER_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);

                    temporalLabel = new Label(33, 34, String.valueOf(partResult),arial10ptBorderedFmt);
                    sheet.addCell(temporalLabel);

                }

                if (!ZERO_VALUE.equals(silver9999)) {

                    temporalLabel = new Label(29, 36, silver9999.toString(),arial10ptFmt);
                    sheet.addCell(temporalLabel);

                    partResult = getContentRecount(SILVER_CONTENT_9999, silver9999, SILVER_BASE_CONTENT);
                    overallMetalWeight = overallMetalWeight.add(partResult);

                    temporalLabel = new Label(33, 36, String.valueOf(partResult),arial10ptFmt);
                    sheet.addCell(temporalLabel);
                }

            }
            temporalLabel = new Label(31, 57, overallCost.toString(),arial10ptBorderedFmt );
            sheet.addCell(temporalLabel);

            temporalLabel = new Label(42, 34, overallMetalWeight.toString(), arial10ptBoldBorderedFmt);
            sheet.addCell(temporalLabel);

            workbook.write();
            workbook.close();

            response.setContentType("application/force-download");
            response.setHeader("Content-Disposition", "attachment; filename= " + getFileName());
            IOUtils.copy(new FileInputStream(tmp), response.getOutputStream());
            response.flushBuffer();
            tmp.deleteOnExit();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (WriteException e) {
            e.printStackTrace();
        } catch (BiffException e) {
            e.printStackTrace();
        }


    }


    private String getFileName() {
        return String.valueOf(new Date().getTime()) + "." + RESULT_FILE_EXTENSION;
    }

    private BigDecimal getContentRecount(BigDecimal currentAmount, BigDecimal currentContent, BigDecimal base) {

        return currentAmount.multiply(currentContent).divide(base, ROUND_SCALE, BigDecimal.ROUND_HALF_UP);

    }

    private void initFormats() throws WriteException {

        arial10pt = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        arial10ptFmt = new WritableCellFormat(arial10pt);
        arial10ptFmt.setAlignment(Alignment.CENTRE);
        arial10ptFmt.setWrap(true);

        arial10ptRight = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        arial10ptRightFmt = new WritableCellFormat(arial10ptRight);
        arial10ptRightFmt.setAlignment(Alignment.RIGHT);
        arial10ptRightFmt.setWrap(true);


        arial10ptBoldBordered = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        arial10ptBoldBorderedFmt = new WritableCellFormat(arial10ptBoldBordered);
        arial10ptBoldBorderedFmt.setAlignment(Alignment.CENTRE);
        arial10ptBoldBorderedFmt.setWrap(true);
        arial10ptBoldBorderedFmt.setBorder(Border.ALL, BorderLineStyle.THIN);


        arial10ptBordered = new WritableFont(WritableFont.ARIAL, 10, WritableFont.NO_BOLD);
        arial10ptBorderedFmt = new WritableCellFormat(arial10ptBordered);
        arial10ptBorderedFmt.setAlignment(Alignment.CENTRE);
        arial10ptBorderedFmt.setWrap(true);
        arial10ptBorderedFmt.setBorder(Border.ALL, BorderLineStyle.THIN);


        arial10ptBold = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        arial10ptBoldFmt = new WritableCellFormat(arial10ptBold);
        arial10ptBoldFmt.setAlignment(Alignment.CENTRE);
        arial10ptBoldFmt.setWrap(true);

        arial10ptBoldLeft = new WritableFont(WritableFont.ARIAL, 10, WritableFont.BOLD);
        arial10ptBoldLeftFmt = new WritableCellFormat(arial10ptBoldLeft);
        arial10ptBoldLeftFmt.setAlignment(Alignment.LEFT);
        arial10ptBoldLeftFmt.setWrap(true);


        arial12ptBold = new WritableFont(WritableFont.ARIAL, 12, WritableFont.BOLD);
        arial12ptBoldFmt = new WritableCellFormat(arial12ptBold);
        arial12ptBoldFmt.setAlignment(Alignment.CENTRE);
        arial12ptBoldFmt.setWrap(true);
        arial12ptBoldFmt.setBorder(Border.ALL, BorderLineStyle.THIN);

    }


}

