package by.epamtc.library.model.dao.impl;

public class SqlQuery {
    public static final String SELECT_EMAIL = "SELECT email FROM users WHERE email = ?;";

    public static final String SELECT_PHONE_NUM = "SELECT phone_number FROM user_details WHERE phone_number = ?;";

    public static final String INSERT_USER = "INSERT INTO users(username, email, password, status, details_id_fk," +
            " role_id_fk) VALUES (?, ?, ?, ?, ?, ?);";

    public static final String INSERT_USER_DETAILS = "INSERT INTO user_details(name, surname, date_of_birth," +
            " phone_number, photo_path) VALUES (?,?,?,?,?);";

    public static final String FIND_ROLE_ID_BY_NAME = "SELECT role_id FROM user_roles WHERE role = ?;";

    public static final String FIND_USER_DETAILS_ID_BY_PHONE = "SELECT details_id FROM user_details WHERE" +
            " phone_number = ?;";

    public static final String FIND_USER_BY_EMAIL = "SELECT user_id,username,email,status,name,surname,date_of_birth," +
            "phone_number,photo_path,role,details_id_fk FROM users JOIN user_details ON details_id = details_id_fk " +
            "JOIN user_roles ON role_id = role_id_fk WHERE email = ?;";

    public static final String FIND_USER_BY_ID = "SELECT user_id,username,email,status,name,surname,date_of_birth," +
            "phone_number,photo_path,role,details_id_fk FROM users JOIN user_details ON details_id = details_id_fk JOIN" +
            " user_roles ON role_id = role_id_fk WHERE user_id = ?;";

    public static final String SELECT_PASSWORD = "SELECT password FROM users WHERE email = ?;";

    public static final String FIND_EMAIL_BY_ID = "SELECT email FROM users WHERE user_id = ?;";

    public static final String SELECT_POPULAR_BOOKS = "SELECT book_id,title,img FROM books ORDER BY available_quantity " +
            "ASC LIMIT 6;";

    public static final String SELECT_BOOKS = "SELECT book_id,title,img,author_pseudo,genre,isbn,available_quantity," +
            "author_img FROM books ORDER BY available_quantity DESC;";

    public static final String SORT_BOOKS = "SELECT book_id,title,img,author_pseudo,genre,isbn,available_quantity," +
            "author_img FROM books ORDER BY ";

    public static final String FIND_BOOK_BY_ID = "SELECT * FROM books WHERE book_id = ?;";

    public static final String FIND_BOOKS_BY_KEYWORD = "SELECT book_id,title,img,author_pseudo,genre,isbn," +
            "available_quantity,author_img FROM books WHERE title LIKE ? OR author_pseudo LIKE ? " +
            "OR isbn LIKE ? OR genre LIKE ? OR available_quantity LIKE ?;";

    public static final String FIND_BOOKS_BY_GENRE = "SELECT book_id,title,img,author_pseudo,genre,isbn," +
            "available_quantity,author_img FROM books WHERE genre LIKE ?;";

    public static final String FIND_BOOKS_BY_AUTHOR = "SELECT book_id,title,img,author_pseudo,genre,isbn," +
            "available_quantity,author_img FROM books WHERE author_pseudo LIKE ?;";

    public static final String FIND_BOOK_COVER_BY_ID = "SELECT img FROM books WHERE book_id = ?;";

    public static final String FIND_BOOK_PDF_BY_ID = "SELECT pdf FROM books WHERE book_id = ?;";

    public static final String FIND_BOOK_QUANTITY_BY_ID = "SELECT available_quantity FROM books WHERE book_id = ?;";

    public static final String UPDATE_USER_PHOTO = "UPDATE user_details SET photo_path = ? WHERE details_id = ?";

    public static final String UPDATE_USER = "UPDATE users SET username = ?, email = ? WHERE user_id = ?;";

    public static final String UPDATE_USER_DETAILS= "UPDATE user_details SET name = ?, surname = ?, date_of_birth = ?," +
            " phone_number = ? WHERE details_id = ?;";

