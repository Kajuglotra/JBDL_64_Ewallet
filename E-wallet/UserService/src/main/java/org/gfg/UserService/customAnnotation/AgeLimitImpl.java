package org.gfg.UserService.customAnnotation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AgeLimitImpl implements ConstraintValidator<AgeLimit, String> {

    private int minimumAge;

    @Override
    public void initialize(AgeLimit constraintAnnotation) {
        this.minimumAge = constraintAnnotation.minimumAge();
        ConstraintValidator.super.initialize(constraintAnnotation);
    }

    @Override
    public boolean isValid(String DateStr, ConstraintValidatorContext constraintValidatorContext) {

        if(StringUtils.isEmpty(DateStr)){
            return false;
        }
        try {
            int pYear =new SimpleDateFormat("dd/MM/yyyy").parse(DateStr).getYear();
            int tYear =new Date().getYear();
            int diff =tYear-pYear;
            if(diff >= minimumAge){
                return  true;
            }
        } catch (ParseException e) {
           return false;
        }

        return false;
    }
}
