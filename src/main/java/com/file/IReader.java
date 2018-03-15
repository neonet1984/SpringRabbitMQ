package com.file;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IReader<T> {
    List<T> read();
    void setPath(String path);
}
