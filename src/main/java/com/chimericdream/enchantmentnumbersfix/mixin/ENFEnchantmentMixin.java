package com.chimericdream.enchantmentnumbersfix.mixin;

import com.chimericdream.enchantmentnumbersfix.RomanNumeralUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(Enchantment.class)
public abstract class ENFEnchantmentMixin {
	@Shadow
	abstract public String getTranslationKey();

	@Shadow
	abstract public int getMaxLevel();

	@Shadow
	abstract public boolean isCursed();

	@Overwrite
	public Text getName(int level) {
		MutableText mutableText = Text.translatable(this.getTranslationKey());
		if (this.isCursed()) {
			mutableText.formatted(Formatting.RED);
		} else {
			mutableText.formatted(Formatting.GRAY);
		}

		if (level != 1 || this.getMaxLevel() != 1) {
			if (level <= 10) {
				mutableText.append(" ").append(Text.translatable("enchantment.level." + level));
			} else {
				mutableText.append(" ").append(RomanNumeralUtil.toRoman(level));
			}
		}

		return mutableText;
	}
}
