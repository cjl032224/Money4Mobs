package Latch.Money4Mobs;

import java.util.ArrayList;
import java.util.List;

public class SetMobList {

    private static List<MobModel> mobList = new ArrayList<MobModel>();

    public List<MobModel> getMobModel() {
        setMobWorth();
        return mobList;
    }
    public void setMobWorth(){
        mobList.add(new MobModel("Bat", 10, 10));
        mobList.add(new MobModel("Bee", 15, 15));
        mobList.add(new MobModel("Blaze", 25, 25));
        mobList.add(new MobModel("Cat", 1, 1));
        mobList.add(new MobModel("CaveSpider", 15, 15));
        mobList.add(new MobModel("Chicken", 2, 2));
        mobList.add(new MobModel("Cod", 5, 5));
        mobList.add(new MobModel("Cow", 2, 2));
        mobList.add(new MobModel("Creeper", 25, 25));
        mobList.add(new MobModel("Dolphin", 175, 175));
        mobList.add(new MobModel("Donkey", 100, 100));
        mobList.add(new MobModel("Drowned", 100, 100));
        mobList.add(new MobModel("ElderGuardian", 300, 300));
        mobList.add(new MobModel("EnderDragon", 10000, 10000));
        mobList.add(new MobModel("Enderman", 25, 25));
        mobList.add(new MobModel("Endermite", 300, 300));
        mobList.add(new MobModel("Evoker", 200, 200));
        mobList.add(new MobModel("Fox", 3, 3));
        mobList.add(new MobModel("Ghast", 30, 30));
        mobList.add(new MobModel("Giant", 1, 1));
        mobList.add(new MobModel("Guardian", 75, 75));
        mobList.add(new MobModel("Hoglin", 50, 50));
        mobList.add(new MobModel("Husk", 30, 30));
        mobList.add(new MobModel("Illusioner", 250, 250));
        mobList.add(new MobModel("IronGolem", 10, 10));
        mobList.add(new MobModel("Llama", 10, 10));
        mobList.add(new MobModel("MagmaCube", 12, 12));
        mobList.add(new MobModel("Mule", 30, 30));
        mobList.add(new MobModel("MushroomCow", 3, 3));
        mobList.add(new MobModel("Panda", 25, 25));
        mobList.add(new MobModel("Parrot", 100, 100));
        mobList.add(new MobModel("Pig", 3, 3));
        mobList.add(new MobModel("PiglinBrute", 75, 75));
        mobList.add(new MobModel("Ocelot", 25, 25));
        mobList.add(new MobModel("Pillager", 25, 25));
        mobList.add(new MobModel("PolarBear", 250, 250));
        mobList.add(new MobModel("Pufferfish", 30, 30));
        mobList.add(new MobModel("Rabbit", 15, 15));
        mobList.add(new MobModel("Ravager", 500, 500));
        mobList.add(new MobModel("Salmon", 5, 5));
        mobList.add(new MobModel("Sheep", 3, 3));
        mobList.add(new MobModel("Shulker", 75, 75));
        mobList.add(new MobModel("Silverfish", 30, 30));
        mobList.add(new MobModel("Skeleton", 15, 15));
        mobList.add(new MobModel("SkeletonHorse", 150, 150));
        mobList.add(new MobModel("Slime", 12, 12));
        mobList.add(new MobModel("Snowman", 100, 100));
        mobList.add(new MobModel("Spider", 15, 15));
        mobList.add(new MobModel("Squid", 10, 10));
        mobList.add(new MobModel("Stray", 25, 25));
        mobList.add(new MobModel("Strider", 50, 50));
        mobList.add(new MobModel("TraderLlama", 150, 150));
        mobList.add(new MobModel("TropicalFish", 5, 5));
        mobList.add(new MobModel("Turtle", 20, 20));
        mobList.add(new MobModel("Vex", 125, 125));
        mobList.add(new MobModel("Villager", 5, 5));
        mobList.add(new MobModel("Vindicator", 45, 45));
        mobList.add(new MobModel("WanderingTrader", 120, 120));
        mobList.add(new MobModel("Witch", 60, 60));
        mobList.add(new MobModel("Wither", 2500, 2500));
        mobList.add(new MobModel("WitherSkeleton", 45, 45));
        mobList.add(new MobModel("Wolf", 25, 25));
        mobList.add(new MobModel("Zombie", 15, 25));
        mobList.add(new MobModel("ZombieHorse", 35, 35));
        mobList.add(new MobModel("ZombieVillager", 20, 20));
        mobList.add(new MobModel("PigZombie", 45, 45));
    }



}
