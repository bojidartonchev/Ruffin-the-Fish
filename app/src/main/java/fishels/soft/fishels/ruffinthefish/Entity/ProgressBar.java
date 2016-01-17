/*
 * Copyright © 2015 Ruffin the Fish
 *
 * This file is part of "Ruffin the Fish".
 *
 * "Ruffin the Fish" is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * "Ruffin the Fish" is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with "Ruffin the Fish".  If not, see <http://www.gnu.org/licenses/>.
 */

package fishels.soft.fishels.ruffinthefish.Entity;

import android.animation.ValueAnimator;
import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Shader;
import android.view.animation.DecelerateInterpolator;

import fishels.soft.fishels.ruffinthefish.Core.Data;
import fishels.soft.fishels.ruffinthefish.Core.GamePanel;
import fishels.soft.fishels.ruffinthefish.GameObjects.Fish.Player;

public class ProgressBar {

    private Bitmap frame;
    private Bitmap barImage;
    private Bitmap outputBar;
    private Bitmap pattern;
    private double scoreWidth;
    private Player player;
    private int scoreToLevelUp;
    private Paint fillPnt;
    private Paint strokePnt;
    private int textSize;
    private String scoreText;

    public ProgressBar(Bitmap frame, Bitmap bar,Player player)
    {
        this.frame = Bitmap.createScaledBitmap(frame, GamePanel.getWIDTH(),frame.getHeight(),true);
        this.barImage= bar;
        this.player=player;
        this.textSize = GamePanel.getWIDTH()/32;
        this.pattern= Data.getImage(Data.PATTERN);
        this.fillPnt = this.getFillPaint();
        this.strokePnt = this.getStrokePaint();
        this.scoreText="";
    }

    public void update(int score)
    {
        if(score==0){
            this.scoreToLevelUp = 10+this.player.getCurrentLevel().getValue()*10;
            this.scoreWidth = GamePanel.getWIDTH()/this.scoreToLevelUp;
        }
        int width = 1+(int) (score*this.scoreWidth);
        outputBar = Bitmap.createBitmap(barImage, 0, 0,
                width, barImage.getHeight());
        //this.animateScore();
    }

    public void draw(Canvas canvas)
    {
        canvas.drawBitmap(outputBar, 0, 0, null);
        canvas.drawBitmap(frame, 0, 0, null);
        drawStrokedText("Score: " + scoreText, 500, 500, canvas);

    }

    private Paint getFillPaint(){
        Paint pnt = new Paint();
        pnt.setTextSize(this.textSize);

        Shader shader = new BitmapShader(pattern,
                Shader.TileMode.REPEAT, Shader.TileMode.REPEAT);
        pnt.setShader(shader);

        return pnt;
    }

    private Paint getStrokePaint(){
        Paint pnt = new Paint();
        pnt.setTextSize(this.textSize);
        pnt.setStyle(Paint.Style.STROKE);
        pnt.setStrokeWidth(2);
        pnt.setColor(Color.BLACK);

        return pnt;
    }

    private void drawStrokedText(String text,int x,int y, Canvas canvas){
        canvas.drawText(text, x, y, this.strokePnt);
        canvas.drawText(text, x, y, this.fillPnt);
    }
    private void setScoreText(String text){
        this.scoreText=text;
    }

    public void animateScore() {
                final ValueAnimator animValue = ValueAnimator.ofInt((int)ScoreContainer.getCurrentScore()-(int)ScoreContainer.getScoreToAdd(), (int)ScoreContainer.getCurrentScore());
                animValue.setInterpolator(new DecelerateInterpolator());
                animValue.setDuration(1000);
                animValue.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

                    @Override
                    public void onAnimationUpdate(ValueAnimator animation) {
                        setScoreText((String)animValue.getAnimatedValue());
                    }
                });
                animValue.start();
    }

}


