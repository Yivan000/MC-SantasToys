package io.github.elifoster.santastoys.items;

import io.github.elifoster.santastoys.entity.EntityEnderBlast;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.neoforged.neoforge.common.Tags;

public class ItemEnderBlaster extends Item {
    ItemEnderBlaster() {
        super(new Item.Properties()
          .stacksTo(1));
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack heldItem = player.getItemInHand(hand);
        if (!level.isClientSide() && (player.isCreative() || player.getInventory().contains(Tags.Items.ENDER_PEARLS))) {
            ThrowableItemProjectile blast = new EntityEnderBlast(level, player);
            blast.shootFromRotation(player, player.getXRot(), player.getYRot(), 0, 2.5f, 1);
            level.addFreshEntity(blast);
        }
        return InteractionResultHolder.fail(heldItem);
    }
}
