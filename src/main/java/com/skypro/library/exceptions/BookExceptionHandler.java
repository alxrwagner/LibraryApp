package com.skypro.library.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
// указывает на то что будет проверять все действия в контроллере и перехватывать и обрабатывать исключения
public class BookExceptionHandler {

    @ExceptionHandler
    // Если ловим ошибку указанную в круглых скобках, то помещаем в ответ сообщение класса, указанного в даймондах
    public ResponseEntity<MessageException> handleException(BookException exception) {// <класс, сообщение которого будем возвращать>, (исключение, которое хотим перехватывать)
        MessageException message = new MessageException();
        message.setMessage(exception.getMessage());

        return new ResponseEntity<>(message, HttpStatus.NOT_FOUND); // Возвращаем новый ResponseEntity на основе сообщения и статуса выполнения
    }
}
