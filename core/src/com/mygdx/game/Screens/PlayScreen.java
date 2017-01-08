package com.mygdx.game.Screens;

        import com.badlogic.gdx.Game;
        import com.badlogic.gdx.Gdx;
        import com.badlogic.gdx.Screen;
        import com.badlogic.gdx.graphics.g2d.BitmapFont;
        import com.badlogic.gdx.graphics.g2d.SpriteBatch;
        import com.badlogic.gdx.scenes.scene2d.InputEvent;
        import com.badlogic.gdx.scenes.scene2d.Stage;
        import com.badlogic.gdx.scenes.scene2d.ui.Skin;
        import com.badlogic.gdx.scenes.scene2d.ui.Table;
        import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
        import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
        import com.badlogic.gdx.utils.Align;
        import com.mygdx.game.MyGdxGame;
        import com.mygdx.game.creatures.Hero;
        import com.mygdx.game.story.StoryManager;
        import com.mygdx.game.utils.TextDisplay;


/**
 * Created by louie on 7/27/2016.
 */
public class PlayScreen implements Screen{

    ScreenManager manager;
    SpriteBatch batch;
    Game game;
    Table table;
    Skin skin;
    public Stage stage;
    BitmapFont font;
    BitmapFont redFont;

    TextButton scrollUp;
    TextButton scrollDown;
    TextButton button;
    TextButton hidedisplayButton;
    TextButton menuButton;

    TextDisplay display;

    String str;
    public Hero hero;
    StoryManager storyManager;


    public PlayScreen(MyGdxGame game, SpriteBatch batch, ScreenManager manager){

        this.game = game;
        this.batch = batch;
        this.manager = manager;

        str = "";


        //instantiating Stage, Table, BitmapFont, and Skin objects.
        redFont = new BitmapFont(Gdx.files.internal("TestFontRed.fnt"));
        font = new BitmapFont(Gdx.files.internal("TestFont.fnt"));
        table = new Table();
        stage = new Stage();
        skin = new Skin(Gdx.files.internal("Basic Skin.json"));


        //Create some Buttons
        scrollUp = new TextButton("Scroll up", skin, "default");
        scrollDown = new TextButton("Scroll down", skin, "default");

        hidedisplayButton = new TextButton("Hide", skin, "default");
        button = new TextButton("Start!", skin, "default");
        menuButton = new TextButton("Menu", skin, "default");



        //Add some listeners to the buttons
        addListeners();


        display = new TextDisplay(font, this, table, 35, 6);

        //Set the properties for the stage
        SetStageProperties();
        //table.debug();


    }

    public void start(){
        storyManager = new StoryManager(manager, display, hero, game, batch);
    }


    private void update(float dt){
        display.update(dt);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

        update(delta);

        batch.begin();
        stage.draw();
        batch.end();
    }

    private void addListeners() {
        hidedisplayButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (display.isVisible() == true) {
                    display.setVisible(false);
                    hidedisplayButton.setText("Show");
                } else {
                    display.setVisible(true);
                    hidedisplayButton.setText("Hide");
                }
            }

        });



        button.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if (!display.isHasNewText()) {
                    storyManager.getCurrentStory().progress();
                }
            }
        });

        scrollUp.addListener(new ClickListener(){
           @Override
            public void clicked(InputEvent event, float x, float y){
               if(!(display.isHasNewText())){
                   display.getTextPos().y -= 20;
               }
           }
        });

        scrollDown.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                if(!(display.isHasNewText())){
                    display.getTextPos().y += 20;
                }
            }
        });

        menuButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y){
                game.setScreen(manager.getMenu());
                Gdx.input.setInputProcessor(manager.getMenu().stage);

            }

        });

    }

    private void SetStageProperties() {

        //Setting table properties
        table.setWidth(stage.getWidth());
        table.setHeight(stage.getHeight() / -1.5f);
        table.align(Align.center|Align.top);
        table.setPosition(0, Gdx.graphics.getHeight());
        table.padTop(30);

        //Adding actors to table
        table.add(display);
        table.add(button).padBottom(50);
        table.add(hidedisplayButton).padBottom(50).padLeft(25);
        table.add(scrollUp).padBottom(50).padLeft(25);
        table.add(scrollDown).padBottom(50).padLeft(25);
        table.add(menuButton).padBottom(50).padLeft(25);

        //Adding table to stage and setting stage as the input processor
        stage.addActor(table);
        Gdx.input.setInputProcessor(stage);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public Hero getHero() {
        return hero;
    }

    public void setHero(Hero hero) {
        this.hero = hero;
    }

    public StoryManager getStoryManager() {
        return storyManager;
    }

    public void setStoryManager(StoryManager storyManager) {
        this.storyManager = storyManager;
    }
}
