# Simple Password Manager&nbsp;— Java Swing desktop app

A lightweight, **offline** password manager written in pure Java 17/Swing.  
Everything is stored locally in a single encrypted CSV-style file — no servers, no external
libraries.

---

## ✨ Key features

| UI / UX | Data model |
|---------|------------|
| Create, edit and delete entries with **Name, Password, Category, Login, Website, Location** columns :contentReference[oaicite:8]{index=8} | All records kept in-memory inside a plain POJO list (`Database`) :contentReference[oaicite:9]{index=9} |
| One-click **strong password generator** (12 random symbols) :contentReference[oaicite:10]{index=10} | CSV-like serialization with proper escaping (`Entry.toCSV`, `fromCSV`) :contentReference[oaicite:11]{index=11} |
| Instant **search bar**, sort by Name / Category, category bulk-delete :contentReference[oaicite:12]{index=12} | On-disk file is encrypted end-to-end with a master password; decryption attempt time is logged :contentReference[oaicite:13]{index=13} |
| “Open / Create file” start window with master-password prompt :contentReference[oaicite:14]{index=14} | Caesar-style shift cipher keyed by master passw:contentReference[oaicite:15]{index=15}
