package com.joo.abysshop.util.exception;

import com.joo.abysshop.util.constants.Messages;
import com.joo.abysshop.util.constants.RedirectMappings;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UnauthorizedAccessException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public RedirectView handleUnauthorizedAccessException(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute(Messages.FAILURE_MESSAGE, "권한이 없습니다.");
        return new RedirectView(RedirectMappings.REDIRECT_INDEX);
    }
}
