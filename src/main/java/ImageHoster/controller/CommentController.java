package ImageHoster.controller;

import ImageHoster.model.Comment;
import ImageHoster.model.Image;
import ImageHoster.model.User;
import ImageHoster.service.CommentService;
import ImageHoster.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

@Controller
public class CommentController {
    @Autowired
    ImageService imageService;
    @Autowired
    CommentService commentService;
    @RequestMapping(value = "/image/{imageId}/{imageTitle}/comments", method = RequestMethod.POST)
    public String createComments(@RequestParam("comment") String comment, @PathVariable(name = "imageId") int imageId, @PathVariable(name = "imageTitle") String imageTitle, HttpSession session) throws IOException {

        User user = (User) session.getAttribute("loggeduser");
        Image image=imageService.getImage(imageId);
        Comment newComment=new Comment();
        newComment.setText(comment);
        newComment.setUser(user);
        newComment.setImage(image);
        newComment.setCreatedDate(new Date());





        commentService.createComment(newComment);
        return "redirect:/images/"+image.getId()+"/"+image.getTitle();
    }
}
