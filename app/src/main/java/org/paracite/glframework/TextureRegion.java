package org.paracite.glframework;

public class TextureRegion {
	public final float u1, v1;
	public final float u2, v2;
	public final Texture texture;
	public final int rotation;

	public TextureRegion(Texture texture, float xUpperLeft, float yUpperLeft, float width, float height) {
		this.u1 = xUpperLeft / texture.width;
		this.v1 = yUpperLeft / texture.height;
		this.u2 = this.u1 + width / texture.width;
		this.v2 = this.v1 + height / texture.height;
		this.rotation = 0;
		this.texture = texture;
	}
	
	public TextureRegion(Texture texture, float xUpperLeft, float yUpperLeft, float width, float height, int rotation) {
		this.u1 = xUpperLeft / texture.width;
		this.v1 = yUpperLeft / texture.height;
		this.u2 = this.u1 + width / texture.width;
		this.v2 = this.v1 + height / texture.height;
		this.rotation = rotation;
		this.texture = texture;
	}
}
