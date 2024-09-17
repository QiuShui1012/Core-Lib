package zh.qiushui.mod.core.registry;

import com.google.common.base.Suppliers;
import dev.architectury.registry.registries.RegistrarManager;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.material.Fluid;
import zh.qiushui.mod.core.object.StoredFlowableFluid;

import java.util.function.Function;
import java.util.function.Supplier;

public class PackagedRegistry {
    private final Function<String, ResourceLocation> function;
    private final Supplier<RegistrarManager> manager;

    public PackagedRegistry(String modId, Function<String, ResourceLocation> function) {
        this.function = function;
        this.manager = Suppliers.memoize(() -> RegistrarManager.get(modId));
    }

    public Block block(String path, Block block) {
        return manager.get().get(Registries.BLOCK).register(this.function.apply(path), () -> block).get();
    }

    public Item item(String path, Item item) {
        return manager.get().get(Registries.ITEM).register(this.function.apply(path), () -> item).get();
    }

    public CreativeModeTab creativeModeTab(String path, CreativeModeTab creativeModeTab) {
        return manager.get().get(Registries.CREATIVE_MODE_TAB).register(this.function.apply(path), () -> creativeModeTab).get();
    }

    public Fluid fluid(String path, Fluid fluid) {
        return manager.get().get(Registries.FLUID).register(this.function.apply(path), () -> fluid).get();
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
        return manager.get().get(Registries.FLUID).register(this.function.apply(path), () -> fluidStill).get();
    }

    public <F extends Fluid> F flowableFluidFlowing(String path, F fluidFlowing) {
        return manager.get().get(Registries.FLUID).register(this.function.apply(path), () -> fluidFlowing).get();
    }
}
