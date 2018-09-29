
package org.jtm.utils;


import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelImportUtils {
    /**
     * 输入流
     */
    private InputStream inputStream;
    /**
     * 开始读取的行数，默认为0，从第一行开始
     */
    private Integer startRowNum = 0;
    /**
     * 结束读取的行数，为null则根据sheet获取
     */
    private Integer endRowNum = null;
    /**
     * 开始读取的列数，默认为0，从第一列开始
     */
    private Integer startCellNum = 0;
    /**
     * 结束读取的列数，为null则根据sheet中的row获取
     */
    private Integer endCellNum = null;

    /**
     * 通用构造方法
     *
     * @param in          输入流
     * @param startRowNum 开始读取行号
     * @param endCellNum  结束读取列号
     */
    public ExcelImportUtils(InputStream in, Integer startRowNum, Integer endCellNum) {
        this.inputStream = in;
        this.startRowNum = startRowNum;
        this.endCellNum = endCellNum;
    }

    /**
     * 默认构造方法
     *
     * @param in 文件、网络输入流
     */
    public ExcelImportUtils(InputStream in) {
        this.inputStream = in;
    }

    /**
     * 自定义Excel起始读取位置构造方法
     *
     * @param in           输入流
     * @param startRowNum  开始读取行号
     * @param endRowNum    结束读取行号
     * @param startCellNum 开始读取列号
     * @param endCellNum   结束读取列好
     */
    public ExcelImportUtils(InputStream in, Integer startRowNum, Integer endRowNum, Integer startCellNum, Integer endCellNum) {
        this.inputStream = in;
        this.startCellNum = startCellNum;
        this.endCellNum = endCellNum;
        this.startRowNum = startRowNum;
        this.endRowNum = endRowNum;
    }

    public List<List<String>> getExcelData() throws IOException, InvalidFormatException {
        // 创建工作表
        Workbook workbook = WorkbookFactory.create(inputStream);
        workbook.getNumberOfSheets();
        Sheet sheet = workbook.getSheetAt(0);
        return getDataFromSheet(sheet);
    }

    /**
     * 从sheet中解析数据
     *
     * @param sheet sheet对象
     * @return 封装的嵌套列表数据
     * @author dzhang
     */
    private List<List<String>> getDataFromSheet(Sheet sheet) {
        // 初始化返回列表
        List<List<String>> lists = new ArrayList<>();
        // 如果没有结束读取的行数，则通过sheet获取
        this.endRowNum = this.endRowNum == null ? sheet.getLastRowNum() : this.endRowNum;
        for (int i = this.startRowNum; i < this.endRowNum; i++) {
            List<String> list = new ArrayList<>();
            // 获取每一行的对象
            Row row = sheet.getRow(i);
            this.endCellNum = this.endCellNum == null ? row.getLastCellNum() : this.endCellNum;
            for (int j = this.startCellNum; j < this.endCellNum; j++) {
                // 获取单元格
                Cell cell = row.getCell(j);
                if (null != cell) {
                    String val;
                    switch (cell.getCellTypeEnum()) {
                        case _NONE:
                            val = "";
                            break;
                        case BLANK:
                            val = "";
                            break;
                        case ERROR:
                            val = "";
                            break;
                        case FORMULA:
                            val = cell.getStringCellValue();
                            break;
                        case STRING:
                            val = cell.getStringCellValue();
                            break;
                        case NUMERIC:
                            val = String.valueOf(cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            val = String.valueOf(cell.getBooleanCellValue());
                            break;
                        default:
                            val = cell.getStringCellValue();
                    }
                    list.add(val);
                }
            }
            lists.add(list);
        }
        return lists;
    }


    public static void main(String[] args) {
        String fileName = "E:\\1.xlsx";
        try {
            ExcelImportUtils excelImportUtils = new ExcelImportUtils(new FileInputStream(fileName));
            excelImportUtils.getExcelData();
        } catch (InvalidFormatException | IOException e) {
            e.printStackTrace();
        }
    }

}
