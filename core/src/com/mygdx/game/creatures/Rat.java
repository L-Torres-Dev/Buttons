package com.mygdx.game.creatures;

import com.mygdx.game.skills.PhysicalSkill;
import com.mygdx.game.skills.Skill;
import com.mygdx.game.skills.SkillType;
import com.mygdx.game.skills.specificSkills.Strike;
import com.mygdx.game.skills.specificSkills.creaturespecificSkills.PoisonousStrike;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by louie on 9/11/2016.
 */
public class Rat extends Creature {

    Random randNum = new Random();
    boolean hit;                //Holds true if the hero hits the enemy successfully
    boolean crit;               //Holds true if the hero hits and the hit was a critical hit.

    public Rat(){


        name = "Rat";
        level = 1;
        baseStatPoints = 15 + (level * 5);
        base_health = 20;
        this.calculateStats(level, baseStatPoints);

        base_health += (3 * level);
        base_strength += 3;
        base_defense += 1;
        base_arcana += 2;
        base_resistance += 1;
        base_speed += 8;
        expGive += (8 * level);

        current_health = base_health;




        chosenSkill = skillSet.get(0);

    }

    public Rat(int level){

        name = "Rat";
        super.level = level;
        baseStatPoints = 15 + (level * 5);
        base_health = 4;
        base_stamina = 8;
        base_mana = 3;
        goldAmount = 2 * level;
        attributePoints = (3 * level) - 3;

        this.calculateStats(level, baseStatPoints);
        this.calculateAttributes(level, attributePoints);

        skillSet = new ArrayList<Skill>();

        base_health += (3 * level);
        base_stamina += (4 * level);
        base_mana += (2 * level);

        base_strength += 2;
        base_defense += 1;
        base_arcana += 1;
        base_resistance += 1;
        base_speed += 2;
        base_luck += 2;

        expGive += (8 * level);

        current_health = base_health;

        update();
        createSkills();

        chosenSkill = skillSet.get(0);
    }

    private void specialDmgCases(double dmg, Character enemy){
        if(dmg > enemy.getCurrent_health()){
            dmg = enemy.getCurrent_health();
        }

        if(dmg < 1){
            dmg = 1;
        }

        enemy.setCurrent_health(enemy.getCurrent_health() - dmg);

        if(enemy.getCurrent_health() < 1 && enemy.getCurrent_health() > 0){
            enemy.setCurrent_health(1);
        }

        if(enemy.getCurrent_health() < 0){
            enemy.setCurrent_health(0);
        }

    }
    @Override
    public void chooseSkill(){
        super.chooseSkill();


        ArrayList<Skill> possibleSkills = new ArrayList<Skill>(4);

        for(Skill skill : skillSet){
            int counter = 0;
            if(skill.checkCosts()){
                possibleSkills.add(counter, skill);
                counter++;
            }
        }

        Skill possibleSkill = possibleSkills.get(0);
        Skill chosenSkill = possibleSkills.get(0);

        for(Skill skill: possibleSkills){
            if(skill.getPower() > possibleSkill.getPower()){
                chosenSkill = skill;
            }
        }

        this.chosenSkill = chosenSkill;
    }

    @Override
    public void attack(Character enemy) {
        double dmg = 0;
        double heal = 0;

        chooseSkill();

        if(chosenSkill.getPower() > 0){
            chosenSkill.use(this, enemy);

        }
    }

    private void createSkills() {

        PhysicalSkill strike = new Strike(this);
        skillTree.add(strike);

        if(strike.getReqLvl() <= this.level){
            skillSet.add(strike);
        }

        PhysicalSkill poisonStrike = new PoisonousStrike(this);
        poisonStrike.setReqLvl(1);
        skillTree.add(poisonStrike);

        if(strike.getReqLvl() <= this.level){
            skillSet.add(poisonStrike);
        }
    }

    @Override
    public String toString() {
        return super.toString();
    }

    /**
     * The calculateAttributes method takes the level
     * of the Rat Object and its attributPoints and increases
     * its attributes based on its level and attributePoints
     * @param lvl the level of the Rat Object
     * @param points the attributePoints of the Rat Object
     */
    protected void calculateAttributes(int lvl, int points){

        boolean statistic = true;
        while(points > 0 && statistic == true){

            int numChanged;

            numChanged = randNum.nextInt(3);
            numChanged = TestPoints(points, numChanged);
            base_health += numChanged;
            points -= numChanged;

            statistic = checkStatistic(points);
            if(statistic == false)
                break;

            numChanged = randNum.nextInt(3) + 1;
            numChanged = TestPoints(points, numChanged);
            base_stamina += numChanged;
            points -= numChanged;

            statistic = checkStatistic(points);
            if(statistic == false)
                break;

            numChanged = randNum.nextInt(2);
            numChanged = TestPoints(points, numChanged);
            base_mana += numChanged;
            points -= numChanged;

            statistic = checkStatistic(points);
            if(statistic == false)
                break;
        }

    }

    /**
     * the calculateStats method takes the level of the
     * Rat Object and its StatPoints and periodically increases
     * its stats based on it's level and StatPoints.
     * @param l the level of the Rat Object
     * @param sp the StatPoints of the Rat Object.
     */

    protected void calculateStats(int l, int sp)
    {

        boolean statistic = true;           //Boolean to hold whether the loop should continue
        while (sp > 0 && statistic == true)
        {
            int numChanged;

            // for Strength stat
            numChanged = randNum.nextInt(4) + 2;
            numChanged = TestPoints(sp, numChanged);
            base_strength += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;

            // for Speed stat
            numChanged = randNum.nextInt(5) + 3;
            numChanged = TestPoints(sp, numChanged);
            base_speed += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;

            // for Luck stat
            numChanged = randNum.nextInt(5) + 3;
            numChanged = TestPoints(sp, numChanged);
            base_luck += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;

            // for Defense stat
            numChanged = randNum.nextInt(2) + 1;
            numChanged = TestPoints(sp, numChanged);
            base_defense += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;

            // for Arcana stat
            numChanged = randNum.nextInt(2) + 0;
            numChanged = TestPoints(sp, numChanged);
            base_arcana += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;

            // for Resistance stat
            numChanged = randNum.nextInt(2) + 0;
            numChanged = TestPoints(sp, numChanged);
            base_resistance += numChanged;
            sp -= numChanged;

            statistic = checkStatistic(sp);
            if (statistic == false)
                break;




        }
        baseStatPoints = sp;

    }

    /**
     * The checkStatistic method takes the current amount amount of
     * StatPoints that the Rat Object has and checks to see if it is
     * greater than 0. if it is, the method will return true.
     * @param points the StatPoints of the Rat Object.
     * @return the boolean value.
     */
    private boolean checkStatistic(double points)
    {
        boolean stat = false;

        if (points > 0)
        {
            stat = true;
//			System.out.println("Stat is true \n");
        }
        else
        {
            stat = false;
//			System.out.println("Stat is False \n");
        }

        return stat;
    }

    /**
     * The TestPoints method checks to see if the numchanged (that
     * is the number that will be incremented on the particular stat or attribute)
     * exceeds the amount of points that the creature currently has
     * during generation. This is to make sure that the creature does
     * not exceed the amount of total stat points allocated to it.
     */
    public int TestPoints(int points, int nc)
    {
        if (points < nc)
        {
            nc = points;
        }
        return nc;

    }


}
