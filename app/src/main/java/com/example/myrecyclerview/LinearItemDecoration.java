package com.example.myrecyclerview;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.view.View;

import androidx.recyclerview.widget.RecyclerView;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;
    private int radius = 18;
    private int radiusSolid = 6;
    private int line = 4;

    public LinearItemDecoration() {
        mPaint = new Paint();
        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
    }

    public void onDraw(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int childCount = parent.getChildCount();
        RecyclerView.LayoutManager manager = parent.getLayoutManager();
//        for (int i = 0; i < childCount; i++) {
//            View child = parent.getChildAt(i);
//            //动态获取outRect的left值
//            int left = manager.getLeftDecorationWidth(child);
//            int cx = left / 2;
//            int cy = child.getTop() + child.getHeight() / 2;
//            c.drawCircle(cx, cy, 20, mPaint);
//        }
        int count = manager.getChildCount();
        for (int i = 0; i < childCount; i++) {
            View child = parent.getChildAt(i);
            int left = manager.getLeftDecorationWidth(child);
            int cx = left / 2;
            int cy = child.getTop() + child.getHeight() / 2;
            int index = parent.getChildAdapterPosition(child);
            if (index == 0) {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(Color.parseColor("#ACABAB"));
                mPaint.setStrokeWidth(line);
                c.drawCircle(cx, cy, radius, mPaint);

                mPaint.setColor(Color.parseColor("#2E44B3"));
                int startX = cx;
                int endX = cx;
                int startY = cy + +radius + line;
                int endY = child.getTop() + child.getHeight();
                c.drawLine(startX, startY, endX, endY, mPaint);
            } else if (index == 1 && count > 2) {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(Color.parseColor("#2E44B3"));
                mPaint.setStrokeWidth(line);

                //画上半部分的线
                int startX = cx;
                int endX = cx;
                int startY = child.getTop();
                int endY = startY + child.getHeight() / 2 - radius;
                c.drawLine(startX, startY, endX, endY, mPaint);

                //画外边的圆圈
                c.drawCircle(cx, cy, radius, mPaint);

                //画里边的实心圆
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mPaint.setStrokeWidth(0);
                c.drawCircle(cx, cy, radiusSolid, mPaint);

                //画下班部分的线
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(line);
                startX = cx;
                endX = cx;
                startY = cy + +radius;
                endY = child.getTop() + child.getHeight();
                c.drawLine(startX, startY, endX, endY, mPaint);
            } else {
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setColor(Color.parseColor("#2E44B3"));
                mPaint.setStrokeWidth(line);

                //画上半部分的线
                int startX = cx;
                int endX = cx;
                int startY = child.getTop();
                int endY = startY + child.getHeight() / 2 - radius;
                c.drawLine(startX, startY, endX, endY, mPaint);

                //画实心圆
                mPaint.setStyle(Paint.Style.FILL_AND_STROKE);
                mPaint.setStrokeWidth(0);
                c.drawCircle(cx, cy, radius, mPaint);

                //画下班部分的线
                mPaint.setStyle(Paint.Style.STROKE);
                mPaint.setStrokeWidth(line);
                startX = cx;
                endX = cx;
                startY = cy + +radius;
                endY = child.getTop() + child.getHeight();
                c.drawLine(startX, startY, endX, endY, mPaint);
            }

        }
    }


    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.left = 200;
    }
}


