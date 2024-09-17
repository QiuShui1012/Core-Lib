package zh.qiushui.mod.core.fabric;

import net.fabricmc.api.ModInitializer;
import zh.qiushui.mod.core.CoreLibInitializer;

public final class CoreLibInitializerImpl implements ModInitializer {
    @Override
    public void onInitialize() {
        CoreLibInitializer.init();
    }
}
