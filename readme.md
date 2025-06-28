# Mikari NPC Generator

A Java-based NPC generator packaged as an AppImage for portable use on Linux systems, including Steam Deck.

---

## Prerequisites

### For Building AppImage
- Linux x64 compatible JDK (download `.tar.gz` from [Adoptium](https://adoptium.net/de/temurin/releases/?version=21&os=any&arch=any))
- extract it using `tar -xzf OpenJDK21U-jdk_x64_linux_hotspot_21.0.7_6.tar.gz`
- rename it to `jre` and move it to NPC_Generator.AppDir

---

## Build Instructions

### 1. **Compile the Java Application**
- Make sure all `.java` files compile and generate a `.jar`
- In IntelliJ go to ProjectStructure > Artifacts > + > Add the class you want to execute (NPCGenerator)
- Go to Build > Build Artifacs... > Build
- This creates a .jar in out > artifacts
- Move this .jar to src > NPC_Generator.AppDir

### 2. AppDir Structure

```
NPC_Generator.AppDir/
├── AppRun                           # launch script
├── npc_generator.desktop            # app entry
├── icon.png                         # icon
├── mikari_npc_generator.jar         # compiled app
└── jre/                             # Linux JDK placed here
```

### 3. On the Steam Deck
- pull this repo on to the Steam Deck
- Download AppImage and make it executable
```bash
  wget https://github.com/AppImage/AppImageKit/releases/download/continuous/appimagetool-x86_64.AppImage
  chmod +x appimagetool-x86_64.AppImage
```
- build the project
```bash
 ARCH=x86_64 ./appimagetool-x86_64.AppImage NPCGenerator.AppDir
```
- to make it executable
```bash
 ./NPCGenerator.AppImage --appimage-extract
 cd squashfs-root
 chmod +x AppRun
```

### 4. For Convenient Start
- copy the .desktop file
```bash
 cp sqashfs-root/npc_generator.desktop ~/.local/share/applications/
```
- change the contents of the file to:
```bash
 [Desktop Entry]
 Name=NPC Generator
 Exec=~/squashfs-root/AppRun
 Icon=/home/deck/squashfs-root/icon
 Type=Application
 Categories=Utility;
```
- make it executable
```bash
  chmod +x ~/.local/share/applications/npc_generator.desktop
```
