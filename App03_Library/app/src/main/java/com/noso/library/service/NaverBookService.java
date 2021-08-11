package com.noso.library.service;

import com.noso.library.model.NaverBookDTO;

public interface NaverBookService {
    public NaverBookDTO getBooks(String search);
}
