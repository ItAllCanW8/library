package by.epamtc.library.model.dao.impl;

import by.epamtc.library.controller.attribute.RequestParameter;
import by.epamtc.library.exception.ConnectionPoolException;
import by.epamtc.library.exception.DaoException;
import by.epamtc.library.model.connection.ConnectionPool;
import by.epamtc.library.model.dao.LibCoefficientsDao;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class LibCoefficientsDaoImpl implements LibCoefficientsDao {
    private static final ConnectionPool pool = ConnectionPool.getInstance();

    private LibCoefficientsDaoImpl() {
    }

    private static class Holder {
        static final LibCoefficientsDao INSTANCE = new LibCoefficientsDaoImpl();
    }

    public static LibCoefficientsDao getInstance() {
        return LibCoefficientsDaoImpl.Holder.INSTANCE;
    }

    @Override
    public boolean updateCoefficients(int bookIssuingDaysNum, String readingRoomOpening, String readingRoomClosing)
            throws DaoException {
        try(Connection connection = pool.takeConnection();
            PreparedStatement statement = connection.prepareStatement(SqlQuery.UPDATE_LIB_COEFFICIENTS)) {
            System.out.println("dao");
            statement.setString(1, String.valueOf(bookIssuingDaysNum));
            statement.setString(2, readingRoomOpening);
            statement.setString(3, readingRoomClosing);

            statement.execute();
            return statement.getUpdateCount() == 1;
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error updating library coefficients.", e);
        }
    }

    @Override
    public Map<String, String> loadCoefficients() throws DaoException {
        try(Connection connection = pool.takeConnection();
            Statement statement = connection.createStatement()) {
            System.out.println(statement);
            ResultSet resultSet = statement.executeQuery(SqlQuery.SELECT_LIB_COEFFICIENTS);
            System.out.println("after exec");

            while (resultSet.next())
                System.out.println("NEXT");

            System.out.println(resultSet.getObject(1));

            return createMapFromResultSet(resultSet);
        } catch (SQLException | ConnectionPoolException e) {
            throw new DaoException("Error loading library coefficients.", e);
        }
    }

    private Map<String, String> createMapFromResultSet(ResultSet resultSet) throws SQLException {
        System.out.println("create");
//        int bookIssuingDaysNum = resultSet.getInt("issuing_days_number");
//        System.out.println(bookIssuingDaysNum);
        String readingRoomOpening = resultSet.getString("reading_room_opening");
        System.out.println(readingRoomOpening);
        String readingRoomClosing = resultSet.getString("reading_room_closing");
        System.out.println(readingRoomClosing);

        Map<String, String> coefs = new LinkedHashMap<>();
//        coefs.put(RequestParameter.BOOK_ISSUING_DAYS_NUM, String.valueOf(bookIssuingDaysNum));
        coefs.put(RequestParameter.READING_ROOM_OPENING, readingRoomOpening);
        coefs.put(RequestParameter.READING_ROOM_CLOSING, readingRoomClosing);

        System.out.println(coefs);

        return coefs;
    }
}
