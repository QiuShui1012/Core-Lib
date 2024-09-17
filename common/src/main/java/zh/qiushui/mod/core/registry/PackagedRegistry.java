package zh.qiushui.mod.core.registry;

import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import zh.qiushui.mod.core.object.StoredFlowableFluid;

import java.util.function.Function;

public class PackagedRegistry {
    private final Function<String, ResourceLocation> function;

    public PackagedRegistry(Function<String, ResourceLocation> function) {
        this.function = function;
    }

    public Block block(String path, Block block) {
        return Registry.register(BuiltInRegistries.BLOCK, this.function.apply(path), block);
    }

    public Item item(String path, Item item) {
        return Registry.register(BuiltInRegistries.ITEM, this.function.apply(path), item);
    }

    public CreativeModeTab itemGroup(String path, CreativeModeTab itemGroup) {
        return Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, this.function.apply(path), itemGroup);
    }

    public Fluid fluid(String path, Fluid fluid) {
        return Registry.register(BuiltInRegistries.FLUID, this.function.apply(path), fluid);
    }
    public <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathFluid, S fluidStill, F fluidFlowing) {
        return flowableFluid(pathFluid, "flowing_" + pathFluid, fluidStill, fluidFlowing);
    }

    public <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathStill, String pathFlowing, S fluidStill, F fluidFlowing) {
        S still = flowableFluidStill(pathStill, fluidStill);
        F flowing = flowableFluidFlowing(pathFlowing, fluidFlowing);
        return new StoredFlowableFluid<S, F>(still, flowing);
    }

    public <S extends Fluid> S flowableFluidStill(String path, S fluidStill) {
        return Registry.register(BuiltInRegistries.FLUID, this.function.apply(path), fluidStill);
    }

    public <F extends Fluid> F flowableFluidFlowing(String path, F fluidFlowing) {
        return Registry.register(BuiltInRegistries.FLUID, this.function.apply(path), fluidFlowing);
    }
}
