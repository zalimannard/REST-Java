package com.example.rest.repository;


import com.example.rest.entity.Creator;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
public class CreatorRepository implements IRestRepository<Creator>
{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"country_id\", \"name\" "
            + "FROM \"Creators\" "
            + "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"country_id\", \"name\" "
            + "FROM \"Creators\" "
            + "WHERE \"id\" = ?";
    
    private static String selectByCountryIdQuery = "SELECT \"id\", \"country_id\", \"name\" " +
            "FROM \"Creators\" " +
            "WHERE \"country_id\" = ?";

    private static String insertQuery = "INSERT INTO \"Creators\"(\"country_id\", \"name\") " +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"country_id\", \"name\"";

    private static String updateQuery = "UPDATE \"Creators\" " +
            "SET \"country_id\" = ?, \"name\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"country_id\", \"name\"";

    private static String deleteQuery = "DELETE FROM \"Creators\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"country_id\", \"name\"";

    public CreatorRepository(JdbcOperations JdbcOperations)
    {
        this.jdbcOperations = JdbcOperations;
    }

    @Override
    public Creator[] select()
    {
        ArrayList<Creator> values = new ArrayList<Creator>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while(rowSet.next())
        {
            values.add(new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)));
        }
        Creator[] result = new Creator[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Creator select(Integer id)
    {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if(!rowSet.next())
        {
            return null;
        }
        return new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)
        );
    }
    
    public Creator[] selectByCountryId(Integer countryId)
    {
        ArrayList<Creator> values = new ArrayList<Creator>();
        Object[] params = new Object[] { countryId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByCountryIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)
        ));
        }
        Creator[] result = new Creator[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Creator insert(Creator entity)
    {
        Object[] params = new Object[] { entity.getCountryId(), entity.getName() };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)
        );
    }

    @Override
    public Creator update(Integer id, Creator entity)
    {
        Object[] params = new Object[] { entity.getCountryId(), entity.getName(), id };
        int[] types = new int[] { Types.INTEGER, Types.VARCHAR, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)
        );
    }

    @Override
    public Creator delete(Integer id)
    {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Creator(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getString(3)
        );
    }
}

