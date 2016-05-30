package com.guilhermemorescobisotto.ducktrello.EnumConstant;

/**
 * Created by guilhermemorescobisotto on 4/29/16.
 */
public class DuckConstants {

    public static final String APP_KEY = "accb172d9b5fc66e43158a080fabd8c2";
    public static final String API_BASE = "https://trello.com";
    public static final String API_VERSION = "/1";
    public static final String API_URL = API_BASE + API_VERSION;
    public static final String API_KEY_AND_TOKEN_PARAMS = "?key=" + APP_KEY + "&token={token}";


    public static final String API_AUTHORIZE = API_URL + "/authorize?";
    public static final String API_TOKEN_MEMBER = API_URL + "/tokens/{token}/member";

    public static final String API_BOARD = API_URL + "/members/my/boards";
    public static final String API_GET_BOARD = "/boards/{boardId}";
    public static final String API_BOARD_MEMBERS = API_URL + API_GET_BOARD + "/members";

    public static final String API_LIST = API_URL + "/lists/{listId}";
    public static final String API_GET_LIST = API_URL + API_GET_BOARD + "/lists";
    public static final String API_LIST_CARDS = API_LIST + "/cards";

    public static final String API_BOARD_CARDS = API_URL + API_GET_BOARD + "/cards";
    public static final String API_CARD_MEMBERS = API_BOARD_CARDS + "/{cardId}/members";

    public static final String DATE_TRELLO_PATTERN = "yyyy-MM-dd'T'HH:mm:ss.SSSZ";

    public static final String API_GET_PHOTO = "https://trello-avatars.s3.amazonaws.com/{avatarHash}/170.png";



}
