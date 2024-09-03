package exercise.dto;

import java.util.List;

import exercise.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

// BEGIN
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PostDTO {

    private long id;
    private String title;
    private String body;
    private List<CommentDTO> comments;
}
// END
