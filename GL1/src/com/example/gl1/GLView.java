package com.example.gl1;

import android.content.Context;
import android.opengl.GLSurfaceView;

public class GLView extends GLSurfaceView {
	
	private GLRenderer renderer;
	
	public GLView(Context context) {
		super(context);
		renderer = new GLRenderer();
		setRenderer(renderer);
	}
}
