package com.example.pascm033.marvelcharacters;

import android.net.Uri;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import retrofit2.Response;

public class NetworkUtils {
    private static final String LOG_TAG = NetworkUtils.class.getSimpleName();

    //Base URI for the Books API
    private static final String BOOK_BASE_URL = "https://www.googleapis.com/books/v1/volumes?";
    //parameter for the search string
    private static final String QUERY_PARAM = "q";
    //parameter that limits the search results
    private static final String MAX_RESULTS = "maxResults";
    //parameter to filter by print type
    private static final String PRINT_TYPE = "printType";

    static VolumesResponse getBookInfo(String query, int max, String type) {
        BookService bookService = RetrofitUtils.getInstance().create(BookService.class);
        try {
            Response<VolumesResponse> response = bookService.getBooks(query, max, type)
                    .execute();

            if (response != null) {
                return response.body();
            }

        } catch (IOException ex) {
            Log.e("TAG", "Error in API call");
        }
        return null;
    }

    static String getBookInfo(String queryString) {
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;

        String bookJSONString = null;

        //create the HTTP request
        try {

            //build up your query URI, limiting results to 10 items and printed books
            Uri builtURI = Uri.parse(BOOK_BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, queryString)
                    .appendQueryParameter(MAX_RESULTS, "10")
                    .appendQueryParameter(PRINT_TYPE, "books")
                    .build();

            //URI -> URL
            URL requestURL = new URL(builtURI.toString());

            //open the URL connection and make the request
            urlConnection = (HttpURLConnection) requestURL.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }

            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;

            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }
            if (buffer.length() == 0) {
                return null;
            }
            bookJSONString = buffer.toString();

        }  catch (IOException e) {
            e.printStackTrace();
            return null;
        } finally {
            if(urlConnection != null) {
                urlConnection.disconnect();
            }
            if(reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            Log.d(LOG_TAG, bookJSONString);
         return bookJSONString;
        }
    }

}
