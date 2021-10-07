package com.example.rest.repository;


import com.example.rest.entity.BaseEntity;
import com.example.rest.entity.Country;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
public class CountryRepository implements IRestRepository<Country>
{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"name\" "
            + "FROM \"Countries\" "
            + "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"name\" "
            + "FROM \"Countries\" "
            + "WHERE \"id\" = ?";

    private static String insertQuery = "INSERT INTO \"Countries\"(\"name\") "
            + "VALUES (?) "
            + "RETURNING \"id\", \"name\"";

    private static String updateQuery = "UPDATE \"Countries\" "
            + "SET \"name\" = ?"
            + "WHERE \"id\" = ? "
            + "RETURNING \"id\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"Countries\" "
            + "WHERE \"id\" = ? "
            + "RETURNING \"id\", \"name\"";

    public CountryRepository(JdbcOperations JdbcOperations)
    {
        this.jdbcOperations = JdbcOperations;
    }

    @Override
    public Country[] select()
    {
        ArrayList<Country> values = new ArrayList<Country>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while(rowSet.next())
        {
            values.add(new Country(
                    rowSet.getInt(1),
                    rowSet.getString(2)));
        }
        Country[] result = new Country[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Country select(Integer id)
    {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if(!rowSet.next())
        {
            return null;
        }
        return new Country(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Country insert(Country entity)
    {
        Object[] params = new Object[] { entity.getName() };
        int[] types = new int[] { Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Country(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Country update(Integer id, Country entity)
    {
        Object[] params = new Object[] { entity.getName(), id };
        int[] types = new int[] { Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Country(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }

    @Override
    public Country delete(Integer id)
    {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Country(
                rowSet.getInt(1),
                rowSet.getString(2)
        );
    }
}

