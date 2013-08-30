package com.rise.common.util.database;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.context.ApplicationContext;

import com.rise.common.util.checker.Precondition;
import com.rise.common.util.constants.HibernateHelperConstants;
import com.rise.common.util.exception.DatabaseException;

public class DatabaseUtil {

	private static final String ALL = "%";
	private static final String TABLE_TYPE = "Table";
	private static final int TABLE_NAME_COLUMN_INDEX = 3;

	private static DatabaseUtil instance = new DatabaseUtil();

	public DatabaseUtil() {
		// prevent instantiation
	}

	public static DatabaseUtil getInstance() {
		return instance;
	}

	public Connection getConnection() throws SQLException {
		ApplicationContext applicationContext = ApplicationContextProvider
				.getApplicationContext();
		Precondition.ensureNotNull(applicationContext,
				HibernateHelperConstants.APPLICATION_CONTEXT);
		DataSource dataSource = (DataSource) applicationContext
				.getBean(HibernateHelperConstants.DATA_SOURCE);
		Precondition.ensureNotNull(dataSource,
				HibernateHelperConstants.SQL_DATA_SOURCE);
		return dataSource.getConnection();
	}

	public void close(Connection argConnection) {
		if (argConnection != null) {
			try {
				argConnection.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void close(Statement argStatement) {
		if (argStatement != null) {
			try {
				argStatement.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void close(ResultSet argResultSet) {
		if (argResultSet != null) {
			try {
				argResultSet.close();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void rollback(Connection argConnection) {
		if (argConnection != null) {
			try {
				argConnection.rollback();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public void commit(Connection argConnection) {
		if (argConnection != null) {
			try {
				argConnection.commit();
			} catch (Exception ignore) {
				// ignore
			}
		}
	}

	public List<String> getTables() {
		Connection connection = null;
		ResultSet resultSet = null;
		DatabaseMetaData databaseMetaData = null;
		List<String> tablesList = new ArrayList<String>();
		try {
			connection = getConnection();
			databaseMetaData = connection.getMetaData();
			String[] types = new String[] { TABLE_TYPE };
			resultSet = databaseMetaData.getTables(null, null, ALL, types);
			if (Precondition.checkNotNull(resultSet)) {
				while (resultSet.next()) {
					tablesList
							.add(resultSet.getString(TABLE_NAME_COLUMN_INDEX));
				}
			}
		} catch (Exception e) {
			rollback(connection);
			throw new DatabaseException(
					"Error while obtaining list of table names from database",
					e);
		} finally {
			close(resultSet);
			close(connection);
		}
		return tablesList;
	}
}
