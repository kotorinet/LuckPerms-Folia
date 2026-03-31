# LuckPerms

<p align="center">
<strong>Powerful permissions plugin for Minecraft servers - Folia Compatible Edition</strong>
</p>

<p align="center">
<img src="https://img.shields.io/badge/Version-5.5.0-brightgreen?style=for-the-badge" alt="Version" />
<img src="https://img.shields.io/badge/API-1.21+-blue?style=for-the-badge" alt="API Version" />
<img src="https://img.shields.io/badge/Java-21+-orange?style=for-the-badge" alt="Java" />
<img src="https://img.shields.io/badge/Folia-Supported-purple?style=for-the-badge" alt="Folia" />
<img src="https://img.shields.io/badge/Status-Experimental-red?style=for-the-badge" alt="Experimental" />
</p>

---

## ⚠️ Experimental Notice

> **This is an EXPERIMENTAL fork of LuckPerms with Folia support.**

* This version is still under active development and testing
* Bugs, race conditions, or unexpected behavior **may occur**, especially under heavy load
* Not yet recommended for production-critical servers without proper testing
* APIs and internal behavior **may change without notice**

💡 If you're running a production server, consider using the official stable release of LuckPerms unless you specifically need Folia support.

---

## Overview

LuckPerms is a permissions plugin for Minecraft servers. It allows server admins to control what features players can use by creating groups and assigning permissions.

This is a **Folia-compatible fork** with full support for multi-threaded region servers while maintaining backward compatibility with Paper and Spigot.

### Key Features

* **Folia Support** - Full compatibility with Folia's multi-threaded regions using proper schedulers
* **Cross-Platform** - Works on Bukkit, Spigot, Paper, Folia, Sponge, Fabric, Forge, and more
* **Storage Options** - MySQL, MariaDB, PostgreSQL, SQLite, H2, MongoDB, or YAML/JSON files
* **Web Editor** - Easy-to-use web interface for managing permissions
* **Context System** - Apply permissions based on world, server, gamemode, and custom contexts
* **Track System** - Automatic promotion and demotion paths
* **Verbose Mode** - Debug permission checks in real-time
* **Migration** - Import data from other permission plugins
* **API** - Comprehensive developer API for integrations
* **Fast & Efficient** - Optimized caching and async operations

---

## Installation

### Requirements

**Required:**

* Java 21 or higher
* Minecraft Server 1.21+ (Paper, Folia, Spigot, or compatible)

**Optional:**

* **Vault** - For legacy plugin compatibility
* **PlaceholderAPI** - For placeholder expansions

### Quick Start

1. Download `LuckPerms-Bukkit-5.5.0.jar` from releases
2. Place in your server's `plugins` folder
3. Start the server to generate configuration files
4. Configure `config.yml` to your needs
5. Use `/lp` commands or the web editor to manage permissions

---

## Folia Compatibility

This version includes full Folia support with proper thread-safe implementations:

* **EntityScheduler** - Player-specific operations run on the player's owning region thread
* **GlobalRegionScheduler** - Server-wide synchronized tasks
* **AsyncScheduler** - Background operations without blocking regions
* **Automatic Detection** - Seamlessly switches between Folia and Bukkit schedulers at runtime

The plugin works identically on Folia, Paper, and Spigot servers with zero configuration changes.

---

## Documentation

For full documentation, commands, permissions, and API usage:

* **Official Wiki:** https://luckperms.net/wiki
* **Commands:** https://luckperms.net/wiki/Command-Usage
* **Permissions:** https://luckperms.net/wiki/Permissions
* **API:** https://luckperms.net/wiki/Developer-API

---

## Credits

**Original LuckPerms** by [Luck](https://github.com/lucko)

* Official Repository: https://github.com/LuckPerms/LuckPerms
* Website: https://luckperms.net
* License: MIT License

**Folia Migration** by **kotori**

* Added multi-threaded region support for Folia 1.21.11
* Maintained backward compatibility with Paper/Spigot
* Thread-safe scheduler implementations

---

## License

LuckPerms is licensed under the **MIT License**.

See [LICENSE.txt](LICENSE.txt) for the full license text.

---

## Support

For issues with the **original LuckPerms**:

* Discord: https://discord.gg/luckperms
* GitHub Issues: https://github.com/LuckPerms/LuckPerms/issues

For issues specific to **Folia compatibility**:

* Report on this fork's issue tracker
