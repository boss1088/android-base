package com.jordifierro.androidbase.data.repository;

import com.jordifierro.androidbase.data.net.RestApi;
import com.jordifierro.androidbase.domain.entity.NoteEntity;
import com.jordifierro.androidbase.domain.entity.UserEntity;
import com.jordifierro.androidbase.domain.repository.NoteRepository;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Response;
import rx.Observable;
import rx.functions.Func1;

public class NoteDataRepository implements NoteRepository {

    private final RestApi restApi;

    @Inject
    public NoteDataRepository(RestApi restApi) {
        this.restApi = restApi;
    }

    @Override
    public Observable<NoteEntity> createNote(UserEntity user, final NoteEntity note) {
        return this.restApi.createNote(user.getAuthToken(), note)
                .map(new Func1<Response<NoteEntity>, NoteEntity>() {
                    @Override
                    public NoteEntity call(Response<NoteEntity> noteEntityResponse) {
                        if (!noteEntityResponse.isSuccess()) throw new RuntimeException();
                        return noteEntityResponse.body();
                    }
                });
    }

    @Override
    public Observable<NoteEntity> getNote(UserEntity user, int noteId) {
        return this.restApi.getNote(user.getAuthToken(), noteId)
                .map(new Func1<Response<NoteEntity>, NoteEntity>() {
                    @Override
                    public NoteEntity call(Response<NoteEntity> noteEntityResponse) {
                        if (!noteEntityResponse.isSuccess()) throw new RuntimeException();
                        return noteEntityResponse.body();
                    }
                });
    }

    @Override
    public Observable<List<NoteEntity>> getNotes(UserEntity user) {
        return this.restApi.getNotes(user.getAuthToken())
                .map(new Func1<Response<List<NoteEntity>>, List<NoteEntity>>() {
                    @Override
                    public List<NoteEntity> call(Response<List<NoteEntity>> listResponse) {
                        if (!listResponse.isSuccess()) throw new RuntimeException();
                        return listResponse.body();
                    }
                });
    }

    @Override
    public Observable<NoteEntity> updateNote(UserEntity user, NoteEntity note) {
        return this.restApi.updateNote(user.getAuthToken(), note.getId(), note)
                .map(new Func1<Response<NoteEntity>, NoteEntity>() {
                    @Override
                    public NoteEntity call(Response<NoteEntity> noteEntityResponse) {
                        if (!noteEntityResponse.isSuccess()) throw new RuntimeException();
                        return noteEntityResponse.body();
                    }
                });
    }

    @Override
    public Observable<Void> deleteNote(UserEntity user, int noteId) {
        return this.restApi.deleteNote(user.getAuthToken(), noteId)
                .map(new Func1<Response, Void>() {
                    @Override
                    public Void call(Response response) {
                        if (!response.isSuccess()) throw new RuntimeException();
                        return null;
                    }
                });
    }
}
