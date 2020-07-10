package pl.sda.follow.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

// <-- lombok
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(exclude = {"creationDate"})
// lombok -->
public class Follow {
    private String id;

    private String followerUsername;

    private String followeeUsername;

    private LocalDateTime creationDate;
}
