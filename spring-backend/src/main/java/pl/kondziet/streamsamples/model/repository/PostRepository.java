package pl.kondziet.streamsamples.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.enums.PostCategory;
import pl.kondziet.streamsamples.model.subselects.PostWithLikes;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {

    @Query("""
SELECT NEW pl.kondziet.streamsamples.model.subselects.PostWithLikes(
    p.id, 
    p.author, 
    p.postCategory, 
    p.title,
    p.description, 
    p.code, (
        SELECT COUNT(upl)
        FROM UserPostLikes upl
        WHERE upl.post.id = p.id
    )
)
FROM Post p
""")
    List<PostWithLikes> findPostsWithLikes();

    @Query("""
SELECT NEW pl.kondziet.streamsamples.model.subselects.PostWithLikes(p.id, p.author, p.postCategory, p.description, p.code, COUNT(upl))
FROM Post p
LEFT JOIN UserPostLikes upl ON p.id = upl.post.id
WHERE p.postCategory = :postCategory
GROUP BY p.id
""")
    List<PostWithLikes> findPostsWithLikesByCategory(@Param("postCategory") PostCategory postCategory);
}
