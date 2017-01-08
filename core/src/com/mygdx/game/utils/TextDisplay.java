package com.mygdx.game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

/**
 * Created by louie on 8/2/2016.
 */
public class TextDisplay extends Actor{

    Sound sound;
    Texture texture;
    BitmapFont font;
    String text;
    String currentText;         // The String object that the display is looping over to create a new String object
    String finalText;
    Table table;


    Vector2 position;           // For the position of the TextDisplay

    Vector2 textPos;            // For the position of the text within the display
    int textXOffset;            // Horizontal offset for the text in the display
    int textYOffset;            // Vertical offset for the text in the display

    // the original textYoffset
    static final int OG_Y_OFFSET = 190;

    boolean hasNewText;         // Boolean that holds if the TextDisplay object is being updated with new text
    boolean isVisible;          // Boolean that holds if the TextDisplay object is visible

    int textCounter;            // place holder for the reiteration of the text that is being displayed
    int linelength;             // how many characters can be in a single line
    int maxlines;                // how many lines can be in the TextDisplay
    int lineAmount;             // how many lines are in the TextDisplay currently
    int maxCharInLine;          // holds the Max characters that can be in a line.
    float counter;              // counter is how many seconds have passed since the last character in TextDisplay was drawn

    public TextDisplay(BitmapFont font, Screen screen, Table table, int mcl, int ml){
        this.font = font;
        this.table = table;
        this.maxCharInLine = mcl;
        this.maxlines = ml;

        // Set variables for the position of the TextDisplay
        position = new Vector2(Gdx.graphics.getWidth() / 5,Gdx.graphics.getHeight() / 2.25f);
        texture = new Texture(Gdx.files.internal("TextArea.png"));
        hasNewText = false;

        // Set variables for the position of the text within the Text Display
        textXOffset = 10;
        textYOffset = 190;
        textPos = new Vector2(position.x + textXOffset, position.y + textYOffset);

        isVisible = true;
        counter = 0;
        textCounter = 0;
        currentText = text;
        linelength = 0;
        lineAmount = 0;

        text = "";
        finalText = "";

        sound = Gdx.audio.newSound(Gdx.files.internal("sounds/textDisplay/TextSound3.wav"));



    }

    public void addText(String text){
        this.text = "";
        finalText = "";
        textPos.y = position.y + OG_Y_OFFSET;
        this.currentText = text;
        lineAmount = 1;
        counter = 0;
        textCounter = 0;
        hasNewText = true;
        this.text = "";
        int textLength = currentText.length();
        int lineLength = 0;
        for(int i = 0; i < textLength; i++){
            if(lineLength >= maxCharInLine && currentText.charAt(i) == ' '){
                finalText += currentText.charAt(i);
                finalText += '\n';
                lineLength = 0;
            }
            else{
                finalText += currentText.charAt(i);
                lineLength++;
            }
        }
    }

    public void update(float dt){
        counter += dt;
        if(hasNewText && counter >= .0002f){
            //sound.play(.3f);
            if(linelength >= maxCharInLine && currentText.charAt(textCounter) == ' '){      // Executed when the text reaches the maximum characters for a line
                if(lineAmount >= maxlines){
                    textPos.y += 35;
                }
                this.text += currentText.charAt(textCounter);
                this.text += '\n';
                lineAmount += 1;
                counter = 0;
                linelength = 0;

            }
            else{
                this.text += currentText.charAt(textCounter);
                linelength += 1;
                counter = 0;
            }

            if(text.equals(finalText)){
                finalText = "";
                currentText = "";
                counter = 0;
                textCounter = 0;
                linelength = 0;
                hasNewText = false;
            }

            textCounter += 1;
        }
    }

    @Override
    public void draw(Batch batch, float parenAlpha) {
        if(isVisible) {
            batch.draw(texture, position.x, position.y);
            font.draw(batch, text, textPos.x, textPos.y);
        }
    }

    public BitmapFont getFont() {
        return font;
    }

    public void setFont(BitmapFont font) {
        this.font = font;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Table getTable() {
        return table;
    }

    public void setTable(Table table) {
        this.table = table;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public String getCurrentText() {
        return currentText;
    }

    public void setCurrentText(String currentText) {
        this.currentText = currentText;
    }

    public boolean isHasNewText() {
        return hasNewText;
    }

    public void setHasNewText(boolean hasNewText) {
        this.hasNewText = hasNewText;
    }

    public float getCounter() {
        return counter;
    }

    public void setCounter(float counter) {
        this.counter = counter;
    }

    public String getFinalText() {
        return finalText;
    }

    public void setFinalText(String finalText) {
        this.finalText = finalText;
    }

    public int getLinelength() {
        return linelength;
    }

    public void setLinelength(int linelength) {
        this.linelength = linelength;
    }

    public int getTextCounter() {
        return textCounter;
    }

    public void setTextCounter(int textCounter) {
        this.textCounter = textCounter;
    }

    public int getMaxCharInLine() {
        return maxCharInLine;
    }

    public void setMaxCharInLine(int maxCharInLine) {
        this.maxCharInLine = maxCharInLine;
    }

    public Vector2 getTextPos() {
        return textPos;
    }

    public void setTextPos(Vector2 textPos) {
        this.textPos = textPos;
    }

    public int getTextYOffset() {
        return textYOffset;
    }

    public void setTextYOffset(int textYOffset) {
        this.textYOffset = textYOffset;
    }

    public int getTextXOffset() {
        return textXOffset;
    }

    public void setTextXOffset(int textXOffset) {
        this.textXOffset = textXOffset;
    }

    public int getMaxlines() {
        return maxlines;
    }

    public void setMaxlines(int maxlines) {
        this.maxlines = maxlines;
    }

    public int getLineAmount() {
        return lineAmount;
    }
}
