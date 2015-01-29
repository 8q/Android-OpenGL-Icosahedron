package com.example.gl1;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.opengl.GLU;
import android.opengl.GLSurfaceView.Renderer;

public class GLRenderer implements Renderer {
	
	private MyIcosa icosa = new MyIcosa();
	
	private float angle = 0.0f;
	
	@Override
	public void onDrawFrame(GL10 gl) {
		
		//カラーバッファとデプスバッファを消去する。
	    gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
	    
	    //次の行列演算ターゲットをモデルビュー行列にする。
        gl.glMatrixMode(GL10.GL_MODELVIEW);
        
        //現在の行列を単位行列にする。
        gl.glLoadIdentity();
        
        //物体を平行移動をさせる。
        gl.glTranslatef(0, 0, -4f);
        
        if(++angle >= 360.0) angle = 0.0f;
        	//物体を回転させる。
        	gl.glRotatef(angle, 1, 1, 1);
        
        icosa.draw(gl);

	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
		//深度バッファテストを有効にする。
	    gl.glEnable(GL10.GL_DEPTH_TEST);
	    
	    //入力された深さ値が、格納されている深さ値以下の場合に成功する。
	    gl.glDepthFunc(GL10.GL_LEQUAL);
	    
	    //照明モデルを有効にする。
	    gl.glEnable(GL10.GL_LIGHTING);
	    
	    //GL_LIGHT0=(1,1,1,1)RGBA
	    gl.glEnable(GL10.GL_LIGHT0);
	    
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
		
		//スクリーン上のどの範囲に描画するかを決定する。
	    gl.glViewport(0, 0, width, height);
	    
	    //射影行列
	    gl.glMatrixMode(GL10.GL_PROJECTION);
	    
        //現在の行列を単位行列にする。
	    gl.glLoadIdentity();
	    
	    //視野角、奥行きの最大、最小距離を設定する。
	    GLU.gluPerspective(gl, 45f,(float) width / height, 1f, 50f);
	    
	    //RGBA #DAA520 (golden rod)
        gl.glClearColor(218.0f/255.0f, 165.0f/255.0f, 32.0f/255.0f, 1.0f);

	}

}
