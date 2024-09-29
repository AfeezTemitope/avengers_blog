package com.avengersblog.Services.Comments;

import com.avengersblog.Data.Model.Comments;
import com.avengersblog.Data.Model.Post;
import com.avengersblog.Data.Model.User;
import com.avengersblog.Data.Repository.CommentRepository;
import com.avengersblog.Data.Repository.PostRepository;
import com.avengersblog.Data.Repository.UserRepository;
import com.avengersblog.Dto.request.Comments.CreateCommentRequest;
import com.avengersblog.Dto.request.Comments.DeleteCommentResponse;
import com.avengersblog.Dto.request.Comments.UpdateCommentRequest;
import com.avengersblog.Dto.response.Comments.CreateCommentResponse;
import com.avengersblog.Exceptions.Comments.CommentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Comments> getCommentsByPostId(Long postId)  {
      //  return commentRepository.findByPostId(postId);
        return null;
    }

    @Override
    public CreateCommentResponse commentsOnPost(CreateCommentRequest request) {
        if (request.getComment() == null || request.getComment().isEmpty()) throw new CommentException("Comment text cannot be null or empty.");

        Optional<Post> findPost = postRepository.findById(request.getPostId());
        if(!findPost.isPresent()) throw new CommentException("Post does not exist.");

        Optional<User> findUser = userRepository.findById(request.getUserId());
        if(!findUser.isPresent()) throw new CommentException("User does not exist.");
        Comments comment = new Comments();
        comment.setComment(request.getComment());
        comment.setCreatedAt(LocalDateTime.now());
        comment.setPostId(findPost.get());
        comment.setUserId(findUser.get());
        commentRepository.save(comment);
        return new CreateCommentResponse();
    }

    @Override
    public CreateCommentResponse updateComment(UpdateCommentRequest updateCommentRequest) {
        Comments comment = commentRepository.findById(Long.valueOf(updateCommentRequest.getCommentId())).orElseThrow(() -> new CommentException("Comment not found."));
        if (updateCommentRequest.getComment() != null && !updateCommentRequest.getComment().isEmpty()) comment.setComment(updateCommentRequest.getComment());
        comment.setUpdatedAt(LocalDateTime.now());
        commentRepository.save(comment);
        return new CreateCommentResponse();

    }

    @Override
    public DeleteCommentResponse deleteComment(CreateCommentResponse response) {
        return null;
    }
}
