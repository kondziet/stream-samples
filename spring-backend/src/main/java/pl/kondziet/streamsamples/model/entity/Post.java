package pl.kondziet.streamsamples.model.entity;

import jakarta.persistence.*;
import lombok.*;
import pl.kondziet.streamsamples.model.enums.PostCategory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "POSTS")
public class Post implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String title;
    @Column(length = 400)
    private String description;
    @Enumerated(EnumType.STRING)
    private PostCategory postCategory;
    @Column(length = 5000)
    private String code;

    @EqualsAndHashCode.Exclude
    @ManyToOne
    private User author;

    @EqualsAndHashCode.Exclude
    @Builder.Default
    @ManyToMany(mappedBy = "likedPosts")
    private Set<User> usersWhoLiked = new HashSet<>();

}
