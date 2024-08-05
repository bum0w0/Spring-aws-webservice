package board.spring_aws.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PostsRepository extends JpaRepository<Posts, Long> {

    // SpringDataJpa에서 제공하지 않는 메소드는 아래처럼 쿼리로 작성해도 무방 (현재 코드를 SpringDataJpa에서 제공하는 기본 메소드만으로 해결할 수 있지만 가독성을 위해 추가)
    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();
}
