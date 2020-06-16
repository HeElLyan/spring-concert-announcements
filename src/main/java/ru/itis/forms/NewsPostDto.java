//package ru.itis.forms;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.itis.models.NewsPost;
//import ru.itis.models.User;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@Builder
//@NoArgsConstructor
//@AllArgsConstructor
//public class NewsPostDto {
//    private Long id;
//
//    private String headLine;
//    private String description;
//
//    private String type;
//
//    private List<UserDto> likes;
//
//    private int likeCount;
//
//    public static NewsPostDto from(NewsPost post) {
//        List<User> likes = post.getLikes() == null ? new ArrayList<>() : post.getLikes();
//
//        return NewsPostDto.builder()
//                .id(post.getId())
//                .likes(UserDto.from(likes))
//                .likeCount(likes.size())
//                .description(post.getDescription())
//                .headLine(post.getHeadLine())
//                .build();
//    }
//
//    public static List<NewsPostDto> from(List<NewsPost> posts) {
//        return posts.stream().map(NewsPostDto::from).collect(Collectors.toList());
//    }
//}
