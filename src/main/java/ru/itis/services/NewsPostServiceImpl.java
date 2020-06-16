package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.models.NewsPost;
import ru.itis.repositories.NewsPostRepository;
import ru.itis.repositories.PhotoRepository;

import java.util.List;

@Service
public class NewsPostServiceImpl implements NewsPostService {

    @Autowired
    private NewsPostRepository newsPostRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @Override
    public List<NewsPost> getNews() {
        return newsPostRepository.findAll();
    }

    @Override
    public List<NewsPost> findById(Long id) {
        return newsPostRepository.findAllById(id);
    }

//    @Override
//    public List<NewsPostDto> recommend(User user) {
//        Map<User, Long> similarityMap = new HashMap<>();
//
//        List<NewsPost> userMostLikedNews = newsPostRepository.findAllByLikesContains(user);
//
//        for (NewsPost newsPost : userMostLikedNews) {
//            for (User mostLikedPostByUser : newsPost.getLikes()) {
//                if (mostLikedPostByUser.equals(user))
//                    continue;
//                Long prevValue = similarityMap.getOrDefault(mostLikedPostByUser, 0L);
//                similarityMap.put(mostLikedPostByUser, prevValue + 1);
//            }
//        }
//
//        List<User> users = similarityMap.entrySet().stream()
//                .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
//                .map(Map.Entry::getKey)
//                .collect(Collectors.toList());
//
//        List<NewsPost> recommendations = new ArrayList<>();
//
//        for (User similarUser : users) {
//            List<NewsPost> suppose = newsPostRepository.findAllByLikesContains(similarUser);
//            for (NewsPost newsPost : suppose) {
//                if (!userMostLikedNews.contains(newsPost) && !recommendations.contains(newsPost))
//                    recommendations.add(newsPost);
//            }
//        }
//        return NewsPostDto.from(recommendations);
//    }

    @Override
    public boolean add(String title, String description, String filePath) {
        NewsPost newsPost = NewsPost.builder()
                .headLine(title)
                .description(description)
                .photo(photoRepository.findByPath(filePath))
                .build();
        newsPostRepository.save(newsPost);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        newsPostRepository.delete(id);
        return true;
    }


}
