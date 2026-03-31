/*
 * This file is part of LuckPerms, licensed under the MIT License.
 *
 *  Copyright (c) lucko (Luck) <luck@lucko.me>
 *  Copyright (c) contributors
 *
 *  Permission is hereby granted, free of charge, to any person obtaining a copy
 *  of this software and associated documentation files (the "Software"), to deal
 *  in the Software without restriction, including without limitation the rights
 *  to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 *  copies of the Software, and to permit persons to whom the Software is
 *  furnished to do so, subject to the following conditions:
 *
 *  The above copyright notice and this permission notice shall be included in all
 *  copies or substantial portions of the Software.
 *
 *  THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 *  IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 *  FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 *  AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 *  LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 *  OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 *  SOFTWARE.
 */

package me.lucko.luckperms.bukkit.scheduler;

import me.lucko.luckperms.bukkit.LPBukkitBootstrap;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitTask;

import java.util.concurrent.TimeUnit;

/**
 * Adapter for Bukkit/Folia server scheduler operations.
 * Provides a unified interface for scheduling tasks that works on both platforms.
 */
public interface ServerSchedulerAdapter {

    static ServerSchedulerAdapter create(LPBukkitBootstrap bootstrap) {
        if (isFolia()) {
            return new FoliaServerScheduler(bootstrap);
        } else {
            return new BukkitServerScheduler(bootstrap);
        }
    }

    static boolean isFolia() {
        try {
            Class.forName("io.papermc.paper.threadedregions.scheduler.GlobalRegionScheduler");
            return true;
        } catch (ClassNotFoundException e) {
            return false;
        }
    }

    void runTask(Plugin plugin, Runnable task);

    void runTaskLater(Plugin plugin, Runnable task, long delay);

    void runTaskAsynchronously(Plugin plugin, Runnable task);

    void runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay);

    class BukkitServerScheduler implements ServerSchedulerAdapter {
        private final LPBukkitBootstrap bootstrap;

        BukkitServerScheduler(LPBukkitBootstrap bootstrap) {
            this.bootstrap = bootstrap;
        }

        @Override
        public void runTask(Plugin plugin, Runnable task) {
            this.bootstrap.getServer().getScheduler().runTask(plugin, task);
        }

        @Override
        public void runTaskLater(Plugin plugin, Runnable task, long delay) {
            this.bootstrap.getServer().getScheduler().runTaskLater(plugin, task, delay);
        }

        @Override
        public void runTaskAsynchronously(Plugin plugin, Runnable task) {
            this.bootstrap.getServer().getScheduler().runTaskAsynchronously(plugin, task);
        }

        @Override
        public void runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) {
            this.bootstrap.getServer().getScheduler().runTaskLaterAsynchronously(plugin, task, delay);
        }
    }

    class FoliaServerScheduler implements ServerSchedulerAdapter {
        private final LPBukkitBootstrap bootstrap;

        FoliaServerScheduler(LPBukkitBootstrap bootstrap) {
            this.bootstrap = bootstrap;
        }

        @Override
        public void runTask(Plugin plugin, Runnable task) {
            this.bootstrap.getServer().getGlobalRegionScheduler().execute(plugin, task);
        }

        @Override
        public void runTaskLater(Plugin plugin, Runnable task, long delay) {
            this.bootstrap.getServer().getGlobalRegionScheduler().runDelayed(plugin, scheduledTask -> task.run(), delay);
        }

        @Override
        public void runTaskAsynchronously(Plugin plugin, Runnable task) {
            this.bootstrap.getServer().getAsyncScheduler().runNow(plugin, scheduledTask -> task.run());
        }

        @Override
        public void runTaskLaterAsynchronously(Plugin plugin, Runnable task, long delay) {
            this.bootstrap.getServer().getAsyncScheduler().runDelayed(plugin, scheduledTask -> task.run(), delay * 50, TimeUnit.MILLISECONDS);
        }
    }
}
