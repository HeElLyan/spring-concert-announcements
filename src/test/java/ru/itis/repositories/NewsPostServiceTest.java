package ru.itis.repositories;


import org.junit.Assert;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.itis.forms.ConcertForm;
import ru.itis.models.*;
import ru.itis.services.*;

import java.util.List;
import java.util.UUID;


public class NewsPostServiceTest {

    @Autowired
    private NewsPostService newsPostService;
    @Autowired
    private PhotoService photoService;
    @Autowired
    private HQService hqService;
    @Autowired
    private UserRepository authServise;
    @Autowired
    private PlaneService planeService;

    @Test
    public void tesNPRep() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();

        Long id = 6666L;
        NewsPost newsPost = NewsPost.builder()
                .headLine(title)
                .description(description)
                .photo(photoService.findByPath(path))
                .id(id)
                .build();
        newsPostService.add(title,description,path);
        List<NewsPost> result = newsPostService.findById(id);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(description, result.get(0).getDescription());
        newsPostService.delete(id);
        result = newsPostService.findById(id);
        Assert.assertEquals(0, result.size());

    }



    @Test
    public void tesHQRep() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();

        Long id = 6666L;

        ConcertForm hq = ConcertForm.builder()
                .city(title)
                .description(path)
                .build();

        hqService.add(hq);

        List<HeadQuarter> result = (List<HeadQuarter>) hqService.findById(id);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(title, result.get(0).getCity());

        hqService.delete(id);
        result = (List<HeadQuarter>) hqService.findById(id);
        Assert.assertEquals(0, result.size());
    }


    @Test
    public void tesUSRep() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();

        Long id = 6666L;
        User newsPost = User.builder()
                .name(title)
                .email(description)
                .id(id)
                .build();
        authServise.save(newsPost);
        List<NewsPost> result = (List<NewsPost>) authServise.findOne(id);
        Assert.assertEquals(1, result.size());
        authServise.delete(id);
        result = (List<NewsPost>) authServise.findOne(id);
        Assert.assertEquals(0, result.size());

    }



    @Test
    public void tesPhRep() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();

        Long id = 6666L;
        Photo newsPost = Photo.builder()
                .path(title)
                .id(id)
                .build();
        photoService.add(path);
        List<Photo> result = (List<Photo>) photoService.getPhoto(id);
        Assert.assertEquals(1, result.size());
        Assert.assertEquals(title, result.get(0).getPath());

    }



    @Test
    public void tesPLRep() {
        String title = UUID.randomUUID().toString();
        String description = UUID.randomUUID().toString();
        String path = UUID.randomUUID().toString();

        Long id = 6666L;
        List<ModulInfo> result = planeService.getInfo();
        Assert.assertEquals(1, result.size());

    }

    @Autowired
    private TicketService ticketService;

    @MockBean
    private TicketRepository ticketRepositoryMock;
    @Test
    public void testGetAllTickets() {
        List<Ticket> ticketList = ticketRepositoryMock.findAll();
        List<Ticket> ticketList1 = ticketService.getTickets();
        Assert.assertTrue(ticketList1.containsAll(ticketList) && ticketList1.containsAll(ticketList));
    }


    @MockBean
    private HQRepository hqRepositoryMock;
    @Test
    public void testGetAllHQ() {
        List<HeadQuarter> ticketList = hqRepositoryMock.findAll();
        List<HeadQuarter> ticketList1 = hqService.findAll();
        Assert.assertTrue(ticketList1.containsAll(ticketList) && ticketList1.containsAll(ticketList));
    }



    @MockBean
    private PhotoRepository photoRepositoryMock;
    @Test
    public void testGetOnePhoto() {
        Photo ticketList = photoRepositoryMock.findOne((long) 1);
        Photo ticketList1 = photoService.getPhoto((long) 1);
        Assert.assertTrue(ticketList1.equals(ticketList));
    }



    @Autowired
    private CommentService commentService;
    @MockBean
    private CommentRepository commentRepositoryMock;
    @Test
    public void testGetAllComments() {
        List<Comment> ticketList = commentRepositoryMock.findAllByDescriptionContaining("a");
        List<Comment> ticketList1 = commentService.findAllByDescription("a");
        Assert.assertTrue(ticketList1.containsAll(ticketList) && ticketList1.containsAll(ticketList));
    }
}

