package com.hss01248.spmvc.auth.encrypt;

import org.apache.shiro.authc.credential.PasswordService;
import org.mindrot.jbcrypt.BCrypt;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class BCryptPasswordService implements PasswordService {
    @Override
    public String encryptPassword(Object plaintextPassword) throws IllegalArgumentException {
        final String str;
        if (plaintextPassword instanceof char[]) {
            str = new String((char[]) plaintextPassword);
        } else if (plaintextPassword instanceof String) {
            str = (String) plaintextPassword;
        } else {
            throw new SecurityException("Unsupported password type: " + plaintextPassword.getClass().getName());
        }
        return BCrypt.hashpw(str, BCrypt.gensalt());
    }

    @Override
    public boolean passwordsMatch(Object submittedPlaintext, String encrypted) {
        return BCrypt.checkpw(new String((char[]) submittedPlaintext), encrypted);
    }
}
