package by.epamtc.library.util;

import org.mindrot.jbcrypt.BCrypt;

public final class Encryptor {
    private Encryptor(){}

    public static String encrypt(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String password, String hashedValue) {
        return BCrypt.checkpw(password, hashedValue);
    }
}
