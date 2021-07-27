package by.epamtc.library.model.dao.impl;

public class SqlQuery {
    public static final String SELECT_EMAIL = "SELECT email FROM users WHERE email = ?;";

    public static final String SELECT_PHONE_NUM = "SELECT phone_number FROM user_details WHERE phone_number = ?;";

    public static final String INSERT_USER = "INSERT INTO users(username, email, password, status, details_id_fk," +
            " role_id_fk) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String INSERT_USER_DETAILS = "INSERT INTO user_details(name, surname, date_of_birth," +
            " phone_number, photo_path) VALUES (?,?,?,?,?);";

    public static final String FIND_ROLE_ID_BY_NAME = "SELECT user_role_id FROM user_roles WHERE role = ?;";

    public static final String FIND_USER_DETAILS_ID_BY_PHONE = "SELECT details_id FROM user_details WHERE" +
            " phone_number = ?;";

    public static final String FIND_USER_BY_EMAIL = "SELECT * FROM users JOIN user_details ON details_id =" +
            " details_id_fk JOIN user_roles ON role_id = role_id_fk WHERE email = ?;";
    public static final String FIND_USER_DETAILS_BY_ID = "SELECT details_id FROM user_details WHERE email = ?;";

    public static final String SELECT_PASSWORD = "SELECT password FROM users WHERE email = ?;";

    public static final String SELECT_POPULAR_BOOKS = "SELECT * FROM books ORDER BY available_quantity ASC LIMIT 6;";
    public static final String SELECT_BOOKS = "SELECT * FROM books;";
}
