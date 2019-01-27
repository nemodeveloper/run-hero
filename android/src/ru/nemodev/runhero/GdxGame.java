package ru.nemodev.runhero;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;

public class GdxGame extends RelativeLayout
{
    private View gameView;

    public GdxGame(Context context)
    {
        this(context, null);
    }

    public GdxGame(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public GdxGame(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
        init();
    }

    public void setGameView(View gameView)
    {
        this.gameView = gameView;
        addView(gameView);
    }

    private void init()
    {
        inflate(getContext(), R.layout.gdxgame, this);
    }

}
