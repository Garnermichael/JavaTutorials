package com.zetcode;

import org.skife.jdbi.v2.sqlobject.Bind;
import org.skife.jdbi.v2.sqlobject.SqlQuery;
import org.skife.jdbi.v2.sqlobject.customizers.Mapper;

public interface MyDAO {

    @SqlQuery("SELECT * FROM Cars WHERE Id = :id")
    @Mapper(CarMapper.class)
    Car findById(@Bind("id") int id);

    @SqlQuery("SELECT COUNT(Id) FROM Cars")
    int countCars();
}
