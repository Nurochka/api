package objects.reqres;

import com.google.gson.annotations.Expose;

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
    String pantone_value;

}
