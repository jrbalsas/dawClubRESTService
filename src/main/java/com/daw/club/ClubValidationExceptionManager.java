package com.daw.club;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

/** Capture Bean Validation exceptions and generate JSON response with 
 *  an array of error messages, e.g [{"name":"propname","error":"error text"},..]
 *
 * @author jrbalsas
 */
@Provider
public class ClubValidationExceptionManager implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        //convert bean-validation contstraintviolation set to json object

        List<Object> errors = new ArrayList<>();

        for (ConstraintViolation<?> cv : e.getConstraintViolations()) {
            
            //attribute name is the last part, e.g. method.arg0.propname
            String[] parts=cv.getPropertyPath().toString().split("\\.");            
            
            Object m = new Object () {
                public String name = parts[parts.length-1];
                public String error = cv.getMessage();
            };
            errors.add(m);
        };
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(errors)
                .build();
    }

}
