package ru.nemodev.runhero.core.service;

public interface PlayService
{
    void signIn();
    void signOut();

    boolean isSignedIn();

    void unlockAchievement(String achievementKey);
    void submitScore(int highScore);

    void showAchievement();
    void showScore();
    void rateGame();
}
