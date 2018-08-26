package e.apple.starwartest.presenter;

import e.apple.starwartest.model.Responce;

public interface CharacterListView 
{
    void loadData(Responce responce);

    void showProgressBar();

    void hideProgressBar();

    void setError(Exception e);

    void setThrowableError(Throwable t);
}
