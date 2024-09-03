package exercise.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommentDTO {

    private long id;
    private String body;
}
// END
