package com.chimericdream.enchantnumfix.mixin;

import com.chimericdream.enchantnumfix.RomanNumeralUtil;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.registry.tag.EnchantmentTags;
import net.minecraft.screen.ScreenTexts;
import net.minecraft.text.MutableText;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.text.Texts;
import net.minecraft.util.Formatting;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Overwrite;

@Mixin(Enchantment.class)
public abstract class ENFEnchantmentMixin {
    @Overwrite
    public static Text getName(RegistryEntry<Enchantment> enchantment, int level) {
        MutableText mutableText = enchantment.value().description.copy();

        if (enchantment.isIn(EnchantmentTags.CURSE)) {
            Texts.setStyleIfAbsent(mutableText, Style.EMPTY.withColor(Formatting.RED));
        } else {
            Texts.setStyleIfAbsent(mutableText, Style.EMPTY.withColor(Formatting.GRAY));
        }

        if (level != 1 || enchantment.value().getMaxLevel() != 1) {
            mutableText.append(ScreenTexts.SPACE).append(RomanNumeralUtil.toRoman(level));
        }

        return mutableText;
    }
}
