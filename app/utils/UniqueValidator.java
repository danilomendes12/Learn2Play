package utils;

import models.User;
import play.data.validation.Constraints;
import play.libs.F;

/**
 * Created by danilomendes on 11/7/14.
 */
public class UniqueValidator extends Constraints.Validator<String>{
    @Override
    public boolean isValid(String s) {
        return User.find.where().eq("nickname", s).findRowCount() == 0;
    }

    @Override
    public F.Tuple<String, Object[]> getErrorMessageKey() {
        return new F.Tuple<String, Object[]>("error.invalid", new Object[] {});
    }
}