    public static final String UPDATE_PASSWORD= "UPDATE users SET password = ? WHERE user_id = ?;";
    public static final String UPDATE_USER_STATUS = "UPDATE users SET status = ? WHERE user_id = ?;";
    public static final String UPDATE_USER_ROLE = "UPDATE users SET role_id_fk = ? WHERE user_id = ?;";
    public static final String SELECT_ALL_USERS = "SELECT user_id,username,email,status,name,surname,date_of_birth," +
            "phone_number,photo_path,role,details_id_fk FROM users JOIN user_details ON details_id = details_id_fk " +
            "JOIN user_roles ON role_id = role_id_fk;";

    public static final String FIND_USERS_BY_ROLE = "SELECT user_id,username,email,status,name,surname,date_of_birth," +
            "phone_number,photo_path,role,details_id_fk FROM users JOIN user_details ON details_id = details_id_fk " +
            "JOIN user_roles ON role_id = role_id_fk WHERE role = ?;";

    public static final String FIND_USERS_BY_STATUS = "SELECT user_id,username,email,status,name,surname,date_of_birth," +
            "phone_number,photo_path,role,details_id_fk FROM users JOIN user_details ON details_id = details_id_fk " +
            "JOIN user_roles ON role_id = role_id_fk WHERE status = ?;";

    public static final String CHECK_BOOK_FOR_EXISTENCE = "SELECT title FROM books WHERE title = ?;";
    public static final String INSERT_BOOK = "INSERT INTO books(title, author_pseudo, isbn, available_quantity," +
            " genre, short_description, pdf, img, author_img) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";
    public static final String UPDATE_BOOK = "UPDATE books SET title = ?, author_pseudo = ?, isbn = ?," +
            " available_quantity = ?, genre = ?, short_description = ? WHERE book_id = ?;";
    public static final String DELETE_BOOK = "DELETE FROM books WHERE book_id = ?;";
    public static final String UPDATE_BOOK_COVER = "UPDATE books SET img = ? WHERE book_id = ?";
    public static final String UPDATE_AUTHOR_PHOTO = "UPDATE books SET author_img = ? WHERE book_id = ?";
    public static final String UPDATE_BOOK_PDF = "UPDATE books SET pdf = ? WHERE book_id = ?";

    public static final String CHECK_BOOK_REQUEST_FOR_EXISTENCE = "SELECT request_id FROM book_requests WHERE" +
            " book_id_fk = ? AND user_id_fk = ? AND (state ='approved' OR state = 'left');";

    public static final String INSERT_BOOK_REQUEST = "INSERT INTO book_requests(request_type, state, request_date," +
            " book_id_fk, user_id_fk) VALUES (?, ?, ?, ?, ?);";

    public static final String UPDATE_BOOK_QUANTITY = "UPDATE books SET available_quantity = ? WHERE book_id = ?;";

    public static final String SELECT_BOOK_REQUESTS = "SELECT request_id, request_type,state,request_date," +
            "expected_return_date,closing_date,penalty_amount, book_id_fk, user_id_fk,title,img,pdf,username,photo_path" +
            " FROM book_requests JOIN users ON user_id = user_id_fk JOIN user_details ON details_id = details_id_fk " +
            "JOIN books ON book_id = book_id_fk;";

    public static final String SELECT_BOOK_REQUESTS_BY_READER_ID = "SELECT request_id,request_type,state,request_date," +
            "expected_return_date,closing_date,penalty_amount,book_id_fk,title,img,pdf,available_quantity " +
            "FROM book_requests JOIN books ON book_id = book_id_fk WHERE user_id_fk = ?;";

    public static final String SORT_BOOK_REQUESTS = "SELECT request_id, request_type,state,request_date,expected_return_date," +
            "closing_date,penalty_amount, book_id_fk, user_id_fk,title,img,pdf,username,photo_path FROM book_requests " +
            "JOIN users ON user_id = user_id_fk JOIN user_details ON details_id = details_id_fk JOIN books ON " +
            "book_id = book_id_fk ORDER BY ";

