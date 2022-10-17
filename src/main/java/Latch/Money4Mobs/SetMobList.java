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
        mobList.add(new MobModel("Allay", 120.0, 120.0, true, false, null));
        mobList.add(new MobModel("Axolotl", 5.0, 5.0, true, false, null));
        mobList.add(new MobModel("Bat", 10.0, 10.0, true, false, null));
        mobList.add(new MobModel("Bee", 15.0, 15.0, true, false, null));
        mobList.add(new MobModel("Blaze", 25.0, 25.0,  true,false, null));
        mobList.add(new MobModel("Cat", 1.0, 1.0,  true,false, null));
        mobList.add(new MobModel("CaveSpider", 15.0, 15.0, true, false, null));
        mobList.add(new MobModel("Chicken", 2.0, 2.0, true, false, null));
        mobList.add(new MobModel("Cod", 5.0, 5.0, false, true, null));
        mobList.add(new MobModel("Cow", 2.0, 2.0, false, true, null));
        mobList.add(new MobModel("Creeper", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Dolphin", 175.0, 175.0, true, false, null));
        mobList.add(new MobModel("Donkey", 100.0, 100.0, true, false, null));
        mobList.add(new MobModel("Drowned", 100.0, 100.0, true, false, null));
        mobList.add(new MobModel("ElderGuardian", 300.0, 300.0, true, false, null));
        mobList.add(new MobModel("EnderDragon", 10000.0, 10000.0,  true, false, null));
        mobList.add(new MobModel("Enderman", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Endermite", 300.0, 300.0, true, false, null));
        mobList.add(new MobModel("Evoker", 200.0, 200.0, true, false, null));
        mobList.add(new MobModel("Fox", 3.0, 3.0, true, false, null));
        mobList.add(new MobModel("Frog", 75.0, 75.0, true, false, null));
        mobList.add(new MobModel("Ghast", 30.0, 30.0, true, false, null));
        mobList.add(new MobModel("Giant", 1.0, 1.0, true, false, null));
        mobList.add(new MobModel("GlowSquid", 5.0, 5.0, true, false, null));
        mobList.add(new MobModel("Goat", 10.0, 10.0, true, false, null));
        mobList.add(new MobModel("Guardian", 75.0, 75.0, true, false, null));
        mobList.add(new MobModel("Hoglin", 50.0, 50.0, true, false, null));
        mobList.add(new MobModel("Horse", 20.0, 20.0, true, false, null));
        mobList.add(new MobModel("Husk", 30.0, 30.0, true, false, null));
        mobList.add(new MobModel("Illusioner", 250.0, 250.0, true, false, null));
        mobList.add(new MobModel("IronGolem", 10.0, 10.0, true, false, null));
        mobList.add(new MobModel("Llama", 10.0, 10.0, true, false, null));
        mobList.add(new MobModel("MagmaCube", 12.0, 12.0, true, false, null));
        mobList.add(new MobModel("Mule", 30.0, 30.0, true, false, null));
        mobList.add(new MobModel("MushroomCow", 3.0, 3.0, true, false, null));
        mobList.add(new MobModel("Ocelot", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Panda", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Parrot", 100.0, 100.0, true, false, null));
        mobList.add(new MobModel("Phantom", 45.0, 45.0, true, false, null));
        mobList.add(new MobModel("Pig", 3.0, 3.0, true, false, null));
        mobList.add(new MobModel("Piglin", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("PiglinBrute", 75.0, 75.0, true, false, null));
        mobList.add(new MobModel("Pillager", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Player", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("PolarBear", 250.0, 250.0, true, false, null));
        mobList.add(new MobModel("Pufferfish", 30.0, 30.0, true, false, null));
        mobList.add(new MobModel("Rabbit", 15.0, 15.0, true, false, null));
        mobList.add(new MobModel("Ravager", 500.0, 500.0, true, false, null));
        mobList.add(new MobModel("Salmon", 5.0, 5.0, true, false, null));
        mobList.add(new MobModel("Sheep", 3.0, 3.0, true, false, null));
        mobList.add(new MobModel("Shulker", 75.0, 75.0, true, false, null));
        mobList.add(new MobModel("Silverfish", 30.0, 30.0, true, false, null));
        mobList.add(new MobModel("Skeleton", 15.0, 15.0, true, false, null));
        mobList.add(new MobModel("SkeletonHorse", 150.0, 150.0, true, false, null));
        mobList.add(new MobModel("Slime", 12.0, 12.0, true, false, null));
        mobList.add(new MobModel("Snowman", 100.0, 100.0, true, false, null));
        mobList.add(new MobModel("Spider", 15.0, 15.0, true, false, null));
        mobList.add(new MobModel("Squid", 10.0, 10.0, true, false, null));
        mobList.add(new MobModel("Stray", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Strider", 50.0, 50.0, true, false, null));
        mobList.add(new MobModel("Tadpole", 50.0, 50.0, true, false, null));
        mobList.add(new MobModel("TraderLlama", 150.0, 150.0, true, false, null));
        mobList.add(new MobModel("TropicalFish", 5.0, 5.0, true, false, null));
        mobList.add(new MobModel("Turtle", 20.0, 20.0, true, false, null));
        mobList.add(new MobModel("Vex", 125.0, 125.0, true, false, null));
        mobList.add(new MobModel("Villager", 5.0, 5.0, true, false, null));
        mobList.add(new MobModel("Vindicator", 45.0, 45.0, true, false, null));
        mobList.add(new MobModel("WanderingTrader", 120.0, 120.0, true, false, null));
        mobList.add(new MobModel("Warden", 10000.0, 10000.0, true, false, null));
        mobList.add(new MobModel("Witch", 60.0, 60.0, true, false, null));
        mobList.add(new MobModel("Wither", 2500.0, 2500.0, true, false, null));
        mobList.add(new MobModel("WitherSkeleton", 45.0, 45.0, true, false, null));
        mobList.add(new MobModel("Wolf", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Zoglin", 25.0, 25.0, true, false, null));
        mobList.add(new MobModel("Zombie", 15.0, 25.0, true, false, null));
        mobList.add(new MobModel("ZombieHorse", 35.0, 35.0, true, false, null));
        mobList.add(new MobModel("ZombieVillager", 20.0, 20.0, true, false, null));
        mobList.add(new MobModel("ZombifiedPiglin", 45.0, 45.0, true, false, null));
    }
}
