package tests;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import objects.HH.VacanciesList;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static java.net.HttpURLConnection.HTTP_OK;

public class HHTest {

    @Test
    public void checkVacanciesList(){
    String body = given()
                .log().all()
                .when()
                .get("https://api.hh.ru/vacancies?text=QA automation")
                .then()
                .log().all()
                .statusCode(HTTP_OK)
                .extract().body().asString();

        VacanciesList vacanciesList = new Gson().fromJson(body, VacanciesList.class);
        int salaryTo = vacanciesList.getItems().get(0).getSalary().getTo();
        System.out.println(salaryTo);

        VacanciesList vacanciesListWithExpose =  new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create()
                .fromJson(body, VacanciesList.class);
        System.out.println(vacanciesListWithExpose);

    }
}