    public static final String FIND_BOOK_REQUESTS_BY_TYPE = "SELECT request_id, request_type,state,request_date,expected_return_date," +
            "closing_date, penalty_amount, book_id_fk, user_id_fk,title,img,pdf,username,photo_path FROM book_requests " +
            "JOIN users ON user_id = user_id_fk JOIN user_details ON details_id = details_id_fk JOIN books ON" +
            " book_id = book_id_fk WHERE request_type LIKE ?;";

    public static final String FIND_BOOK_REQUESTS_BY_STATE = "SELECT request_id, request_type,state,request_date,expected_return_date," +
            "closing_date, penalty_amount, book_id_fk, user_id_fk,title,img,pdf,username,photo_path FROM book_requests " +
            "JOIN users ON user_id = user_id_fk JOIN user_details ON details_id = details_id_fk JOIN books ON" +
            " book_id = book_id_fk WHERE state LIKE ?;";

    public static final String FIND_EMAIL_BY_REQUEST_ID = "SELECT email FROM book_requests JOIN users ON book_requests.user_id_fk" +
            " = users.user_id WHERE request_id = ?;";

    public static final String LOAD_READING_ROOM_BY_READER_ID = "SELECT title,img,pdf FROM book_requests JOIN books ON " +
            "book_id = book_id_fk WHERE user_id_fk = ? AND request_type='to_reading_room' AND state ='approved';";

    public static final String UPDATE_BOOK_REQUEST_STATE = "UPDATE book_requests SET state = ? WHERE request_id = ?;";
    public static final String UPDATE_BOOK_REQUEST_STATE_TO_APPROVED = "UPDATE book_requests SET state = ?," +
            "expected_return_date = ?  WHERE request_id = ?;";

    public static final String CLOSE_BOOK_REQUEST = "UPDATE book_requests SET state = 'closed', closing_date = ?" +
            " WHERE request_id = ?;";

    public static final String DELETE_BOOK_REQUEST = "DELETE FROM book_requests WHERE request_id = ?;";

    public static final String UPDATE_LIB_COEFFICIENTS = "UPDATE coefficients SET issuing_days_number = ?," +
            " reading_room_opening = ?, reading_room_closing = ?";
    public static final String SELECT_LIB_COEFFICIENTS = "SELECT * FROM coefficients;";

    public static final String CHECK_USER_REPORT_FOR_EXISTENCE = "SELECT report_id FROM user_reports WHERE" +
            " is_processed = '1' AND subject = ? AND message = ? AND user_id_fk = ?;";

    public static final String INSERT_USER_REPORT = "INSERT INTO user_reports(is_processed, subject, message," +
            " creation_date, user_id_fk) VALUES (?, ?, ?, ?, ?);";

    public static final String SELECT_USER_REPORTS = "SELECT report_id, is_processed, subject, creation_date,user_id_fk, email, " +
            "username, role FROM user_reports JOIN users ON user_id_fk = user_id JOIN user_roles ON role_id_fk = role_id;";

    public static final String FIND_USER_REPORT_BY_ID = "SELECT report_id, is_processed, subject, message, response," +
            " creation_date, user_id_fk, email, username, role FROM user_reports JOIN users ON user_id_fk = user_id " +
            "JOIN user_roles ON role_id_fk = role_id WHERE report_id = ?;";

    public static final String UPDATE_USER_REPORT_RESPONSE = "UPDATE user_reports SET response = ?, is_processed = 1" +
            " WHERE report_id = ?;";

    public static final String SORT_USER_REPORTS = "SELECT report_id, is_processed, subject, creation_date,user_id_fk," +
            " email, username, role FROM user_reports JOIN users ON user_id_fk = user_id JOIN user_roles ON " +
            "role_id_fk = role_id ORDER BY ";

    public static final String FIND_REPORTS_BY_AVAILABILITY = "SELECT report_id, is_processed, subject, creation_date,user_id_fk, email, " +
            "username, role FROM user_reports JOIN users ON user_id_fk = user_id JOIN user_roles ON role_id_fk = role_id " +
            "WHERE is_processed = ?;";

    public static final String LOAD_NUMBER_OF_DAYS_COEFF = "SELECT coefficient_value FROM coefficients WHERE " +
            "coefficient_name = 'number_of_days'";
}
