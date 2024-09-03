package zh.qiushui.mod.core;

import net.fabricmc.api.ModInitializer;

public class CoreLibInitializer implements ModInitializer {
	@Override
	public void onInitialize() {
		QSCoreUtil.LOGGER.info("Hello QiuShui's mods!");
	}
}