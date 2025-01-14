package com.elvarg.world.content.skills.farming;

import com.elvarg.world.content.skills.farming.*;
import java.util.EnumSet;
import java.util.Objects;
import java.util.Optional;

import com.elvarg.cache.impl.definitions.ItemDefinition;
import com.elvarg.world.entity.impl.player.Player;
import com.elvarg.world.model.Item;
import com.elvarg.world.model.Skill;
import com.elvarg.world.model.dialogue.DialogueManager;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Sets;

public enum FarmIdentification {

	GUAM(new Item(199), new Item(249), 1, 2.75),

	MARRENTILL(new Item(201), new Item(251), 5, 3.75),

	TARROMIN(new Item(203), new Item(253), 11, 5),

	HARRALANDER(new Item(205), new Item(255), 20, 6.25),

	RANARR(new Item(207), new Item(257), 25, 7.5),

	TOADFLAX(new Item(3049), new Item(2998), 30, 8),

	IRIT(new Item(209), new Item(259), 40, 8.75),

	AVANTOE(new Item(211), new Item(261), 48, 10),

	KWUARM(new Item(213), new Item(263), 54, 11.25),

	SNAPDRAGON(new Item(3051), new Item(3000), 59, 11.75),

	CADANTINE(new Item(215), new Item(265), 65, 12.5),

	LANTADYME(new Item(2485), new Item(2481), 67, 13.125),

	DWARF_WEED(new Item(217), new Item(267), 70, 13.75),

	TORSTOL(new Item(219), new Item(269), 75, 15);

	private Item grimyHerb, cleanHerb;

	private int levelRequired;

	private double xpReward;

	public static final ImmutableSet<FarmIdentification> FARM_VALUES = Sets
			.immutableEnumSet(EnumSet.allOf(FarmIdentification.class));

	private FarmIdentification(final Item grimyHerb, final Item cleanHerb, final int levelRequired,
			final double xpReward) {
		this.grimyHerb = grimyHerb;
		this.cleanHerb = cleanHerb;
		this.levelRequired = levelRequired;
		this.xpReward = xpReward;
	}

	public Item getGrimyHerb() {
		return grimyHerb;
	}

	public Item getCleanHerb() {
		return cleanHerb;
	}

	public static Optional<FarmIdentification> get(Item item) {
		return FARM_VALUES.stream().filter(Objects::nonNull)
				.filter(herbs -> herbs.getGrimyHerb().getId() == item.getId()).findAny();
	}

	public int getRequired() {
		return levelRequired;
	}

	public double getExperience() {
		return xpReward;
	}

	public static void cleanHerb(Player player, Item item) {
		final Optional<FarmIdentification> farm = FarmIdentification.get(item);
		if (farm.isPresent()) {
			if (player.getSkillManager().getCurrentLevel(Skill.FARMING) >= farm.get().getRequired()) {
				player.getInventory().delete(farm.get().getGrimyHerb(), item.getSlot());
				player.getInventory().add(farm.get().getCleanHerb());
				player.getSkillManager().addExperience(Skill.FARMING, (int) farm.get().getExperience());
				player.getPacketSender().sendMessage("You clean the dirt off the "
						+ ItemDefinition.forId(farm.get().getGrimyHerb().getId()).getName().toLowerCase() + ".");
			} else {
				DialogueManager.sendStatement(player,
						"You need a Farming level of atleast " + farm.get().getRequired() + " to clean this herb.");
				player.getPacketSender().sendMessage(
						"You need a Farming level of atleast " + farm.get().getRequired() + " to clean this herb.");
			}
		}
	}
        
        public static void cleanField(Player player, Item field) {
            //TODO:
        }
        
        public static void plantOnField(Player player, Item field) {
            //TODO:
        }
        
        public static void collectFromField(Player player, Item field) {
            //TODO:
        }
}