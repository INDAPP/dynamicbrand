package it.fdc.dynamicbrand;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ricca on 08/07/2016.
 */

public class BrandImageView extends ImageView {
    private final static int POINT_REF = 15;
    private Paint mPointPaint, mAreasPaint, mPrinciplesPaint;
    private int mColorBlue, mColorOrange, mColorGreen;

    private RectF mBounds = new RectF();
    private RectF mBoundsInset = new RectF();
    private Rect mOverlayBounds = new Rect();
    private PointF mPoint1 = new PointF();
    private PointF mPoint8 = new PointF();
    private List<PointF> mPoints = new ArrayList<>();
    private List<Float> mPointsValue = new ArrayList<>();
    private String[] mWeight;

    private int[] mPrinciples = new int[] {};
    private boolean[] mAreas = new boolean[] {};

    public BrandImageView(Context context) {
        super(context);
        init();
    }

    public BrandImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public BrandImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BrandImageView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setColor(Color.BLACK);
        mPointPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mAreasPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mAreasPaint.setColor(ContextCompat.getColor(getContext(), R.color.brandAreas));
        mAreasPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mColorBlue = ContextCompat.getColor(getContext(), R.color.brandBlue);
        mColorOrange = ContextCompat.getColor(getContext(), R.color.brandOrange);
        mColorGreen = ContextCompat.getColor(getContext(), R.color.brandGreen);

        mPrinciplesPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPrinciplesPaint.setColor(mColorBlue);
        mPrinciplesPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        mWeight = getResources().getStringArray(R.array.principles_weight);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        int startLeft = parentWidth > parentHeight ? (parentWidth-parentHeight)/2 : 0;
        int startTop = parentHeight > parentWidth ? (parentHeight-parentWidth)/2 : 0;
        float size = Math.min(parentWidth, parentHeight);
        mBounds.set(startLeft, startTop, startLeft+size, startTop+size);
        mBoundsInset.set(mBounds);
        mBoundsInset.inset(size/10,size/10);
        mPoint1.set(mBoundsInset.left, mBoundsInset.top);
        mPoint8.set(mBoundsInset.centerX(), mBoundsInset.bottom);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float startAngle = 90;
        float sweepAngle = 90;

        for (boolean area : mAreas) {
            if (area)  sweepAngle += 22.5f;
            else if (sweepAngle > 0) {
                canvas.drawArc(mBoundsInset, startAngle, sweepAngle, true, mAreasPaint);
                startAngle += sweepAngle + 22.5f;
                sweepAngle = 0;
            } else
                startAngle += 22.5f;
        }
        if (sweepAngle > 0)
            canvas.drawArc(mBoundsInset, startAngle, sweepAngle, true, mAreasPaint);

        float unit = mBounds.width() / 10;
        int index = 0;
        mPoints.clear();
        mPointsValue.clear();
        for (int principle : mPrinciples) {
            if (principle > 0) {
                float x = mBounds.left + unit + index % 3 * unit * 4;
                float y = mBounds.top + unit + index / 3 * unit * 4;
                if (index != 0 && index != 7) {
                    mPoints.add(new PointF(x, y));
                    float weight = Float.valueOf(mWeight[index]);
                    mPointsValue.add(POINT_REF * weight * principle);
                }
            }
            index++;
        }

        Path triangle1, triangle2, triangle3;
        switch (mPoints.size()) {
            case 1:
                triangle1 = getTriangle(mPoint1, mPoint8, mPoints.get(0));
                triangle1.setFillType(Path.FillType.EVEN_ODD);
                mPrinciplesPaint.setColor(mColorBlue);
                canvas.drawPath(triangle1, mPrinciplesPaint);
                break;
            case 2:
                triangle1 = getTriangle(mPoint1, mPoint8, mPoints.get(0));
                triangle2 = getTriangle(mPoint8, mPoints.get(0), mPoints.get(1));
                mPrinciplesPaint.setColor(mColorBlue);
                canvas.drawPath(triangle1, mPrinciplesPaint);
                mPrinciplesPaint.setColor(mColorGreen);
                canvas.drawPath(triangle2, mPrinciplesPaint);
                break;
            case 3:
                triangle1 = getTriangle(mPoint1, mPoint8, mPoints.get(0));
                triangle2 = getTriangle(mPoints.get(0), mPoints.get(1), mPoints.get(2));
                mPrinciplesPaint.setColor(mColorBlue);
                canvas.drawPath(triangle1, mPrinciplesPaint);
                mPrinciplesPaint.setColor(mColorOrange);
                canvas.drawPath(triangle2, mPrinciplesPaint);
                break;
            case 4:
                triangle1 = getTriangle(mPoint1, mPoint8, mPoints.get(0));
                triangle2 = getTriangle(mPoints.get(0), mPoints.get(1), mPoints.get(2));
                triangle3 = getTriangle(mPoints.get(0), mPoints.get(1), mPoints.get(3));
                mPrinciplesPaint.setColor(mColorBlue);
                canvas.drawPath(triangle1, mPrinciplesPaint);
                mPrinciplesPaint.setColor(mColorOrange);
                canvas.drawPath(triangle2, mPrinciplesPaint);
                mPrinciplesPaint.setColor(mColorGreen);
                canvas.drawPath(triangle3, mPrinciplesPaint);
                break;
        }

        canvas.drawCircle(mPoint1.x, mPoint1.y, POINT_REF, mPointPaint);
        canvas.drawCircle(mPoint8.x, mPoint8.y, POINT_REF, mPointPaint);
        for (int i=0; i<mPoints.size(); i++) {
            PointF p = mPoints.get(i);
            float r = mPointsValue.get(i);
            canvas.drawCircle(p.x,p.y,r,mPointPaint);
        }

        Drawable overlay = ContextCompat.getDrawable(getContext(), R.drawable.tsr_overlay);
        mBoundsInset.round(mOverlayBounds);
        overlay.setBounds(mOverlayBounds);
        overlay.draw(canvas);
    }

    public void setData(int[] principles, boolean[] areas) {
        this.mPrinciples = principles;
        this.mAreas = areas;
        this.invalidate();
    }

    private Path getTriangle(PointF p1, PointF p2, PointF p3) {
        Path triangle = new Path();
        triangle.moveTo(p1.x,p1.y);
        triangle.lineTo(p2.x,p2.y);
//        triangle.moveTo(p2.x,p2.y);
        triangle.lineTo(p3.x, p3.y);
//        triangle.moveTo(p3.x, p3.y);
        triangle.lineTo(p1.x, p1.y);
        triangle.close();

        return triangle;
    }
}
