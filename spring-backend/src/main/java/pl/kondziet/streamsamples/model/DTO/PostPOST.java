package pl.kondziet.streamsamples.model.DTO;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kondziet.streamsamples.model.entity.User;
import pl.kondziet.streamsamples.model.enums.PostCategory;

@Data
public class PostPOST {

    private String title;
    private String description;
    private PostCategory postCategory;
    private String code;
}
