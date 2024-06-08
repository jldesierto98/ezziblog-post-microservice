package com.eeezi.ezziblogrestapi.comment.repository;

import com.eeezi.ezziblogrestapi.comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {

        List<Comment> findByPostId(Long postId);

}
