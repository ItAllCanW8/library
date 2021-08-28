package by.epamtc.library.util;

import org.mindrot.jbcrypt.BCrypt;

/**
 * Class used to encrypt and check already encrypted passwords.
 *
 * @author Artur Mironchik
 */
public final class Encryptor {
    private Encryptor(){}

    /**
     * Encrypt string.
     *
     * @param password the password
     * @return the string
     */
    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Check boolean.
     *
     * @param password    the password
     * @param hashedValue the hashed value
     * @return the boolean
     */
    public static boolean check(String password, String hashedValue) {
        return BCrypt.checkpw(password, hashedValue);
    }
}
