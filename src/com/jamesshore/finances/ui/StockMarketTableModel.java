package com.jamesshore.finances.ui;

import javax.swing.table.*;
import com.jamesshore.finances.domain.*;
import com.jamesshore.finances.util.*;

public class StockMarketTableModel extends AbstractTableModel {
	private static final long serialVersionUID = 1L;
	private static final String[] COLUMN_TITLES = { "Year", "Starting Balance", "Cost Basis", "Sales", "Growth", "Ending Balance" };
	private static final Class<?>[] COLUMN_CLASSES = { Year.class, SelfRenderable.class, SelfRenderable.class, SelfRenderable.class, SelfRenderable.class, SelfRenderable.class };

	private StockMarketProjection projection;

	public StockMarketTableModel(StockMarketProjection projection) {
		this.projection = projection;
	}

	public void setProjection(StockMarketProjection projection) {
		this.projection = projection;
		this.fireTableDataChanged();
	}

	public StockMarketProjection stockMarketProjection() {
		return projection;
	}

	@Override
	public int getColumnCount() {
		return COLUMN_TITLES.length;
	}

	@Override
	public String getColumnName(int column) {
		return COLUMN_TITLES[column];
	}

	@Override
	public Class<?> getColumnClass(int column) {
		return COLUMN_CLASSES[column];
	}

	@Override
	public int getRowCount() {
		return projection.numberOfYears();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		StockMarketYear currentYear = projection.getYearOffset(rowIndex);
		switch (columnIndex) {
			case 0:
				return currentYear.year();
			case 1:
				return currentYear.startingBalance();
			case 2:
				return currentYear.startingCostBasis();
			case 3:
				return currentYear.totalSold();
			case 4:
				return currentYear.growth();
			case 5:
				return currentYear.endingBalance();
			default:
				throw new UnreachableCodeException();
		}
	}

	public Dollars startingBalance() {
		return projection.getYearOffset(0).startingBalance();
	}

}
