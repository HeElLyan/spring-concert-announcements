package ru.itis.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.itis.forms.ConcertForm;
import ru.itis.models.Comment;
import ru.itis.models.HeadQuarter;
import ru.itis.models.User;
import ru.itis.repositories.CommentRepository;
import ru.itis.repositories.HQRepository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HQServiceImpl implements HQService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private HQRepository hqRepository;

    @Override
    public HeadQuarter findByCity(String city) {
        return hqRepository.findByCity(city);
    }

    @Override
    public HeadQuarter findById(Long id) {
        return hqRepository.findById(id);
    }

    @Override
    public List<HeadQuarter> findAllByCity(String city) {
        return hqRepository.findAllByCity(city);
    }

    @Override
    public List<HeadQuarter> findAll() {
        return hqRepository.findAll();
    }

//    public List<HeadQuarter> findAllRecommended() {
//        return sortByComment(hqRepository.findAll());
//    }
//
//    private List<HeadQuarter> sortByComment(List<HeadQuarter> headQuarterList) {
//        Map<Comment, Long> similarityMap = new HashMap<>();
//
//        List<HeadQuarter> headQuarterList2 = new ArrayList<>();
////        int commentsCountMax = 1000;
////        int commentsCount = commentRepository.findAllByHq(headQuarter).size();
//
//        for (HeadQuarter headQuarter: headQuarterList) {
//            for (Comment comment: commentRepository.findAllByHq(headQuarter)){
//                if(!similarityMap.containsKey(comment)){
//                    similarityMap.put(comment, 1L);
//                } else {
//                    similarityMap.replace(comment, similarityMap.get(comment) + 1);
//                }
//            }
//        }
////        List<HeadQuarter> headQuarterList3 = similarityMap.entrySet().stream()
////                .sorted((commentRepository.findAllByHq(headQuarterList.get(1)).size(), commentRepository.findAllByHq(headQuarterList.get(2)).size())-> o2.getValue())
//
//        return null;
//    }

    @Override
    public List<HeadQuarter> recommend(User user) {
        if(!commentRepository.findAllByUser(user).isEmpty()) {
            Map<HeadQuarter, Integer> similarityMap = new HashMap<>();

            List<HeadQuarter> userCommentedHq = new ArrayList<>();
            //получили все прокомментрированные юзером концерты
            for (Comment comment : commentRepository.findAllByUser(user)) {
//                if (userCommentedHq.isEmpty()) {
//                    userCommentedHq.add(comment.getHq());
//                } else {
                    if (!userCommentedHq.contains(comment.getHq())){
                        userCommentedHq.add(comment.getHq());
                    }
//                    for (int i = 0; i < userCommentedHq.size(); i++) {
//                        if (comment.getHq().equals(userCommentedHq.get(i))) {
//                            userCommentedHq.add(comment.getHq());
//                        }
//                    }
//                    for (HeadQuarter headQuarter : userCommentedHq) {
//                        if (comment.getHq() != headQuarter) {
//                            userCommentedHq.add(comment.getHq());
//                        }
//                    }

            }

            System.out.println("################commented" + userCommentedHq);

            List<HeadQuarter> allHq = hqRepository.findAll();

            List<HeadQuarter> userNotCommentedHq = hqRepository.findAll();
            for (HeadQuarter headQuarter1: allHq) {
                for (HeadQuarter headQuarter2: userCommentedHq){
                    if(headQuarter2.equals(headQuarter1)){
                        userNotCommentedHq.remove(headQuarter2);
                    }
                }
            }
//            List<HeadQuarter> userNotCommentedHq = new ArrayList<>();
//            for (Comment comment : commentRepository.findAllByUserNot(user)) {
//                if (!userNotCommentedHq.contains(comment.getHq())){
//                    userNotCommentedHq.add(comment.getHq());
//                }
////                if (userNotCommentedHq.isEmpty()) {
////                    userNotCommentedHq.add(comment.getHq());
////                } else {
////                    for (int i = 0; i < userNotCommentedHq.size(); i++) {
////                        if (comment.getHq() != userNotCommentedHq.get(i)) {
////                            userNotCommentedHq.add(comment.getHq());
////                        }
////                    }
////                    for (HeadQuarter headQuarter : userNotCommentedHq) {
////                        if (comment.getHq() != headQuarter) {
////                            userNotCommentedHq.add(comment.getHq());
////                        }
////                    }
//
//            }

            System.out.println("################notCommented" + userNotCommentedHq);

            for (HeadQuarter headQuarter : userCommentedHq) {
                similarityMap.put(headQuarter, commentRepository.findAllByHqAndUser(headQuarter, user).size());
            }

            for (HeadQuarter headQuarter : userNotCommentedHq) {
                similarityMap.put(headQuarter, 0);
            }

            List<HeadQuarter> headQuarterList = similarityMap.entrySet().stream()
                    .sorted((o1, o2) -> o2.getValue().compareTo(o1.getValue()))
                    .map(Map.Entry::getKey)
                    .collect(Collectors.toList());

            return headQuarterList;
        } else {
            return findAll();
        }
    }

    @Override
    public boolean add(ConcertForm form) {
        HeadQuarter hq = HeadQuarter.builder()
                .city(form.getCity())
                .description(form.getDescription())
                .build();
        hqRepository.save(hq);
        return true;
    }

    @Override
    public boolean delete(Long id) {
        hqRepository.delete(id);
        return true;
    }
}
