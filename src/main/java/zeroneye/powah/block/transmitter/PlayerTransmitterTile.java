package zeroneye.powah.block.transmitter;

import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.util.NonNullList;
import net.minecraft.world.dimension.DimensionType;
import zeroneye.lib.util.Energy;
import zeroneye.lib.util.Player;
import zeroneye.powah.block.ITiles;
import zeroneye.powah.block.PowahTile;
import zeroneye.powah.compat.curios.CuriosCompat;
import zeroneye.powah.item.BindingCardItem;

public class PlayerTransmitterTile extends PowahTile {
    private boolean acrossDim;

    public PlayerTransmitterTile(int capacity, int transfer, boolean acrossDim) {
        super(ITiles.PLAYER_TRANSMITTER, capacity, transfer, transfer, false);
        this.acrossDim = acrossDim;
        this.inv.add(2);
    }

    public PlayerTransmitterTile() {
        this(0, 0, false);
    }

    @Override
    public void readStorable(CompoundNBT compound) {
        super.readStorable(compound);
        this.acrossDim = compound.getBoolean("AcrossDimension");
    }

    @Override
    public CompoundNBT writeStorable(CompoundNBT compound) {
        compound.putBoolean("AcrossDimension", this.acrossDim);
        return super.writeStorable(compound);
    }

    @Override
    protected void onFirstTick() { //TODO remove 03/11/2019
        if (this.world == null) return;
        if (!this.world.isRemote) {
            if (getBlock() instanceof PlayerTransmitterBlock) {
                PlayerTransmitterBlock powahBlock = (PlayerTransmitterBlock) getBlock();
                this.acrossDim = powahBlock.isAcrossDim();
                markDirtyAndSync();
            }
        }
        super.onFirstTick();
    }

    @Override
    protected boolean postTicks() {
        final int[] i = {0};
        if (this.world == null) return false;
        if (this.world.isRemote) return false;
        if (this.internal.hasEnergy()) {
            NonNullList<ItemStack> stacks = this.inv.getStacks();
            for (int i1 = 0; i1 < stacks.size(); i1++) {
                ItemStack stack = stacks.get(i1);
                if (stack.getItem() instanceof BindingCardItem) {
                    BindingCardItem item = (BindingCardItem) stack.getItem();
                    item.getPlayer(stack).ifPresent(player -> {
                        DimensionType type = this.world.dimension.getType();
                        if (this.acrossDim || player.dimension.equals(type)) {
                            for (ItemStack stack1 : Player.invStacks(player)) {
                                int amount = Math.min(this.internal.getMaxExtract(), this.internal.getEnergyStored());
                                int received = Energy.receive(stack1, amount, false);
                                i[0] += extractEnergy(received, false, null);
                            }
                            for (ItemStack stack1 : CuriosCompat.getAllStacks(player)) {
                                int amount = Math.min(this.internal.getMaxExtract(), this.internal.getEnergyStored());
                                int received = Energy.receive(stack1, amount, false);
                                i[0] += extractEnergy(received, false, null);
                            }
                        }
                    });
                }
            }
        }
        return i[0] > 0;
    }

    @Override
    protected ExtractionType getExtractionType() {
        return ExtractionType.OFF;
    }

    @Override
    public boolean keepInventory() {
        return false;
    }

    @Override
    public boolean canInsert(int index, ItemStack stack) {
        if (this.world == null || !(stack.getItem() instanceof BindingCardItem)) {
            return false;
        } else {
            if (getBlock() instanceof PlayerTransmitterBlock && index == 1 && ((PlayerTransmitterBlock) getBlock()).getSlots() < 2) {
                return false;
            }
        }
        return ((BindingCardItem) stack.getItem()).getPlayer(stack).isPresent() || super.canInsert(index, stack);
    }
}
