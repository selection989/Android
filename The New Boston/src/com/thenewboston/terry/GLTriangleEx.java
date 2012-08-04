package com.thenewboston.terry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class GLTriangleEx {
	private final float vertices[] = { 0f, 1f,// p0
			1f, -1f,// p1
			-1f, -1f // p2

	};

	private final FloatBuffer vertBuff;
	private final short[] pIndex = { 0, 1, 2 };
	private final ShortBuffer pBuffer;

	public GLTriangleEx() {
		ByteBuffer bBuff = ByteBuffer.allocateDirect(vertices.length * 4);
		bBuff.order(ByteOrder.nativeOrder());
		vertBuff = bBuff.asFloatBuffer();
		vertBuff.put(vertices);
		vertBuff.position(0);
		ByteBuffer pBBuff = ByteBuffer.allocate(pIndex.length * 2);
		pBBuff.order(ByteOrder.nativeOrder());
		pBuffer = pBBuff.asShortBuffer();
		pBuffer.put(pIndex);
		pBuffer.position(0);

	}

	public void draw(GL10 gl) {
		gl.glFrontFace(GL10.GL_CW);
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, vertBuff);
		gl.glDrawElements(GL10.GL_TRIANGLES, pIndex.length,
				GL10.GL_UNSIGNED_SHORT, pBuffer);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}
