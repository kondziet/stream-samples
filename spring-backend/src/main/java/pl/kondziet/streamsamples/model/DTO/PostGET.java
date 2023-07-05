package pl.kondziet.streamsamples.model.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PostGET {

    private String authorNickname;
    private String title;
    private String code;

}
