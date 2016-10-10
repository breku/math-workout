package com.breku.math;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.breku.math.googleplay.GoogleApiService;
import com.breku.math.mainmenu.MainMenuScreen;
import com.breku.math.screen.AbstractScreen;

public class MyGdxGame extends Game {
	SpriteBatch batch;
	Texture img;
	private static final String TAG = "MyGdxGame";
	private AbstractScreen currentScreen;

	private final GoogleApiService googleApiService;

	public MyGdxGame(GoogleApiService googleApiService) {
		this.googleApiService = googleApiService;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		currentScreen =new MainMenuScreen(googleApiService);
		setScreen(currentScreen);

	}


	private boolean touchedAlready = false;

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);


		batch.begin();
		currentScreen.render(Gdx.graphics.getDeltaTime());
		batch.end();


	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}
}
