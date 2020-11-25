package org.maktab.onlinestore.data.remote.retrofit;

import com.google.gson.JsonArray;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;

import org.maktab.onlinestore.data.model.Product;
import org.maktab.onlinestore.data.model.Images;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public class GetListOfProductDeserializer implements JsonDeserializer<List<Product>> {

    @Override
    public List<Product> deserialize(
            JsonElement json,
            Type typeOfT,
            JsonDeserializationContext context) throws JsonParseException {
        List<Product> items = new ArrayList<>();

        JsonArray bodyArray = json.getAsJsonArray();
        for (int i = 0; i < bodyArray.size(); i++) {
            JsonObject productObject = bodyArray.get(i).getAsJsonObject();
            String title = productObject.get("name").getAsString();
            int id = productObject.get("id").getAsInt();
            String price = productObject.get("price").getAsString();
            String regular_price = productObject.get("regular_price").getAsString();
            String sale_price = productObject.get("sale_price").getAsString();
           /* String sale_price_dates_from = productObject.get("date_on_sale_from").getAsString();
            String sale_price_dates_to = productObject.get("date_on_sale_to").getAsString();*/
            String weight = productObject.get("weight").getAsString();
            JsonObject dimensions = productObject.getAsJsonObject("dimensions");
            String length = dimensions.get("length").getAsString();
            String width = dimensions.get("width").getAsString();
            String height = dimensions.get("height").getAsString();
            String description = productObject.get("description").getAsString();
            String short_description = productObject.get("short_description").getAsString();
            String average_rating = productObject.get("average_rating").getAsString();
            int rating_count = productObject.get("rating_count").getAsInt();
            JsonArray photoArray = productObject.get("images").getAsJsonArray();
            List<Images> imagesArray = new ArrayList<>();
            for (int j = 0; j < photoArray.size(); j++) {
                JsonObject photoObject = photoArray.get(j).getAsJsonObject();
                int imageId = photoObject.get("id").getAsInt();
                String url = photoObject.get("src").getAsString();
                Images images = new Images(imageId,url);
                imagesArray.add(images);
            }

            Product item = new Product(title,id,price,regular_price,sale_price,weight,length,width,height,description,short_description,
                    average_rating,rating_count, imagesArray);
            items.add(item);
        }

        return items;
    }
}
