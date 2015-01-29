package com.example.gl1;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.nio.ShortBuffer;

import javax.microedition.khronos.opengles.GL10;

public class MyIcosa {
	
	private final FloatBuffer posBuffer;
	private final ShortBuffer indexBuffer;
	private final FloatBuffer nomalBuffer;
	
	public MyIcosa(){
		
		//Golden Number
		final float g = (float)((1.0 + Math.sqrt(5))/2.0);
		
		float positions[] = {
			0, g/2.0f, -0.5f,
			0, g/2.0f, 0.5f,
			0, -g/2.0f, 0.5f,
			0, -g/2.0f, -0.5f,
			-g/2.0f, 0.5f, 0,
			g/2.0f, 0.5f, 0,
			g/2.0f, -0.5f, 0,
			-g/2.0f, -0.5f, 0,
			-0.5f, 0, g/2.0f,
			0.5f, 0, g/2.0f,
			0.5f, 0, -g/2.0f,
			-0.5f, 0, -g/2.0f,
		};
				
	    ByteBuffer vbb = ByteBuffer.allocateDirect(positions.length * 4);
	    vbb.order(ByteOrder.nativeOrder());
	    posBuffer = vbb.asFloatBuffer();
	    posBuffer.put(positions);
	    posBuffer.position(0);
	    
		short indices[] = {
			0, 1, 4,
			0, 1, 5,
			0, 4, 11,
			0, 5, 10,
			0, 10, 11,
			1, 4, 8,
			1, 5, 9,
			1, 8, 9,
			2, 3, 6,
			2, 3, 7,
			2, 6, 9,
			2, 7, 8,
			2, 8, 9,
			3, 6, 10,
			3, 7, 11,
			3, 10, 11,
			4, 7, 8,
			4, 7, 11,
			5, 6, 9,
			5, 6, 10,
		};
		
	    vbb = ByteBuffer.allocateDirect(indices.length * 4);
	    vbb.order(ByteOrder.nativeOrder());
	    indexBuffer = vbb.asShortBuffer();
	    indexBuffer.put(indices);
	    indexBuffer.position(0);
	    
	    //立体の中心が原点だから頂点座標がそのまま点法線ベクトルになるんじゃないかと思った。正規化しなくても大丈夫っぽい？
	    float normals[] = positions.clone();
	    
	    vbb = ByteBuffer.allocateDirect(normals.length * 4);
	    vbb.order(ByteOrder.nativeOrder());
	    nomalBuffer = vbb.asFloatBuffer();
	    nomalBuffer.put(normals);
	    nomalBuffer.position(0);
	    
	}
	
	public void draw(GL10 gl){
				
	    gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
	    
	    gl.glEnableClientState(GL10.GL_NORMAL_ARRAY);
	    
	    gl.glNormalPointer(GL10.GL_FLOAT, 0, nomalBuffer);
	    
	    gl.glVertexPointer(3, GL10.GL_FLOAT, 0, posBuffer);
	    
	    gl.glDrawElements(GL10.GL_TRIANGLE_STRIP, 60, GL10.GL_UNSIGNED_SHORT, indexBuffer);
	    
	}
	
}
