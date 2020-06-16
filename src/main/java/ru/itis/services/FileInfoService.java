package ru.itis.services;

import org.springframework.web.multipart.MultipartFile;

public interface FileInfoService {

    boolean save(MultipartFile multipartFile);

}
