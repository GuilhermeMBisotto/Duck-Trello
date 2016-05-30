package com.guilhermemorescobisotto.ducktrello.Services;


import com.guilhermemorescobisotto.ducktrello.APIService.APIServiceHandler;
import com.guilhermemorescobisotto.ducktrello.DataHolder;
import com.guilhermemorescobisotto.ducktrello.Helpers.Essential;
import com.guilhermemorescobisotto.ducktrello.Helpers.OnTaskCompleted;
import com.guilhermemorescobisotto.ducktrello.Models.Board;
import com.guilhermemorescobisotto.ducktrello.Models.Member;
import com.guilhermemorescobisotto.ducktrello.Models.Members;
import com.guilhermemorescobisotto.ducktrello.Models.User;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by guilhermemorescobisotto on 5/17/16.
 */
public class HomeService {

    public static void initService(final OnTaskCompleted callback) {

        UserService.getUser(new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                Essential.log("User onSuccess");
                DataHolder.getRef().currentUser = (User) obj;
                getBoards(callback);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                Essential.log("User onError");
                callback.onTaskCompletedFailure(errorMessage);
            }
        });
    }

    private static void getBoards(final OnTaskCompleted callback) {
        BoardService.getBoards(new APIServiceHandler() {
            @Override
            public void onSuccess(Object obj) {
                Essential.log("Boards onSuccess");
                List<Board> boards = new ArrayList<>();
                boards.addAll(((ArrayList<Board>) obj));

                if (DataHolder.getRef().boards == null) {
                    DataHolder.getRef().boards = new ArrayList<>();
                } else {
                    DataHolder.getRef().boards.clear();
                }
                DataHolder.getRef().boards.addAll(boards);

                for (Board board : boards) {
                    for (Members member : board.membersList) {
                        if (member.idMember.equalsIgnoreCase(DataHolder.getRef().currentUser.id)) {
                            board.setUserType(member.memberType);
                        }
                    }
                }
                getMemberFromBoard(callback);
            }

            @Override
            public void onError(int errorCode, String errorMessage, Object err) {
                Essential.log("Boards onError");
                callback.onTaskCompletedFailure(errorMessage);
            }
        });
    }

    private static void getMemberFromBoard(final OnTaskCompleted callback) {

        final ArrayList<Board> boards = new ArrayList<>(DataHolder.getRef().boards);
        final int[] cont = new int[1];
        cont[0] = 0;


        for (int i = 0; i < boards.size(); i++) {
            final int index = i;
            BoardService.getMemberFromBoard(boards.get(i).id, new APIServiceHandler() {
                @Override
                public void onSuccess(Object obj) {
                    cont[0] = cont[0] + 1;
                    List<Member> members = new ArrayList<>();
                    members.addAll(((ArrayList<Member>) obj));

                    boards.get(index).setMembers(members);

                    if (cont[0] == (boards.size())) {
                        callback.onTaskCompletedSuccess();
                    }
                }

                @Override
                public void onError(int errorCode, String errorMessage, Object err) {
                    Essential.log("Members onError");
                    callback.onTaskCompletedFailure(errorMessage);
                }
            });
        }
    }
}
