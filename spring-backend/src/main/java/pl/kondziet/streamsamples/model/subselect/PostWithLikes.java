package pl.kondziet.streamsamples.model.subselect;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.PostCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostWithLikes {

    private Long postId;
    private User user;
    private PostCategory postCategory;
    private String title;
    private String description;
    private String code;
    private Long likesCount;
    private Long commentsCount;

}
