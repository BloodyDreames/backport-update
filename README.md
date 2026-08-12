# Backport — Minecraft 1.21.1 (Forge)

Source of the Backport mod for Minecraft 1.21.1 on Forge.

Backport brings the content and gameplay changes of the Minecraft Java 26.3
update to earlier versions of the game: the Poplar wood family, Shelf Mushroom
and Red Shrub, wool and concrete stairs and slabs, cushions, the Straw Bed, the
Dappled Forest biome, the Abandoned Camp structure, explorer maps, and a
selection of gameplay tweaks and vanilla bug fixes.

Every supported Minecraft version and mod loader lives on its own branch. The
project page, the changelog and the downloads are on the
[`main`](../../tree/main) branch.

## Download

- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/backport-update)
- [Modrinth](https://modrinth.com/mod/backport-update)
- [Releases](../../releases)

## Building

```bash
./gradlew build
```

On Windows PowerShell:

```powershell
.\gradlew.bat build
```

The production JAR lands in `build/libs/`, named
`backport-<mod version>+mc1.21.1-forge.jar`.

## License

MIT — see [`LICENSE`](LICENSE).
