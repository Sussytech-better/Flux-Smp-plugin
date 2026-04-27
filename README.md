# Flux SMP Plugin

A Minecraft Spigot plugin that implements the **Flux Events System** for SMP servers - bringing dynamic, rotating events that keep gameplay exciting and unpredictable!

## ⚡ Features

### 🔄 Flux Events System
- **Always 1 active event** at a time
- **Automatic rotation** every 10 Minecraft days (240,000 ticks)
- **Smooth transitions** with title/subtitle/chat broadcasts to all players
- **Previous event disabled** before new one starts

### 📊 Flux Bar (Activity Tracker)
- **Global server activity** tracker displayed via BossBar
- **Fills with player activity:**
  - Player kills: +5% progress
  - Player deaths: +3% progress
  - Passive time: +0.1% per minute
- **Triggers early rotation** when reaching 100%
- **Resets to 0%** after rotation

### 🔥 Built-in Flux Events (12 Total)

| Event | Description |
|-------|-------------|
| 🩸 **Blood Flux** | Increased PvP damage (50% more), better kill rewards |
| 💎 **Rich Flux** | Higher ore spawn rates (30% chance for extra drops) |
| ⛈ **Storm Flux** | Constant thunderstorms + stronger mobs (Strength effect) |
| 🧭 **Hunt Flux** | Players track nearest player with compass |
| 🕊 **Peace Flux** | PvP completely disabled (players can't damage each other) - lasts 3 days |
| 🧨 **Destruction Flux** | Explosions and TNT are 2x stronger |
| 👻 **Shadow Flux** | Reduced visibility (blindness effects every 4 seconds) |
| ⚔️ **War Flux** | PvP rewards boosted (+50 XP for kills) |
| 💀 **Chaos Flux** | Random potion effects every 5 minutes |
| 🪙 **Fortune Flux** | Increased loot (50% chance to double mob drops) |
| 🧲 **Gravity Flux** | Modified knockback (2x velocity on damage) |
| 🧊 **Freeze Flux** | Slowness effects + water turns to ice |

## 🚀 Installation

1. **Download** the plugin JAR file (`flux-smp-plugin-1.0.0.jar`)
2. **Place** it in your server's `plugins/` folder
3. **Restart** the server
4. The plugin will **automatically start** the Flux Events System!

## 🛠️ Commands

| Command | Description | Permission |
|---------|-------------|------------|
| `/flux info` | Shows current active event information | `flux.admin` |
| `/flux reload` | Manually rotate to a new event | `flux.admin` |

## ⚙️ Permissions

- `flux.admin` - Allows use of admin commands (default: op)

## 🔧 Building from Source

```bash
# Clone the repository
git clone https://github.com/Sussytech-better/Flux-Smp-plugin.git
cd Flux-Smp-plugin

# Build with Maven
mvn clean package
```

The compiled JAR will be in `target/flux-smp-plugin-1.0.0.jar`

## 📋 Requirements

- **Spigot/Paper 1.21+** or compatible
- **Java 17+**

## ✅ Compatibility

**Minecraft 1.21+ Ready**
- Updated API version to 1.21
- Fixed PotionEffectType constants for new API
- Compatible with latest Spigot/Paper builds

## 🎮 How It Works

1. **Server starts** → Random event is selected and activated
2. **Every 10 Minecraft days** → Event automatically rotates
3. **Player activity** → Flux Bar fills up
4. **Flux Bar reaches 100%** → Early event rotation triggered
5. **Players are notified** → Title, subtitle, and chat announcements

## 🏗️ Architecture

```
Flux-Smp-plugin/
├── src/main/java/com/sussytech/fluxsmp/
│   ├── FluxSmpPlugin.java      # Main plugin class
│   ├── managers/
│   │   └── FluxManager.java    # Core event & flux bar logic
│   ├── events/                 # All 12 flux events
│   ├── listeners/
│   │   └── PlayerListener.java # Player event handling
│   └── commands/
│       └── FluxCommand.java    # Admin commands
├── src/main/resources/
│   └── plugin.yml              # Plugin configuration
├── pom.xml                     # Maven build configuration
└── README.md                   # This file
```

## 🔒 Configuration

The plugin is designed to work **out-of-the-box** with sensible defaults. All events are built-in and cannot be disabled individually. No additional configuration files are needed!

## 📝 License

This project is open source. Feel free to modify and distribute according to your needs.

---

**Made with ❤️ for the Minecraft SMP community**