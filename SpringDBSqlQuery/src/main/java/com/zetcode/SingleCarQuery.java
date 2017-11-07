package com.zetcode;

import java.sql.ResultSet;
import java.sql.Types;
import java.util.Map;
import javax.sql.DataSource;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlQuery;

public class SingleCarQuery extends SqlQuery<Car> {

    private static final String SQL = "SELECT * FROM Cars WHERE Id=?";

    SingleCarQuery(DataSource ds) {
        super(ds, SQL);
        declareParameter(new SqlParameter(Types.INTEGER));
    }

    @Override
    protected RowMapper<Car> newRowMapper(Object[] paramArrayOfObject,
            Map paramMap) {

        RowMapper<Car> rowMapper = (ResultSet rs, int paramInt) -> {
            
            Car car = new Car();
            car.setId(rs.getLong(1));
            car.setName(rs.getString(2));
            car.setPrice(rs.getInt(1));
            return car;
        };

        return rowMapper;
    }
}
