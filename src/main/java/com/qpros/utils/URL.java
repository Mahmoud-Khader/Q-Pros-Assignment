package com.qpros.utils;

public class URL {
    public static final String HOST = "https://petstore.swagger.io/v2";
    public static final String PET = "/pet/";
    public static final String PET_URL = HOST + PET;
    public static final String FIND_PET_BY_STATUS = PET_URL+"findByStatus?status=";

    public enum STATUS {
        available,
        pending,
        sold
    }
}
