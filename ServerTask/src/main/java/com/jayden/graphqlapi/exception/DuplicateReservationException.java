package com.jayden.graphqlapi.exception;

import graphql.ErrorType;
import graphql.GraphQLError;
import graphql.language.SourceLocation;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DuplicateReservationException extends RuntimeException implements GraphQLError {

    private Map<String, Object> extensions = new HashMap<>();
    private static final String MESSAGE = "The meeting room is already booked at the time.";

    public DuplicateReservationException() {
        super(MESSAGE);
    }

    @Override
    public List<SourceLocation> getLocations() {
        return null;
    }

    @Override
    public Map<String, Object> getExtensions() {
        return extensions;
    }

    @Override
    public ErrorType getErrorType() {
        return ErrorType.DataFetchingException;
    }
}