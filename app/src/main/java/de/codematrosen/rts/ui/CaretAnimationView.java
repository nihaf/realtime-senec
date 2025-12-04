package de.codematrosen.rts.ui;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import de.codematrosen.rts.R;

/**
 * Custom view that displays animated caret symbols flowing horizontally.
 * Used to indicate energy flow direction in the grid section.
 * <p>
 * - Red carets flowing LEFT indicate energy IMPORT from grid
 * - Green (sage) carets flowing RIGHT indicate energy EXPORT to grid
 */
public class CaretAnimationView extends View {

    public enum Direction {
        LEFT,   // Import from grid
        RIGHT   // Export to grid
    }

    private static final int CARET_COUNT = 4;
    private static final float CARET_SIZE = 0.2f;  // Relative to view height
    private static final float CARET_STROKE_WIDTH = 6f;
    private static final long ANIMATION_DURATION = 2500L;

    private final Paint caretPaint;
    private final Path caretPath;

    private Direction direction = Direction.RIGHT;
    private float animationProgress = 0f;
    private ValueAnimator animator;

    public CaretAnimationView(Context context) {
        this(context, null);
    }

    public CaretAnimationView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CaretAnimationView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        caretPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        caretPaint.setStyle(Paint.Style.STROKE);
        caretPaint.setStrokeWidth(CARET_STROKE_WIDTH);
        caretPaint.setStrokeCap(Paint.Cap.ROUND);
        caretPaint.setStrokeJoin(Paint.Join.ROUND);
        caretPaint.setColor(context.getResources().getColor(R.color.gray_400, context.getTheme()));
        caretPath = new Path();
    }

    /**
     * Sets the direction of the caret animation.
     *
     * @param direction LEFT for import (red), RIGHT for export (green/sage)
     */
    public void setDirection(@NonNull Direction direction) {
        if (this.direction != direction) {
            this.direction = direction;
            invalidate();
        }
    }

    /**
     * Gets the current animation direction.
     */
    public Direction getDirection() {
        return direction;
    }

    /**
     * Starts the caret animation.
     */
    public void startAnimation() {
        if (animator != null && animator.isRunning()) {
            return;
        }

        animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(ANIMATION_DURATION);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.setRepeatMode(ValueAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.addUpdateListener(animation -> {
            animationProgress = (float) animation.getAnimatedValue();
            invalidate();
        });
        animator.start();
        setVisibility(VISIBLE);
    }

    /**
     * Stops the caret animation.
     */
    public void stopAnimation() {
        if (animator != null) {
            animator.cancel();
            animator = null;
        }
        setVisibility(GONE);
    }

    /**
     * Checks if the animation is currently running.
     */
    public boolean isAnimating() {
        return animator != null && animator.isRunning();
    }

    @Override
    protected void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();
        int height = getHeight();
        if (width == 0 || height == 0) {
            return;
        }

        float caretHeight = height * CARET_SIZE;
        float caretWidth = caretHeight * 0.6f;
        float spacing = width / (float) (CARET_COUNT);

        // Draw multiple carets at different positions
        for (int i = 0; i < CARET_COUNT + 1; i++) {
            float baseOffset;
            if (direction == Direction.RIGHT) {
                // Moving right: carets travel from left to right
                baseOffset = (animationProgress * spacing) + (i * spacing) - spacing;
            } else {
                // Moving left: carets travel from right to left
                baseOffset = width - (animationProgress * spacing) - (i * spacing) + spacing;
            }

            // Calculate alpha based on position (fade at edges)
            float normalizedX = baseOffset / width;
            float alpha = calculateAlpha(normalizedX);

            if (alpha > 0) {
                caretPaint.setAlpha((int) (alpha * 180));  // Max alpha 180 for subtle effect
                drawCaret(canvas, baseOffset, height / 2f, caretWidth, caretHeight);
            }
        }
    }

    private float calculateAlpha(float normalizedX) {
        // Fade in/out at edges
        float fadeZone = 0.25f;
        if (normalizedX < fadeZone) {
            return normalizedX / fadeZone;
        } else if (normalizedX > (1 - fadeZone)) {
            return (1 - normalizedX) / fadeZone;
        }
        return 1f;
    }

    private void drawCaret(Canvas canvas, float centerX, float centerY, float width, float height) {
        caretPath.reset();

        if (direction == Direction.RIGHT) {
            // Draw > shape
            caretPath.moveTo(centerX - width / 2, centerY - height / 2);
            caretPath.lineTo(centerX + width / 2, centerY);
            caretPath.lineTo(centerX - width / 2, centerY + height / 2);
        } else {
            // Draw < shape
            caretPath.moveTo(centerX + width / 2, centerY - height / 2);
            caretPath.lineTo(centerX - width / 2, centerY);
            caretPath.lineTo(centerX + width / 2, centerY + height / 2);
        }

        canvas.drawPath(caretPath, caretPaint);
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        stopAnimation();
    }
}
