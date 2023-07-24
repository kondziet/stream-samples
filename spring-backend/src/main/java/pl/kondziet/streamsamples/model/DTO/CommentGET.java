package pl.kondziet.streamsamples.model.DTO;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kondziet.streamsamples.model.entity.User;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentGET {

    private Long id;
    private String authorNickname;
    private String content;
}
