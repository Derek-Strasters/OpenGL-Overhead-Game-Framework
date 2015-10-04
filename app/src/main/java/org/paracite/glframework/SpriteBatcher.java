package org.paracite.glframework;

import javax.microedition.khronos.opengles.GL10;

import org.paracite.glframework.core.Graphics;
import org.paracite.glframework.math.Vector2D;


public class SpriteBatcher {
	final float[] verticesBuffer;
	int bufferIndex;
	final Vertices vertices;
	int numSprites;

	public SpriteBatcher(Graphics graphics, int maxSprites) {
		this.verticesBuffer = new float[maxSprites * 4 * 4];
		this.vertices = new Vertices(graphics, maxSprites * 4, maxSprites * 6, false, true);
		this.bufferIndex = 0;
		this.numSprites = 0;
		short[] indices = new short[maxSprites * 6];
		int len = indices.length;
		short j = 0;
		for (int i = 0; i < len; i += 6, j += 4) {
			indices[i + 0] = (short) (j + 0);
			indices[i + 1] = (short) (j + 1);
			indices[i + 2] = (short) (j + 2);
			indices[i + 3] = (short) (j + 2);
			indices[i + 4] = (short) (j + 3);
			indices[i + 5] = (short) (j + 0);
		}
		vertices.setIndices(indices, 0, indices.length);
	}

	public void beginBatch(Texture texture) {
		texture.bind();
		numSprites = 0;
		bufferIndex = 0;
	}

	public void endBatch() {
		if (numSprites > 0) {
			vertices.setVertices(verticesBuffer, 0, bufferIndex);
			vertices.bind();
			vertices.draw(GL10.GL_TRIANGLES, 0, numSprites * 6);
			vertices.unbind();
		}
	}
	
	public void drawSprite(float x, float y, float width, float height, TextureRegion region) {
		float halfWidth = width / 2;
		float halfHeight = height / 2;

		if(region.rotation == 0) {

		float x1 = x - halfWidth;
		float y1 = y - halfHeight;
		float x2 = x + halfWidth; 
		float y2 = y + halfHeight;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v1;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v1;
		
		numSprites++; 
		}else{
			float x1;
			float y1;
			float x2;
			float y2;
			float x3;
			float y3;
			float x4;
			float y4;
			
			switch(region.rotation) {
			case 1:		
				x1 = x + halfHeight;
				y1 = y - halfWidth;
				x2 = x + halfHeight; 
				y2 = y + halfWidth;
				x3 = x - halfHeight;
				y3 = y + halfWidth;
				x4 = x - halfHeight;
				y4 = y - halfWidth;
				break;
			case 2:
				x1 = x + halfWidth;
				y1 = y + halfHeight;
				x2 = x - halfWidth; 
				y2 = y + halfHeight;
				x3 = x - halfWidth;
				y3 = y - halfHeight;
				x4 = x + halfWidth;
				y4 = y - halfHeight;
				break;
			case 3:
				x1 = x - halfHeight;
				y1 = y + halfWidth;
				x2 = x - halfHeight; 
				y2 = y - halfWidth;
				x3 = x + halfHeight;
				y3 = y - halfWidth;
				x4 = x + halfHeight;
				y4 = y + halfWidth;
				break;
			default:			
				x1 = x - halfWidth;
				y1 = y - halfHeight;
				x2 = x + halfWidth; 
				y2 = y - halfHeight;
				x3 = x + halfWidth;
				y3 = y + halfHeight;
				x4 = x - halfWidth;
				y4 = y + halfHeight;
				break;		
			};
			
			verticesBuffer[bufferIndex++] = x1;
			verticesBuffer[bufferIndex++] = y1;
			verticesBuffer[bufferIndex++] = region.u1;
			verticesBuffer[bufferIndex++] = region.v2;
			
			verticesBuffer[bufferIndex++] = x2;
			verticesBuffer[bufferIndex++] = y2;
			verticesBuffer[bufferIndex++] = region.u2;
			verticesBuffer[bufferIndex++] = region.v2;
			
			verticesBuffer[bufferIndex++] = x3;
			verticesBuffer[bufferIndex++] = y3;
			verticesBuffer[bufferIndex++] = region.u2;
			verticesBuffer[bufferIndex++] = region.v1;
			
			verticesBuffer[bufferIndex++] = x4;
			verticesBuffer[bufferIndex++] = y4;
			verticesBuffer[bufferIndex++] = region.u1;
			verticesBuffer[bufferIndex++] = region.v1;
			
			numSprites++;
		}
	}
	
	public void drawSprite(float x, float y, float width, float height, float angle, TextureRegion region) {
		float halfWidth = width / 2;
		float halfHeight = height / 2;
		
		float rad = angle * Vector2D.TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		float x1 = -halfWidth * cos + halfHeight * sin;
		float y1 = -halfWidth * sin - halfHeight * cos;
		float x2 =  halfWidth * cos + halfHeight * sin;
		float y2 =  halfWidth * sin - halfHeight * cos;
		float x3 =  halfWidth * cos - halfHeight * sin;
		float y3 =  halfWidth * sin + halfHeight * cos;
		float x4 = -halfWidth * cos - halfHeight * sin;
		float y4 = -halfWidth * sin + halfHeight * cos;
		
		x1 += x;
		y1 += y;
		x2 += x;
		y2 += y;
		x3 += x;
		y3 += y;
		x4 += x;
		y4 += y;
		
		verticesBuffer[bufferIndex++] = x1;
		verticesBuffer[bufferIndex++] = y1;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x2;
		verticesBuffer[bufferIndex++] = y2;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v2;
		
		verticesBuffer[bufferIndex++] = x3;
		verticesBuffer[bufferIndex++] = y3;
		verticesBuffer[bufferIndex++] = region.u2;
		verticesBuffer[bufferIndex++] = region.v1;
		
		verticesBuffer[bufferIndex++] = x4;
		verticesBuffer[bufferIndex++] = y4;
		verticesBuffer[bufferIndex++] = region.u1;
		verticesBuffer[bufferIndex++] = region.v1;
		
		numSprites++;
	}
	
}
