package com.fbb.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.fbb.util.LogUtil;

public class SettingDao {
	public static final String TABLE_NAME = "setting";
	public static final String sql_add = "replace into " + TABLE_NAME
			+ " values (?,?)";

	public String getByName(String name) {
		if (name == null || name.equalsIgnoreCase(""))
			return null;
		String result = null;
		Connection conn = DB.getConnection();
		Statement stmt = DB.createStmt(conn);
		ResultSet resultSet = DB.executeQuery(stmt, "select * from "
				+ TABLE_NAME + " where setting_name = '" + name+ "'");
		try {
			if (resultSet.next()) {
				result = resultSet.getString("setting_value");
			}
		} catch (SQLException e) {
			e.printStackTrace();
			LogUtil.e("select表" + TABLE_NAME + "异常");
		} finally {
			DB.close(conn);
			DB.close(stmt);
			DB.close(resultSet);
		}
		return result;
	}
}
