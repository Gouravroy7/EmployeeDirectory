package comgv.dao;

import comgv.entity.Login;

public interface LoginDAO {
 String authenticate(Login login);
}
