package com.rustie.game.utils;

import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Mesh;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttribute;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g3d.utils.MeshBuilder;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;
import com.badlogic.gdx.utils.GdxRuntimeException;

/**
 * Created by rustie on 5/18/17.
 */


public class PolygonShapeDrawer extends MeshBuilder {
    private Texture texture;

    public PolygonShapeDrawer () {
        super();
        super.begin(
                new VertexAttributes(new VertexAttribute(VertexAttributes.Usage.Position, 2, ShaderProgram.POSITION_ATTRIBUTE), VertexAttribute
                        .ColorPacked(), VertexAttribute.TexCoords(0)), GL20.GL_TRIANGLES);
    }

    @Override
    public Mesh end () {
        throw new GdxRuntimeException("Not supported!");
    }

    @Override
    public Mesh end (Mesh mesh) {
        throw new GdxRuntimeException("Not supported!");
    }

    public void setTextureRegion (TextureRegion region) {
        if (getNumIndices() > 0)
            throw new GdxRuntimeException("Cannot change the TextureRegion in while creating a shape, call draw first.");
        texture = region.getTexture();
        setUVRange(region);
    }

    public void draw (PolygonSpriteBatch batch) {
        if (texture == null)
            throw new GdxRuntimeException("No texture specified, call setTextureRegion before creating the shape");
        batch.draw(texture, getVertices(), 0, getNumVertices() * getFloatsPerVertex(), getIndices(), 0, getNumIndices());
        clear();
    }
}