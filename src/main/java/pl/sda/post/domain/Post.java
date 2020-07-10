package pl.sda.post.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

/* <--lombok */
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = "creationDate")
/* lombok --> */
public class Post {
    private Integer id;

    private String text;

    private String username;

    private LocalDateTime creationDate;
}
