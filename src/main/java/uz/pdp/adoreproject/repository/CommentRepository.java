package uz.pdp.adoreproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.adoreproject.entity.Comment;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Integer> {
  List<Comment> findByRestaurantId(Integer id);
}