package net.evolveip.report.utilities;

import java.awt.Color;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFColor;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 *
 */

/**
 * @author brobert
 *
 */
public class DefaultStyleFunction implements ReportStyleFunction {

	private final XSSFColor HEADER_BACKGROUND = new XSSFColor(new Color(204, 240, 255));



	/* (non-Javadoc)
	 * @see net.evolveip.report.utilities.ReportStyleFunction#style(org.apache.poi.xssf.usermodel.XSSFWorkbook)
	 */
	@Override
	public XSSFWorkbook style(XSSFWorkbook workbook) {
		CellStyle basicHeaderStyle = getBasicHeaderStyle(workbook);
		for (int i = 0; i < workbook.getNumberOfSheets(); i++) {
			XSSFSheet sheet = workbook.getSheetAt(i);
			removeGridlines(sheet);
			basicFormatHeaders(sheet, basicHeaderStyle);
			autoSizeAll(sheet);
			XSSFWorkbook b = null;
		}
		return workbook;
	}



	/**
	 * This is yet to be implemented but should be applied to all populated
	 * cells to convert to numeric if neccesary
	 * 
	 * @param cell
	 * @param s
	 */
	private void applyValue(Cell cell, String s) {
		try {
			Double num = Double.parseDouble(s);
			cell.setCellValue(num);
			cell.setCellType(Cell.CELL_TYPE_NUMERIC);
		} catch (NumberFormatException e) {
			cell.setCellValue(s);
		}
	}



	/**
	 *
	 * @param workbook
	 */
	private void basicFormatHeaders(XSSFSheet sheet, CellStyle basicHeaderStyle) {
		XSSFRow header = getFirstPopulatedRow(sheet);
		for (int i = header.getFirstCellNum(); i < getPopulatedColCount(sheet); i++) {
			header.getCell(i).setCellStyle(basicHeaderStyle);
		}
	}



	/**
	 * @return
	 */
	private CellStyle getBasicHeaderStyle(XSSFWorkbook workbook) {
		XSSFCellStyle basicHeaderStyle = workbook.createCellStyle();
		XSSFFont bold = workbook.createFont();
		bold.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		basicHeaderStyle.setFillForegroundColor(HEADER_BACKGROUND);
		basicHeaderStyle.setFont(bold);
		basicHeaderStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		return basicHeaderStyle;
	}



	/**
	 * @param workbook
	 */
	private void removeGridlines(XSSFSheet sheet) {
		sheet.setDisplayGridlines(false);
		sheet.setPrintGridlines(false);

	}



	private void autoSizeAll(XSSFSheet sheet) {
		int columnCount = getPopulatedColCount(sheet);
		for (int k = 0; k < columnCount; k++) {
			sheet.autoSizeColumn(k);
			int beforeWidth = sheet.getColumnWidth(k);
			sheet.setColumnWidth(k, beforeWidth + 256);
		}
	}



	/**
	 * @param sheet
	 * @return
	 */
	private int getPopulatedColCount(XSSFSheet sheet) {
		XSSFRow firstRow = sheet.getRow(sheet.getFirstRowNum());
		int colCount = firstRow.getFirstCellNum();
		while (firstRow.getCell(colCount) != null) {
			colCount++;
		}
		return colCount;
	}



	/**
	 * @param sheet
	 * @return
	 */
	private XSSFRow getFirstPopulatedRow(XSSFSheet sheet) {
		return sheet.getRow(sheet.getFirstRowNum());
	}

}
