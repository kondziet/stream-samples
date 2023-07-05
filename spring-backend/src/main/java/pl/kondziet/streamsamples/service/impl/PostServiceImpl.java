package pl.kondziet.streamsamples.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import pl.kondziet.streamsamples.model.entity.Post;
import pl.kondziet.streamsamples.model.repository.PostRepository;
import pl.kondziet.streamsamples.service.PostService;


@AllArgsConstructor
@Service
public class PostServiceImpl implements PostService {

    private PostRepository postRepository;

    @Override
    public void addNewPost(Post post) {
        postRepository.save(post);
    }
}
