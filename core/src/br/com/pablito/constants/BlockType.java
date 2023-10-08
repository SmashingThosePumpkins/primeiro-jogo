package br.com.pablito.constants;

public enum BlockType {
    GRASS("grass.png");

    private final String textureName;

    BlockType(String textureName) {
        this.textureName = textureName;
    }

    public String getTextureName() {
        return textureName;
    }
}
