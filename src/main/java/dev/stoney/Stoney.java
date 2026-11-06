package dev.stoney;

import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class Stoney implements ModInitializer {
    private static final Logger LOGGER = LoggerFactory.getLogger("stoney");
    private static final Map<Block, String> VARIANTS = new LinkedHashMap<>();
    private static final Properties SETTINGS = new Properties();
    static {
        VARIANTS.put(Blocks.GRANITE, "granite");
        VARIANTS.put(Blocks.DIORITE, "diorite");
        VARIANTS.put(Blocks.ANDESITE, "andesite");
        VARIANTS.put(Blocks.CALCITE, "calcite");
        VARIANTS.put(Blocks.DEEPSLATE, "deepslate");
        VARIANTS.put(Blocks.TUFF, "tuff");
    }
    @Override public void onInitialize() {
        Path path = FabricLoader.getInstance().getConfigDir().resolve("stoney.properties");
        VARIANTS.values().forEach(name -> SETTINGS.setProperty(name, "true"));
        if (Files.exists(path)) {
            try (Reader reader = Files.newBufferedReader(path)) { SETTINGS.load(reader); }
            catch (IOException e) { LOGGER.warn("Could not read Stoney config; using defaults", e); }
        } else {
            try {
                Files.createDirectories(path.getParent());
                try (Writer writer = Files.newBufferedWriter(path)) { SETTINGS.store(writer, "Set a variant to false to preserve vanilla generation. Restart to apply changes."); }
            } catch (IOException e) { LOGGER.warn("Could not create Stoney config", e); }
        }
    }
    public static BlockState generatedState(BlockGetter level, BlockPos pos, BlockState vanilla) {
        Block block = level.getBlockState(pos.below()).getBlock();
        String name = VARIANTS.get(block);
        return name != null && Boolean.parseBoolean(SETTINGS.getProperty(name, "true")) ? block.defaultBlockState() : vanilla;
    }
}
