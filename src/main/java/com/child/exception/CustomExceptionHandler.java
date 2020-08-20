package com.child.exception;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomExceptionHandler {
    private ModelAndView getModelAndView(Throwable e, ModelAndView view) {
        List<String> details = new ArrayList<>();
        details.add(e.getLocalizedMessage());
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setDeatails(details);
        view.addObject("exception",errorResponse);
        return view;
    }
    //404
    @ExceptionHandler({EntityNotFoundException.class, NoHandlerFoundException.class})
    public final ModelAndView handleEntityNotFound(EntityNotFoundException e){
        ModelAndView view = new ModelAndView("error/404");
        return getModelAndView(e, view);
    }

    //All 403
    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAuthorization(AccessDeniedException e){
        ModelAndView view = new ModelAndView("error/403");
        return getModelAndView(e, view);
    }

    //All Error
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleException(Throwable e){
        ModelAndView view = new ModelAndView("error/500");
        return getModelAndView(e, view);
    }
}
