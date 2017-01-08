package com.mygdx.game.skills;

import java.util.ArrayList;

/**
 * Created by louie on 9/10/2016.
 */
public class SkillTree {

    ArrayList<Skill> skills;




    public SkillTree(){

        skills = new ArrayList<Skill>();
    }

    public String toString() {


        return null;
    }

    public void add(Skill skill){
        skills.add(skill);
    }

    public void add( int index, Skill skill){
        skills.add(index, skill);
    }

    public ArrayList<Skill> getSkills() {
        return skills;
    }

    public void setSkills(ArrayList<Skill> skills) {
        this.skills = skills;
    }

}
