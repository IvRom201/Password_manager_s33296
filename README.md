# 🔐 Simple Password Manager — Java Swing Desktop App

A lightweight, **offline** password manager written in pure Java 17 + Swing, with a clean Windows-native UI.  
Everything is stored locally in a single encrypted CSV-style file — no cloud, no telemetry, no external libraries.

---

## ✨ Features Overview

### 🖥️ UI / UX

| 💡 | |
|----|--|
| **Native Windows look** with system font (`Segoe UI`), proper spacing and platform-consistent controls | 
| “Open / Create file” start screen with **master-password prompt** |
| One-click **strong-password generator** (12 random characters) |
| Fast **search bar** and **category filter** with real-time updates |
| Clean **table view** with columns: `Name`, `Password`, `Category`, `Login`, `Website`, `Location` |
| Category bulk-delete, column sorting, and double-click to edit |
| All windows inherit from a common `BaseWindow` with built-in app icon |

---

### 🔐 Data & Storage

| 🔒 | |
|----|--|
| All entries stored in memory inside a simple Java POJO list (`Database`) |
| Data saved to an encrypted `.csv` file, **locally on disk** |
| Caesar-style encryption (shift-based), keyed by master password |
| Simple escaping in `.csv` with `Entry.toCSV()` / `Entry.fromCSV()` |
| All I/O handled by `Loader.readFile(...)` and `Loader.writeFile(...)` |
| Every decryption failure is logged; no silent errors |

---

## 📦 Project Structure

