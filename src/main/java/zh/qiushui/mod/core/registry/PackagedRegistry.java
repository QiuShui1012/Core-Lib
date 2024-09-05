package zh.qiushui.mod.core.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import zh.qiushui.mod.core.api.object.StoredFlowableFluid;

import java.util.function.Function;

public class PackagedRegistry {
    private final Function<String, Identifier> function;

    public PackagedRegistry(Function<String, Identifier> function) {
        this.function = function;
    }

    public Block block(String path, Block block) {
        return Registry.register(Registries.BLOCK, this.function.apply(path), block);
    }

    public Item item(String path, Item item) {
        return Registry.register(Registries.ITEM, this.function.apply(path), item);
    }

    public ItemGroup itemGroup(String path, ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, this.function.apply(path), itemGroup);
    }

    public Fluid fluid(String path, Fluid fluid) {
        return Registry.register(Registries.FLUID, this.function.apply(path), fluid);
    }
    public <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathFluid, S fluidStill, F fluidFlowing) {
        return flowableFluid(pathFluid, "flowing_" + pathFluid, fluidStill, fluidFlowing);
    }

    public <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathStill, String pathFlowing, S fluidStill, F fluidFlowing) {
        S still = flowableFluidStill(pathStill, fluidStill);
        F flowing = flowableFluidFlowing(pathFlowing, fluidFlowing);
        return new StoredFlowableFluid<>(still, flowing);
    }

    public <S extends Fluid> S flowableFluidStill(String path, S fluidStill) {
        return Registry.register(Registries.FLUID, this.function.apply(path), fluidStill);
    }

    public <F extends Fluid> F flowableFluidFlowing(String path, F fluidFlowing) {
        return Registry.register(Registries.FLUID, this.function.apply(path), fluidFlowing);
    }
}
