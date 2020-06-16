//package ru.itis.forms;
//
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//import ru.itis.models.Comment;
//import ru.itis.models.HeadQuarter;
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
//public class HeadQuarterDto {
//    private Long id;
//
//    private String city;
//
//    private String description;
//
//    private String line_up;
//
//    private List<CommentDto> comments;
//
//    private int commentsCount;
//
//    public static HeadQuarterDto from(HeadQuarter headQuarter) {
//        List<Comment> comments = headQuarter.getComments() == null ? new ArrayList<>() : headQuarter.getComments();
//
//        return HeadQuarterDto.builder()
//                .id(headQuarter.getId())
//                .description(headQuarter.getDescription())
//                .city(headQuarter.getCity())
//                .line_up(headQuarter.getLine_up())
//                .comments(CommentDto.from(comments))
//                .commentsCount(comments.size())
//                .build();
//    }
//
//    public static List<HeadQuarterDto> from(List<HeadQuarter> headQuarter) {
//        return headQuarter.stream().map(HeadQuarterDto::from).collect(Collectors.toList());
//    }
//}
