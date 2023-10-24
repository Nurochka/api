package objects.reqres;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class RegUser {
    String email;
    String password;

}
