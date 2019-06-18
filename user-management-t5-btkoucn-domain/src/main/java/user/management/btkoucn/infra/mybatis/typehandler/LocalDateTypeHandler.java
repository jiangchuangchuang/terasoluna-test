package user.management.btkoucn.infra.mybatis.typehandler;
import java.sql.CallableStatement;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;
import org.joda.time.LocalDate;

public class LocalDateTypeHandler extends BaseTypeHandler<LocalDate> {

	@Override
    public void setNonNullParameter(PreparedStatement ps, int i,
    		LocalDate parameter, JdbcType jdbcType) throws SQLException {
		Date d = new Date(parameter.toDate().getTime());
        ps.setDate(i,d);
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, String columnName)
            throws SQLException {
        return toLocalDate(rs.getDate(columnName));
    }

    @Override
    public LocalDate getNullableResult(ResultSet rs, int columnIndex)
            throws SQLException {
        return toLocalDate(rs.getDate(columnIndex));
    }

    @Override
    public LocalDate getNullableResult(CallableStatement cs, int columnIndex)
            throws SQLException {
        return toLocalDate(cs.getDate(columnIndex));
    }

	private LocalDate toLocalDate(Date target) {

        if (target == null) {
            return null;
        } else {
            return new LocalDate(target.toLocalDate().getYear(),target.toLocalDate().getMonthValue(),target.toLocalDate().getDayOfMonth());
        }
    }

}
