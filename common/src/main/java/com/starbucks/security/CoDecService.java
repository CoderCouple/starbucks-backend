package com.starbucks.security;

import org.mindrot.jbcrypt.BCrypt;

public class CoDecService {

    public static String encryptPassword(final String plainPwd) {
        return BCrypt.hashpw(plainPwd, BCrypt.gensalt());
    }

    public static boolean checkPassword(final String plainPwd, final String encryptedPwd) {
        return BCrypt.checkpw(plainPwd, encryptedPwd);

    }
}
