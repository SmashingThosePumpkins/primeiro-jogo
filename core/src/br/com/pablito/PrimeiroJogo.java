package br.com.pablito;

import br.com.pablito.entity.Bloco;
import br.com.pablito.entity.Personagem;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.ScreenUtils;

import static br.com.pablito.constants.BlockType.GRASS;

public class PrimeiroJogo extends ApplicationAdapter {
    OrthographicCamera cam;
    SpriteBatch batch;

    Personagem personagem;
    Bloco blocoDeGrama;


    @Override
    public void create() {
        batch = new SpriteBatch();
        personagem = new Personagem();
        blocoDeGrama = new Bloco(GRASS);

        cam = new OrthographicCamera();

        cam = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        cam.position.set(cam.viewportWidth / 2f, cam.viewportHeight / 2f, 0);
        cam.update();
    }

    @Override
    public void render() {
        handleInput();
        ScreenUtils.clear(0, 0, 0, 1);
        cam.update();
        batch.setProjectionMatrix(cam.combined);

        batch.begin();
        batch.draw(personagem.update(), personagem.getX(), personagem.getY(), 85, 120);
        batch.draw(blocoDeGrama.getTexture(), 0, 0, cam.viewportWidth, 50);
        batch.end();
    }

    private void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)) {
            personagem.walkRight();
            System.out.println("right");
        } else if (Gdx.input.isKeyPressed(Input.Keys.LEFT)) {
            personagem.walkLeft();
        } else {
            personagem.idle();
        }
        if (Gdx.input.isKeyJustPressed(Input.Keys.UP)) {
            personagem.jump();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
    }
}
