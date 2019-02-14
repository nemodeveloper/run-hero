package ru.nemodev.runhero;

import android.app.Activity;
import android.content.Intent;

import com.google.example.games.basegameutils.GameHelper;

import ru.nemodev.runhero.service.PlayService;

public class AndroidPlayService implements PlayService
{
    private final static int requestCode = 1;

    private final Activity activity;
    private final GameHelper gameHelper;

    public AndroidPlayService(Activity activity)
    {
        this.activity = activity;
        this.gameHelper = new GameHelper(activity, GameHelper.CLIENT_GAMES);
        this.gameHelper.enableDebugLog(true);

        this.gameHelper.setup(new GameHelper.GameHelperListener()
        {
            @Override
            public void onSignInFailed() { }

            @Override
            public void onSignInSucceeded() { }
        });
    }

    public void onStartActivity()
    {
        gameHelper.onStart(activity);
    }

    public void onStopActivity()
    {
        gameHelper.onStop();
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        gameHelper.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public void signIn()
    {
        try
        {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.beginUserInitiatedSignIn();
                }
            });
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public void signOut()
    {
        try
        {
            activity.runOnUiThread(new Runnable()
            {
                @Override
                public void run()
                {
                    gameHelper.signOut();
                }
            });
        }
        catch (Exception e)
        {

        }
    }

    @Override
    public boolean isSignedIn()
    {
        return gameHelper.isSignedIn();
    }

    @Override
    public void unlockAchievement(String achievementKey)
    {

    }

    @Override
    public void submitScore(int highScore)
    {

    }

    @Override
    public void showAchievement()
    {

    }

    @Override
    public void showScore()
    {

    }

    @Override
    public void rateGame()
    {

    }
}
