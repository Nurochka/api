package objects.HH;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import objects.HH.Salary;

@Data
public class Vacancy {
    @Expose
    String name;
    Salary salary;
    @SerializedName("alternate_url")
    String alternateUrl;
}
