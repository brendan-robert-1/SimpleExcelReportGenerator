/**
 *
 */
package net.evolveip.report.utilities;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

/**
 * @author brobert
 *
 */
public @FunctionalInterface interface ReportStyleFunction {

	public XSSFWorkbook style(XSSFWorkbook workbook);
}
