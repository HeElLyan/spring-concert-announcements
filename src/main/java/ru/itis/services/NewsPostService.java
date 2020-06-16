package ru.itis.services;

import ru.itis.models.NewsPost;

import java.util.List;

public interface NewsPostService {
    List<NewsPost> getNews();
    List<NewsPost> findById(Long id);
//    List<NewsPostDto> recommend(User user);

    boolean add(String title, String description, String filePath);
    boolean delete(Long id);
}
