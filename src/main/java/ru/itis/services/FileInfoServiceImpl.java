package ru.itis.services;

import lombok.SneakyThrows;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Component
public class FileInfoServiceImpl implements FileInfoService {

    @SneakyThrows
    @Override
    public boolean save(MultipartFile multipartFile) {

        byte[] bytes = multipartFile.getBytes();

        String name = multipartFile.getOriginalFilename();

        String rootPath = "/Users/heel/IdeaProjects/2Course/4sem-spring/MyProjects/spring-concert-announcements-app/src/main/resources/static/images";  //try also "C:\path\"
        File dir = new File(rootPath + File.separator + "loadFiles");

        if (!dir.exists()) {
            dir.mkdirs();
        }

        File uploadedFile = new File(dir.getAbsolutePath() + File.separator + name);

        BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(uploadedFile));
        stream.write(bytes);
        stream.flush();
        stream.close();

        return true;
    }
}
