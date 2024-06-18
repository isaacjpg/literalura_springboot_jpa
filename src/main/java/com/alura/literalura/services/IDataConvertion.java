package com.alura.literalura.services;

public interface IDataConvertion {
    <T> T converData(String json, Class<T> clase);
}
