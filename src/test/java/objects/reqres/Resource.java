package objects.reqres;

import com.google.gson.annotations.Expose;
import lombok.Data;

@Data
public class Resource {
    @Expose
    objects.reqres.Data data;
}
