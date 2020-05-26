package interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import java.io.Serializable;

@Interceptor
@ExceptionCaughtInvocation
public class ErrorDisplayer implements Serializable {

    @AroundInvoke
    public Object catchException(InvocationContext invocationContext) {
        try {
            return invocationContext.proceed();
        } catch (Exception exception) {
            String errorMessage = "Exception occured during handling of database operation: " + exception.getMessage();
            System.out.println(errorMessage);
            return errorMessage;
        }
    }

}