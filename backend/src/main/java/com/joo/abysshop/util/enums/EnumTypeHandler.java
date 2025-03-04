package com.joo.abysshop.util.enums;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.ibatis.type.JdbcType;
import org.apache.ibatis.type.TypeHandler;

public class EnumTypeHandler<E extends Enum<E>> implements TypeHandler<E> {

    private Class<E> type;

    @Override
    public void setParameter(PreparedStatement ps, int i, E parameter, JdbcType jdbcType)
        throws SQLException {

    }

    @Override
    public E getResult(ResultSet rs, String columnName) throws SQLException {
        return null;
    }

    @Override
    public E getResult(ResultSet rs, int columnIndex) throws SQLException {
        return null;
    }

    @Override
    public E getResult(CallableStatement cs, int columnIndex) throws SQLException {
        return null;
    }
}
