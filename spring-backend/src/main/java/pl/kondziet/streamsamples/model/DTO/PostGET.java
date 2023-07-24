package pl.kondziet.streamsamples.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.PostCategory;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostGET {

    private Long postId;
    private String authorNickname;
    private String postCategory;
    private String title;
    private String description;
    private String code;
    private Long likesCount;
    private Long commentsCount;

}
