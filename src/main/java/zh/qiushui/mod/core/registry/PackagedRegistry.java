package zh.qiushui.mod.core.registry;

import net.minecraft.block.Block;
import net.minecraft.fluid.Fluid;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import zh.qiushui.mod.core.QSCoreUtil;
import zh.qiushui.mod.core.object.StoredFlowableFluid;

public class PackagedRegistry {
    public static Block block(String path, Block block) {
        return Registry.register(Registries.BLOCK, QSCoreUtil.idCore(path), block);
    }

    public static Item item(String path, Item item) {
        return Registry.register(Registries.ITEM, QSCoreUtil.idCore(path), item);
    }

    public static ItemGroup itemGroup(String path, ItemGroup itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, QSCoreUtil.idCore(path), itemGroup);
    }

    public static Fluid fluid(String path, Fluid fluid) {
        return Registry.register(Registries.FLUID, QSCoreUtil.idCore(path), fluid);
    }
    public static <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathFluid, S fluidStill, F fluidFlowing) {
        return flowableFluid(pathFluid, "flowing_" + pathFluid, fluidStill, fluidFlowing);
    }

    public static <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> flowableFluid(String pathStill, String pathFlowing, S fluidStill, F fluidFlowing) {
        S still = flowableFluidStill(pathStill, fluidStill);
        F flowing = flowableFluidFlowing(pathFlowing, fluidFlowing);
        return new StoredFlowableFluid<>(still, flowing);
    }

    public static <S extends Fluid> S flowableFluidStill(String path, S fluidStill) {
        return Registry.register(Registries.FLUID, QSCoreUtil.idCore(path), fluidStill);
    }

    public static <F extends Fluid> F flowableFluidFlowing(String path, F fluidFlowing) {
        return Registry.register(Registries.FLUID, QSCoreUtil.idCore(path), fluidFlowing);
    }
}
