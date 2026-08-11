# Backport

Backport brings the content and gameplay changes of the **Minecraft Java 26.3**
update to earlier versions of the game, on NeoForge, Forge, Fabric and Quilt.

Play with poplar wood, cushions, wool and concrete stairs, the Dappled Forest
biome and the rest of 26.3 without leaving the version your world, your server
or your modpack is built on.

## Download

- **[CurseForge](https://www.curseforge.com/minecraft/mc-mods/backport-update)**
- **[Modrinth](https://modrinth.com/mod/backport-update)**
- **[Releases](../../releases)** — the same builds, one release per Minecraft version

Files are named `backport-<mod version>+mc<Minecraft version>-<loader>.jar`.
Pick the one matching your game version and mod loader, and drop it into `mods/`.

The mod is needed on both the client and the server.

## Supported versions

| Minecraft | NeoForge | Forge | Fabric |
|---|---:|---:|---:|
| 26.2 | ✅ | — | ✅ |
| 26.1 | ✅ | ✅ | ✅ |
| 1.21.11 | ✅ | — | ✅ |
| 1.21.1 | ✅ | ✅ | ✅ |
| 1.20.6 | — | ✅ | — |
| 1.20.4 | — | ✅ | — |
| 1.20.2 | — | ✅ | — |
| 1.20.1 | ✅ | ✅ | ✅ |
| 1.19.2 | — | ✅ | — |

✅ — a release build is available. Quilt loads the Fabric build.

## What is in it

| Category | Content |
|---|---|
| Wood | The full Poplar family: logs, planks, door, trapdoor, fence, signs, boats, sapling, three leaf colours with matching particles |
| Plants | Shelf Mushroom, which bounces anything that lands on it, and Red Shrub |
| Building | Wool Stairs and Slabs ×32, Concrete Stairs and Slabs ×32 |
| Furniture | Cushion ×16, and the Straw Bed — sleep through the night without moving your spawn point |
| World | The Dappled Forest biome and the Abandoned Camp structure in 18 biome variants |
| Items | 16 explorer maps, each with its own icon |
| Other | Gameplay tweaks and a selection of vanilla bug fixes from the 26.3 snapshots |

## What is not in it

Backport does not change vanilla datapack or resourcepack formats, does not rename
registries and does not alter command semantics — a mod that did would break other
mods, datapacks and servers. Engine work from 26.3 (order-independent transparency,
the move to SDL3, Vulkan, MultiDrawIndirect) is outside what a mod can do at all.

## Compatibility

Backport adds its own blocks and items in its own namespace and leaves vanilla
content alone, so it sits well next to other content mods. Worlds are safe to
update: nothing already placed is removed or renamed between versions.

Removing the mod from a world will leave holes where its blocks were, as with any
content mod.

## Changelog

See [`CHANGELOG.md`](CHANGELOG.md) for the full history.

## Reporting a problem

Open an [issue](../../issues) with your Minecraft version, mod loader, mod version
and — if the game crashed — the crash report or log. Bug reports that name the
exact version combination get fixed fastest.

## License

All rights reserved. See [`LICENSE`](LICENSE).
