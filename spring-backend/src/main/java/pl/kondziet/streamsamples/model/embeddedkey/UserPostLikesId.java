package pl.kondziet.streamsamples.model.embeddedkey;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Embeddable
public class UserPostLikesId implements Serializable {

    @Column(name = "USER_ID")
    private Long userId;

    @Column(name = "POST_ID")
    private Long postId;
}
