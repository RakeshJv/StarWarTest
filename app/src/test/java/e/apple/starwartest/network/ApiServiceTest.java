package e.apple.starwartest.network;

import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import e.apple.starwartest.activity.MainActivity;
import e.apple.starwartest.model.Responce;
import e.apple.starwartest.presenter.CharacterListView;
import e.apple.starwartest.presenter.ServerOperation;
import okhttp3.Request;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;


import static org.hamcrest.CoreMatchers.any;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

public class ApiServiceTest {


    @Mock
    ApiService apiService;

    @Mock
    CharacterListView view;

    @Mock
    Responce responce;


    @Mock
    Exception exception;

    @Mock
    MainActivity mainActivity;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }


    @Test
    public void getDataDownload() {

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
        ServerOperation serverOperation = new ServerOperation(view);
        serverOperation.loadData();
        assertFalse(((Responce) responce).getResults().isEmpty());

    }

    @Test
    public void show_AlertFor_Data_Download_Error() {

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
        ServerOperation serverOperation = new ServerOperation(view);
        serverOperation.loadData();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        when(apiService.getCharacterList()).thenReturn(responce);
        serverOperation = new ServerOperation(view);
        serverOperation.loadData();
        verify(view).setError(exception);
        assertTrue(mainActivity.getCustomDialog().isShowing() == true);

    }

}