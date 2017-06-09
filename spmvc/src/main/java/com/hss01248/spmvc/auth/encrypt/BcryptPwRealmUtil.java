package com.hss01248.spmvc.auth.encrypt;

import org.apache.shiro.authc.credential.PasswordMatcher;
import org.apache.shiro.realm.jdbc.JdbcRealm;

/**
 * Created by Administrator on 2017/6/9 0009.
 */
public class BcryptPwRealmUtil {

    public JdbcRealm jdbcRealm() {
      final JdbcRealm jdbcRealm = new JdbcRealm();
      //jdbcRealm.setDataSource(dataSource);
      // jdbcRealm.setAuthenticationQuery(Person2.SHIRO_AUTHENTICATION_QUERY);
     final PasswordMatcher passwordMatcher = new PasswordMatcher();
     passwordMatcher.setPasswordService(new BCryptPasswordService());
     jdbcRealm.setCredentialsMatcher(passwordMatcher);
      return jdbcRealm;
 }

}
