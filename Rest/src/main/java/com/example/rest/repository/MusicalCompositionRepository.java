package com.example.rest.repository;


import com.example.rest.entity.MusicalComposition;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
public class MusicalCompositionRepository implements IRestRepository<MusicalComposition>
{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\" "
            + "FROM \"MusicalCompositions\" "
            + "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\" "
            + "FROM \"MusicalCompositions\" "
            + "WHERE \"id\" = ?";
    
    private static String selectByLanguageIdQuery = "SELECT \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\" " +
            "FROM \"MusicalCompositions\" " +
            "WHERE \"language_id\" = ?";
    
    private static String selectByComposerIdQuery = "SELECT \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\" " +
            "FROM \"MusicalCompositions\" " +
            "WHERE \"composer_id\" = ?";
    
    private static String selectBySingerIdQuery = "SELECT \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\" " +
            "FROM \"MusicalCompositions\" " +
            "WHERE \"singer_id\" = ?";

    private static String insertQuery = "INSERT INTO \"MusicalCompositions\"(\"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\") " +
            "VALUES (?, ?, ?, ?, ?) " +
            "RETURNING \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\"";

    private static String updateQuery = "UPDATE \"MusicalCompositions\" " +
            "SET \"language_id\" = ?, \"composer_id\" = ?, \"singer_id\" = ?, \"name\" = ?, \"year\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\"";

    private static String deleteQuery = "DELETE FROM \"MusicalCompositions\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"language_id\", \"composer_id\", \"singer_id\", \"name\", \"year\"";

    public MusicalCompositionRepository(JdbcOperations JdbcOperations)
    {
        this.jdbcOperations = JdbcOperations;
    }

    @Override
    public MusicalComposition[] select()
    {
        ArrayList<MusicalComposition> values = new ArrayList<MusicalComposition>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while(rowSet.next())
        {
            values.add(new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6)));
        }
        MusicalComposition[] result = new MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public MusicalComposition select(Integer id)
    {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if(!rowSet.next())
        {
            return null;
        }
        return new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6)
        );
    }
    
    public MusicalComposition[] selectByLanguageId(Integer languageId)
    {
        ArrayList<MusicalComposition> values = new ArrayList<MusicalComposition>();
        Object[] params = new Object[] { languageId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByLanguageIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6))
            );
        }
        MusicalComposition[] result = new MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }
    
    public MusicalComposition[] selectByComposerId(Integer composerId)
    {
        ArrayList<MusicalComposition> values = new ArrayList<MusicalComposition>();
        Object[] params = new Object[] { composerId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByComposerIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6))
            );
        }
        MusicalComposition[] result = new MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }
    
    public MusicalComposition[] selectBySingerId(Integer singerId)
    {
        ArrayList<MusicalComposition> values = new ArrayList<MusicalComposition>();
        Object[] params = new Object[] { singerId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectBySingerIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6))
            );
        }
        MusicalComposition[] result = new MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public MusicalComposition insert(MusicalComposition entity)
    {
        Object[] params = new Object[] { entity.getLanguageId(), entity.getComposerId(), entity.getSingerId(), entity.getName(), entity.getYear()};
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6)
        );
    }

    @Override
    public MusicalComposition update(Integer id, MusicalComposition entity)
    {
        Object[] params = new Object[] { entity.getLanguageId(), entity.getComposerId(), entity.getSingerId(), entity.getName(), entity.getYear(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER, Types.VARCHAR, Types.TIMESTAMP, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6)
        );
    }

    @Override
    public MusicalComposition delete(Integer id)
    {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3),
                    rowSet.getInt(4),
                    rowSet.getString(5),
                    rowSet.getTimestamp(6)
        );
    }
}

