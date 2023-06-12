package Money4Mobs.Money4Mobs;

import java.util.ArrayList;
import java.util.List;

public class SetMobList {

    private static List<Money4Mobs.Money4Mobs.MobModel> mobList = new ArrayList<Money4Mobs.Money4Mobs.MobModel>();

    public List<Money4Mobs.Money4Mobs.MobModel> getMobModel() {
        setMobWorth();
        return mobList;
    }

    public void setMobWorth(){
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Allay", 120.0, 120.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Axolotl", 5.0, 5.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Bat", 10.0, 10.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Bee", 15.0, 15.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Blaze", 25.0, 25.0,  true,false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Cat", 1.0, 1.0,  true,false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Camel", 1.0, 1.0,  true,false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("CaveSpider", 15.0, 15.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Chicken", 2.0, 2.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Cod", 5.0, 5.0, false, true, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Cow", 2.0, 2.0, false, true, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Creeper", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Dolphin", 175.0, 175.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Donkey", 100.0, 100.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Drowned", 100.0, 100.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("ElderGuardian", 300.0, 300.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("EnderDragon", 10000.0, 10000.0,  true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Enderman", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Endermite", 300.0, 300.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Evoker", 200.0, 200.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Fox", 3.0, 3.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Frog", 75.0, 75.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Ghast", 30.0, 30.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Giant", 1.0, 1.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("GlowSquid", 5.0, 5.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Goat", 10.0, 10.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Guardian", 75.0, 75.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Hoglin", 50.0, 50.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Horse", 20.0, 20.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Husk", 30.0, 30.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Illusioner", 250.0, 250.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("IronGolem", 10.0, 10.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Llama", 10.0, 10.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("MagmaCube", 12.0, 12.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Mule", 30.0, 30.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("MushroomCow", 3.0, 3.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Ocelot", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Panda", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Parrot", 100.0, 100.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Phantom", 45.0, 45.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Pig", 3.0, 3.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Piglin", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("PiglinBrute", 75.0, 75.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Pillager", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Player", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("PolarBear", 250.0, 250.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Pufferfish", 30.0, 30.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Rabbit", 15.0, 15.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Ravager", 500.0, 500.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Salmon", 5.0, 5.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Sheep", 3.0, 3.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Shulker", 75.0, 75.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Silverfish", 30.0, 30.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Skeleton", 15.0, 15.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("SkeletonHorse", 150.0, 150.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Slime", 12.0, 12.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Sniffer", 3.0, 3.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Snowman", 100.0, 100.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Spider", 15.0, 15.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Squid", 10.0, 10.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Stray", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Strider", 50.0, 50.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Tadpole", 50.0, 50.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("TraderLlama", 150.0, 150.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("TropicalFish", 5.0, 5.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Turtle", 20.0, 20.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Vex", 125.0, 125.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Villager", 5.0, 5.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Vindicator", 45.0, 45.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("WanderingTrader", 120.0, 120.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Warden", 10000.0, 10000.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Witch", 60.0, 60.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Wither", 2500.0, 2500.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("WitherSkeleton", 45.0, 45.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Wolf", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Zoglin", 25.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("Zombie", 15.0, 25.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("ZombieHorse", 35.0, 35.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("ZombieVillager", 20.0, 20.0, true, false, null));
        mobList.add(new Money4Mobs.Money4Mobs.MobModel("ZombifiedPiglin", 45.0, 45.0, true, false, null));
    }
}
