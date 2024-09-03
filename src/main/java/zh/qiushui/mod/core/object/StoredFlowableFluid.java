package zh.qiushui.mod.core.object;

import net.minecraft.fluid.Fluid;
import org.apache.commons.lang3.tuple.Pair;

public class StoredFlowableFluid<S extends Fluid, F extends Fluid> extends Pair<S,F> {
    private final S still;
    private final F flowing;

    public StoredFlowableFluid(S still, F flowing) {
        this.still = still;
        this.flowing = flowing;
    }

    public static <S extends Fluid, F extends Fluid> StoredFlowableFluid<S, F> of(S still, F flowing) {
        return new StoredFlowableFluid<>(still, flowing);
    }


    public S getStill() {
        return still;
    }

    public F getFlowing() {
        return flowing;
    }

    @Deprecated
    @Override
    public S getLeft() {
        return getStill();
    }

    @Deprecated
    @Override
    public F getRight() {
        return getFlowing();
    }

    @Deprecated
    @Override
    public F setValue(F value) {
        return value;
    }
}
