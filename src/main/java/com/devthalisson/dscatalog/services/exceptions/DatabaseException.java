package com.devthalisson.dscatalog.services.exceptions;

import org.springframework.dao.DataIntegrityViolationException;

public class DatabaseException extends RuntimeException {

    public DatabaseException(String msg) { super(msg); }
}
