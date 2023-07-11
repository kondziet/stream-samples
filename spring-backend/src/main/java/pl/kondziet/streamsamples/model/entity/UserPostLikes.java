package pl.kondziet.streamsamples.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kondziet.streamsamples.model.embeddedkey.UserPostLikesId;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "USER_POST_LIKES")
public class UserPostLikes {

    @EmbeddedId
    private UserPostLikesId userPostLikeId;
    @ManyToOne
    @MapsId(value = "USER_ID")
    private User user;

    @ManyToOne
    @MapsId(value = "POST_ID")
    private Post post;
}
