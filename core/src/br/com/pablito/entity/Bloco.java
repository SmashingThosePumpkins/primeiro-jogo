package br.com.pablito.entity;

import br.com.pablito.constants.BlockType;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Bloco extends Sprite {
    BlockType blockType;

    public Bloco(BlockType blockType) {
        this.blockType = blockType;
        Texture texture = new Texture(Gdx.files.internal(blockType.getTextureName()));
        texture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.ClampToEdge);
        this.setTexture(texture);
    }
}
