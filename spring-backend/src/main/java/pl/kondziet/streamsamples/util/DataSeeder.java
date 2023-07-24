package pl.kondziet.streamsamples.util;

import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import pl.kondziet.streamsamples.model.embeddedkey.UserPostLikesId;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.entity.UserPostLikes;
import pl.kondziet.streamsamples.model.enums.PostCategory;
import pl.kondziet.streamsamples.model.enums.Role;
import pl.kondziet.streamsamples.model.repository.CommentRepository;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.model.repository.UserPostLikesRepository;
import pl.kondziet.streamsamples.model.repository.UserRepository;
import pl.kondziet.streamsamples.model.entity.Comment;

@AllArgsConstructor
@Component
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PostRepository postRepository;
    private final CommentRepository commentRepository;
    private final UserPostLikesRepository userPostLikesRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        User user1 = User.builder()
                .nickname("prisonMike")
                .email("example")
                .password(passwordEncoder.encode("example"))
                .role(Role.USER)
                .active(true)
                .build();

        User user2 = User.builder()
                .nickname("dwight")
                .email("dwight")
                .password(passwordEncoder.encode("dwight"))
                .role(Role.USER)
                .active(true)
                .build();

        userRepository.save(user1);
        userRepository.save(user2);

        Post post1 = Post.builder()
                .description("Check out my way for filtering integers :)")
                .postCategory(PostCategory.FILTERING)
                .code("List<Integer> numbers = new ArrayList(List.of(1, 2, 3, 4, 5));\nList<Integer> result = numbers.stream().filter(num -> num % 2 == 0).collect(Collectors.toList);")
                .author(user1)
                .title("How to filter even numbers from list of integers")
                .build();

        Post post2 = Post.builder()
                .description("I will use mapToInt() in the next sample.")
                .postCategory(PostCategory.REDUCING)
                .code("List<Integer> numbers = new ArrayList(List.of(1, 2, 3, 4, 5));\nInteger sum = integers.stream().reduce(0, Integer::sum);")
                .author(user1)
                .title("Get sum of all integers in list")
                .build();

        postRepository.save(post1);
        postRepository.save(post2);

        user1.getPosts().add(post1);
        user1.getPosts().add(post2);

        user1.getLikedPosts().add(post1);
        post1.getUsersWhoLiked().add(user1);
        UserPostLikes userPostLikes1 = UserPostLikes.builder()
                .userPostLikeId(UserPostLikesId.builder()
                        .userId(user1.getId())
                        .postId(post1.getId())
                        .build()
                )
                .user(user1)
                .post(post1)
                .build();

        postRepository.save(post1);
        userRepository.save(user1);
        userPostLikesRepository.save(userPostLikes1);

        Comment comment1 = Comment.builder()
                .author(user1)
                .post(post1)
                .content("Nice solution :)")
                .build();

        user1.getComments().add(comment1);
        post1.getComments().add(comment1);

        commentRepository.save(comment1);
    }
}
