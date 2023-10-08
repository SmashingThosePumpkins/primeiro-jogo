package br.com.pablito.entity;


import br.com.pablito.constants.CharacterSpriteState;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Personagem extends Sprite {
    public static final int MAX_JUMP_HEIGHT = 150;
    public static final float ANIMATION_DURATION = 0.2f;
    public static float currentAnimationTime;

    static Map<Integer, TextureRegion[]> spriteFrames = new HashMap<>();
    static Animation<TextureRegion> currentAnimation;

    public Personagem() {
        super();
        initializeCharacterSheet(4, 4);
    }

    private static void initializeCharacterSheet(int cols, int rows) {
        Texture friskSheet = new Texture(Gdx.files.internal("frisk_sprite_sheet.png"));

        TextureRegion[][] tmp = TextureRegion.split(friskSheet,
                friskSheet.getWidth() / cols,
                friskSheet.getHeight() / rows);

        spriteFrames.put(CharacterSpriteState.IDLE, new TextureRegion[]{tmp[0][0]});
        spriteFrames.put(CharacterSpriteState.LEFT, tmp[2]);
        spriteFrames.put(CharacterSpriteState.RIGHT, tmp[3]);

        currentAnimation = new Animation<>(ANIMATION_DURATION, spriteFrames.get(CharacterSpriteState.IDLE));
    }

    public TextureRegion update() {
        if (isJumping) {
            if (isJumpingUpward) {
                if (getY() <= startingHeight + MAX_JUMP_HEIGHT) {
                    setY(getY() + 10);
                } else {
                    isJumpingUpward = false;
                }
            }
        }

        if (!isJumpingUpward) {
            setY(getY() - 10);
        }

        TextureRegion currentFrame = currentAnimation.getKeyFrame(currentAnimationTime += Gdx.graphics.getDeltaTime(), true);
        return currentFrame;
    }

    private boolean isJumping;
    private boolean isJumpingUpward;
    private float startingHeight;

    public void jump() {
        if (!isJumping) {
            isJumping = true;
            isJumpingUpward = true;
            startingHeight = getY();
        }
    }

    public void walkLeft() {
        setX(getX() - 6);
        currentAnimation = new Animation<>(ANIMATION_DURATION, spriteFrames.get(CharacterSpriteState.LEFT));
    }

    public void walkRight() {
        setX(getX() + 6);
        currentAnimation = new Animation<>(ANIMATION_DURATION, spriteFrames.get(CharacterSpriteState.RIGHT));
    }

    public void idle() {
        currentAnimation = new Animation<>(ANIMATION_DURATION, spriteFrames.get(CharacterSpriteState.IDLE));
    }
}
