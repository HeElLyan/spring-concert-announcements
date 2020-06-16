//package ru.itis.forms;
//
//import lombok.*;
//import ru.itis.models.Comment;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//@Data
//@Builder
//@AllArgsConstructor
//@NoArgsConstructor
//public class CommentDto {
//    private Long id;
//
//    private String description;
//
//    private UserDto user;
//
//    private HeadQuarterDto headQuarter;
//
//    public static CommentDto from(Comment comment) {
//        return CommentDto.builder()
//                .id(comment.getId())
//                .description(comment.getDescription())
//                .user(UserDto.from(comment.getUser()))
//                .headQuarter(HeadQuarterDto.from(comment.getHq()))
//                .build();
//    }
//
//    public static List<CommentDto> from(List<Comment> comments) {
//        return comments.stream().map(CommentDto::from).collect(Collectors.toList());
//    }
//}
