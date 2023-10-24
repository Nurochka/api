package objects.reqres;

import com.google.gson.annotations.Expose;
import lombok.Data;

import java.lang.reflect.GenericDeclaration;
import java.lang.reflect.TypeVariable;
import java.util.ArrayList;


@Data
public class ResourcesList {
    @Expose
    ArrayList<Resource> items;

}
