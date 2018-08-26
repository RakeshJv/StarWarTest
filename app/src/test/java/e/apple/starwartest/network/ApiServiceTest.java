package e.apple.starwartest.network;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import e.apple.starwartest.activity.MainActivity;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.presenter.ServerOperation;
import okhttp3.Request;
import retrofit2.Call;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ApiServiceTest {


    @Mock
    ApiService apiService;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getErrorDataDownload() {

        Call<Responce> responce = new Call<Responce>() {
            @Override
            public Response<Responce> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<Responce> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Responce> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        when(apiService.getCharacterList()).thenReturn(responce);

    }

    @Test
    public void getDataFromServer() {

        Call<Responce> responce = new Call<Responce>() {
            @Override
            public Response<Responce> execute() throws IOException {
                return null;
            }

            @Override
            public void enqueue(Callback<Responce> callback) {

            }

            @Override
            public boolean isExecuted() {
                return false;
            }

            @Override
            public void cancel() {

            }

            @Override
            public boolean isCanceled() {
                return false;
            }

            @Override
            public Call<Responce> clone() {
                return null;
            }

            @Override
            public Request request() {
                return null;
            }
        };
        when(apiService.getCharacterList()).thenReturn(responce);

    }

}