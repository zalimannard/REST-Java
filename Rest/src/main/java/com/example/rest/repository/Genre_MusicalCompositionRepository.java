package com.example.rest.repository;


import com.example.rest.entity.Genre_MusicalComposition;
import java.sql.Types;
import java.util.ArrayList;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;


@Repository
public class Genre_MusicalCompositionRepository implements IRestRepository<Genre_MusicalComposition>
{
    protected final JdbcOperations jdbcOperations;

    private static String selectQuery = "SELECT \"id\", \"genre_id\", \"musicalCompositions_id\" "
            + "FROM \"Cenre_MusicalCompositions\" "
            + "ORDER BY \"id\"";

    private static String selectByIdQuery = "SELECT \"id\", \"genre_id\", \"musicalCompositions_id\" "
            + "FROM \"Cenre_MusicalCompositions\" "
            + "WHERE \"id\" = ?";
    
    private static String selectByGenreIdQuery = "SELECT \"id\", \"genre_id\", \"musicalCompositions_id\" " +
            "FROM \"Cenre_MusicalCompositions\" " +
            "WHERE \"genre_id\" = ?";
    
    private static String selectByMusicalCompositionIdQuery = "SELECT \"id\", \"genre_id\", \"musicalCompositions_id\" " +
            "FROM \"Cenre_MusicalCompositions\" " +
            "WHERE \"musicalCompositions_id\" = ?";

    private static String insertQuery = "INSERT INTO \"Cenre_MusicalCompositions\"(\"genre_id\", \"musicalCompositions_id\") " +
            "VALUES (?, ?) " +
            "RETURNING \"id\", \"genre_id\", \"musicalCompositions_id\"";

    private static String updateQuery = "UPDATE \"Cenre_MusicalCompositions\" " +
            "SET \"genre_id\" = ?, \"musicalCompositions_id\" = ? " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"genre_id\", \"musicalCompositions_id\"";

    private static String deleteQuery = "DELETE FROM \"Cenre_MusicalCompositions\" " +
            "WHERE \"id\" = ? " +
            "RETURNING \"id\", \"genre_id\", \"musicalCompositions_id\"";

    public Genre_MusicalCompositionRepository(JdbcOperations JdbcOperations)
    {
        this.jdbcOperations = JdbcOperations;
    }

    @Override
    public Genre_MusicalComposition[] select()
    {
        ArrayList<Genre_MusicalComposition> values = new ArrayList<Genre_MusicalComposition>();
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectQuery);
        while(rowSet.next())
        {
            values.add(new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)));
        }
        Genre_MusicalComposition[] result = new Genre_MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Genre_MusicalComposition select(Integer id)
    {
        Object[] params = new Object[] {id};
        int[] types = new int[] {Types.INTEGER};
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByIdQuery, params, types);
        if(!rowSet.next())
        {
            return null;
        }
        return new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
        );
    }
    
    public Genre_MusicalComposition[] selectByGenreId(Integer genreId)
    {
        ArrayList<Genre_MusicalComposition> values = new ArrayList<Genre_MusicalComposition>();
        Object[] params = new Object[] { genreId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByGenreIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3))
            );
        }
        Genre_MusicalComposition[] result = new Genre_MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }
    
    public Genre_MusicalComposition[] selectByMusicalCompositionId(Integer musicalCompositionId)
    {
        ArrayList<Genre_MusicalComposition> values = new ArrayList<Genre_MusicalComposition>();
        Object[] params = new Object[] { musicalCompositionId };
        int[] types = new int[] { Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(selectByMusicalCompositionIdQuery, params, types);
        while (rowSet.next()) {
            values.add(new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3))
            );
        }
        Genre_MusicalComposition[] result = new Genre_MusicalComposition[values.size()];
        result = values.toArray(result);
        return result;
    }

    @Override
    public Genre_MusicalComposition insert(Genre_MusicalComposition entity)
    {
        Object[] params = new Object[] { entity.getGenreId(), entity.getMusicalCompositionId() };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(insertQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
        );
    }

    @Override
    public Genre_MusicalComposition update(Integer id, Genre_MusicalComposition entity)
    {
        Object[] params = new Object[] { entity.getGenreId(), entity.getMusicalCompositionId(), id };
        int[] types = new int[] { Types.INTEGER, Types.INTEGER, Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(updateQuery, params, types);
        if (!rowSet.next())
        {
            return null;
        }
        return new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
        );
    }

    @Override
    public Genre_MusicalComposition delete(Integer id)
    {
        Object[] params = new Object[] { id };
        int[] types = new int[] {Types.INTEGER };
        SqlRowSet rowSet = jdbcOperations.queryForRowSet(deleteQuery, params, types);
        if (!rowSet.next()) {
            return null;
        }
        return new Genre_MusicalComposition(
                    rowSet.getInt(1),
                    rowSet.getInt(2),
                    rowSet.getInt(3)
        );
    }
}

