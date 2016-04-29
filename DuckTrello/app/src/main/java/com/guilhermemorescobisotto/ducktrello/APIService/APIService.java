package com.guilhermemorescobisotto.ducktrello.APIService;

import com.github.kittinunf.fuel.Fuel;
import com.github.kittinunf.fuel.core.FuelError;
import com.github.kittinunf.fuel.core.Handler;
import com.github.kittinunf.fuel.core.Manager;
import com.github.kittinunf.fuel.core.Request;
import com.github.kittinunf.fuel.core.Response;
import com.google.gson.Gson;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;

import kotlin.Pair;

import java.util.HashMap;
import java.util.List;


/**
 * Created by guilhermemorescobisotto on 4/28/16.
 */
public class APIService {

	private static final Gson gson = new Gson();
    private static final String APILink = "https://api.trello.com/";

	// API Configuration
	public static void selfConfiguration() {
		// Set root path
		Manager.Companion.getInstance().setBasePath(APILink);
		// Define headers
		Manager.Companion.getInstance().setBaseHeaders(new HashMap<String, String>() {{
			put("Content-Type", "application/x-www-form-urlencoded");
			put("API-Target", "true");
		}});
	}

    // API Calls
    public static void GET(String action, List<Pair<String, String>> params, APIServiceHandler handler) {
        Fuel.get(action, params).responseString( APIService.HANDLER(handler) );
    }
    public static void POST(String action, List<Pair<String, String>> params, APIServiceHandler handler) {
        Fuel.post(action, params).responseString( APIService.HANDLER(handler) );
    }
    public static void PUT(String action, List<Pair<String, String>> params, APIServiceHandler handler) {
        Fuel.put(action, params).responseString( APIService.HANDLER(handler) );
    }
    public static void DELETE(String action, List<Pair<String, String>> params, APIServiceHandler handler) {
        Fuel.delete(action, params).responseString(APIService.HANDLER(handler));
    }

	// Private API
	private static Handler<String> HANDLER(final APIServiceHandler handler) {
		handler.onStart();
		return new Handler<String>() {
			@Override
			public void success(Request request, Response response, String content) {
				APIServiceResult result = gson.fromJson(content, APIServiceResult.class);

				if (result.isSuccess) {
					handler.onSuccess(result.data);
				}
				else {
					handler.onError(result.errorCode, result.errorMessage, null);
				}

				handler.onFinish();
			}
			@Override
			public void failure(Request request, Response response, FuelError fuelError) {
				Essential.log("Something fail during API Request");
				Essential.log(request.toString());
				Essential.log(response.toString());
				Essential.log(fuelError.toString());
				handler.onFailure(-1, "Failed during API Request", null);
				handler.onFinish();
			}
		};
	}
}
