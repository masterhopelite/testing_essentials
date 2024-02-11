import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import org.testng.Assert;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import Files.ReUsableMethods;
import Files.payload;


public class automation_api {

	public static void main(String[] args) throws IOException {
		String requestbody = "{\"email\":\"dummy12@d2ctest.com\",\"password\":\"abcdef\"}";
		RestAssured.baseURI = "https://unstop.com";
		String Response = RestAssured.given()
				.header("Content-Type", "application/json")
				.body(requestbody)

				.when()
				.post("/api/user/login")

				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.response()
				.asString();

		JsonObject jsonObjectAlt = JsonParser.parseString(Response).getAsJsonObject();
		Gson gson = new GsonBuilder().setPrettyPrinting().create();

		String formattedJson = gson.toJson(jsonObjectAlt);
		String outputFile = "api_Automation\\Demo\\src\\formatted.json";
		try (FileWriter writer = new FileWriter(outputFile)) {
			writer.write(formattedJson);
		} catch (IOException e) {
			e.printStackTrace();
		}
		JsonPath js = new JsonPath(Response); // for parsing json
		// String placeId = js.getString("place_id");
		String accessToken = js.get("access_token");

		// System.out.println(formattedJson);
		// System.out.println(accessToken);

		RestAssured.baseURI = "https://unstop.com/";
		String pose = RestAssured.given()
				.header("Content-Type", "application/json", "Authorization", accessToken)

				.when()
				.get("api/mentorship/get-top-mentors")

				.then()
				.assertThat()
				.statusCode(200)
				.extract()
				.response()
				.asString();
		// System.out.println(pose);
		JsonObject obj = JsonParser.parseString(pose).getAsJsonObject();
		Gson son = new GsonBuilder().setPrettyPrinting().create();
		String fj = son.toJson(obj);
		// System.out.println(fj);
		// JsonPath ks = new JsonPath(pose);
		// String placeId = js.getString("place_id");
		JsonPath ls = new JsonPath(fj);
		int count = ls.getInt("data.username.size()");
		String usser = "f";
		for (int i = 0; i < count; i++) {
			usser = ls.get("data.username[" + i + "]");
			System.out.println(usser);
		}
		System.out.println(count);

	}

}
