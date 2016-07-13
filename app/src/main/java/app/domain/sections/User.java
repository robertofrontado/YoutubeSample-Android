package app.domain.sections;

import lombok.Data;

/**
 * Created by robertofrontado on 7/13/16.
 */
@Data
public class User {
    private final String name;
    private final String email;
    private final String token;
    private final String photoUrl;

}
