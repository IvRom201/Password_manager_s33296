# Simple Password Manager — Java Swing desktop app

A lightweight, **offline** password manager written in pure Java 17/Swing.  
Everything is stored locally in a single encrypted CSV-style file – no servers and no external libraries.

---

## ✨ Key features

| UI / UX | Data model |
|---------|------------|
| Create, edit and delete entries with **Name, Password, Category, Login, Website, Location** columns | All records kept in-memory inside a plain POJO list (`Database`) |
| One-click **strong-password generator** (12 random symbols) | CSV-style serialization with proper escaping (`Entry.toCSV`, `fromCSV`) |
| Instant **search bar**, sort by Name / Category, category bulk-delete | On-disk file is encrypted end-to-end with a master password; every decryption attempt is logged |
| “Open / Create file” start window with master-password prompt | Simple Caesar-shift cipher keyed by the master password (`Encryptor`) |
| Single **Save** button (or auto-save on close) | File I/O handled by `Loader.readFile / writeFile` |
