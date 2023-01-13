package t3h.vn.backend_2208.validation;

import t3h.vn.backend_2208.db.UserUtils;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.SQLException;

public class UniqueEmailValidator implements ConstraintValidator<UniqueEmailValidation, String> {
    @Override
    public boolean isValid(String email, ConstraintValidatorContext context) {
        try {
            return !UserUtils.checkExistByEmail(email);
        } catch (SQLException e) {
            return false;
        }
    }
}
