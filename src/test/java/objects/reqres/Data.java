package objects.reqres;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@lombok.Data
public class Data {
    @Expose
    String id;
    @Expose
    String name;
    @Expose
    int year;
    @Expose
    String color;
    @SerializedName("pantone_value")
    String pantoneValue;

}
