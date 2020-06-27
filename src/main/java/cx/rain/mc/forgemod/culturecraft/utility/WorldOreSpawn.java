package cx.rain.mc.forgemod.culturecraft.utility;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.event.terraingen.OreGenEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import java.util.Random;

/**
 * Ore Spawn
 *
 * @author flysong
 */
public class WorldOreSpawn extends WorldGenerator {
    private int MaxY;
    private int SpawnTime;
    private int AddTime;
    private WorldGenMinable Generator;

    /**
     * @param MaxY      Max Pos Y
     * @param SpawnTime Spawn time,if negative, the probability of a chunk generation is AddTime/|SpawnTime|,else, the times of chunk generation |SpawnTime|/AddTime
     * @param Generator Generator
     */
    public WorldOreSpawn(int MaxY, int SpawnTime, WorldGenMinable Generator) {
        this(MaxY, SpawnTime, 1, Generator);
    }

    /**
     * @param MaxY      Max Pos Y
     * @param SpawnTime Spawn time,if negative, the probability of a chunk generation is AddTime/|SpawnTime|,else, the times of chunk generation |SpawnTime|/AddTime
     * @param Generator Generator
     * @param AddTime   If SpawnTime is negative, the probability of a chunk generation is AddTime/|SpawnTime|,else, the times of chunk generation |SpawnTime|/AddTime
     */
    public WorldOreSpawn(int MaxY, int SpawnTime, int AddTime, WorldGenMinable Generator) {
        this.MaxY = MaxY;
        this.SpawnTime = SpawnTime;
        this.AddTime = AddTime;
        this.Generator = Generator;
    }

    @Override
    public boolean generate(World worldIn, Random rand, BlockPos position) {
        if (TerrainGen.generateOre(worldIn, rand, this, position, OreGenEvent.GenerateMinable.EventType.CUSTOM)) {
            if (SpawnTime >= 0) {
                for (int i = 0; i < SpawnTime; i += AddTime) {
                    int posX = position.getX() + rand.nextInt(16);
                    int posY = rand.nextInt(MaxY - 4) + 4;
                    int posZ = position.getZ() + rand.nextInt(16);
                    BlockPos blockpos = new BlockPos(posX, posY, posZ);
                    Generator.generate(worldIn, rand, blockpos);
                }
            } else {
                if (rand.nextInt(-SpawnTime) <= AddTime) {
                    int posX = position.getX() + rand.nextInt(16);
                    int posY = rand.nextInt(MaxY - 4) + 4;
                    int posZ = position.getZ() + rand.nextInt(16);
                    BlockPos blockpos = new BlockPos(posX, posY, posZ);
                    Generator.generate(worldIn, rand, blockpos);
                }
            }
        }
        return true;
    }
}
