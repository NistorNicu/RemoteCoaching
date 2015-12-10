package com.remotecoaching.app.service;

import java.io.FileNotFoundException;
import java.io.FileReader;

import javax.activation.DataSource;

import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.StringUtils;
import com.remotecoaching.app.persistence.ImporterInterface;
import com.remotecoaching.app.persistence.MyDataSource;

public class ImpoterService implements ImporterInterface {

	private static final String SQL_INSERT = "INSERT INTO ${table}(${keys}) VALUES(${values})";
	private static final String TABLE_REGEX = "\\$\\{table\\}";
	private static final String KEYS_REGEX = "\\$\\{keys\\}";
	private static final String VALUES_REGEX = "\\$\\{values\\}";

	private Connection connection;
	private char seprator;


public ImpoterService() {
    this.connection = MyDataSource.getInstance().getConnection();
    this.seprator = ',';
}

	
	public void loadCSV(String csvFile, String tableName) throws Exception {

		CSVReader csvReader = null;
		if (null == this.connection) {
			throw new Exception("Not a valid connection.");
		}
		try {

			csvReader = new CSVReader(new FileReader(csvFile), this.seprator);

		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception("Error occured while executing file. " + e.getMessage());
		}

		String[] headerRow = csvReader.readNext();

		if (null == headerRow) {
			throw new FileNotFoundException(
					"No columns defined in given CSV file." + "Please check the CSV file format.");
		}

//		String questionmarks = StringUtils.repeat("?,", headerRow.length);
//		questionmarks = (String) questionmarks.subSequence(0, questionmarks.length() - 1);
//
//		String query = SQL_INSERT.replaceFirst(TABLE_REGEX, tableName);
//		query = query.replaceFirst(KEYS_REGEX, StringUtils.join(headerRow, ","));
//		query = query.replaceFirst(VALUES_REGEX, questionmarks);
//
//		System.out.println("Query: " + query);
		//TODO prepare query 	
		String[] nextLine;
		Connection con = null;
		PreparedStatement ps = null;
		try {
			con = this.connection;
			con.setAutoCommit(false);
			ps = (PreparedStatement) con.prepareStatement(query);

			
			final int batchSize = 1000;
			int count = 0;
			while ((nextLine = csvReader.readNext()) != null) {

				if (null != nextLine) {
					int index = 1;
					for (String string : nextLine) {
						//TODO set up query
					}
					ps.addBatch();
				}
				if (++count % batchSize == 0) {
					ps.executeBatch();
				}
			}
			ps.executeBatch(); // insert remaining records
			con.commit();
		} catch (Exception e) {
			con.rollback();
			e.printStackTrace();
			throw new Exception("Error occured while loading data from file to database." + e.getMessage());
		} finally {
			if (null != ps)
				ps.close();
			if (null != con)
				con.close();

			csvReader.close();
		}
	}

	public char getSeprator() {
		return seprator;
	}

	public void setSeprator(char seprator) {
		this.seprator = seprator;
	}


}
